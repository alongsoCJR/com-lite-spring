package com.focus.spring.v3;

import com.focus.spring.service.v3.impl.PetStoreServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class ApplicationContextV3Test {


    @Test
    public void getBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config-v3.xml");

        //根据获取出来的ClassName实例化实体类
        PetStoreServiceImpl petStoreService = (PetStoreServiceImpl) ctx.getBean("petStore");

        //验证是否实例化成功
        org.junit.Assert.assertNotNull(petStoreService);

        org.junit.Assert.assertNotNull(petStoreService.getAccountDao());

        org.junit.Assert.assertNotNull(petStoreService.getItemDao());

        Assert.assertEquals(1, petStoreService.getValue());

    }

}
