package org.lite.spring.v5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.aop.aspectj.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectj.AspectJBeforeAdvice;
import org.litespring.aop.aspectj.AspectJExpressionPointcut;
import org.litespring.aop.config.AspectInstanceFactory;
import org.litespring.aop.framework.AopConfig;
import org.litespring.aop.framework.AopConfigException;
import org.litespring.aop.framework.AopConfigSupport;
import org.litespring.aop.framework.CglibProxyFactory;
import org.litespring.factory.BeanFactory;
import org.litespring.service.v5.impl.PetStoreServiceImpl;
import org.litespring.tx.manager.TransactionManager;
import org.litespring.util.MessageTracker;

import java.util.List;

/**
 * @author chenjianrong-lhq 2019年06月09日 09:49:34
 * @Description: Test6:ReflectiveMethodInvocation，DynamicAdvisedInterceptor的封装,AopConfig的抽象，CglibProxyFactory的抽象
 * @ClassName: CglibAopProxyTest
 */
public class CglibAopProxyTest extends AbstraceV5Test {


    private static AspectJBeforeAdvice beforeAdvice = null;
    private static AspectJAfterReturningAdvice afterAdvice = null;
    private static AspectJExpressionPointcut pc = null;
    private static AspectInstanceFactory aspectInstanceFactory = null;
    private static BeanFactory beanFactory = null;

    private TransactionManager tx;

    @Before
    public void setUp() throws Exception {
        MessageTracker.clearMsgs();


        String expression = "execution(* org.litespring.service.v5.impl..placeOrder(..))";
        pc = new AspectJExpressionPointcut();
        beanFactory = super.getBeanFactory("petstore-v5.xml");
        aspectInstanceFactory = super.getAspectInstanceFactory("tx");
        aspectInstanceFactory.setBeanFactory(beanFactory);

        pc.setExpression(expression);
        beforeAdvice = new AspectJBeforeAdvice(
                TransactionManager.class.getMethod("start"),
                pc,
                aspectInstanceFactory);

        afterAdvice = new AspectJAfterReturningAdvice(
                TransactionManager.class.getMethod("commit"),
                pc,
                aspectInstanceFactory);

    }

    @Test
    public void testGetProxy() throws AopConfigException {

        AopConfig config = new AopConfigSupport();

        config.addAdvice(beforeAdvice);
        config.addAdvice(afterAdvice);
        config.setTargetObject(new PetStoreServiceImpl());


        CglibProxyFactory proxyFactory = new CglibProxyFactory(config);

        PetStoreServiceImpl proxy = (PetStoreServiceImpl) proxyFactory.getProxy();

        proxy.placeOrder();


        List<String> msgList = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgList.size());
        Assert.assertEquals("tx start!", msgList.get(0));
        Assert.assertEquals("PetStoreServiceImpl.placeOrder", msgList.get(1));
        Assert.assertEquals("tx commit!", msgList.get(2));

        proxy.toString();
    }

}
