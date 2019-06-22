package org.litespring.beans.factory.support;

import org.litespring.beans.BeanDefinition;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjianrong-lhq 2019年02月23日 22:51:24
 * @Description:
 * @ClassName: GeneralDeanFactory
 */
public class GenericBeanDefinition implements BeanDefinition {

    private String id;

    private String beanClassName;

    private boolean singleton = true;

    private boolean prototype = false;

    private String scope = SCOPE_DEFAULT;

    private Class<?> beanClass;

    /**
     * 标识这个bean是否是手工合成的
     **/
    private boolean isSynthetic = false;

    private List<PropertyValue> propertyValueLIst = new ArrayList<PropertyValue>();

    private ConstructorArgument constructorArgument = new ConstructorArgument();

    public GenericBeanDefinition(String id, String beanClassName) {
        this.id = id;
        this.beanClassName = beanClassName;
    }

    public GenericBeanDefinition() {

    }

    public GenericBeanDefinition(Class<?> clz) {
        this.beanClass=clz;
        this.beanClassName=clz.getName();
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getBeanClassName() {
        return this.beanClassName;
    }

    public boolean isSingleton() {
        return this.singleton;
    }

    public boolean isPrototype() {
        return this.prototype;
    }

    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_DEFAULT.equals(scope) || SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public String getScope() {
        return this.scope;
    }

    public List<PropertyValue> getPropertyValues() {
        return this.propertyValueLIst;
    }

    public ConstructorArgument getConstructorArgument() {
        return this.constructorArgument;
    }

    public String getID() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean hasConstructorArgumentValues() {
        return !this.constructorArgument.isEmpty();
    }

    public Class<?> getBeanClass() {
        if (this.beanClass == null) {
            throw new IllegalStateException(
                    "Bean class name [" + this.getBeanClassName() + "] has not been resolved into an actual Class");
        }
        return this.beanClass;
    }

    public boolean hasBeanClass() {
        return this.beanClass != null;
    }

    public Class<?> resolveBeanClass(ClassLoader beanClassLoder) throws ClassNotFoundException {
        String className = getBeanClassName();

        if (className == null) {
            return null;
        }
        Class<?> resolveClass = beanClassLoder.loadClass(className);
        this.beanClass = resolveClass;
        return resolveClass;
    }

    public boolean isSynthetic() {
        return isSynthetic;
    }

    public void setSynthetic(boolean synthetic) {
        this.isSynthetic = synthetic;
    }
}

