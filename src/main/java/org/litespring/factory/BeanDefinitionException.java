package org.litespring.factory;

import org.litespring.beans.BeansException;

/**
 * @author chenjianrong-lhq 2019年02月24日 10:16:55
 * @Description: 读取XML文件时异常
 * @ClassName: BeanDefinitionException
 */
public class BeanDefinitionException extends BeansException {
    public BeanDefinitionException(String msg) {
        super(msg);
    }
}
