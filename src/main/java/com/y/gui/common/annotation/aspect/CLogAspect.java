package com.y.gui.common.annotation.aspect;

import com.alibaba.fastjson.JSON;
import com.y.gui.common.annotation.CLog;
import com.y.gui.common.utils.IPUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义日志输出
 */
@Slf4j
@Aspect
@Component
public class CLogAspect {
    @Pointcut(value = "@annotation(com.y.gui.annotation.CLog)")
    public void cLogPointCut() {
    }

    @Around("cLogPointCut()")
    public Object aroundCut(ProceedingJoinPoint joinPoint) {
        // 如果不是方法类型，抛异常
        if (!(joinPoint.getSignature() instanceof MethodSignature)) {
            throw new IllegalArgumentException("====>this annotation only used on method.");
        }

        CLogBean cLogBean = new CLogBean();
        Object result;
        // 方法执行开始时间
        long methodStartTime = System.currentTimeMillis();
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            cLogBean.setExceptionMsg(e.getMessage());
            throw new RuntimeException(e);
        }
        // 方法执行结束时间
        cLogBean.setExecuteTime(System.currentTimeMillis() - methodStartTime);

        // 运行时参数
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        CLog cLog = method.getAnnotation(CLog.class);
        // 方法未配置@CLog注解
        if (null == cLog)
            return result;

        // 类名
        cLogBean.setClassName(joinPoint.getTarget().getClass().getName());
        // 方法名称
        cLogBean.setMethodName(signature.getName());
        // 参数信息
        Object[] args = joinPoint.getArgs();
        Parameter[] parameters = method.getParameters();
        cLogBean.setResultType(method.getGenericReturnType().getTypeName());

        // 判断是否打印入参
        if (cLog.printParam()) {
            cLogBean.setLogParam(Boolean.TRUE);
            cLogBean.setParamMap(buildParams(args, parameters));
        }

        // 判断是否打印出参
        if (cLog.printResult()) {
            cLogBean.setLogResult(Boolean.TRUE);
            cLogBean.setResult(result);
        }

        //获取request
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (null != requestAttributes) {
            ServletRequestAttributes servletRequest = (ServletRequestAttributes) requestAttributes;
            HttpServletRequest request = servletRequest.getRequest();
            cLogBean.setRequestURL(request.getRequestURL().toString());
            cLogBean.setIp(IPUtils.IP(request));
        }

        printLog(cLogBean, cLog);
        return result;
    }

    /**
     * 构造入参
     * @param args
     * @param parameters
     * @return
     */
    private Map<String, String> buildParams(Object[] args, Parameter[] parameters) {
        if (args == null || parameters == null) {
            return null;
        }
        if (args.length != parameters.length) {
            return null;
        }
        if (args.length == 0) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            map.put(parameters[i].getName(), JSON.toJSONString(args[i]));
        }
        return map;
    }

    /**
     * 打印日志
     * @param cLogBean
     * @param cLog
     */
    private void printLog(CLogBean cLogBean, CLog cLog) {
        if (null == cLog.logLevel()) {
            log.info("CLogAspect: {}", cLogBean);
            return;
        }
        switch (cLog.logLevel()) {
            case TRACE:
                log.trace("CLogAspect: {}", cLogBean);
                break;
            case DEBUG:
                log.debug("CLogAspect: {}", cLogBean);
                break;
            case WARN:
                log.warn("CLogAspect: {}", cLogBean);
                break;
            case ERROR:
                log.error("CLogAspect: {}", cLogBean);
                break;
            default:
                log.info("CLogAspect: {}", cLogBean);
                break;
        }
    }

    @Getter
    @Setter
    class CLogBean {
        /**
         * 类名
         */
        private String className;
        /**
         * 方法名
         */
        private String methodName;
        /**
         * 执行耗时：毫秒
         */
        private Long executeTime;
        /**
         * 是否打印入参
         */
        private Boolean logParam = Boolean.FALSE;
        /**
         * 方法入参
         */
        private Map<String, String> paramMap;
        /**
         * 是否打印出参
         */
        private Boolean logResult = Boolean.FALSE;
        /**
         * 方法返回值
         */
        private Object result;
        /**
         * 方法返回值类型
         */
        private String resultType;
        /**
         * 异常信息
         */
        private String exceptionMsg;

        /**
         * 请求路径
         */
        private String requestURL;
        /**
         * 请求地址IP
         */
        private String ip;

        @Override
        public String toString() {
            StringBuilder logBuilder = new StringBuilder("position:" + this.className + "#" + this.methodName + ", executeTime:" + this.executeTime + " milliseconds");
            if (this.logParam && null != this.paramMap) {
                logBuilder.append(", param:" + JSON.toJSONString(this.paramMap));
            }
            if (this.logResult && null != this.result) {
                logBuilder.append(", result:" + JSON.toJSONString(this.result));
            }
            logBuilder.append(", returnType:" + this.resultType);
            if (!StringUtils.isEmpty(this.requestURL)) {
                logBuilder.append(", url:"+ this.requestURL);
            }
            if (!StringUtils.isEmpty(this.ip)) {
                logBuilder.append(", ip:"+ this.ip);
            }
            if (!StringUtils.isEmpty(this.exceptionMsg)) {
                logBuilder.append(", e:" + this.exceptionMsg);
            }
            return logBuilder.toString();
        }
    }
}
