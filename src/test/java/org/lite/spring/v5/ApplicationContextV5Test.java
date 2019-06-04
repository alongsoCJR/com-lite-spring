package org.lite.spring.v5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v5.impl.PetStoreServiceImpl;
import org.litespring.util.MessageTracker;

import java.util.List;

public class ApplicationContextV5Test {

    @Before
    public void clearMsg() {
        MessageTracker.clearMsgs();
    }

    @Test
    public void testPlaceHoder() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v5.xml");
        /** 根据获取出来的ClassName实例化实体类**/
        PetStoreServiceImpl petStoreService = (PetStoreServiceImpl) ctx.getBean("petStore");
        /** 验证是否实例化成功**/
        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());

        petStoreService.placeOrder();

        /** 怎么做断言**/
        List<String> msgList = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgList.size());
        Assert.assertEquals("tx start!", msgList.get(0));
        Assert.assertEquals("PetStoreServiceImpl.placeOrder", msgList.get(1));
        Assert.assertEquals("tx commit!", msgList.get(2));
    }

}