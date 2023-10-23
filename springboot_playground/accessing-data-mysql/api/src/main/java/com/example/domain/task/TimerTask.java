package com.example.domain.task;

import com.example.autoconfigure.ExampleBizService;
import com.example.domain.advice.AOPTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TimerTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExampleBizService exampleBizService;

    @Scheduled(cron = "*/5 * * * * *")
    @AOPTarget
    public void runPerSecond() {
        logger.info("TimerTask: done! service: {}", exampleBizService.getBizDesc());
    }
}
