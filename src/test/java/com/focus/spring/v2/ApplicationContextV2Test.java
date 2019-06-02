package com.focus.spring.v2;

import com.focus.spring.service.v2.impl.PetStoreServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextV2Test {


    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config-v2.xml");
        //根据获取出来的ClassName实例化实体类
        PetStoreServiceImpl petStoreService = (PetStoreServiceImpl) ctx.getBean("petStore");

        //验证是否实例化成功
        org.junit.Assert.assertNotNull(petStoreService);

        org.junit.Assert.assertNotNull(petStoreService.getAccountDao());

        org.junit.Assert.assertNotNull(petStoreService.getItemDao());

        org.junit.Assert.assertEquals("alongso",petStoreService.getOwner());

        org.junit.Assert.assertEquals(2, petStoreService.getVersion().intValue());

        Assert.assertEquals(true,petStoreService.isFlag());

    }


}
