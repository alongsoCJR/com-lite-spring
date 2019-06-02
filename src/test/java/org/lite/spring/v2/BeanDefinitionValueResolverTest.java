package org.lite.spring.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;
import org.litespring.beans.factory.support.BeanDefinitionValueResolver;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.dao.v2.AccountDao;

/**
 * @author chenjianrong-lhq 2019年03月03日 19:20:05
 * @Description:
 * @ClassName: BeanDefinitionValueResolverTest
 */
public class BeanDefinitionValueResolverTest {

    @Test
    public void testBDValueResolver() {
        Resource resource = new ClassPathResource("petstore-v2.xml");

        DefaultBeanFactory factory = new DefaultBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinations(resource);

        BeanDefinitionValueResolver resolver=new BeanDefinitionValueResolver(factory);

        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");

        Object object=resolver.resolveValueIfNecessry(reference);

        Assert.assertNotNull(object);

        Assert.assertTrue(object instanceof AccountDao);

//        BeanDefinition definition = factory.getBeanDefinition("petStore");
//
//        List<PropertyValue> pvs = definition.getPropertyValues();
//
//        for (PropertyValue pv : pvs) {
//            Object val = pv.getValue();
//            if (val instanceof RuntimeBeanReference) {
//                String beanName = ((RuntimeBeanReference)(val)).getBeanName();
//                System.out.println(factory.getBean(beanName));
//            }
//
//            if(val instanceof TypedStringValue){
//                String value = ((TypedStringValue)(val)).getValue();
//                System.out.println(value);
//            }
//        }
    }

    @Test
    public void testBDStringResolver() {
        Resource resource = new ClassPathResource("petstore-v2.xml");

        DefaultBeanFactory factory = new DefaultBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);

        reader.loadBeanDefinations(resource);

        BeanDefinitionValueResolver resolver=new BeanDefinitionValueResolver(factory);

        TypedStringValue reference = new TypedStringValue("enVal");

        Object object=resolver.resolveValueIfNecessry(reference);

        Assert.assertNotNull(object);

        Assert.assertTrue(object instanceof String);
    }
}
