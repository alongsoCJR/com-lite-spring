package org.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.service.v3.impl.PetStoreServiceImpl;

import java.util.List;

/**
 * @author chenjianrong-lhq 2019年03月03日 19:18:46
 * @Description:
 * @ClassName: BeanFactoryV2Test
 */
public class BeanFactoryV3Test {

    @Test
    public void testBeanDefilition() {
        Resource resource = new ClassPathResource("petstore-v3.xml");

        DefaultBeanFactory factory = new DefaultBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinations(resource);

        PetStoreServiceImpl petStoreService =(PetStoreServiceImpl)factory.getBean("petStore");

        BeanDefinition definition = factory.getBeanDefinition("petStore");

        Assert.assertEquals("org.litespring.service.v3.impl.PetStoreServiceImpl",definition.getBeanClassName());

        ConstructorArgument arguments=definition.getConstructorArgument();

        List<ConstructorArgument.ValueHolder> valueHolders =arguments.getArgumentValues();

        Assert.assertEquals(3,valueHolders.size());


        RuntimeBeanReference ref1=(RuntimeBeanReference)valueHolders.get(0).getValue();
        Assert.assertEquals("accountDao",ref1.getBeanName());


        RuntimeBeanReference ref2=(RuntimeBeanReference)valueHolders.get(1).getValue();
        Assert.assertEquals("itemDao",ref2.getBeanName());


        TypedStringValue ref3=(TypedStringValue)valueHolders.get(2).getValue();
        Assert.assertEquals("1",ref3.getValue());
    }


}
