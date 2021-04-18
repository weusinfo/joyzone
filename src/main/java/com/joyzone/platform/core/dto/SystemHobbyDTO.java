package com.joyzone.platform.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ApiModel("系统爱好清单信息DTO")
@Configuration
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class SystemHobbyDTO extends BaseModel {
    private String hobbyType;
    private Long userId;
    private int num;
    private List<HobbyDTO> hobbyNameList;

    public String getHobbyType() {
        return hobbyType;
    }

    public void setHobbyType(String hobbyType) {
        this.hobbyType = hobbyType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<HobbyDTO> getHobbyNameList() {
        return hobbyNameList;
    }

    public void setHobbyNameList(List<HobbyDTO> hobbyNameList) {
        this.hobbyNameList = hobbyNameList;
    }
}
