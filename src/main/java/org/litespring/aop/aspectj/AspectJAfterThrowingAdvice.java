package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * @author chenjianrong-lhq 2019年06月09日 08:53:08
 * @Description:
 * @ClassName: AspectJAfterThrowingAdvice
 */
public class AspectJAfterThrowingAdvice extends AbstractAspectAdvice {


    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        try {
            return methodInvocation.proceed();
        } catch (Throwable t) {
            //例如：在方法placeOrder执行之后,如果有异常抛出，则执行throwing方法
            super.invokeAdviceMethod();
            throw t;
        }
    }

    public AspectJAfterThrowingAdvice(Method adviceMethod, Pointcut pc, Object adviceObject) {
        super(adviceMethod, pc, adviceObject);
    }
}
