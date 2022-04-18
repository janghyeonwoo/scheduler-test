package com.example.scheduler.config;

import com.example.scheduler.dto.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ChunkJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job chunkJob() throws Exception {
        Job job = jobBuilderFactory.get("chunk_job4")
                .start(step(null))
                .build();
        return job;
    }

    @Bean
    @JobScope
    public Step step(@Value("#{jobParameters[requestDate]}") String requestDate) throws Exception {
        return stepBuilderFactory.get("chunk_step2")
                .<User,User>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<User> reader() throws Exception {
        return new JdbcPagingItemReaderBuilder<User>()
                .pageSize(5)
                .fetchSize(5)
                .dataSource(dataSource)
                .rowMapper(new BeanPropertyRowMapper<>(User.class))
                .queryProvider(customQueryProvider())
                .name("JdbcPagingItemReader")
                .build();

    }

    @Bean
    @StepScope
    public ItemProcessor<User,User> processor(){
        return new ItemProcessor<User, User>() {
            @Override
            public User process(User item) throws Exception {
                log.error("==========================================================");
                log.error("item :: {}", item.getIdx());
                item.setAge(item.getIdx() + 300);
                return item;
            }
        };
    }

    @Bean
    @StepScope
    public JdbcBatchItemWriter<User> writer(){
        log.error("---------------------------------------- writer-------");
        return new JdbcBatchItemWriterBuilder<User>()
                .dataSource(dataSource)
                .sql("UPDATE USER SET age = :age WHERE idx = :idx")
                .beanMapped()
                .build();
    }

    public PagingQueryProvider customQueryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean queryProviderFactoryBean = new SqlPagingQueryProviderFactoryBean();
        queryProviderFactoryBean.setDataSource(dataSource);
        queryProviderFactoryBean.setSelectClause("SELECT idx,name");
        queryProviderFactoryBean.setFromClause("FROM USER");
//        queryProviderFactoryBean.setWhereClause("");
        Map<String, Order> sortKey = new HashMap<>();
        sortKey.put("idx", Order.ASCENDING);
        queryProviderFactoryBean.setSortKeys(sortKey);
        return queryProviderFactoryBean.getObject();
    }
}
