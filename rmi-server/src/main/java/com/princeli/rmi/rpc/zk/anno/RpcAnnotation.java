package com.princeli.rmi.rpc.zk.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: rmi-demo
 * @description: ${description}
 * @author: ly
 * @create: 2018-07-06 15:41
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcAnnotation {

    Class<?> value();

    String version() default "";

}
