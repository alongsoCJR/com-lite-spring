package org.litespring.aop;

import java.lang.reflect.Method;

/**
 * @author chenjianrong-lhq 2019年06月02日 22:16:05
 * @Description:
 * @ClassName: MethodMatcher
 */
public interface MethodMatcher {

    boolean matches(Method method1);
}
