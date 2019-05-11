package org.litespring.beans.factory.config;

import org.litespring.factory.BeanFactory;

/**
 * @author chenjianrong-lhq 2019年02月25日 22:19:00
 * @Description:
 * @ClassName: ConfigableBeanFactory
 */
public interface ConfigableBeanFactory extends BeanFactory {

    void setBeanClassLoder(ClassLoader beanClassLoder);

    ClassLoader getBeanClassLoder();
}
