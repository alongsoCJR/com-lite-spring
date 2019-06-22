package org.lite.spring.v5;

import org.litespring.aop.config.AspectInstanceFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.factory.BeanFactory;
import org.litespring.service.v5.impl.PetStoreServiceImpl;

import java.lang.reflect.Method;

/**
 * @author chenjianrong-lhq 2019年06月15日 15:59:32
 * @Description: 对于测试案例中重复的代码，我们可以抽取出一个抽象测试类
 * @ClassName: AbstraceV5Test
 */
public class AbstraceV5Test {

    protected BeanFactory getBeanFactory(String configFile) {
        Resource resource = new ClassPathResource(configFile);
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinations(resource);
        return factory;
    }

    protected Method getAdviceMethod(String methodName) throws NoSuchMethodException {
        return PetStoreServiceImpl.class.getMethod(methodName);
    }

    protected AspectInstanceFactory getAspectInstanceFactory(String targetBeanName) {
        AspectInstanceFactory aspectInstanceFactory = new AspectInstanceFactory();
        aspectInstanceFactory.setAspectBeanName(targetBeanName);
        return aspectInstanceFactory;
    }
}
