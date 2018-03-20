package xyz.fabiano.spring.localstack;

import cloud.localstack.docker.LocalstackDocker;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class LocalstackDockerBuilder {

    private Map<String, String> environmentVariables = new HashMap<>();

    private List<String> services = new ArrayList<>();

    private String externalHost = "localhost";

    private boolean randomPorts;

    private boolean pullNewImage = false;

    public LocalstackDocker build() {
        LocalstackDocker docker = LocalstackDocker.getLocalstackDocker();
        docker.setRandomizePorts(randomPorts);
        docker.setPullNewImage(pullNewImage);
        docker.setExternalHostName(externalHost);

        String servicesJoined = String.join(",", this.services);
        if(StringUtils.isNotEmpty(servicesJoined)) {
            environmentVariables.put("SERVICES", servicesJoined);
        }

        docker.setEnvironmentVariables(environmentVariables);
        return docker;
    }

    public LocalstackDockerBuilder withService(String service) {
        this.services.add(service);
        return this;
    }

    public LocalstackDockerBuilder withServices(String... services) {
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
