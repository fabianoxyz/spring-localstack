package xyz.fabiano.spring.localstack.support;

import cloud.localstack.docker.LocalstackDocker;
import com.amazonaws.services.apigateway.AmazonApiGateway;
import com.amazonaws.services.apigateway.AmazonApiGatewayClientBuilder;
import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClientBuilder;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreams;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsClientBuilder;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClientBuilder;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.redshift.AmazonRedshift;
import com.amazonaws.services.redshift.AmazonRedshiftClientBuilder;
import com.amazonaws.services.route53.AmazonRoute53;
import com.amazonaws.services.route53.AmazonRoute53ClientBuilder;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

public final class AmazonDockerClientsHolder extends AbstractAmazonDockerClientsHolder {

    public AmazonDockerClientsHolder(LocalstackDocker localstackDocker) {
        super(localstackDocker);
    }

    @Override
    public AmazonSQS amazonSQS() {
        return decorateWithConfigsAndBuild(AmazonSQSClientBuilder.standard(), localstackDocker::getEndpointSQS);
    }

    @Override
    public AmazonSNS amazonSNS() {
        return decorateWithConfigsAndBuild(AmazonSNSClientBuilder.standard(), localstackDocker::getEndpointSNS);
    }

    @Override
    public AmazonKinesis amazonKinesis() {
        return decorateWithConfigsAndBuild(AmazonKinesisClientBuilder.standard(), localstackDocker::getEndpointKinesis);
    }

    @Override
    public AmazonDynamoDB amazonDynamoDB() {
        return decorateWithConfigsAndBuild(AmazonDynamoDBClientBuilder.standard(), localstackDocker::getEndpointDynamoDB);
    }

    @Override
    public AmazonDynamoDBStreams amazonDynamoDBStreams() {
        return decorateWithConfigsAndBuild(AmazonDynamoDBStreamsClientBuilder.standard(), localstackDocker::getEndpointDynamoDBStreams);
    }

    @Override
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        return decorateWithConfigsAndBuild(AmazonSimpleEmailServiceClientBuilder.standard(), localstackDocker::getEndpointSES);
    }

    @Override
    public AmazonApiGateway amazonApiGateway() {
        return decorateWithConfigsAndBuild(AmazonApiGatewayClientBuilder.standard(), localstackDocker::getEndpointAPIGateway);
    }

    @Override
    public AmazonRedshift amazonRedshift() {
        return decorateWithConfigsAndBuild(AmazonRedshiftClientBuilder.standard(), localstackDocker::getEndpointRedshift);
    }

    @Override
    public AmazonCloudWatch amazonCloudWatch() {
        return decorateWithConfigsAndBuild(AmazonCloudWatchClientBuilder.standard(), localstackDocker::getEndpointCloudWatch);
    }

    @Override
    public AmazonCloudFormation amazonCloudFormation() {
        return decorateWithConfigsAndBuild(AmazonCloudFormationClientBuilder.standard(), localstackDocker::getEndpointCloudFormation);
    }

    @Override
    public AmazonKinesisFirehose amazonKinesisFirehose() {
        return decorateWithConfigsAndBuild(AmazonKinesisFirehoseClientBuilder.standard(), localstackDocker::getEndpointFirehose);
    }

    @Override
    public AmazonRoute53 amazonRoute53() {
        return decorateWithConfigsAndBuild(AmazonRoute53ClientBuilder.standard(), localstackDocker::getEndpointRoute53);
    }

    @Override
    public AWSLambda awsLambda() {
        return decorateWithConfigsAndBuild(AWSLambdaClientBuilder.standard(), localstackDocker::getEndpointLambda);
    }
}