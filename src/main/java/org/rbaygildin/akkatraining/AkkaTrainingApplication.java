package org.rbaygildin.akkatraining;

import akka.actor.ActorSystem;
import org.rbaygildin.akkatraining.actor.JobSchedulerActor;
import org.rbaygildin.akkatraining.config.SpringExtensionProvider;
import org.rbaygildin.akkatraining.utils.ActorUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.rbaygildin.akkatraining.utils.ActorUtils.actorOf;

@SpringBootApplication
public class AkkaTrainingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AkkaTrainingApplication.class, args);
    }

}
