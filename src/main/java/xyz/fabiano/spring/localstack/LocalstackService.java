package xyz.fabiano.spring.localstack;

public enum LocalstackService {
    API_GATEWAY("apigateway"),
    KINESIS("kinesis"),
    DYNAMO("dynamodb"),
    DYNAMO_STREAMS("dynamodbstreams"),
    ELASTICSEARCH("elasticsearch"),
    S3("s3"),
    FIREHOSE("firehose"),
    LAMBDA("lambda"),
    SNS("sns"),
    SQS("sqs"),
    REDSHIFT("redshift"),
    ELASTICSEARCH_SERVICE("es"),
    SES("ses"),
    ROUTE53("route53"),
    CLOUDFORMATION("cloudformation"),
    CLOUDWATCH("cloudwatch"),
    SSM("ssm");

    private final String service;

    LocalstackService(String service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return service;
    }
}
