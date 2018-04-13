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
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sqs.AmazonSQS;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import xyz.fabiano.spring.localstack.legacy.LocalstackDocker;
import xyz.fabiano.spring.localstack.support.AmazonAsyncDockerClientsHolder;
import xyz.fabiano.spring.localstack.support.AmazonClientsHolder;
import xyz.fabiano.spring.localstack.support.AmazonDockerClientsHolder;

@Configuration
@ConditionalOnProperty("spring.localstack.enabled")
public class ClientsAutoConfiguration {

    private boolean asyncClientsEnabled;

    public ClientsAutoConfiguration(
        @Value("${spring.localstack.async-clients.enabled:false}") boolean asyncClientsEnabled) {
        this.asyncClientsEnabled = asyncClientsEnabled;
    }

    @Bean
    public AmazonClientsHolder amazonClientsHolder(LocalstackDocker localstackDocker) {
        if (asyncClientsEnabled) {
            return new AmazonAsyncDockerClientsHolder(localstackDocker);
        } else {
            return new AmazonDockerClientsHolder(localstackDocker);
        }
    }

    @Bean
    @Primary
    public AmazonS3 amazonS3(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonS3();
    }

    @Bean
    public AmazonSQS amazonSQS(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonSQS();
    }

    @Bean
    public AmazonSNS amazonSNS(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonSNS();
    }

    @Bean
    public AmazonCloudWatch amazonCloudWatch(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonCloudWatch();
    }

    @Bean
    public AmazonKinesis amazonKinesis(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonKinesis();
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonDynamoDB();
    }

    @Bean
    public AmazonDynamoDBStreams amazonDynamoDBStreams(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonDynamoDBStreams();
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonSimpleEmailService();
    }

    @Bean
    public AmazonApiGateway amazonApiGateway(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonApiGateway();
    }

    @Bean
    public AmazonRedshift amazonRedshift(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonRedshift();
    }

    @Bean
    public AmazonKinesisFirehose amazonKinesisFirehose(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonKinesisFirehose();
    }

    @Bean
    public AmazonRoute53 amazonRoute53(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonRoute53();
    }

    @Bean
    public AWSLambda awsLambda(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.awsLambda();
    }

    @Bean
    public AmazonCloudFormation amazonCloudFormation(AmazonClientsHolder amazonClientsHolder) {
        return amazonClientsHolder.amazonCloudFormation();
    }
}


