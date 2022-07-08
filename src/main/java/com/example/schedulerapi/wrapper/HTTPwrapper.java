package com.example.schedulerapi.wrapper;

import com.example.schedulerapi.models.SchedulerModel;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class HTTPwrapper {

    private final RestTemplate restTemplate;

    @Autowired
    public HTTPwrapper(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }

    public void sendRequest(SchedulerModel object, String URL){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // build the request
        HttpEntity<String> entity = new HttpEntity<>(new Gson().toJson(object), headers);
        ResponseEntity<String> response = this.restTemplate.postForEntity(URL, entity, String.class);
    }
}
