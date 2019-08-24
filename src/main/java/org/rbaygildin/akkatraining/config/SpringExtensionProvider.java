package org.rbaygildin.akkatraining.config;

import akka.actor.*;
import org.springframework.context.ApplicationContext;

public class SpringExtensionProvider extends AbstractExtensionId<SpringExtensionProvider.SpringExtension> {

    public static final SpringExtensionProvider PROVIDER = new SpringExtensionProvider();

    @Override
    public SpringExtension createExtension(ExtendedActorSystem system) {
        return new SpringExtension();
    }

    public static class SpringExtension implements Extension {
        private volatile ApplicationContext applicationContext;

        public void initialize(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public Props props(Class<? extends Actor> actorType) {
            return Props.create(SpringActorProducer.class, applicationContext, actorType);
        }
    }
}
