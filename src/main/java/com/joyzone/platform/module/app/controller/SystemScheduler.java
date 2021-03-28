package com.joyzone.platform.module.app.controller;

import com.joyzone.platform.core.mapper.TeamUsersMapper;
import com.joyzone.platform.core.model.TeamModel;
import com.joyzone.platform.core.model.TeamUsersModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class SystemScheduler {

    @Autowired
    private AppTeamController teamController;
    @Resource
    private TeamUsersMapper teamUsersMapper;

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");

    @Scheduled(cron = "0 0 12 * * ?")
    public void reportCurrentTime(){
        System.out.println("现在时间："+format.format(new Date()));
        List<Map<String,Object>> systemActivityList = teamController.getSystemActivity();
        List<Object> hobbyNames = systemActivityList.stream().map(i->i.get("hobbyName")).collect(Collectors.toList());
        int random = (int)(Math.random()*(4-0)+0);
        int randomUserNum = (int)(Math.random()*(15-5)+5);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date newUnpackingTime = calendar.getTime();
        String startTime = format.format(newUnpackingTime);
        TeamModel teamModel = new TeamModel();
        teamModel.setOwner(0L);
        teamModel.setActivityName((String)hobbyNames.get(random));
        teamModel.setStartTime(newUnpackingTime);
        teamModel.setActivityAddress("自定义");
        teamModel.setPayWay(2);
        teamModel.setNumber(randomUserNum);
        teamController.saveActivity(teamModel);

    }

}
