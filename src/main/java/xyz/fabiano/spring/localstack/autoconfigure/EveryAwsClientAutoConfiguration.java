package xyz.fabiano.spring.localstack.autoconfigure;

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
import com.amazonaws.services.stepfunctions.AWSStepFunctionsClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import xyz.fabiano.spring.localstack.support.AmazonClientsHolder;

@Configuration
@ConditionalOnProperty(value = {"spring.localstack.services", "spring.localstack.enabled"}, matchIfMissing = true)
public class EveryAwsClientAutoConfiguration {

    private AmazonClientsHolder amazonClientsHolder;

    public EveryAwsClientAutoConfiguration(AmazonClientsHolder amazonClientsHolder) {
        this.amazonClientsHolder = amazonClientsHolder;
    }

    @Bean
    @Primary
    public AmazonS3 amazonS3() {
        return amazonClientsHolder.amazonS3();
    }

    @Bean
    public AmazonSQS amazonSQS() {
        return amazonClientsHolder.amazonSQS();
    }

    @Bean
    public AmazonSNS amazonSNS() {
        return amazonClientsHolder.amazonSNS();
    }

    @Bean
    public AmazonCloudWatch amazonCloudWatch() {
        return amazonClientsHolder.amazonCloudWatch();
    }

    @Bean
    public AmazonKinesis amazonKinesis() {
        return amazonClientsHolder.amazonKinesis();
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return amazonClientsHolder.amazonDynamoDB();
    }

    @Bean
    public AmazonDynamoDBStreams amazonDynamoDBStreams() {
        return amazonClientsHolder.amazonDynamoDBStreams();
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        return amazonClientsHolder.amazonSimpleEmailService();
    }

    @Bean
    public AmazonApiGateway amazonApiGateway() {
        return amazonClientsHolder.amazonApiGateway();
    }

    @Bean
    public AmazonRedshift amazonRedshift() {
        return amazonClientsHolder.amazonRedshift();
    }

    @Bean
    public AmazonKinesisFirehose amazonKinesisFirehose() {
        return amazonClientsHolder.amazonKinesisFirehose();
    }

    @Bean
    public AmazonRoute53 amazonRoute53() {
        return amazonClientsHolder.amazonRoute53();
    }

    @Bean
    public AWSLambda awsLambda() {
        return amazonClientsHolder.awsLambda();
    }

    @Bean
    public AmazonCloudFormation amazonCloudFormation() {
        return amazonClientsHolder.amazonCloudFormation();
    }

    @Bean
    public AWSSimpleSystemsManagement awsSimpleSystemsManagement() { return  amazonClientsHolder.awsSimpleSystemsManagement(); }

    @Bean
    public AWSStepFunctions awsStepFunctions() { return amazonClientsHolder.awsStepFunctions(); }
}
