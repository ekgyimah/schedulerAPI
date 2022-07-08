package com.example.schedulerapi.models;

import lombok.Data;

@Data
public class WebhookModel {
    private String URL;
    private Integer interval;

    //Default constructor
    public WebhookModel() {
    }

    public WebhookModel(String URL, Integer interval) {
        this.URL = URL;
        this.interval = interval;
    }
}
