package org.litespring.beans.factory.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.factory.config.ConfigableBeanFactory;
import org.litespring.factory.BeanCreationException;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @author chenjianrong-lhq 2019年03月12日 22:54:29
 * @Description:
 * @ClassName: ConstructorResolver
 */
public class ConstructorResolver {

    private final Log logger = LogFactory.getLog(getClass());

    private ConfigableBeanFactory beanFactory;

    public ConstructorResolver(ConfigableBeanFactory factory) {
        this.beanFactory = factory;
    }

    //选择合适的构造器，按照参数的顺序校验参数类型，得到参数的对应对象，构造器实例化对象
    public Object autowireContructor(final BeanDefinition definition) {
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;
        Class<?> beanClass = null;
        try {
            //可以缓存起来
            beanClass = this.beanFactory.getBeanClassLoder().loadClass(definition.getBeanClassName());
        } catch (Exception e) {
            throw new BeanCreationException(definition.getID() + " Instantiation of bean failed,can't loadClass");
        }

        Constructor<?>[] candidates = beanClass.getConstructors();

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(beanFactory);

        ConstructorArgument cagrs = definition.getConstructorArgument();

        SimpleTypeConverter converter = new SimpleTypeConverter();

        for (int i = 0; i < candidates.length; i++) {
            Class<?>[] paramTypes = candidates[i].getParameterTypes();
            if (paramTypes.length != cagrs.getArgumentCount()) {
                continue;
            }
            argsToUse = new Object[paramTypes.length];

            //判断参数类型与传递过来的参数数组是否匹配
            boolean result = this.valueMatchTypes(paramTypes, cagrs.getArgumentValues(), argsToUse, resolver, converter);

            if (result) {
                constructorToUse = candidates[i];
                break;
            }
        }
        if (constructorToUse == null) {
            throw new BeanCreationException(definition.getID() + " can't find a apporiate constructor!");
        }
        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(definition.getID() + " can't find a create instance using " + argsToUse);
        }
    }

    private boolean valueMatchTypes(Class<?>[] paramTypes,
                                    List<ConstructorArgument.ValueHolder> valueHolders,
                                    Object[] argsToUse,
                                    BeanDefinitionValueResolver resolver,
                                    SimpleTypeConverter converter) {
        for (int i = 0;i< paramTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = valueHolders.get(i);

            //获取valueHolder集合中对应的值RuntimeBeanReference或者TypedStringValue类型
            Object originalValue = valueHolder.getValue();
            try {
                //获得真正的值，从beanDefinitionMap中根据beanId获取
                Object resolveValue = resolver.resolveValueIfNecessry(originalValue);
                //根据参数的类型校验是否需要转化
                Object convertedValue = converter.convertIfNecessary(resolveValue, paramTypes[i]);
                //将构造器参数对应的实例存入数组
                argsToUse[i] = convertedValue;
            } catch (Exception e) {
                logger.error(e);
                return false;
            }
        }
        return true;

    }
}
