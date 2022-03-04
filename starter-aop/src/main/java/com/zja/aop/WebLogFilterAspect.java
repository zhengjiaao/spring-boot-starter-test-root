/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-04 13:22
 * @Since:
 */
package com.zja.aop;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志aop：自定义注解进行aop拦截
 */
@Slf4j
@Aspect
@Component
public class WebLogFilterAspect {

    @Pointcut(value = "@annotation(com.zja.aop.WebLogFilter)")
    public void pointCut() {
    }

    /**
     * 环绕通知（@Around）：在被通知的方法调用之前和调用之后执行自定义的方法
     * 注意：环绕增强，相当于MethodInterceptor
     * 环绕通知=前置+目标方法执行+后置通知，proceed方法就是用于启动目标方法执行的
     *
     * @param proceedingJoinPoint   getArgs()：返回方法参数.getThis()：返回代理对象.getTarget()：返回目标对象.getSignature()：返回所建议方法的描述.toString()：打印所建议方法的有用描述
     */
    @Around("pointCut()")
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        StringBuilder requestLog = new StringBuilder();
        Signature signature = proceedingJoinPoint.getSignature();

        requestLog.append("Web接口日志输出：" + "\n")
                .append(((MethodSignature) signature).getMethod().getAnnotation(ApiOperation.class).value()).append("\n")
                .append("请求信息：").append("URL = {").append(request.getRequestURI()).append("},\n")
                .append("请求方式 = {").append(request.getMethod()).append("},\n")
                .append("请求IP = {").append(request.getRemoteAddr()).append("},\n")
                .append("类方法 = {").append(signature.getDeclaringTypeName()).append(".")
                .append(signature.getName()).append("},\n");

        // 处理请求参数
        String[] paramNames = ((MethodSignature) signature).getParameterNames();
        Object[] paramValues = proceedingJoinPoint.getArgs();
        int paramLength = null == paramNames ? 0 : paramNames.length;
        if (paramLength == 0) {
            requestLog.append("请求参数 = {} ");
        } else {
            requestLog.append("请求参数 = [");
            for (int i = 0; i < paramLength - 1; i++) {
                requestLog.append(paramNames[i]).append("=").append(JSONObject.toJSONString(paramValues[i])).append(",");
            }
            requestLog.append(paramNames[paramLength - 1]).append("=").append(JSONObject.toJSONString(paramValues[paramLength - 1])).append("]");
        }

        boolean result = true;
        try {
            //方法执行后，返回的结果数据
            Object proceed = proceedingJoinPoint.proceed();
            System.out.println("proceed=" + proceed);
        } catch (Throwable t) {
            result = false;
        }
        requestLog.append("\n" + "请求结果：").append(result == true ? "Success." : "Fail.");

        log.info(requestLog.toString());
    }

}
