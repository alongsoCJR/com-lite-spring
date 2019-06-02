package com.focus.spring.v1;

import com.focus.spring.service.v1.IPetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @Author chenjianrong-lhq
 * @Description BeanFactory测试类，测试详细的属性，比如bean的scope属性,另外还测试了创建bean和xml文件不存在异常
 * @Date 2019-06-01 15:08
 **/
public class BeanFactoryGetBeanDetailsTest {

    DefaultListableBeanFactory factory = null;

    XmlBeanDefinitionReader registry = null;

    Resource resource = null;

    @Before
    public void setUp() {
        /** 新建BeanFactory实例，
         *      注意ClassPathXmlApplicationContext将其封装起来了**/
        factory = new DefaultListableBeanFactory();

        /** 这里运用了依赖倒置原则，采取抽离接口的方式，将高组件注入到低组件当中
         *  依赖倒置原则：
         *      1.高层模块不应该依赖于低层模块，二者都应该依赖于抽象
         *      2.抽象不应该依赖于细节，细节应该依赖于抽象
         **/
        registry = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void getBean() {
        /** 加载xml配置文件，获取类信息资源类（获取字节流） **/
        resource = new ClassPathResource("spring-config-v1.xml");

        /** 装配类信息资源类，将beanDefilition注册进BeanFactory中 **/
        registry.loadBeanDefinitions(resource);

        /** 通过BeanFactory获取beanDefilition**/
        BeanDefinition db = factory.getBeanDefinition("petStore");

        db.setScope(BeanDefinition.SCOPE_PROTOTYPE);

        /** 调用BeanFactory的getBean方法，获取实例化的bean**/
        IPetStoreService petStoreService = (IPetStoreService) factory.getBean("petStore");
        IPetStoreService petStoreService2 = (IPetStoreService) factory.getBean("petStore");

        Assert.assertEquals(BeanDefinition.SCOPE_PROTOTYPE, db.getScope());

        Assert.assertNotNull(petStoreService);

        /** 实例调用equals方法先去看类是否重写了equals方法，如果没有，则比较的是地址引用**/
        Assert.assertFalse(petStoreService.equals(petStoreService2));

        Assert.assertTrue(db.isPrototype());

        Assert.assertFalse(db.isSingleton());
    }

    @Test
    public void testInvalidBean() {
        resource = new ClassPathResource("spring-config-v1.xml");
        registry.loadBeanDefinitions(resource);
        try {
            factory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }
        Assert.fail("except BeanCreationException");
    }

    @Test
    public void testInvalidXML() {
        try {
            resource = new ClassPathResource("config-v1.xml");
            registry.loadBeanDefinitions(resource);
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("except BeanDefinitionStoreException");
    }

}
