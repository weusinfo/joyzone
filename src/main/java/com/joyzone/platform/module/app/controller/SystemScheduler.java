package com.joyzone.platform.module.app.controller;

import com.joyzone.platform.core.model.TeamModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Component
public class SystemScheduler {

    @Autowired
    private AppTeamController teamController;

    private static final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(cron = "0/50 * * * * ?")
    public void reportCurrentTime(){
        System.out.println("现在时间："+format.format(new Date()));
        List<Map<String,Object>> systemActivityList = teamController.getSystemActivity();
        List<Object> hobbyNames = systemActivityList.stream().map(i->i.get("hobbyName")).collect(Collectors.toList());
        int random = (int)(Math.random()*(4-0)+0);
        Calendar cal=java.util.Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE,1);
        Date nextDate=cal.getTime();
        TeamModel teamModel = new TeamModel();
        teamModel.setOwner(0L);
        teamModel.setActivityName((String)hobbyNames.get(random));
        teamModel.setStartTime(nextDate);
        //teamModel.setActivityAddress("光谷");
        teamModel.setPayWay(2);
        teamController.saveActivity(teamModel);
    }

}
