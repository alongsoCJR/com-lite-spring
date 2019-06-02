package org.litespring.factory;

import org.litespring.beans.BeansException;

public class BeanDefinitionStoreException extends BeansException {
    public BeanDefinitionStoreException(String msg, Throwable e) {
        super(msg,e);
    }
}
