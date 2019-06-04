package org.litespring.aop.config;

import org.litespring.beans.BeanUtils;
import org.litespring.factory.BeanFactory;
import org.litespring.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author chenjianrong-lhq 2019年06月03日 22:26:55
 * @Description:
 * @ClassName: MethodLocatingFactory
 */
public class MethodLocatingFactory {

    private String targetBeanName;

    private String methodName;

    private Method method;

    public void setTargetBeanName(String targetBeanName) {
        this.targetBeanName = targetBeanName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setBeanFatory(BeanFactory factory) {
        if (!StringUtils.hasText(this.targetBeanName)) {
            throw new IllegalArgumentException("Property 'targetBeanName' is required!");
        }
        if (!StringUtils.hasText(this.methodName)) {
            throw new IllegalArgumentException("Property 'methodName' is required!");
        }
        Class<?> targetClass = factory.getType(this.targetBeanName);

        if (targetClass == null) {
            throw new IllegalArgumentException("Cannot determine type of bean with name " + this.targetBeanName);
        }

        this.method = BeanUtils.resolveSignature(this.methodName, targetClass);

        if (this.method == null) {
            throw new IllegalArgumentException("Unable to locate method [" + this.methodName + "] on bean [" + this.targetBeanName + "]");

        }

    }

    public Method getObject() {
        return this.method;
    }
}
