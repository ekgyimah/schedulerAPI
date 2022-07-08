package com.example.schedulerapi.scheduler;

import com.example.schedulerapi.models.SchedulerModel;
import com.example.schedulerapi.wrapper.HTTPwrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerService {

    private final TaskScheduler executor;
    private HashMap<String, ScheduledFuture> scheduled = new HashMap<>();
    private final HTTPwrapper httpWrapper;

    @Autowired
    public SchedulerService(TaskScheduler executor,  HTTPwrapper httpWrapper) {
        this.executor = executor;
        this.httpWrapper = httpWrapper;
    }

    //Schedule event
    public void scheduling(String URL, int interval) throws Exception{
        if(!scheduled.containsKey(URL)){
            Runnable task = () -> {
                sendTime(URL);
            };
            scheduled.put(URL, executor.scheduleAtFixedRate(task, interval));
        } else {
            throw new Exception();
        }
    }

    //Delete scheduled event
    public void deleteSchedule(String URL) throws Exception{
        if(!scheduled.containsKey(URL)){
            throw new Exception();
        } else {
            scheduled.get(URL).cancel(true);
            scheduled.remove(URL);
        }
    }

    //Modify scheduled event
    public void modifySchedule(String URL, int newInterval) throws Exception{
        deleteSchedule(URL);
        scheduling(URL, newInterval);
    }

    //Send callback request to registered Webhook URL
    public void sendTime(String URL){
        SchedulerModel schedulerModel = new SchedulerModel(LocalDateTime.now().toString());
        httpWrapper.sendRequest(schedulerModel, URL);
    }


    public HashMap<String, ScheduledFuture> getE() {
        return scheduled;
    }
}
