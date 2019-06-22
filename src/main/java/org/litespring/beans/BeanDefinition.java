package org.litespring.beans;

import java.util.List;

/**
 * @author chenjianrong-lhq 2019年02月23日 22:11:50
 * @Description:
 * @ClassName: BeanDefinition
 */
public interface BeanDefinition {

    public static final String SCOPE_DEFAULT = "";

    public static final String SCOPE_SINGLETON = "singleton";

    public static final String SCOPE_PROTOTYPE = "prototype";

    String getBeanClassName();

    boolean isSingleton();

    boolean isPrototype();

    void setScope(String scope);

    String getScope();

    List<PropertyValue> getPropertyValues();

    ConstructorArgument getConstructorArgument();

    String getID();

    boolean hasConstructorArgumentValues();

    Class<?> getBeanClass();

    boolean hasBeanClass();

    Class<?> resolveBeanClass(ClassLoader beanClassLoder) throws ClassNotFoundException;

    boolean isSynthetic();
}
