# A Simple Localstack and Spring Integration
[![Build Status](https://travis-ci.org/fabianoo/spring-localstack.svg?branch=master)](https://travis-ci.org/fabianoo/spring-localstack)


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

## Spring Boot Configuration
To activate the auto-configuration with Spring Boot, just add the following property:
```properties  
spring.localstack.enabled=true  
```
When you get your application running, this property will get up a docker image with Localstack and create beans for each AWS client already with the needed configuration for use them with Localstack. Then, you can just inject them like this:
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

If you wish to use the async the clients, you just need to:
```properties
spring.localstack.async-clients.enabled=true  
```