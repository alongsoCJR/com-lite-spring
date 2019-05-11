package org.litespring.beans.factory.config;

/**
 * @author chenjianrong-lhq 2019年02月26日 21:57:42
 * @Description:
 * @ClassName: SingletonBeanRegistry
 */
public interface SingletonBeanRegistry {
    void registerSingleton(String beanName,Object singletonObject) throws IllegalStateException;

    Object getSingleton(String beanName);
}
