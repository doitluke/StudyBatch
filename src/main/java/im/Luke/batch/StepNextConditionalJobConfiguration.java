package im.Luke.batch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StepNextConditionalJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job stepNextConditionalJob() {

        return jobBuilderFactory.get("stepNextConditionalJob").start(conditionalJobStep1())
                .on("FAILED").to(conditionalJobStep3()).on("*").end().from(conditionalJobStep1())
                .on("*").to(conditionalJobStep2()).next(conditionalJobStep3()).on("*").end().end()
                .build();
    }

    private Step conditionalJobStep1() {

        return stepBuilderFactory.get("step1").tasklet((contribution, chunkContext) -> {
            log.info(">>> this is stepNextConditionalJob Step1");
            contribution.setExitStatus(ExitStatus.FAILED);
            return RepeatStatus.FINISHED;
        }).build();

    }

    private Step conditionalJobStep2() {

        return stepBuilderFactory.get("conditionStep2").tasklet((contribution, chunkContext) -> {
            log.info(">>> this is stepNextConditionalJob step2");
            return RepeatStatus.FINISHED;
        }).build();
    }

    private Step conditionalJobStep3() {

        return stepBuilderFactory.get("conditionStep3").tasklet((contribution, chunkContext) -> {
            log.info(">>> this is stepNextConditionalJob step3");
            return RepeatStatus.FINISHED;
        }).build();
    }

}
