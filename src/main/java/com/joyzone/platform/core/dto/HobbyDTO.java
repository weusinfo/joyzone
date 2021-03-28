package com.joyzone.platform.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joyzone.platform.core.model.BaseModel;
import io.swagger.annotations.ApiModel;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@ApiModel("用户爱好清单信息DTO")
@Configuration
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class HobbyDTO extends BaseModel {
    private Long hobbyId;
    private String hobbyName;
    private int selected;

    public Long getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(Long hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }
}
