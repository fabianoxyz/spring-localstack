package xyz.fabiano.spring.localstack;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum LocalstackService {
    API_GATEWAY("apigateway"),
    KINESIS("kinesis"),
    DYNAMO("dynamodb"),
    DYNAMO_STREAMS("dynamodbstreams"),
    S3("s3"),
    FIREHOSE("firehose"),
    LAMBDA("lambda"),
    SNS("sns"),
    SQS("sqs"),
    REDSHIFT("redshift"),
    SES("ses"),
    ROUTE53("route53"),
    CLOUDFORMATION("cloudformation"),
    CLOUDWATCH("cloudwatch"),
    SSM("ssm"),
    STEP_FUNCTION("stepfunctions"),

    ELASTICSEARCH("elasticsearch", false),
    ELASTICSEARCH_SERVICE("es", false);

    private final String service;
    private final boolean defaultService;

    LocalstackService(String service) {
        this.service = service;
        this.defaultService = true;
    }

    LocalstackService(String service, boolean defaultService) {
        this.service = service;
        this.defaultService = defaultService;
    }

    public static Set<LocalstackService> defaultServices() {
        return Stream.of(LocalstackService.values())
            .filter(l -> l.defaultService)
            .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return service;
    }
}
