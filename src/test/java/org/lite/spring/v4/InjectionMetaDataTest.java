package org.lite.spring.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.annotation.AutowiredFieldElement;
import org.litespring.beans.factory.annotation.InjectionElement;
import org.litespring.beans.factory.annotation.InjectionMetadata;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.dao.v4.AccountDao;
import org.litespring.dao.v4.ItemDao;
import org.litespring.service.v4.impl.PetStoreServiceImpl;

import java.lang.reflect.Field;
import java.util.LinkedList;

/**
 * eigth Test,InjectionMetaData的抽象，将实例set进我们的目标类当中
 * 这里有一个很好的设计【抽离出InjectionElement抽象类，面向接口编程，不要面向实现编程】
 * 注意：InjectionMetaData类是对DependencyDescriptor类的封装
 */
public class InjectionMetaDataTest {

    @Test
    public void testInjection() throws Exception {

        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinations(resource);

        Class<?> clz = PetStoreServiceImpl.class;
        LinkedList<InjectionElement> elements = new LinkedList<InjectionElement>();

        // 下次测试，应该是获取@autowired注释的集合，这里指的是accountDao，itemDao
        {
            Field f = PetStoreServiceImpl.class.getDeclaredField("accountDao");
            InjectionElement injectionElem = new AutowiredFieldElement(f, true, factory);
            elements.add(injectionElem);
        }
        {
            Field f = PetStoreServiceImpl.class.getDeclaredField("itemDao");
            InjectionElement injectionElem = new AutowiredFieldElement(f, true, factory);
            elements.add(injectionElem);
        }

        InjectionMetadata metadata = new InjectionMetadata(clz, elements);

        // 这是new的一个bean，设置属性的inject方法在后面执行
        PetStoreServiceImpl petStore = new PetStoreServiceImpl();

        metadata.inject(petStore);

        Assert.assertTrue(petStore.getAccountDao() instanceof AccountDao);

        Assert.assertTrue(petStore.getItemDao() instanceof ItemDao);

    }
}

