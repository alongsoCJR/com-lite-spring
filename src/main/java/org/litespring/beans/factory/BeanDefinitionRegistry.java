package org.litespring.beans.factory;

import org.litespring.beans.BeanDefinition;

/**
 * @author chenjianrong-lhq 2019年02月24日 12:07:57
 * @Description:
 * @ClassName: BeanDefinitionRegistry
 */
public interface BeanDefinitionRegistry {

    BeanDefinition getBeanDefinition(String petStore);

    void registerBeanDefinition(String beanId,BeanDefinition bd);
}
