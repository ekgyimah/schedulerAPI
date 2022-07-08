package com.example.schedulerapi.models;

import lombok.Data;

import java.util.concurrent.*;

@Data
public class Scheduled implements ScheduledFuture<Scheduled> {

    public Scheduled() {
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public Scheduled get() throws InterruptedException, ExecutionException {
        return null;
    }

    @Override
    public Scheduled get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
