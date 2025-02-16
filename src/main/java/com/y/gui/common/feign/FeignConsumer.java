package com.y.gui.common.feign;

/*import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;*/

/**
 * 动态注入FeignClient代理服务的注解
 * 可以加在被调用服务Controller接口类型的bean字段上，动态代理注入服务对象。name为在目标系统application.name
 * 注意: 由于Spring和Feign的限制，服务接口上不能有@RequestMapping注解
 * https://github.com/spring-cloud/spring-cloud-openfeign/issues/587
 *
 * @author zhangxiaohui
 * @date 2024/10/18 上午10:35
 */
/*@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented*/
public @interface FeignConsumer {

    /**
     * 服务提供方应用名称 Spring配置 application.name
     * @return
     *//*
    @AliasFor("name")
    String value() default "";

    *//**
     * 服务提供方应用名称 Spring配置 application.name
     * @return
     *//*
    @AliasFor("value")
    String name() default "";

    *//**
     * 服务代理对象的beanName，缺省为字段名称
     * @return
     *//*
    String contextId() default "";*/
}
