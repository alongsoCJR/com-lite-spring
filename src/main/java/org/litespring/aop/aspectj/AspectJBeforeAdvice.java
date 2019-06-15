package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * @author chenjianrong-lhq 2019年06月09日 07:34:14
 * @Description:
 * @ClassName: AspectJBeforeAdvice
 */
public class AspectJBeforeAdvice extends AbstractAspectAdvice {


    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        //例如：在方法placeOrder执行之前执行start
        super.invokeAdviceMethod();
        Object o = methodInvocation.proceed();
        return o;
    }

    public AspectJBeforeAdvice(Method adviceMethod, Pointcut pc, Object adviceObject) {
        super(adviceMethod, pc, adviceObject);
    }
}
