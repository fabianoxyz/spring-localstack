package xyz.fabiano.spring.localstack.help;

import cloud.localstack.DockerTestUtils;
import cloud.localstack.TestUtils;
import cloud.localstack.docker.LocalstackDocker;
import com.amazonaws.client.builder.AwsAsyncClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.apigateway.AmazonApiGatewayAsync;
import com.amazonaws.services.apigateway.AmazonApiGatewayAsyncClientBuilder;
import com.amazonaws.services.cloudformation.AmazonCloudFormationAsync;
import com.amazonaws.services.cloudformation.AmazonCloudFormationAsyncClientBuilder;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsync;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsAsyncClientBuilder;
import com.amazonaws.services.kinesis.AmazonKinesisAsync;
import com.amazonaws.services.kinesis.AmazonKinesisAsyncClientBuilder;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseAsync;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseAsyncClientBuilder;
import com.amazonaws.services.lambda.AWSLambdaAsync;
import com.amazonaws.services.lambda.AWSLambdaAsyncClientBuilder;
import com.amazonaws.services.redshift.AmazonRedshiftAsync;
import com.amazonaws.services.redshift.AmazonRedshiftAsyncClientBuilder;
import com.amazonaws.services.route53.AmazonRoute53Async;
import com.amazonaws.services.route53.AmazonRoute53AsyncClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsyncClientBuilder;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

import java.util.function.Supplier;

public class DockerClientsHolder extends DockerTestUtils {

    private static final String region = Regions.DEFAULT_REGION.getName();

    private static final LocalstackDocker DOCKER = LocalstackDocker.getLocalstackDocker();

    public static AmazonSQSAsync amazonSQSAsync() {
        return decorateWithConfigsAndBuild(AmazonSQSAsyncClientBuilder.standard(), DOCKER::getEndpointSQS);
    }

    public static AmazonSNSAsync amazonSNSAsync() {
        return decorateWithConfigsAndBuild(AmazonSNSAsyncClientBuilder.standard(), DOCKER::getEndpointSNS);
    }

    public static AmazonKinesisAsync amazonKinesisAsync() {
        return decorateWithConfigsAndBuild(AmazonKinesisAsyncClientBuilder.standard(), DOCKER::getEndpointKinesis);
    }

    public static AmazonDynamoDBAsync amazonDynamoDBAsync() {
        return decorateWithConfigsAndBuild(AmazonDynamoDBAsyncClientBuilder.standard(), DOCKER::getEndpointDynamoDB);
    }

    public static AmazonDynamoDBStreamsAsync amazonDynamoDBStreamsAsync() {
        return decorateWithConfigsAndBuild(AmazonDynamoDBStreamsAsyncClientBuilder.standard(), DOCKER::getEndpointDynamoDBStreams);
    }

    public static AmazonSimpleEmailServiceAsync amazonSimpleEmailServiceAsync() {
        return decorateWithConfigsAndBuild(AmazonSimpleEmailServiceAsyncClientBuilder.standard(), DOCKER::getEndpointSES);
    }

    public static AmazonApiGatewayAsync amazonApiGatewayAsync() {
        return decorateWithConfigsAndBuild(AmazonApiGatewayAsyncClientBuilder.standard(), DOCKER::getEndpointAPIGateway);
    }

    public static AmazonRedshiftAsync amazonRedshiftAsync() {
        return decorateWithConfigsAndBuild(AmazonRedshiftAsyncClientBuilder.standard(), DOCKER::getEndpointRedshift);
    }

    public static AmazonCloudWatchAsync amazonCloudWatchAsync() {
        return decorateWithConfigsAndBuild(AmazonCloudWatchAsyncClientBuilder.standard(), DOCKER::getEndpointCloudWatch);
    }

    public static AmazonCloudFormationAsync amazonCloudFormationAsync() {
        return decorateWithConfigsAndBuild(AmazonCloudFormationAsyncClientBuilder.standard(), DOCKER::getEndpointCloudFormation);
    }

    public static AmazonKinesisFirehoseAsync amazonKinesisFirehoseAsync() {
        return decorateWithConfigsAndBuild(AmazonKinesisFirehoseAsyncClientBuilder.standard(), DOCKER::getEndpointFirehose);
    }

    public static AmazonRoute53Async amazonRoute53Async() {
        return decorateWithConfigsAndBuild(AmazonRoute53AsyncClientBuilder.standard(), DOCKER::getEndpointRoute53);
    }

    public static AWSLambdaAsync awsLambdaAsync() {
        return decorateWithConfigsAndBuild(AWSLambdaAsyncClientBuilder.standard(), DOCKER::getEndpointLambda);
    }

    public static  <S, T extends AwsAsyncClientBuilder<T, S>> S decorateWithConfigsAndBuild(T builder, Supplier<String> endpointSupplier) {
        return builder
            .withCredentials(TestUtils.getCredentialsProvider())
            .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpointSupplier.get(), region))
            .build();
    }
}