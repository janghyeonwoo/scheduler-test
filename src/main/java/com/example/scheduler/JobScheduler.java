package com.example.scheduler;

import com.example.scheduler.config.ChunkJobConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class JobScheduler {
//    @Autowired
//    private JobLauncher jobLanuncher;
//
//    @Autowired
//    private Job exjob;
//
//    @Scheduled(cron = "1 * * * * *")
//    public void jobScheduled() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
//        Map<String , JobParameter> jobParameterMap = new HashMap<>();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = new Date();
//        String time = format.format(date);
//        jobParameterMap.put("date", new JobParameter(time));
//        JobParameters parameter = new JobParameters(jobParameterMap);
//        JobExecution jobExecution = jobLanuncher.run(exjob,parameter);
//
//        while (jobExecution.isRunning()){
//            log.info("-====---=---=--=");
//        }
//
//    }
}
