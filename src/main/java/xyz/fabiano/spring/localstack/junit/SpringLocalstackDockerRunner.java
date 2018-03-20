package xyz.fabiano.spring.localstack.junit;

import cloud.localstack.docker.LocalstackDocker;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import xyz.fabiano.spring.localstack.LocalstackDockerBuilder;
import xyz.fabiano.spring.localstack.annotation.SpringLocalstackProperties;

import java.lang.annotation.Annotation;
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
        });

        LocalstackDocker docker = builder.build();

        try {
//            docker.startup();
            super.run(notifier);
        }
        finally {
            docker.stop();
        }
    }

    private Optional<SpringLocalstackProperties> findAnnotation(Annotation[] annotations) {
        return Stream.of(annotations)
            .filter(a -> a instanceof SpringLocalstackProperties)
            .findFirst()
            .map(a -> (SpringLocalstackProperties) a);
    }
}
