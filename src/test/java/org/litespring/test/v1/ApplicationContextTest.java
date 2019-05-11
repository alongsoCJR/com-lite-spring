package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.context.support.FileSystemXmlApplicationContext;
import org.litespring.service.v1.IPetStoreService;
import org.litespring.util.ClassUtils;

/**
 * @author chenjianrong-lhq 2019年02月24日 19:49:59
 * @Description: ApplicationContextTest
 * @ClassName: ApplicationContextTest
 */
public class ApplicationContextTest {
    @Test
    public void testGetBean(){
        ApplicationContext ctx=new ClassPathXmlApplicationContext("petstore-v1.xml");
        //根据获取出来的ClassName实例化实体类
        IPetStoreService petStoreService =(IPetStoreService)ctx.getBean("petStore");
        //验证是否实例化成功
        Assert.assertNotNull(petStoreService);
        petStoreService.sayHello();
    }


    @Test
    public  void testFileSystemResource() throws Exception{
        ApplicationContext ctx=new FileSystemXmlApplicationContext("src/test/resource/petstore-v1.xml", ClassUtils.getDefaultClassLoader());
        //根据获取出来的ClassName实例化实体类
        IPetStoreService petStoreService =(IPetStoreService)ctx.getBean("petStore");
        //验证是否实例化成功
        Assert.assertNotNull(petStoreService);
    }
}
