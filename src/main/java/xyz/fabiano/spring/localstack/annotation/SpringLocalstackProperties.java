package xyz.fabiano.spring.localstack.annotation;

import xyz.fabiano.spring.localstack.LocalstackService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface SpringLocalstackProperties {

    LocalstackService[] services() default {};

    String externalHost() default "localhost";

    boolean randomPorts() default true;

    boolean pullNewImage() default false;

    boolean autoRemove() default true;

    String[] extraOptions() default {};

    String region() default "us-east-1";

    String version() default "latest";
}
