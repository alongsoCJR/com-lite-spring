package com.focus.spring.v4;

import com.focus.spring.config.ComponentConfig;
import com.focus.spring.service.v4.impl.PetStoreServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 采用注解的方式对包进行扫描，配置扫描包的信息在配置类上，
 * 配置类必须有@Configuration@ComponentScan标识
 * 在我们实际开发过程中，我们有使用到@RunWith和@ContextConfiguration这两个注解
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ComponentConfig.class)
public class ApplicationContextV4JunitTest {

    @Autowired
    PetStoreServiceImpl petStore;

    @Test
    public void testGetBean() {
        //验证是否实例化成功
        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
    }

}
