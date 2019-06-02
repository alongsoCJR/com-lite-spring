package org.lite.spring.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.factory.config.DependencyDescriptor;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.dao.v4.AccountDao;
import org.litespring.service.v4.impl.PetStoreServiceImpl;

import java.lang.reflect.Field;

/**
 * seventh Test,抽象出新的接口,完成DependencyDescriptor的抽象,获取属性的实例对象
 * 注意：在接下来的测试中，扫描类是如何找到将加了@Autowired注释的属性集合的？
 * DependencyDescriptor代表与扫描类的依赖关系描述
 */
public class DependencyDescriptorTest {


    @Test
    public void testDependencyDescription() throws NoSuchFieldException {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = new ClassPathResource("petstore-v4.xml");
        reader.loadBeanDefinations(resource);

        //"accountDao"从哪里而来，是不是也得通过对字节码的解析找到@autowired注释,按照理解这里应该是一个集合/数组吧
        Field field = PetStoreServiceImpl.class.getDeclaredField("accountDao");
        DependencyDescriptor dependencyDescriptor = new DependencyDescriptor(field, true);

        Object o = factory.resolveDependency(dependencyDescriptor);
        Assert.assertNotNull(o);
        Assert.assertTrue(o instanceof AccountDao);


    }
}
