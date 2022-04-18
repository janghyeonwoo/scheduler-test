package com.example.scheduler.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@EnableBatchProcessing
@RequiredArgsConstructor
@Configuration
public class JobConfig {
    public final JobBuilderFactory jobBuilderFactory;
    public final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job exampleJob(){
        Job exampleJob = jobBuilderFactory.get("ssJob")
                .start(exampleStep())
                .next(nextStep())
                .next(lastStep())
                .build();
        return exampleJob;
    }

    public Step exampleStep(){
        return stepBuilderFactory.get("ssStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("Step!!!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    public Step nextStep(){
        return stepBuilderFactory.get("ssStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("next Step!!!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    public Step lastStep(){
        return stepBuilderFactory.get("ssStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("last Step!!!");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }
}
