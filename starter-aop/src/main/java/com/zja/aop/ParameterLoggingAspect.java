package com.zja.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author: zhengja
 * @since: 2024/01/17 16:22
 */
@Aspect
@Component
public class ParameterLoggingAspect {

    @Before("execution(* com.zja.controller.UserController.*(..))")
    public void logMethodParameters(JoinPoint joinPoint) throws IllegalAccessException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Class<?>[] parameterTypes = method.getParameterTypes();
        String[] parameterNames = methodSignature.getParameterNames();

        for (int i = 0; i < parameterNames.length; i++) {
            for (Annotation annotation : method.getParameterAnnotations()[i]) {
                if (annotation instanceof MyParam) {
                    Object myAnnotatedParameter = joinPoint.getArgs()[i];

                    // 在这里处理获取到的参数值
                    System.out.println("方法中的字段：");
                    System.out.println("参数名称：" + parameterNames[i]);
                    System.out.println("参数类型：" + parameterTypes[i]);
                    System.out.println("参数值：" + myAnnotatedParameter);
                }

                if (annotation instanceof MyBodyParam) {
                    Object arg = joinPoint.getArgs()[i];
                    if (arg != null) {
                        Class<?> argClass = arg.getClass();
                        Field[] fields = argClass.getDeclaredFields();

                        for (Field field : fields) {
                            if (field.isAnnotationPresent(MyParam.class)) {
                                field.setAccessible(true);
                                Object annotatedFieldValue = field.get(arg);

                                // 在这里处理获取到的字段值
                                System.out.println("方法中类里面的字段：");
                                System.out.println("字段名称：" + field.getName());
                                System.out.println("字段类型：" + field.getType());
                                System.out.println("字段值：" + annotatedFieldValue);
                            }
                        }
                    }
                }
            }
        }
    }

    // todo 不推荐，当getRequestBody() 会遇到问题 : getInputStream() has already been called for this request
 /*   @Before("execution(* com.zja.controller.UserController.*(..))")
    public void logMethodParameters(JoinPoint joinPoint) throws IOException {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpRequest = attributes.getRequest();
        // 获取请求的参数
        String paramValue = httpRequest.getParameter("name");
        if (StringUtils.isEmpty(paramValue)) {
            // 获取POST、PUT请求Body的参数
            if (httpRequest.getMethod().equalsIgnoreCase("POST") || httpRequest.getMethod().equalsIgnoreCase("PUT")) {
                String requestBody = getRequestBody(httpRequest);
                paramValue = JSON.parseObject(requestBody).getString("name");
                // 进行参数的处理或其他操作
                System.out.println("POST or PUT Parameter: " + paramValue);
            }
        }
        // 打印参数值或进行其他操作
        System.out.println("Parameter [name]=: " + paramValue);
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }*/
}
