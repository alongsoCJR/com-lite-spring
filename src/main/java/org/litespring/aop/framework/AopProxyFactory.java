package org.litespring.aop.framework;

/**
 * @author chenjianrong-lhq 2019年06月09日 09:59:47
 * @Description:
 * @ClassName: CglibProxyFactory
 */
public interface AopProxyFactory {

    Object getProxy() throws AopConfigException;
}
