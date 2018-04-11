package xyz.fabiano.spring.localstack.autoconfigure;


import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import xyz.fabiano.spring.localstack.legacy.LocalstackDocker;
import xyz.fabiano.spring.localstack.support.AmazonAsyncDockerClientsHolder;
import xyz.fabiano.spring.localstack.support.AmazonClientsHolder;

@Configuration
@ConditionalOnExpression("${spring.localstack.enabled:false} and ${spring.localstack.async-clients.enabled:false}")
public class AsyncClientsAutoConfiguration {

    @Bean
    @Primary
    public AmazonClientsHolder amazonAsyncClientsHolder(LocalstackDocker localstackDocker) {
        return new AmazonAsyncDockerClientsHolder(localstackDocker);
    }
}
