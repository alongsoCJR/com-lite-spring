package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.TypeConverter;
import org.litespring.beans.factory.BeanDefinitionRegistry;
import org.litespring.beans.factory.config.ConfigableBeanFactory;
import org.litespring.factory.BeanCreationException;
import org.litespring.util.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenjianrong-lhq 2019年02月23日 22:08:47
 * @Description:
 * @ClassName: DefaultBeanFactory
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigableBeanFactory, BeanDefinitionRegistry {
    public final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    private ClassLoader beanClassLoder;

    public DefaultBeanFactory() {
    }

    public BeanDefinition getBeanDefinition(String beanId) {
        return this.beanDefinitionMap.get(beanId);
    }

    public void registerBeanDefinition(String beanId, BeanDefinition bd) {
        this.beanDefinitionMap.put(beanId, bd);
    }

    public Object getBean(String beanId) {
        BeanDefinition bd = this.getBeanDefinition(beanId);

        if (bd == null) {
            throw new BeanCreationException("Bean Definition does not exist!");
        }

        if (bd.isSingleton()) {
            Object bean = this.getSingleton(beanId);
            if (bean == null) {
                bean = this.createBean(bd);
                this.registerSingleton(beanId, bean);
            }
            return bean;
        }
        return createBean(bd);
    }

    private Object createBean(BeanDefinition bd) {
        //bean的实例化
        Object bean = this.instaniateBean(bd);
        //set属性
        populateBean(bd, bean);

        return bean;
    }

    private void populateBean(BeanDefinition bd, Object bean) {
        List<PropertyValue> pvs = bd.getPropertyValues();

        if (pvs == null || pvs.isEmpty()) {
            return;
        }

        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);

        TypeConverter typeConverter=new SimpleTypeConverter();
        try {
            for (PropertyValue pv : pvs) {
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();
                Object resolvedValue = resolver.resolveValueIfNecessry(originalValue);

                //假设现在传过来的是ref="accountDao",如何去调用setAccountDao()?

                //Introspector反射器，是不是有点熟悉。
                BeanInfo beanInfo= Introspector.getBeanInfo(bean.getClass());
                //属性的描述器
                PropertyDescriptor[] pds=beanInfo.getPropertyDescriptors();
                for(PropertyDescriptor pd:pds){
                    if(pd.getName().equals(propertyName)){

                        Object convertedValue=typeConverter.convertIfNecessary(resolvedValue,pd.getPropertyType());
                        pd.getWriteMethod().invoke(bean,convertedValue);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanCreationException("failed to obtain beanInfo for class [ ]", e);
        }
    }

    private Object instaniateBean(BeanDefinition bd) {
        //构造解析器应该放在这边
        if(bd.hasConstructorArgumentValues()){
            ConstructorResolver resolver=new ConstructorResolver(this);
            return resolver.autowireContructor(bd);
        }else{
            ClassLoader cl = this.getBeanClassLoder();

            String beanClassName = bd.getBeanClassName();
            try {
                Class<?> clz = cl.loadClass(beanClassName);
                return clz.newInstance();
            } catch (Exception e) {
                throw new BeanCreationException("create bean for " + beanClassName + " failed!", e);
            }
        }
    }


    public void setBeanClassLoder(ClassLoader beanClassLoder) {
        this.beanClassLoder = beanClassLoder;
    }

    public ClassLoader getBeanClassLoder() {
        return (beanClassLoder != null ? beanClassLoder : ClassUtils.getDefaultClassLoader());
    }
}
