package xyz.fabiano.spring.localstack.annotation;

public @interface SpringLocalstackProperties {

    String[] services() default {};

    String externalHost() default "localhost";

    boolean randomPorts() default true;

    boolean pullNewImage() default false;
}
