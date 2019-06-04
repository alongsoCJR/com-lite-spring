package org.litespring.aop;

/**
 * @author chenjianrong-lhq 2019年06月02日 22:22:23
 * @Description:
 * @ClassName: Pointcut
 */
public interface Pointcut {
    MethodMatcher getMethodMatcher();

    String getExpression();
}
