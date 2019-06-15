package org.lite.spring.v5;


import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.aop.aspectj.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectj.AspectJAfterThrowingAdvice;
import org.litespring.aop.aspectj.AspectJBeforeAdvice;
import org.litespring.aop.framework.ReflectiveMethodInvocation;
import org.litespring.service.v5.impl.PetStoreServiceImpl;
import org.litespring.tx.manager.TransactionManager;
import org.litespring.util.MessageTracker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjianrong-lhq 2019年06月09日 07:06:41
 * @Description: Test4:proceed方法使用了递归,一个完美的方法，避开了before与after放置顺序的问题
 * @ClassName: ReflectiveMethodInvocationTest
 */
public class ReflectiveMethodInvocationTest {

    PetStoreServiceImpl petStoreService;

    TransactionManager tx;

    AspectJBeforeAdvice beforeAdvice;

    AspectJAfterReturningAdvice afterReturningAdvice;

    AspectJAfterThrowingAdvice afterThrowingAdvice;


    @Before
    public void setUp() throws NoSuchMethodException {
        petStoreService = new PetStoreServiceImpl();

        tx = new TransactionManager();

        MessageTracker.clearMsgs();

        beforeAdvice = new AspectJBeforeAdvice(TransactionManager.class.getMethod("start"), null, tx);

        afterReturningAdvice = new AspectJAfterReturningAdvice(TransactionManager.class.getMethod("commit"), null, tx);

        afterThrowingAdvice = new AspectJAfterThrowingAdvice(TransactionManager.class.getMethod("rollback"), null, tx);
    }


    @Test
    public void testReflectiveMethodInvocation() throws Throwable {
        Method targetMethod = PetStoreServiceImpl.class.getMethod("placeOrder");

        List<MethodInterceptor> interceptorList = new ArrayList<MethodInterceptor>();

        interceptorList.add(beforeAdvice);
        interceptorList.add(afterReturningAdvice);

        ReflectiveMethodInvocation reflectiveMethodInvocation = new ReflectiveMethodInvocation(petStoreService, targetMethod, new Object[0], interceptorList);

        reflectiveMethodInvocation.proceed();

        List<String> msgList = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgList.size());
        Assert.assertEquals("tx start!", msgList.get(0));
        Assert.assertEquals("PetStoreServiceImpl.placeOrder", msgList.get(1));
        Assert.assertEquals("tx commit!", msgList.get(2));
    }

    @Test
    public void testAfterThrowingMethodInvocation() throws Throwable {
        Method targetMethod = PetStoreServiceImpl.class.getMethod("placeOrderWithException");

        List<MethodInterceptor> interceptorList = new ArrayList<MethodInterceptor>();

        interceptorList.add(beforeAdvice);
        interceptorList.add(afterThrowingAdvice);

        ReflectiveMethodInvocation reflectiveMethodInvocation = new ReflectiveMethodInvocation(petStoreService, targetMethod, new Object[0], interceptorList);

        try {
            reflectiveMethodInvocation.proceed();
        } catch (Throwable throwable) {
            List<String> msgList = MessageTracker.getMsgs();
            Assert.assertEquals(2, msgList.size());
            Assert.assertEquals("tx start!", msgList.get(0));
            Assert.assertEquals("tx rollback!", msgList.get(1));
            return;
        }

        Assert.fail("No exception Throwing");
    }


    @Test
    public void testReverseReflectiveMethodInvocation() throws Throwable {
        Method targetMethod = PetStoreServiceImpl.class.getMethod("placeOrder");

        List<MethodInterceptor> interceptorList = new ArrayList<MethodInterceptor>();

        interceptorList.add(afterReturningAdvice);
        interceptorList.add(beforeAdvice);

        ReflectiveMethodInvocation reflectiveMethodInvocation = new ReflectiveMethodInvocation(petStoreService, targetMethod, new Object[0], interceptorList);

        reflectiveMethodInvocation.proceed();

        List<String> msgList = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgList.size());
        Assert.assertEquals("tx start!", msgList.get(0));
        Assert.assertEquals("PetStoreServiceImpl.placeOrder", msgList.get(1));
        Assert.assertEquals("tx commit!", msgList.get(2));
    }


}
