package com.example.domain.task;

import com.example.domain.advice.AOPTarget;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(0)
@Component
@Slf4j
public class ApplicationRunTask implements ApplicationRunner {

    @Override
    @AOPTarget
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationRunTask: done! {}", args);
    }
}
