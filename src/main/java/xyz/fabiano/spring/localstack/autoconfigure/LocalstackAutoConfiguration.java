package xyz.fabiano.spring.localstack.autoconfigure;

import cloud.localstack.docker.LocalstackDocker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import xyz.fabiano.spring.localstack.support.AmazonClientsHolder;
import xyz.fabiano.spring.localstack.support.AmazonDockerClientsHolder;

@Configuration
@ConditionalOnProperty("spring.localstack.enabled")
public class LocalstackAutoConfiguration {

    private LocalstackDocker localstackDocker;

    @Bean
    public LocalstackDocker localstackDocker() {
        localstackDocker = LocalstackDocker.getLocalstackDocker();
        localstackDocker.startup();
        return localstackDocker;
    }

    @Bean
    public AmazonClientsHolder amazonClientsHolder(LocalstackDocker localstackDocker) {
        return new AmazonDockerClientsHolder(localstackDocker);
    }

    @EventListener({ ContextStoppedEvent.class, ContextClosedEvent.class })
    public void cleanContext() {
        if(localstackDocker != null) {
            localstackDocker.stop();
        }
    }
}
