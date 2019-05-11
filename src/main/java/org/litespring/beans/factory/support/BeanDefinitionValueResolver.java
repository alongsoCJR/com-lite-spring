package org.litespring.beans.factory.support;

import org.litespring.beans.factory.config.ConfigableBeanFactory;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.factory.BeanFactory;

/**
 * @author chenjianrong-lhq 2019年03月03日 19:37:31
 * @Description:
 * @ClassName: BeanDefinitionValueResolver
 */
public class BeanDefinitionValueResolver {
    //这里为什么使用final修饰符
    private final BeanFactory beanFactory;

    public BeanDefinitionValueResolver(ConfigableBeanFactory beanFactory){
        this.beanFactory=beanFactory;
    }

    public Object resolveValueIfNecessry(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference reference=(RuntimeBeanReference)value;
            String beanName = reference.getBeanName();
            Object object=beanFactory.getBean(beanName);
            //pv.setConvertedValue(object);
            return object;
        }else if(value instanceof TypedStringValue){
            TypedStringValue reference=(TypedStringValue)value;
            String val = reference.getValue();
            return val;
        }else{
            //TO DO
            throw new RuntimeException("the value "+value+" has not implemented!");
        }
    }
}
