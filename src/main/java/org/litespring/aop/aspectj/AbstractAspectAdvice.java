package org.litespring.aop.aspectj;

import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * @author chenjianrong-lhq 2019年06月09日 09:03:09
 * @Description:
 * @ClassName: AbstractAspectAdvice
 */
public abstract class AbstractAspectAdvice implements Advice {

    private Method adviceMethod;

    private Pointcut pc;

    private Object adviceObject;


    public AbstractAspectAdvice(Method adviceMethod, Pointcut pc, Object adviceObject) {
        this.adviceMethod = adviceMethod;
        this.pc = pc;
        this.adviceObject = adviceObject;
    }

    public Pointcut getPointcut() {
        return pc;
    }

    public Method getAdviceMethod() {
        return adviceMethod;
    }

    public void invokeAdviceMethod() throws Throwable {
        adviceMethod.invoke(adviceObject);
    }
}
