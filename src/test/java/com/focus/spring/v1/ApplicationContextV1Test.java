package com.focus.spring.v1;

import com.focus.spring.service.v1.IPetStoreService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author chenjianrong-lhq
 * @Description 通过XML方式装配bean, 创建一个简单的bean，注意ClassPathXmlApplicationContext是对所有准备工作的封装，它的代码量非常大
 * @Date 2019-06-01 12:24
 **/
public class ApplicationContextV1Test {

    @Test
    public void testGetBean() {
        /** 创建ClassPathXmlApplicationContext实例，传入配置文件的文件名**/
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-config-v1.xml");

        /** 调用getBean方法获取javaBean实例**/
        IPetStoreService petStoreService = (IPetStoreService) ctx.getBean("petStore");

        /** 验证是否实例化成功**/
        Assert.assertNotNull(petStoreService);

        /** 调用实例化bean的方法**/
        petStoreService.sayHello();
    }
}
