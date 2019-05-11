package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.dao.v2.AccountDao;
import org.litespring.dao.v2.ItemDao;
import org.litespring.service.v1.impl.PetStoreServiceImpl;

import java.util.List;

/**
 * @author chenjianrong-lhq 2019年03月03日 19:18:46
 * @Description:
 * @ClassName: BeanFactoryV2Test
 */
public class BeanFactoryV2Test {

    @Test
    public void testBeanDefilition() {
        Resource resource = new ClassPathResource("petstore-v2.xml");

        DefaultBeanFactory factory = new DefaultBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinations(resource);

        PetStoreServiceImpl petStoreService =(PetStoreServiceImpl)factory.getBean("petStore");

        BeanDefinition definition = factory.getBeanDefinition("petStore");

        List<PropertyValue> pvs = definition.getPropertyValues();

        Assert.assertTrue(pvs.size() == 5);
        {
            PropertyValue pv=this.getPropertyValue("accountDao",pvs);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);

            String beanName=((RuntimeBeanReference)(pv.getValue())).getBeanName();

            petStoreService.setAccountDao((AccountDao) factory.getBean(beanName));

            Assert.assertNotNull(petStoreService.getAccountDao());

        }

        {
            PropertyValue pv=this.getPropertyValue("itemDao",pvs);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof RuntimeBeanReference);

            String beanName=((RuntimeBeanReference)(pv.getValue())).getBeanName();

            petStoreService.setItemDao((ItemDao) factory.getBean(beanName));

            Assert.assertNotNull(petStoreService.getItemDao());
        }

        {
            PropertyValue pv=this.getPropertyValue("owner",pvs);

            Assert.assertNotNull(pv);

            Assert.assertTrue(pv.getValue() instanceof TypedStringValue);

            Assert.assertEquals("alongso",((TypedStringValue)(pv.getValue())).getValue());
        }
    }

    private PropertyValue getPropertyValue(String name, List<PropertyValue> pvs) {
        for (PropertyValue pv:pvs) {
            if(name.equals(pv.getName())){
                return pv;
            }
        }
        return null;
    }
}
