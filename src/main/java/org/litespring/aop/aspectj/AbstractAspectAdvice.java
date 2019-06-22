package org.litespring.aop.aspectj;

import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;
import org.litespring.aop.config.AspectInstanceFactory;

import java.lang.reflect.Method;

/**
 * @author chenjianrong-lhq 2019年06月09日 09:03:09
 * @Description:
 * @ClassName: AbstractAspectAdvice
 */
public abstract class AbstractAspectAdvice implements Advice {

    protected Method adviceMethod;

    protected Pointcut pc;

    protected AspectInstanceFactory aspectInstanceFactory;


    public AbstractAspectAdvice(Method adviceMethod, Pointcut pc, AspectInstanceFactory aspectInstanceFactory) {
        this.adviceMethod = adviceMethod;
        this.pc = pc;
        this.aspectInstanceFactory = aspectInstanceFactory;
    }

    public Pointcut getPointcut() {
        return pc;
    }

    public Method getAdviceMethod() {
        return adviceMethod;
    }

    public void invokeAdviceMethod() throws Throwable {
        adviceMethod.invoke(aspectInstanceFactory.getAspectInstance());
    }

    public Object getAdviceInstance() throws Exception {
        return aspectInstanceFactory.getAspectInstance();
    }
}
