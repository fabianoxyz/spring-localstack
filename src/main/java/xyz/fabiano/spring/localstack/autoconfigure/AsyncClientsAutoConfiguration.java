package xyz.fabiano.spring.localstack.autoconfigure;

import cloud.localstack.docker.LocalstackDocker;
import com.amazonaws.services.apigateway.AmazonApiGatewayAsync;
import com.amazonaws.services.cloudformation.AmazonCloudFormationAsync;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsAsync;
import com.amazonaws.services.kinesis.AmazonKinesisAsync;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseAsync;
import com.amazonaws.services.lambda.AWSLambdaAsync;
import com.amazonaws.services.redshift.AmazonRedshiftAsync;
import com.amazonaws.services.route53.AmazonRoute53Async;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceAsync;
import com.amazonaws.services.sns.AmazonSNSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import xyz.fabiano.spring.localstack.support.AmazonDockerClientsHolder;

@Configuration
@ConditionalOnProperty({"spring.localstack.autoconfiguration", "spring.localstack.async-clients.enabled"})
public class AsyncClientsAutoConfiguration {

    private LocalstackDocker localstackDocker;

    public AsyncClientsAutoConfiguration(LocalstackDocker localstackDocker) {
        this.localstackDocker = localstackDocker;
    }

//    @Bean
//    @Primary
//    public AmazonS3 amazonS3() {
//        return AmazonDockerClientsHolder.amazonS3();
//    }
//
//    @Bean
//    public AmazonSQSAsync amazonSQSAsync() {
//        return AmazonDockerClientsHolder.amazonSQSAsync();
//    }
//
//    @Bean
//    public AmazonSNSAsync amazonSNSAsync() {
//        return AmazonDockerClientsHolder.amazonSNSAsync();
//    }
//
//    @Bean
//    public AmazonCloudWatchAsync amazonCloudWatchAsync() {
//        return AmazonDockerClientsHolder.amazonCloudWatchAsync();
//    }
//
//    @Bean
//    public AmazonKinesisAsync amazonKinesisAsync() {
//        return AmazonDockerClientsHolder.amazonKinesisAsync();
//    }
//
//    @Bean
//    public AmazonDynamoDBAsync amazonDynamoDBAsync() {
//        return AmazonDockerClientsHolder.amazonDynamoDBAsync();
//    }
//
//    @Bean
//    public AmazonDynamoDBStreamsAsync amazonDynamoDBStreamsAsync() {
//        return AmazonDockerClientsHolder.amazonDynamoDBStreamsAsync();
//    }
//
//    @Bean
//    public AmazonSimpleEmailServiceAsync amazonSimpleEmailServiceAsync() {
//        return AmazonDockerClientsHolder.amazonSimpleEmailServiceAsync();
//    }
//
//    @Bean
//    public AmazonApiGatewayAsync amazonApiGatewayAsync() {
//        return AmazonDockerClientsHolder.amazonApiGatewayAsync();
//    }
//
//    @Bean
//    public AmazonRedshiftAsync amazonRedshiftAsync() {
//        return AmazonDockerClientsHolder.amazonRedshiftAsync();
//    }
//
//    @Bean
//    public AmazonKinesisFirehoseAsync amazonKinesisFirehoseAsync() {
//        return AmazonDockerClientsHolder.amazonKinesisFirehoseAsync();
//    }
//
//    @Bean
//    public AmazonRoute53Async amazonRoute53Async() {
//        return AmazonDockerClientsHolder.amazonRoute53Async();
//    }
//
//    @Bean
//    public AWSLambdaAsync awsLambdaAsync() {
//        return AmazonDockerClientsHolder.awsLambdaAsync();
//    }
//
//    @Bean
//    public AmazonCloudFormationAsync amazonCloudFormationAsync() {
//        return AmazonDockerClientsHolder.amazonCloudFormationAsync();
//    }
}
