package xyz.fabiano.spring.localstack.junit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.fabiano.spring.localstack.support.AmazonDockerClientsHolder;

@Configuration
public class SpringTestContext {
    @Bean
    public AmazonDockerClientsHolder amazonDockerClientsHolder() {
        return new AmazonDockerClientsHolder();
    }
}
