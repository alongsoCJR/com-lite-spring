package org.litespring.test.v3;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v3.impl.PetStoreServiceImpl;

/**
 * @author chenjianrong-lhq 2019年02月24日 19:49:59
 * @Description: ApplicationContextTest
 * @ClassName: ApplicationContextTest
 */
public class ApplicationContextV3Test {
    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v3.xml");

        //根据获取出来的ClassName实例化实体类
        PetStoreServiceImpl petStoreService = (PetStoreServiceImpl) ctx.getBean("petStore");

        //验证是否实例化成功
        Assert.assertNotNull(petStoreService);

        Assert.assertNotNull(petStoreService.getAccountDao());

        Assert.assertNotNull(petStoreService.getItemDao());

        Assert.assertEquals(1, petStoreService.getValue());

    }
}
