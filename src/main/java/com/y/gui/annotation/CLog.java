package com.y.gui.annotation;

import com.y.gui.annotation.enums.LogLevelEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CLog {

    /**
     * 日志打印级别
     * @return
     */
    LogLevelEnum logLevel() default LogLevelEnum.INFO;

    /**
     * 是否打印方法入参
     * @return
     */
    boolean printParam() default true;

    /**
     * 是否打印方法出参
     * @return
     */
    boolean printResult() default true;
}
