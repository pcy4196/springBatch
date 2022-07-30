package io.springbatch.springbatchlecture;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class StepExecutionConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepExecutionJob() {
        return jobBuilderFactory.get("stepExecutionJob")
                .start(stepExecutionJobStep1())
                .next(stepExecutionJobStep2())
                .build();
    }
    @Bean
    public Step stepExecutionJobStep1() {
        return stepBuilderFactory.get("stepExecutionJobStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("stepExecutionJobStep1 has executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }


    @Bean
    public Step stepExecutionJobStep2() {
        return stepBuilderFactory.get("stepExecutionJobStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("stepExecutionJobStep2 has executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
