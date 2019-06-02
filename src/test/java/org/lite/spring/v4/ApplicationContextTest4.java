package org.lite.spring.v4;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.context.ApplicationContext;
import org.litespring.context.support.ClassPathXmlApplicationContext;
import org.litespring.service.v4.impl.PetStoreServiceImpl;

/**
 * first Test
 * 采用TDD测试驱动开发的方法，这是一个整体的测试案例。你会发现，所有的版本都会有这个测试类
 * 以及该包下的其他测试案例都是为了它的实现而展开的。
 * 写测试类-->运行不通过-->代码的抽象，写代码使之通过-->代码的封装以及重构-->进行下一轮新的测试循环
 * 该类的测试包含两部分：
 * (1)@Component的实现，将扫描包下的所有bean加入到容器中来
 * (2)@Autowired的实现，给注释所在bean的属性赋值（从spring容器中找到bean）
 *
 */
public class ApplicationContextTest4 {

    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v4.xml");
        //根据获取出来的ClassName实例化实体类
        PetStoreServiceImpl petStoreService = (PetStoreServiceImpl) ctx.getBean("petStore");
        //验证是否实例化成功
        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());
    }


}
