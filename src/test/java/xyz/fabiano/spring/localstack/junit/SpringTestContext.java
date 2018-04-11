package xyz.fabiano.spring.localstack.junit;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.fabiano.spring.localstack.legacy.LocalstackDocker;
import xyz.fabiano.spring.localstack.support.AmazonDockerClientsHolder;

@Configuration
public class SpringTestContext {

    @Bean
    public LocalstackDocker localstackDocker() {
        return LocalstackDocker.getLocalstackDocker();
    }

    @Bean
    public AmazonDockerClientsHolder amazonDockerClientsHolder(LocalstackDocker localstackDocker) {
        return new AmazonDockerClientsHolder(localstackDocker);
    }
}
