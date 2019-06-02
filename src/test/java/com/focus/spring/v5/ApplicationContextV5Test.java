package com.focus.spring.v5;

import com.focus.spring.service.v5.JavaConfig;
import com.focus.spring.service.v5.impl.PetStoreServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = JavaConfig.class)
public class ApplicationContextV5Test {


    @Autowired
    PetStoreServiceImpl petStore;

    @Test
    public void testGetBean() {
        //验证是否实例化成功
        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
    }
}
