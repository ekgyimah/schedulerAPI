package com.example.schedulerapi.models;

import lombok.Data;

@Data
public class SchedulerModel {

    private String currentTime;

    //Default constructor
    public SchedulerModel() {
    }

    public SchedulerModel(String currentTime) {
        this.currentTime = currentTime;
    }
}
