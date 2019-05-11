package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.factory.BeanCreationException;
import org.litespring.factory.BeanDefinitionException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.service.v1.IPetStoreService;

/**
 * @author chenjianrong-lhq 2019年02月23日 21:43:19
 * @Description: BeanFactory测试类
 * @ClassName: BeanFactoryTest
 */
public class BeanFactoryTest {

//    @Test
//    public void testGetBean(){
//        //通过构造器的方式载入bean的配置文件，将xml文件bean的信息放入到HashMap中，hashMap中存放的是beanId-GenericBeanFactory形式
//        BeanFactory factory=new DefaultBeanFactory("petstore-v1.xml");
//        //从hashMap中根据beanId获取相应的BeanDefination对象，这也是要把BeanDefination设置成接口的原因
//        BeanDefinition db=factory.getBeanDefinition("petStore");
//        //验证xml文件中的class属性
//        Assert.assertEquals("IPetStoreService",db.getBeanClassName());
//        //根据获取出来的ClassName实例化实体类
//        IPetStoreService petStoreService=(IPetStoreService)factory.getBean("petStore");
//        //验证是否实例化成功
//        Assert.assertNotNull(petStoreService);
//    }
//
//    @Test
//    public void testInvalidBean(){
//        BeanFactory factory=new DefaultBeanFactory("petstore-v1.xml");
//        try{
//            factory.getBean("invalidBean");
//        }catch (BeanCreationException e){
//            System.out.println("捕获BeanCreationException");
//            return;
//        }
//        Assert.fail("except BeanCreationException");
//    }
//
//    @Test
//    public void testInvalidXML(){
//        try{
//            new DefaultBeanFactory("v1.xml");
//        }catch (BeanDefinitionException e){
//            System.out.println("捕获BeanDefinitionException");
//            return;
//        }
//        Assert.fail("except BeanDefinitionException");
//    }

    DefaultBeanFactory factory=null;

    XmlBeanDefinitionReader registry=null;

    Resource resource=null;

    @Before
    public void setUp(){
        factory=new DefaultBeanFactory();
        registry=new XmlBeanDefinitionReader(factory);
    }

//    @Test
//    public void testReviewCode(){
//        //新建一个DefaultBeanFactory,实现了BeanFactory和BeanDefinitionRegistry接口，三个方法。
//        DefaultBeanFactory factory=new DefaultBeanFactory();
//        //XmlBeanDefinitionReader组合了BeanDefinitionRegistry的实现类，利用实现类的registerBeanDefinition()方法
//        XmlBeanDefinitionReader registry=new XmlBeanDefinitionReader(factory);
//        //运行XmlBeanDefinitionReader的loadBeanDefination()方法
//        registry.loadBeanDefinations("petstore-v1.xml");
//        //从hashMap中根据beanId获取相应的BeanDefination对象
//        BeanDefinition db=factory.getBeanDefinition("petStore");
//        //验证xml文件中的class属性
//        Assert.assertEquals("IPetStoreService",db.getBeanClassName());
//        //根据获取出来的ClassName实例化实体类
//        IPetStoreService petStoreService=(IPetStoreService)factory.getBean("petStore");
//        //验证是否实例化成功
//        Assert.assertNotNull(petStoreService);
//    }
//
    @Test
    public void getBean(){
        resource=new ClassPathResource("petstore-v1.xml");
        registry.loadBeanDefinations(resource);

        BeanDefinition db=factory.getBeanDefinition("petStore");

        db.setScope(BeanDefinition.SCOPE_PROTOTYPE);

        Assert.assertFalse(db.isSingleton());

        Assert.assertTrue(db.isPrototype());

        Assert.assertEquals(BeanDefinition.SCOPE_PROTOTYPE,db.getScope());

        Assert.assertEquals("org.litespring.service.v1.impl.PetStoreServiceImpl",db.getBeanClassName());

        IPetStoreService petStoreService =(IPetStoreService)factory.getBean("petStore");

        Assert.assertNotNull(petStoreService);

        IPetStoreService petStoreService2 =(IPetStoreService)factory.getBean("petStore");

        Assert.assertFalse(petStoreService.equals(petStoreService2));

        petStoreService.sayHello();
    }

    @Test
    public void testInvalidBean(){
        resource=new ClassPathResource("petstore-v1.xml");
        registry.loadBeanDefinations(resource);
        try{
            factory.getBean("invalidBean");
        }catch (BeanCreationException e){
            System.out.println("捕获BeanCreationException");
            return;
        }
        Assert.fail("except BeanCreationException");
    }

    @Test
    public void testInvalidXML(){
        try{
            resource=new ClassPathResource("v1.xml");
            registry.loadBeanDefinations(resource);
        }catch (BeanDefinitionException e){
            System.out.println("捕获BeanDefinitionException");
            return;
        }
        Assert.fail("except BeanDefinitionException");
    }


}
