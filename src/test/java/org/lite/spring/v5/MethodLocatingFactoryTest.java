package org.lite.spring.v5;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.aop.config.MethodLocatingFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.tx.manager.TransactionManager;

import java.lang.reflect.Method;

/**
 * @author chenjianrong-lhq 2019年06月03日 22:19:48
 * @Description: 重头戏还没有出来哦
 * @ClassName: MethodLocatingFactoryTest
 */
public class MethodLocatingFactoryTest {

    @Test
    public void testMethodLocatingFactory() throws NoSuchMethodException {
        Resource resource = new ClassPathResource("petstore-v5.xml");

        DefaultBeanFactory factory = new DefaultBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinations(resource);

        MethodLocatingFactory methodLocatingFactory = new MethodLocatingFactory();
        methodLocatingFactory.setTargetBeanName("tx");

        /** 在下一步中，方法的集合应该放在哪里**/
        methodLocatingFactory.setMethodName("start");
        methodLocatingFactory.setBeanFatory(factory);

        /** 根据方法的执行实例，找到方法对象**/
        Method m = methodLocatingFactory.getObject();

        Assert.assertTrue(TransactionManager.class.equals(m.getDeclaringClass()));
        Assert.assertTrue(m.equals(TransactionManager.class.getMethod("start")));


    }
}
