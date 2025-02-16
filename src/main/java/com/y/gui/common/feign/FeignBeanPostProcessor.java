package com.y.gui.common.feign;

/*import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.cloud.openfeign.FeignClientBuilder;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;*/

import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Feign服务动态注入，直接代理服务接口，注入代理对象，比@FeignClient更简洁
 *
 * @author zhangxiaohui
 * @date 2024/10/18 上午10:35
 */
//@Component
public class FeignBeanPostProcessor implements BeanPostProcessor {

    /*@Override
    @SneakyThrows
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(FeignConsumer.class)) {
                FeignConsumer feignConsumerAnno = field.getAnnotation(FeignConsumer.class);
                Class<?> fieldType = field.getType();
                // 注解上服务name空则跳过
                String name = StringUtils.isEmpty(feignConsumerAnno.value()) ? feignConsumerAnno.value() : feignConsumerAnno.name();
                if(StringUtils.isEmpty(name)) {
                    continue;
                }
                // 服务代理的beanName, 缺省为字段名称
                String contextId = feignConsumerAnno.contextId();
                if(StringUtils.isBlank(contextId)) {
                    contextId = field.getName();
                }
                Object proxyBean = new FeignClientBuilder(applicationContext).forType(fieldType, name).contextId(contextId).build();
                makeAccessible(field);
                field.set(bean, proxyBean);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private void makeAccessible(Field field) {
        if (!Modifier.isPublic(field.getModifiers())) {
            field.setAccessible(true);
        }
    }

    @Autowired
    private ConfigurableApplicationContext applicationContext;*/
}
