package xyz.fabiano.spring.localstack.support;

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

public final class AmazonAsyncDockerClientsHolder extends AbstractAmazonDockerClientsHolder {

    @Override
    public AmazonSQSAsync amazonSQS() {
        return decorateWithConfigsAndBuild(AmazonSQSAsyncClientBuilder.standard(), DOCKER::getEndpointSQS);
    }

    @Override
    public AmazonSNSAsync amazonSNS() {
        return decorateWithConfigsAndBuild(AmazonSNSAsyncClientBuilder.standard(), DOCKER::getEndpointSNS);
    }

    @Override
    public AmazonKinesisAsync amazonKinesis() {
        return decorateWithConfigsAndBuild(AmazonKinesisAsyncClientBuilder.standard(), DOCKER::getEndpointKinesis);
    }

    @Override
    public AmazonDynamoDBAsync amazonDynamoDB() {
        return decorateWithConfigsAndBuild(AmazonDynamoDBAsyncClientBuilder.standard(), DOCKER::getEndpointDynamoDB);
    }

    @Override
    public AmazonDynamoDBStreamsAsync amazonDynamoDBStreams() {
        return decorateWithConfigsAndBuild(AmazonDynamoDBStreamsAsyncClientBuilder.standard(), DOCKER::getEndpointDynamoDBStreams);
    }

    @Override
    public AmazonSimpleEmailServiceAsync amazonSimpleEmailService() {
        return decorateWithConfigsAndBuild(AmazonSimpleEmailServiceAsyncClientBuilder.standard(), DOCKER::getEndpointSES);
    }

    @Override
    public AmazonApiGatewayAsync amazonApiGateway() {
        return decorateWithConfigsAndBuild(AmazonApiGatewayAsyncClientBuilder.standard(), DOCKER::getEndpointAPIGateway);
    }

    @Override
    public AmazonRedshiftAsync amazonRedshift() {
        return decorateWithConfigsAndBuild(AmazonRedshiftAsyncClientBuilder.standard(), DOCKER::getEndpointRedshift);
    }

    @Override
    public AmazonCloudWatchAsync amazonCloudWatch() {
        return decorateWithConfigsAndBuild(AmazonCloudWatchAsyncClientBuilder.standard(), DOCKER::getEndpointCloudWatch);
    }

    @Override
    public AmazonCloudFormationAsync amazonCloudFormation() {
        return decorateWithConfigsAndBuild(AmazonCloudFormationAsyncClientBuilder.standard(), DOCKER::getEndpointCloudFormation);
    }

    @Override
    public AmazonKinesisFirehoseAsync amazonKinesisFirehose() {
        return decorateWithConfigsAndBuild(AmazonKinesisFirehoseAsyncClientBuilder.standard(), DOCKER::getEndpointFirehose);
    }

    @Override
    public AmazonRoute53Async amazonRoute53() {
        return decorateWithConfigsAndBuild(AmazonRoute53AsyncClientBuilder.standard(), DOCKER::getEndpointRoute53);
    }

    @Override
    public AWSLambdaAsync awsLambda() {
        return decorateWithConfigsAndBuild(AWSLambdaAsyncClientBuilder.standard(), DOCKER::getEndpointLambda);
    }
}