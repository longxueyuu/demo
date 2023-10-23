package com.example.domain.task;

import com.example.domain.advice.AOPTarget;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(1)
@Component
@Slf4j
public class CommandLineRunTask implements CommandLineRunner {
    @Override
    @AOPTarget
    public void run(String... args) throws Exception {
        log.info("CommandLineRunTask: done! {}", List.of(args));
    }
}
