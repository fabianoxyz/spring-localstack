package xyz.fabiano.spring.localstack.autoconfigure;

import cloud.localstack.docker.LocalstackDocker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty("spring.localstack.autoconfiguration")
public class LocalstackAutoConfiguration {

    @Bean
    public LocalstackDocker localstackDocker() {
        LocalstackDocker localstackDocker = LocalstackDocker.getLocalstackDocker();
        localstackDocker.startup();
        return localstackDocker;
    }
}
