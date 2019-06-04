package org.lite.spring.v5;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.aop.MethodMatcher;
import org.litespring.aop.aspectj.AspectJExpressionPointcut;
import org.litespring.service.v5.impl.PetStoreServiceImpl;

import java.lang.reflect.Method;

/**
 * @author chenjianrong-lhq 2019年06月02日 22:03:43
 * @Description: 切面表达式抽象类测试类
 * @ClassName: PointCurTest
 */
public class PointCurTest {

    @Test
    public void testPointCut() throws NoSuchMethodException {

        /** 定义需添加切面所在包下所有方法的通用表达式**/
        String expression = "execution(* org.litespring.service.v5.impl..placeOrder(..))";

        /** 实例化一个与表达式相关的实体类**/
        AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);

        /** 获取一个方法的匹配器**/
        MethodMatcher mm = pc.getMethodMatcher();
        {
            /** 需要根据包名获取包下所有的Class对象，然后遍历类中的方法,是否与表达式匹配，如果是执行，不是，跳过**/
            Class<?> targetClass = PetStoreServiceImpl.class;

            /** 验证方法是否匹配得上**/
            Method method1 = targetClass.getMethod("placeOrder");
            Assert.assertTrue(mm.matches(method1));

            Method method2 = targetClass.getMethod("getAccountDao");
            Assert.assertFalse(mm.matches(method2));
        }
    }


}
