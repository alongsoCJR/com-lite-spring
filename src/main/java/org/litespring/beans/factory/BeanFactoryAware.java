package org.litespring.beans.factory;

import org.litespring.beans.BeansException;
import org.litespring.factory.BeanFactory;

public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;

}
