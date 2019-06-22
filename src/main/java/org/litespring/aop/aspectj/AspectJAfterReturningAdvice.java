package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Pointcut;
import org.litespring.aop.config.AspectInstanceFactory;

import java.lang.reflect.Method;

/**
 * @author chenjianrong-lhq 2019年06月09日 08:13:33
 * @Description:
 * @ClassName: AspectJAfterReturningAdvice
 */
public class AspectJAfterReturningAdvice extends AbstractAspectAdvice {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object o = methodInvocation.proceed();
        //例如：在方法placeOrder执行之后执行commit
        super.invokeAdviceMethod();
        return o;
    }

    public AspectJAfterReturningAdvice(Method adviceMethod, Pointcut pc, AspectInstanceFactory aspectInstanceFactory) {
        super(adviceMethod, pc, aspectInstanceFactory);
    }
}
