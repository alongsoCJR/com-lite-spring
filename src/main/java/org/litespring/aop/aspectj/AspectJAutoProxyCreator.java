package org.litespring.aop.aspectj;

import org.litespring.aop.Advice;
import org.litespring.aop.MethodMatcher;
import org.litespring.aop.Pointcut;
import org.litespring.aop.framework.*;
import org.litespring.beans.BeansException;
import org.litespring.beans.factory.config.BeanPostProcessor;
import org.litespring.beans.factory.config.ConfigableBeanFactory;
import org.litespring.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author chenjianrong-lhq 2019年06月15日 20:15:43
 * @Description:
 * @ClassName: AspectJAutoProxyCreator
 */
public class AspectJAutoProxyCreator implements BeanPostProcessor {
    ConfigableBeanFactory beanFactory;

    public Object beforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object afterInitialization(Object bean, String beanName) throws BeansException {

        //如果这个Bean本身就是Advice及其子类，那就不要再生成动态代理了。
        if (isInfrastructureClass(bean.getClass())) {
            return bean;
        }

        List<Advice> advices = getCandidateAdvices(bean);
        if (advices.isEmpty()) {
            return bean;
        }

        return createProxy(advices, bean);
    }

    private List<Advice> getCandidateAdvices(Object bean) {

        List<Object> advices = this.beanFactory.getBeansByType(Advice.class);

        List<Advice> result = new ArrayList<Advice>();
        for (Object o : advices) {
            Pointcut pc = ((Advice) o).getPointcut();
            if (canApply(pc, bean.getClass())) {
                result.add((Advice) o);
            }

        }
        return result;
    }

    protected Object createProxy(List<Advice> advices, Object bean) {

        Object object = null;
        AopConfigSupport config = new AopConfigSupport();
        for (Advice advice : advices) {
            config.addAdvice(advice);
        }

        Set<Class> targetInterfaces = ClassUtils.getAllInterfacesForClassAsSet(bean.getClass());
        for (Class<?> targetInterface : targetInterfaces) {
            config.addInterface(targetInterface);
        }

        config.setTargetObject(bean);

        AopProxyFactory proxyFactory = null;
        /** 这里改动了一下，应该是==0，如果是实体类，走CGLIB动态代理，如果是接口，走JDK动态代理**/
        if (config.getProxiedInterfaces().length == 0) {
            try {
                proxyFactory = new CglibProxyFactory(config);
            } catch (AopConfigException e) {
                e.printStackTrace();
            }
        } else {
            //TODO 需要实现JDK 代理
            try {
                proxyFactory = new JdkAopProxyFactory(config);
            } catch (AopConfigException e) {
                e.printStackTrace();
            }
        }

        try {
            object = proxyFactory.getProxy();
        } catch (AopConfigException e) {
            e.printStackTrace();
        }

        return object;
    }

    protected boolean isInfrastructureClass(Class<?> beanClass) {
        boolean retVal = Advice.class.isAssignableFrom(beanClass);

        return retVal;
    }

    public void setBeanFactory(ConfigableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;

    }

    public static boolean canApply(Pointcut pc, Class<?> targetClass) {


        MethodMatcher methodMatcher = pc.getMethodMatcher();

        Set<Class> classes = new LinkedHashSet<Class>(ClassUtils.getAllInterfacesForClassAsSet(targetClass));
        classes.add(targetClass);
        for (Class<?> clazz : classes) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (methodMatcher.matches(method/*, targetClass*/)) {
                    return true;
                }
            }
        }

        return false;
    }

}