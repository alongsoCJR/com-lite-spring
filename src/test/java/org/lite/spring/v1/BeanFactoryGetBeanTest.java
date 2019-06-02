package org.lite.spring.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.service.v1.IPetStoreService;

public class BeanFactoryGetBeanTest {
    DefaultBeanFactory factory = null;

    XmlBeanDefinitionReader registry = null;

    Resource resource = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        registry = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void testGetBean() {
        resource = new ClassPathResource("petstore-v1.xml");
        registry.loadBeanDefinations(resource);
        //从hashMap中根据beanId获取相应的BeanDefination对象，这也是要把BeanDefination设置成接口的原因
        BeanDefinition db = factory.getBeanDefinition("petStore");
        //验证xml文件中的class属性
        Assert.assertEquals("org.litespring.service.v1.impl.PetStoreServiceImpl", db.getBeanClassName());
        //根据获取出来的ClassName实例化实体类
        IPetStoreService petStoreService = (IPetStoreService) factory.getBean("petStore");
        //验证是否实例化成功
        Assert.assertNotNull(petStoreService);
    }
}
