package xyz.fabiano.spring.localstack.support;

import cloud.localstack.TestUtils;
import cloud.localstack.docker.LocalstackDocker;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import java.util.function.Supplier;

public abstract class AbstractAmazonDockerClientsHolder implements AmazonClientsHolder {

    LocalstackDocker localstackDocker;

    AbstractAmazonDockerClientsHolder(LocalstackDocker localstackDocker) {
        this.localstackDocker = localstackDocker;
    }

    @Override
    public AmazonS3 amazonS3() {
        return decorateWithConfigsAndBuild(
            AmazonS3ClientBuilder
                .standard()
                .enablePathStyleAccess(),
            localstackDocker::getEndpointS3);
    }

    static <S, T extends AwsClientBuilder<T, S>> S decorateWithConfigsAndBuild(T builder, Supplier<String> endpointSupplier) {
        return builder
            .withCredentials(TestUtils.getCredentialsProvider())
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpointSupplier.get(), region))
            .build();
    }
}
