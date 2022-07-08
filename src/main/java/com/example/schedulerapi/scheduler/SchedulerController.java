package com.example.schedulerapi.scheduler;

import com.example.schedulerapi.models.SchedulerModel;
import com.example.schedulerapi.models.WebhookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class SchedulerController {

    private final SchedulerService schedulerService;

    @Autowired
    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @GetMapping("hello")
    public @ResponseBody
    ResponseEntity<?> test(){
        return new ResponseEntity<>("Works", HttpStatus.OK);
    }

    @PostMapping("register")
    public @ResponseBody
    ResponseEntity<String> register(@RequestBody WebhookModel webhookModel) {
        try {
            schedulerService.scheduling(webhookModel.getURL(), webhookModel.getInterval());
            return new ResponseEntity<>("URL registered successfully",HttpStatus.OK);
        } catch (Exception exception){
            exception.printStackTrace();
            return new ResponseEntity<>("URL already registered", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("deregister")
    public @ResponseBody
    ResponseEntity<String> deregister(@RequestBody WebhookModel webhookModel){
        try {
            schedulerService.deleteSchedule(webhookModel.getURL());
            return new ResponseEntity<>("Webhook de-registered successfully", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("URL doesn't exists",HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("update")
    public @ResponseBody
    ResponseEntity<String> update(@RequestBody WebhookModel webhookModel){
        try {
            schedulerService.modifySchedule(webhookModel.getURL(), webhookModel.getInterval());
            return new ResponseEntity<>("Webhook updated successfully", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("URL doesn't exists", HttpStatus.BAD_REQUEST);
        }
    }

    //Webhook Test
    @PostMapping("test")
    public @ResponseBody
    ResponseEntity<HttpStatus> test(@RequestBody SchedulerModel schedulerModel) {
        System.out.println(schedulerModel.getCurrentTime());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Webhook Test
    @PostMapping("test1")
    public @ResponseBody
    ResponseEntity<HttpStatus> test1(@RequestBody SchedulerModel schedulerModel) {
        System.out.println(schedulerModel.getCurrentTime());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Webhook Test
    @PostMapping("test2")
    public @ResponseBody
    ResponseEntity<HttpStatus> test2(@RequestBody SchedulerModel schedulerModel) {
        System.out.println(schedulerModel.getCurrentTime());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
