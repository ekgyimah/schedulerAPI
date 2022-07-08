package com.example.schedulerapi;

import com.example.schedulerapi.models.Scheduled;
import com.example.schedulerapi.scheduler.SchedulerService;
import com.example.schedulerapi.wrapper.HTTPwrapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.test.web.servlet.MockMvc;

import java.util.concurrent.ScheduledFuture;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class SchedulerapiApplicationTests {

    //TODO: Separate unit tests for Controllers

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private TaskScheduler executor;
    @Mock
    private HTTPwrapper httpWrapper;

    @InjectMocks
    SchedulerService schedulerService;

    @Test
    void test_schedule_method() throws Exception{
        schedulerService.scheduling("http://localhost:8080/hello/world",10000);

        verify(httpWrapper, times(0)).sendRequest(ArgumentMatchers.any(), anyString());
        assertThat(schedulerService.getE().size()).isEqualTo(1);
        assertThatException().isThrownBy(()-> {
            schedulerService.scheduling("http://localhost:8080/hello/world",10000);
        });
    }

    @Test
    void test_delete_schedule_method() throws Exception{
        assertThatException().isThrownBy(()-> {
            schedulerService.deleteSchedule("http://localhost:8080/hello/world");
        });

        ScheduledFuture d = new Scheduled();
        when(executor.scheduleAtFixedRate(ArgumentMatchers.isA(Runnable.class), eq(10000L))).thenReturn(d);
        schedulerService.scheduling("http://localhost:8080/hello/world",10000);

        verify(executor).scheduleAtFixedRate(ArgumentMatchers.isA(Runnable.class), eq(10000L));
        schedulerService.deleteSchedule("http://localhost:8080/hello/world");

        assertThat(schedulerService.getE().size()).isEqualTo(0);
    }

    @Test
    void test_update_schedule_method() throws Exception{
        assertThatException().isThrownBy(()-> {
            schedulerService.modifySchedule("http://localhost:8080/hello/world2",10000);
        });

        ScheduledFuture d = new Scheduled();
        when(executor.scheduleAtFixedRate(ArgumentMatchers.isA(Runnable.class), eq(10000L))).thenReturn(d);
        schedulerService.scheduling("http://localhost:8080/hello/world",10000);

        schedulerService.modifySchedule("http://localhost:8080/hello/world",20000);

        assertThat(schedulerService.getE().size()).isEqualTo(1);
    }

}
