package xyz.fabiano.spring.localstack.autoconfigure;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import xyz.fabiano.spring.localstack.LocalstackDockerBuilder;
import xyz.fabiano.spring.localstack.LocalstackService;
import xyz.fabiano.spring.localstack.legacy.LocalstackDocker;
import xyz.fabiano.spring.localstack.support.AmazonAsyncDockerClientsHolder;
import xyz.fabiano.spring.localstack.support.AmazonClientsHolder;
import xyz.fabiano.spring.localstack.support.AmazonDockerClientsHolder;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@ConditionalOnProperty("spring.localstack.enabled")
public class LocalstackAutoConfiguration {

    private LocalstackDocker localstackDocker;

    private boolean asyncClientsEnabled;
    private final boolean pullNewImage;
    private final boolean randomPorts;
    private final String externalHost;
    private String services;

    public LocalstackAutoConfiguration(
        @Value("${spring.localstack.async-clients.enabled:false}") boolean asyncClientsEnabled,
        @Value("${spring.localstack.pull-new-image:false}") boolean pullNewImage,
        @Value("${spring.localstack.random-ports:true}") boolean randomPorts,
        @Value("${spring.localstack.external-host:localhost}") String externalHost,
        @Value("${spring.localstack.services:}") String services) {

        this.asyncClientsEnabled = asyncClientsEnabled;
        this.pullNewImage = pullNewImage;
        this.randomPorts = randomPorts;
        this.externalHost = externalHost;
        this.services = services;
    }

    @Bean
    public LocalstackDocker localstackDocker() {
        LocalstackDockerBuilder localstackBuilder = new LocalstackDockerBuilder();

        localstackDocker = localstackBuilder
            .pullingNewImages(pullNewImage)
            .withRandomPorts(randomPorts)
            .withExternalHost(externalHost)
            .disableCBOR()
            .withServices(services())
            .build();

        localstackDocker.startup();
        return localstackDocker;
    }

    public List<LocalstackService> services() {
        return Stream.of(services.split(","))
            .filter(s -> !StringUtils.isBlank(s))
            .map(s -> LocalstackService.valueOf(s.toUpperCase()))
            .collect(Collectors.toList());
    }

    @Bean
    public AmazonClientsHolder amazonClientsHolder() {
        if (asyncClientsEnabled) {
            return new AmazonAsyncDockerClientsHolder(localstackDocker());
        } else {
            return new AmazonDockerClientsHolder(localstackDocker());
        }
    }

    @EventListener({ContextStoppedEvent.class, ContextClosedEvent.class})
    public void cleanContext() {
        if (localstackDocker != null) {
            localstackDocker.stop();
        }
    }
}
