package org.lite.spring.v6;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v6.IPetStoreService;
import org.litespring.util.MessageTracker;

import java.util.List;

/**
 * @Author chenjianrong-lhq
 * @Description Test1:JDK动态代理的实现，针对接口的proxy，完成JdkAopProxyFactory的抽象
 * @Date 2019-06-10 09:21
 **/
public class ApplicationContextV6Test {

    @Before
    public void clearMsg() {
        MessageTracker.clearMsgs();
    }

    @Test
    public void testPlaceHoder() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v6.xml");
        /** 根据获取出来的ClassName实例化实体类**/
        IPetStoreService petStoreService = (IPetStoreService) ctx.getBean("petStore");

        /** 通过接口调用方法**/
        petStoreService.placeOrder();

        /** 怎么做断言**/
        List<String> msgList = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgList.size());
        Assert.assertEquals("tx start!", msgList.get(0));
        Assert.assertEquals("PetStoreServiceImpl.placeOrder", msgList.get(1));
        Assert.assertEquals("tx commit!", msgList.get(2));
    }

}