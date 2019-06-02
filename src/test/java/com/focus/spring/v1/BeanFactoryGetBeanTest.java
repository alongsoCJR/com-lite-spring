package com.focus.spring.v1;

import com.focus.spring.service.v1.IPetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * @Author chenjianrong-lhq
 * @Description 通过新建BeanFactory实例，装载bean，注意ClassPathXmlApplicationContext是对其的封装
 * @Date 2019-06-01 14:59
 **/
public class BeanFactoryGetBeanTest {

    DefaultListableBeanFactory factory = null;

    XmlBeanDefinitionReader reader = null;

    Resource resource = null;

    @Before
    public void setUp() {
        /** 新建BeanFactory实例**/
        factory = new DefaultListableBeanFactory();

        /** 这里运用了依赖倒置原则，采取抽离接口的方式，将高组件注入到低组件当中
         *  依赖倒置原则：
         *      1.高层模块不应该依赖于低层模块，二者都应该依赖于抽象
         *      2.抽象不应该依赖于细节，细节应该依赖于抽象
         **/
        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void testGetBean() {
        /** 加载xml配置文件，获取类信息资源类（获取字节流信息） **/
        resource = new ClassPathResource("spring-config-v1.xml");

        /** 装配类信息资源类，将beanDefilition注册进BeanFactory中 **/
        reader.loadBeanDefinitions(resource);

        /** 通过BeanFactory获取beanDefilition**/
        BeanDefinition db = factory.getBeanDefinition("petStore");

        /** 验证xml文件中的class属性**/
        Assert.assertEquals("com.focus.spring.service.v1.impl.PetStoreServiceImpl", db.getBeanClassName());

        /** 调用BeanFactory的getBean方法，获取实例化的bean**/
        IPetStoreService petStoreService = (IPetStoreService) factory.getBean("petStore");

        /** 验证是否实例化成功**/
        Assert.assertNotNull(petStoreService);
    }
}
