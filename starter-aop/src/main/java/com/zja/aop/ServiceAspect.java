/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-04 15:29
 * @Since:
 */
package com.zja.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    /**
     * 前置通知（@Before）：在目标方法调用之前调用通知
     */
    @Before("CommonPointcuts.inServiceLayer()")
    public void beforeAdvice() {
        System.out.println("[ServiceAspect] beforeAdvice...");
    }

    /**
     * 后置通知（@After）：在目标方法完成之后调用通知
     * 注意：final增强，不管是抛出异常或者正常退出都会执行
     */
    @After("CommonPointcuts.inServiceLayer()")
    public void afterAdvice() {
        System.out.println("[ServiceAspect] afterAdvice...");
    }


}
