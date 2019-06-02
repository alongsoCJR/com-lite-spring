package org.litespring.beans.factory.config;

import java.util.List;

/**
 * @author chenjianrong-lhq 2019年02月25日 22:19:00
 * @Description:
 * @ClassName: ConfigableBeanFactory
 */
public interface ConfigableBeanFactory extends AutowireCapableBeanFactory {

    void setBeanClassLoder(ClassLoader beanClassLoder);

    ClassLoader getBeanClassLoder();

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    List<BeanPostProcessor> getBeanPostProcessors();
}
