package org.litespring.aop.config;

import org.litespring.beans.factory.BeanFactoryAware;
import org.litespring.factory.BeanFactory;
import org.litespring.util.StringUtils;

/**
 * @author chenjianrong-lhq 2019年06月15日 15:42:53
 * @Description: 通过注入beanFactory的方式轻松拿到需要创建的目标bean对象,这个类的改造促使我们联系上了xml文件了
 * @ClassName: AspectInstanceFactory
 */
public class AspectInstanceFactory implements BeanFactoryAware {

    private String aspectBeanName;

    private BeanFactory beanFactory;

    public void setAspectBeanName(String aspectBeanName) {
        this.aspectBeanName = aspectBeanName;
    }

    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        if (!StringUtils.hasText(this.aspectBeanName)) {
            throw new IllegalArgumentException("'aspectBeanName' is required");
        }
    }

    public Object getAspectInstance() throws Exception {
        return this.beanFactory.getBean(this.aspectBeanName);
    }
}
