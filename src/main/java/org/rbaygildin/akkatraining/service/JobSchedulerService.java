package org.rbaygildin.akkatraining.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobSchedulerService {

    public void log(String s){
        log.info("Got message {}", s);
    }
}
