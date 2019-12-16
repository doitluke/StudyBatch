package im.Luke.batch;

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
@RequiredArgsConstructor
@Configuration // 스프링 배치의 모든 job은 @Configuration으로 등록해서 사용
public class StudyBatchConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job simpleJob() {
        // jobBuilderFactory.get("simpleJob") -> simplejob이라는 이름의 Batch Job을 생성 후 builder를 통해 이름을
        // 지정한다.
        return jobBuilderFactory.get("simpleJob").start(simpleStep1()).build();
    }

    private Step simpleStep1() {

        // stepBuilderFactory.get("simpleStep1") -> simpleStep1이란 이름의 배치 step생성 후 builder를 통해 이름을
        // 지정한다.
        // .tasklet step안에서 단일로 수행될 커스텀한 기능들을 선언할때 사용한다. 여기서는 Batch가 수행되면 log.info 부분이 동작한다.
        return stepBuilderFactory.get("simpleStep1").tasklet((contribute, chunkContext) -> {
            log.info(">>>>> This is step1");
            return RepeatStatus.FINISHED;
        }).build();

    }

}
