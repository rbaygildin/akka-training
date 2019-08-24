package org.rbaygildin.akkatraining.config;

import akka.actor.ActorSystem;
import com.typesafe.config.ConfigFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PreDestroy;

@Configuration
public class ActorSystemConfiguration {

    private final ApplicationContext context;
    private final ActorSystem actorSystem;

    public ActorSystemConfiguration(ApplicationContext context, @Lazy ActorSystem actorSystem) {
        this.context = context;
        this.actorSystem = actorSystem;
    }

    @Bean
    public ActorSystem actorSystem(){
        ActorSystem actorSystem = ActorSystem.create("html-parsing", ConfigFactory.load());
        SpringExtensionProvider.PROVIDER.get(actorSystem).initialize(context);
        return actorSystem;
    }

    @PreDestroy
    private void shutdown(){
        actorSystem.terminate();
    }
}
