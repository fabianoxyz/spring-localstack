package xyz.fabiano.spring.localstack.autoconfigure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
@ConditionalOnProperty("spring.localstack.services")
public class SpecificAwsClientsAutoConfiguration {

    private ArrayList<String> services;

    public SpecificAwsClientsAutoConfiguration(@Value("spring.localstack.services") ArrayList<String> services) {
        this.services = services;
    }
}
