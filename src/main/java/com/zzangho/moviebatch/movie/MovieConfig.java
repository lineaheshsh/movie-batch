package com.zzangho.moviebatch.movie;

import com.zzangho.moviebatch.movie.model.MovieResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@Slf4j
public class MovieConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final @Qualifier("springBatchDB") DataSource dataSource;
    private final TaskExecutor taskExecutor;

    private int chunkSize = 100;

    public MovieConfig(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, EntityManagerFactory entityManagerFactory, RestTemplate restTemplate, DataSource dataSource, TaskExecutor taskExecutor) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
        this.dataSource = dataSource;
        this.taskExecutor = taskExecutor;
    }

    @Bean
    public Job movieJob() throws Exception {
        return jobBuilderFactory.get("movieJob")
                .incrementer(new RunIdIncrementer())
                .start(movieStep())
                .build();
    }

    @Bean
    public Step movieStep() throws Exception {
        return stepBuilderFactory.get("movieStep")
                .<MovieResponse, MovieResponse>chunk(chunkSize)
                .reader(new MongoItemReader<>());
    }


}
