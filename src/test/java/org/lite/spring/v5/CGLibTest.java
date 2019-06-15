package org.lite.spring.v5;

import org.junit.Test;
import org.litespring.service.v5.impl.PetStoreServiceImpl;
import org.litespring.tx.manager.TransactionManager;
import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @author chenjianrong-lhq 2019年06月09日 09:26:49
 * @Description: Test5:使用CGLIB获取增强类，定义拦截器，使用拦截器对方法进行拦截，在方法执行前后修改调用次序
 * @ClassName: CGLibTest
 */
public class CGLibTest {

    @Test
    public void testCallBack() {

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(PetStoreServiceImpl.class);

        enhancer.setCallback(new TransactionInterceptor());
        PetStoreServiceImpl petStore = (PetStoreServiceImpl) enhancer.create();
        petStore.placeOrder();

    }


    public static class TransactionInterceptor implements MethodInterceptor {
        TransactionManager txManager = new TransactionManager();

        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

            txManager.start();
            Object result = proxy.invokeSuper(obj, args);
            txManager.commit();
            return result;
        }

    }


    /**
     * @return void
     * @Author chenjianrong-lhq
     * @Description 过滤掉一些不需要执行的方法
     * @Date 2019-06-10 10:03
     * @Param []
     **/
    @Test
    public void testFilter() {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(PetStoreServiceImpl.class);

        enhancer.setInterceptDuringConstruction(false);

        Callback[] callbacks = new Callback[]{new TransactionInterceptor(), NoOp.INSTANCE};

        Class<?>[] types = new Class<?>[callbacks.length];
        for (int x = 0; x < types.length; x++) {
            types[x] = callbacks[x].getClass();
        }

        /** 设置方法的过滤器，对于一些方法调用的时候不需要执行切面的代码**/
        enhancer.setCallbackFilter(new ProxyCallbackFilter());

        /** 设置拦截器数组**/
        enhancer.setCallbacks(callbacks);

        enhancer.setCallbackTypes(types);

        PetStoreServiceImpl petStore = (PetStoreServiceImpl) enhancer.create();
        petStore.placeOrder();
        System.out.println(petStore.toString());

    }

    private static class ProxyCallbackFilter implements CallbackFilter {

        public ProxyCallbackFilter() {

        }

        public int accept(Method method) {
            if (method.getName().startsWith("place")) {
                return 0;
            } else {
                return 1;
            }

        }
    }
}
