package xyz.fabiano.spring.localstack.junit;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.fabiano.spring.localstack.LocalstackDockerBuilder;
import xyz.fabiano.spring.localstack.annotation.SpringLocalstackProperties;
import xyz.fabiano.spring.localstack.legacy.LocalstackDocker;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SpringLocalstackDockerRunner extends SpringJUnit4ClassRunner {

    private Optional<SpringLocalstackProperties> properties;

    public SpringLocalstackDockerRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
        this.properties = findAnnotation(clazz.getAnnotations());
    }

    @Override
    public void run(RunNotifier notifier) {
        LocalstackDockerBuilder builder = new LocalstackDockerBuilder();

        this.properties.ifPresent(p -> {
            builder.withExternalHost(p.externalHost());
            builder.withServices(p.services());
            builder.withRandomPorts(p.randomPorts());
            builder.pullingNewImages(p.pullNewImage());
            builder.withOptions(options(p));
            builder.withRegion(p.region());
            builder.withVersion(p.version());
        });

        LocalstackDocker docker = builder.build();

        try {
            docker.startup();
            super.run(notifier);
        } finally {
            try {
                docker.stop();
            } catch (Throwable t) {
                //TODO FIXME
                t.printStackTrace();
            }
        }
    }

    private Optional<SpringLocalstackProperties> findAnnotation(Annotation[] annotations) {
        return Stream.of(annotations)
            .filter(a -> a instanceof SpringLocalstackProperties)
            .findFirst()
            .map(a -> (SpringLocalstackProperties) a);
    }

    private List<String> options(SpringLocalstackProperties springLocalstackProperties) {
        List<String> options = new ArrayList<>();
        if (springLocalstackProperties.autoRemove()) {
            options.add("--rm");
        }

        if (springLocalstackProperties.extraOptions() != null && springLocalstackProperties.extraOptions().length > 0) {
            options.addAll(Arrays.asList(springLocalstackProperties.extraOptions()));
        }

        return options;
    }
}
