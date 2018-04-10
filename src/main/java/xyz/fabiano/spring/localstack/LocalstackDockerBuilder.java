package xyz.fabiano.spring.localstack;

import cloud.localstack.TestUtils;
import cloud.localstack.docker.LocalstackDocker;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class LocalstackDockerBuilder {

    private Map<String, String> environmentVariables = new HashMap<>();

    private List<LocalstackService> services = new ArrayList<>();

    private String externalHost = "localhost";

    private boolean randomPorts = false;

    private boolean pullNewImage = false;

    private boolean cborEnable = false;

    public LocalstackDocker build() {
        if(!cborEnable) {
            TestUtils.setEnv("AWS_CBOR_DISABLE", "1");
        }

        LocalstackDocker docker = LocalstackDocker.getLocalstackDocker();
        docker.setRandomizePorts(randomPorts);
        docker.setPullNewImage(pullNewImage);
        docker.setExternalHostName(externalHost);


        String servicesJoined = this.services.stream().map(LocalstackService::toString).collect(joining(","));

        if(StringUtils.isNotEmpty(servicesJoined)) {
            environmentVariables.put("SERVICES", servicesJoined);
        }

        docker.setEnvironmentVariables(environmentVariables);
        return docker;
    }

    public LocalstackDockerBuilder enableCBOR() {
        this.cborEnable = true;
        return this;
    }

    public LocalstackDockerBuilder disableCBOR() {
        this.cborEnable = false;
        return this;
    }

    public LocalstackDockerBuilder withService(LocalstackService service) {
        this.services.add(service);
        return this;
    }

    public LocalstackDockerBuilder withServices(LocalstackService... services) {
        this.services.addAll(Arrays.asList(services));
        return this;
    }

    public LocalstackDockerBuilder withStaticPorts() {
        this.randomPorts = false;
        return this;
    }

    public LocalstackDockerBuilder withRandomPorts() {
        this.randomPorts = true;
        return this;
    }

    public LocalstackDockerBuilder withRandomPorts(boolean random) {
        this.randomPorts = random;
        return this;
    }

    public LocalstackDockerBuilder notPullingNewImages() {
        this.pullNewImage = false;
        return this;
    }

    public LocalstackDockerBuilder alwaysPullingNewImages() {
        this.pullNewImage = true;
        return this;
    }

    public LocalstackDockerBuilder pullingNewImages(boolean pullNewImage) {
        this.pullNewImage = pullNewImage;
        return this;
    }

    public LocalstackDockerBuilder withExternalHost(String externalHost) {
        this.externalHost = externalHost;
        return this;
    }

    public LocalstackDockerBuilder withEnvironmentVariables(Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
        return this;
    }
}
