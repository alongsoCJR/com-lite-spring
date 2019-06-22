package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.BeansException;
import org.litespring.beans.factory.FactoryBean;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.factory.BeanCreationException;

/**
 * @author chenjianrong-lhq 2019年03月03日 19:37:31
 * @Description:
 * @ClassName: BeanDefinitionValueResolver
 */
public class BeanDefinitionValueResolver {
    //这里为什么使用final修饰符
    private final AbstractBeanFactory beanFactory;

    public BeanDefinitionValueResolver(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object resolveValueIfNecessry(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference reference = (RuntimeBeanReference) value;
            String beanName = reference.getBeanName();
            Object object = beanFactory.getBean(beanName);
            //pv.setConvertedValue(object);
            return object;
        } else if (value instanceof TypedStringValue) {
            TypedStringValue reference = (TypedStringValue) value;
            String val = reference.getValue();
            return val;
        } else if (value instanceof BeanDefinition) {
            BeanDefinition beanDefinition = (BeanDefinition) value;
            String innerBeanName = "(inner bean)" + beanDefinition.getBeanClassName() + "#" + Integer.toHexString(System.identityHashCode(beanDefinition));
            return resolveInnerBean(innerBeanName, beanDefinition);
        } else {
            //TO DO
            return value;
//            throw new RuntimeException("the value " + value + " has not implemented!");
        }
    }


    private Object resolveInnerBean(String innerBeanName, BeanDefinition beanDefinition) {

        try {

            Object innerBean = this.beanFactory.createBean(beanDefinition);

            if (innerBean instanceof FactoryBean) {
                try {
                    return ((FactoryBean<?>) innerBean).getObject();
                } catch (Exception e) {
                    throw new BeanCreationException(innerBeanName, "FactoryBean threw exception on object creation", e);
                }
            } else {
                return innerBean;
            }
        } catch (BeansException ex) {
            throw new BeanCreationException(
                    innerBeanName,
                    "Cannot create inner bean '" + innerBeanName + "' " +
                            (beanDefinition != null && beanDefinition.getBeanClassName() != null ? "of type [" + beanDefinition.getBeanClassName() + "] " : "")
                    , ex);
        }
    }

}
