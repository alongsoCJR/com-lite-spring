package org.litespring.beans.factory.config;

import org.litespring.factory.BeanFactory;

public interface AutowireCapableBeanFactory extends BeanFactory {

    Object resolveDependency(DependencyDescriptor dependencyDescriptor);
}
