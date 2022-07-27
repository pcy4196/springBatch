package io.springbatch.springbatchlecture;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job")
                .start(step1())
                .next(step2())
                .build();
    }
    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        // 2022.07.25 JobParameter
//                        // contribution 을 이용하여 parameter 호출
//                        JobParameters jobParameters1 = contribution.getStepExecution().getJobExecution().getJobParameters();
//                        System.out.println("name  : " + jobParameters1.getString("name"));
//                        System.out.println("seq : " + jobParameters1.getLong("seq"));
//                        System.out.println("date : " + jobParameters1.getDate("date"));
//                        System.out.println("age : " + jobParameters1.getDouble("age"));
//
//                        // chunkContext 을 이용하여 parameter 호출 - map 형태라 많이 사용 X
//                        Map<String, Object> jobParameters2 = chunkContext.getStepContext().getJobParameters();

                        // 2022.07.27 JobInstance
                        System.out.println("step1 was executed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }


    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("step2 was executed");
//                        throw new RuntimeException();
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
