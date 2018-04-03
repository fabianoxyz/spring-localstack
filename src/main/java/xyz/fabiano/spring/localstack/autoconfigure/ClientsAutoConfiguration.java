package xyz.fabiano.spring.localstack.autoconfigure;

import cloud.localstack.DockerTestUtils;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.sqs.AmazonSQS;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty("localstack.clients.bean")
public class ClientsAutoConfiguration {

    @Bean
    public AmazonS3 amazonS3() {
        return DockerTestUtils.getClientS3();
    }

    @Bean
    public AmazonSQS amazonSQS() {
        return DockerTestUtils.getClientSQS();
    }
}
