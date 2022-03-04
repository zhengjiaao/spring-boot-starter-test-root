/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-04 11:21
 * @Since:
 */
package com.zja.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 通知有五种类型，分别是：
 * 前置通知（@Before）：在目标方法调用之前调用通知（）
 * 后置通知（@After）：在目标方法完成之后调用通知
 * 环绕通知（@Around）：在被通知的方法调用之前和调用之后执行自定义的方法
 * 返回通知（@AfterReturning）：在目标方法成功执行之后调用通知
 * 异常通知（@AfterThrowing）：在目标方法抛出异常之后调用通知
 *
 * 示例：针对 com.zja.controller 包路下所有控制器的方法添加aop
 * @Pointcut("execution (* com.zja.controller.*.*(..))")
 * 第一个 * 是返回任意类型
 * 第二个 * 是任意类
 * 第三个 * 是任意方法
 */
@Aspect
//@Component
public class TestAopControllerAspect2 {

    /**
     * 针对特定控制器，AopController 的特定方法
     */
    @Pointcut(value = "execution (* com.zja.controller.TestAopController.*(..))")
    public void pointCut() {
    }

    //前置通知（@Before）：在目标方法调用之前调用通知

    /**
     * 前置通知（@Before）：在目标方法调用之前调用通知
     */
    @Before("pointCut()")
    public void beforeAdvice() {
        System.out.println("[Aspect2] beforeAdvice...");
    }

    /**
     * 后置通知（@After）：在目标方法完成之后调用通知
     * 注意：final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("pointCut()")
    public void afterAdvice() {
        System.out.println("[Aspect2] afterAdvice...");
    }

    /**
     * 环绕通知（@Around）：在被通知的方法调用之前和调用之后执行自定义的方法
     * 注意：环绕增强，相当于MethodInterceptor
     * 环绕通知=前置+目标方法执行+后置通知，proceed方法就是用于启动目标方法执行的
     *
     * @param proceedingJoinPoint getArgs()：返回方法参数.getThis()：返回代理对象.getTarget()：返回目标对象.getSignature()：返回所建议方法的描述.toString()：打印所建议方法的有用描述
     */
    @Around("pointCut()")
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("[Aspect2] aroundAdvice before");
        try {
            proceedingJoinPoint.proceed();
        } catch (Throwable t) {
            System.out.println("[Aspect2] aroundAdvice exception");
            t.printStackTrace();
        }
        System.out.println("[Aspect2] aroundAdvice after");
    }

    /**
     * 返回通知（@AfterReturning）：在目标方法成功执行之后调用通知
     *
     * @param joinPoint getArgs()：返回方法参数.getThis()：返回代理对象.getTarget()：返回目标对象.getSignature()：返回所建议方法的描述.toString()：打印所建议方法的有用描述
     */
    @AfterReturning("pointCut()")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("[Aspect2] afterReturning advise");
    }

    /**
     * 异常通知（@AfterThrowing）：在目标方法抛出异常之后调用通知
     *
     * @param joinPoint
     */
    @AfterThrowing("pointCut()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("[Aspect2] afterThrowing advise");
    }


}
