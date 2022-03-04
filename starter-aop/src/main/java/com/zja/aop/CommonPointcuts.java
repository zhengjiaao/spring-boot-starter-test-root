/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2022-03-04 15:10
 * @Since:
 */
package com.zja.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 常见切入点
 *
 * 示例：针对 com.zja.controller 包路下所有控制器的方法添加aop
 * @Pointcut("execution (* com.zja.controller. * . * ( ..))")
 * 第一个 * 是返回任意类型
 * 第二个 * 是任意类
 * 第三个 * 是任意方法
 */
@Aspect
//@Component
public class CommonPointcuts {

    /**
     * A join point is in the web layer if the method is defined
     * in a type in the com.zja.controller package or any sub-package
     * under that.
     */
    @Pointcut("within(com.zja.controller..*)")
    public void inWebLayer() {}

    /**
     * A join point is in the service layer if the method is defined
     * in a type in the com.zja.service package or any sub-package
     * under that.
     */
    @Pointcut("within(com.zja.service..*)")
    public void inServiceLayer() {}

    /**
     * A join point is in the data access layer if the method is defined
     * in a type in the com.zja.dao package or any sub-package
     * under that.
     */
    @Pointcut("within(com.zja.dao..*)")
    public void inDataAccessLayer() {}

    /**
     * A business service is the execution of any method defined on a service
     * interface. This definition assumes that interfaces are placed in the
     * "service" package, and that implementation types are in sub-packages.
     *
     * If you group service interfaces by functional area (for example,
     * in packages com.zja.abc.service and com.zja.def.service) then
     * the pointcut expression "execution(* com.zja..service.*.*(..))"
     * could be used instead.
     *
     * Alternatively, you can write the expression using the 'bean'
     * PCD, like so "bean(*Service)". (This assumes that you have
     * named your Spring service beans in a consistent fashion.)
     */
    @Pointcut("execution(* com.zja..service.*.*(..))")
    public void businessService() {}

    /**
     * A data access operation is the execution of any method defined on a
     * dao interface. This definition assumes that interfaces are placed in the
     * "dao" package, and that implementation types are in sub-packages.
     */
    @Pointcut("execution(* com.zja.dao.*.*(..))")
    public void dataAccessOperation() {}
}
