package org.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.support.ConstructorResolver;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.service.v3.impl.PetStoreServiceImpl;

/**
 * @author chenjianrong-lhq 2019年03月12日 22:44:49
 * @Description:
 * @ClassName: ConstructorResolverTest
 */
public class ConstructorResolverTest {

    @Test
    public void testConstructorResolver(){
        Resource resource = new ClassPathResource("petstore-v3.xml");

        DefaultBeanFactory factory = new DefaultBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinations(resource);

        BeanDefinition definition = factory.getBeanDefinition("petStore");

        ConstructorResolver resolver=new ConstructorResolver(factory);

        PetStoreServiceImpl petStoreService=(PetStoreServiceImpl)resolver.autowireContructor(definition);

        Assert.assertEquals(1,petStoreService.getValue());

        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());



    }
}
