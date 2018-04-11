# A Simple Localstack and Spring Integration
[![Build Status](https://travis-ci.org/fabianoo/spring-localstack.svg?branch=master)](https://travis-ci.org/fabianoo/spring-localstack)
[![PyPI License](https://img.shields.io/pypi/l/localstack.svg)](https://img.shields.io/pypi/l/localstack.svg)



## About Localstack
_LocalStack_ is a fully functional local AWS cloud stack. It provides an easy-to-use test/mocking framework for developing Cloud applications. Currently, the focus is primarily on supporting the AWS cloud stack.

You can checkout more about [here](https://github.com/localstack/localstack).



## Distribution
This library is distributed under Apache 2.0 license and it is available as an artifact in Maven Central. You can just simply add the following dependency to your project.


#### Maven:
```xml
<dependency>
	<groupId>xyz.fabiano</groupId>  
	<artifactId>spring-localstack</artifactId>  
	<version>0.0.3</version>  
</dependency>
```

#### Gradle:
```properties
compile 'xyz.fabiano:spring-localstack:0.0.3'
```



## Requirements

Before you get up and running, you must meet the following requirements:

* `Java` (1.8 or greater)
* `Spring` (Starting from 4.x)
* `Docker` (service running)


## Testing with JUnit Runner

The class `SpringLocalstackDockerRunner` is a JUnit runner with the purpose of testing integrated with Localstack and Spring. With this runner, we can compound with `@SpringLocalstackProperties` for more extensible configuration. Check it out:

```java
@RunWith(SpringLocalstackDockerRunner.class)  
@SpringLocalstackProperties(services = { S3 })  
@ContextConfiguration(classes = SpringTestContext.class)  
public class SpringWithLocalstackExampleTest {
	@Autowired
	private AmazonS3 amazonS3;
  
    @Test  
	public void testIfHas10Files() {  
        ...  
  
        ObjectListing listing = amazonS3.listObjects("my-bucket", "a-preffix");  
		assertThat(listing.getObjectSummaries().size(), is(10));
	}  
}
```


And the context configuration:

```java
@Configuration
public class SpringTestContext {

    @Bean
    public AmazonDockerClientsHolder amazonDockerClientsHolder() {
        return new AmazonDockerClientsHolder(LocalstackDocker.getLocalstackDocker());
    }

    @Bean
    public AmazonS3 amazonS3(AmazonDockerClientsHolder amazonDockerClientsHolder) {
        return amazonDockerClientsHolder.amazonS3();
    }
}
```



## Spring Boot Configuration

To activate the auto-configuration with Spring Boot, just add the following property:
```properties  
spring.localstack.enabled=true  
```
When you get your application running, this property will get up a docker image with Localstack and instantiate beans for each AWS client already with the needed configuration for use them with Localstack. Then, you can just inject them like this:
```java
@Component
public class MyComponent {
	@Autowired  
	private AmazonS3 amazonS3;

	...
	
	public uploadFileToS3(File file) {
		PutObjectRequest request = new PutObjectRequest("my-bucket", "my-data", file);  
		amazonS3.putObject(request);
	}
}
```

If you wish to use the async the clients instead of the defaults, just add the following property:

```properties
spring.localstack.async-clients.enabled=true  
```