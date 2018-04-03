package xyz.fabiano.spring.localstack.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.fabiano.spring.localstack.LocalstackDocker;

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
