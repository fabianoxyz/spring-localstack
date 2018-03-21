package xyz.fabiano.spring.localstack.annotation;

import xyz.fabiano.spring.localstack.LocalstackService;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface SpringLocalstackProperties {

    LocalstackService[] services() default {};

    String externalHost() default "localhost";

    boolean randomPorts() default true;

    boolean pullNewImage() default false;
}
