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
public class StepContributionConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepContributionJob() {
        return jobBuilderFactory.get("stepContributionJob")
                .start(stepContributionJobStep1())
                .next(sstepContributionJobStep2())
                .build();
    }
    @Bean
    public Step stepContributionJobStep1() {
        return stepBuilderFactory.get("stepContributionJobStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("stepContributionJobStep1 has executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }


    @Bean
    public Step sstepContributionJobStep2() {
        return stepBuilderFactory.get("stepContributionJobStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("stepContributionJobStep2 has executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
