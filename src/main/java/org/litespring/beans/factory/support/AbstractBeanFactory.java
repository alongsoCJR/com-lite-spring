package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanDefinitionRegistry;
import org.litespring.beans.factory.config.ConfigableBeanFactory;

/**
 * @author chenjianrong-lhq 2019年06月15日 19:24:12
 * @Description:
 * @ClassName: AbstractBeanFactory
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigableBeanFactory, BeanDefinitionRegistry {

    protected abstract Object createBean(BeanDefinition beanDefinition);
}
