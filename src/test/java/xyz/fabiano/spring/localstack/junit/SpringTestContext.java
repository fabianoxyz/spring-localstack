package xyz.fabiano.spring.localstack.junit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringTestContext {

    @Bean
    public boolean up() {
        return true;
    }
}
