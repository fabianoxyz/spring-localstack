package xyz.fabiano.spring.localstack.autoconfigure;

import cloud.localstack.docker.LocalstackDocker;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import xyz.fabiano.spring.localstack.support.AmazonClientsHolder;
import xyz.fabiano.spring.localstack.support.AmazonDockerClientsHolder;

@Configuration
@ConditionalOnExpression("${spring.localstack.enabled:false} and !${spring.localstack.async-clients.enabled:false}")
public class SyncClientsAutoConfiguration {
    @Bean
    @Primary
    public AmazonClientsHolder amazonClientsHolder(LocalstackDocker localstackDocker) {
        return new AmazonDockerClientsHolder(localstackDocker);
    }
}
