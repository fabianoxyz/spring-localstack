package xyz.fabiano.spring.localstack.support;

import com.amazonaws.services.apigateway.AmazonApiGateway;
import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreams;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.redshift.AmazonRedshift;
import com.amazonaws.services.route53.AmazonRoute53;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.stepfunctions.AWSStepFunctions;

public interface AmazonClientsHolder {

    AmazonS3 amazonS3();

    AmazonSQS amazonSQS();

    AmazonSNS amazonSNS();

    AmazonKinesis amazonKinesis();

    AmazonDynamoDB amazonDynamoDB();

    AmazonDynamoDBStreams amazonDynamoDBStreams();

    AmazonSimpleEmailService amazonSimpleEmailService();

    AmazonApiGateway amazonApiGateway();

    AmazonRedshift amazonRedshift();

    AmazonCloudWatch amazonCloudWatch();

    AmazonCloudFormation amazonCloudFormation();

    AmazonKinesisFirehose amazonKinesisFirehose();

    AmazonRoute53 amazonRoute53();

    AWSLambda awsLambda();

    AWSSimpleSystemsManagement awsSimpleSystemsManagement();

    AWSStepFunctions awsStepFunctions();
}
