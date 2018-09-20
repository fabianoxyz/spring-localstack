package xyz.fabiano.spring.localstack.support;

import cloud.localstack.TestUtils;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import xyz.fabiano.spring.localstack.legacy.LocalstackDocker;

import java.util.function.Function;

public abstract class AbstractAmazonDockerClientsHolder implements AmazonClientsHolder {

    private LocalstackDocker localstackDocker;

    AbstractAmazonDockerClientsHolder(LocalstackDocker localstackDocker) {
        this.localstackDocker = localstackDocker;
    }

    @Override
    public AmazonS3 amazonS3() {
        return decorateWithConfigsAndBuild(
            AmazonS3ClientBuilder.standard().enablePathStyleAccess(),
            LocalstackDocker::getEndpointS3
        );
    }

    <S, T extends AwsClientBuilder<T, S>> S decorateWithConfigsAndBuild(
        T builder,
        Function<LocalstackDocker, String> endpointFunction
    ) {
        return builder
            .withCredentials(TestUtils.getCredentialsProvider())
            .withEndpointConfiguration(new EndpointConfiguration(
                endpointFunction.apply(localstackDocker),
                localstackDocker.getRegion()
            ))
            .build();
    }
}
