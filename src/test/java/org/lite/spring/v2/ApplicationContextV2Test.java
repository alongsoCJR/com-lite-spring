package org.lite.spring.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v1.impl.PetStoreServiceImpl;

/**
 * @author chenjianrong-lhq 2019年02月24日 19:49:59
 * @Description: ApplicationContextV1Test
 * @ClassName: ApplicationContextV1Test
 */
public class ApplicationContextV2Test {
    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v2.xml");
        //根据获取出来的ClassName实例化实体类
        PetStoreServiceImpl petStoreService = (PetStoreServiceImpl) ctx.getBean("petStore");

        //验证是否实例化成功
        Assert.assertNotNull(petStoreService);

        Assert.assertNotNull(petStoreService.getAccountDao());

        Assert.assertNotNull(petStoreService.getItemDao());

        Assert.assertEquals("alongso",petStoreService.getOwner());

        Assert.assertEquals(2, petStoreService.getVersion().intValue());

        Assert.assertEquals(true,petStoreService.isFlag());

    }
}
