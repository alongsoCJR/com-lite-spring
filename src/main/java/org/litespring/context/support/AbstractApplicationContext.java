package org.litespring.context.support;

import org.litespring.beans.factory.annotation.AutowiredAnnotationProcessor;
import org.litespring.beans.factory.config.ConfigableBeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.context.ApplicationContext;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

/**
 * @author chenjianrong-lhq 2019年02月25日 21:48:19
 * @Description:
 * @ClassName: AbstractApplicationContext
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory = null;

    private ClassLoader beanClassLoder;

    public AbstractApplicationContext(String path) {
        this(path, ClassUtils.getDefaultClassLoader());
    }

    public AbstractApplicationContext(String path, ClassLoader classLoader) {
        factory = new DefaultBeanFactory();
        Resource resource = this.getResourceByPath(path);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinations(resource);
        factory.setBeanClassLoder(classLoader);
        registerBeanPostProcessor(factory);
    }


    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }

    protected abstract Resource getResourceByPath(String path);

    public ClassLoader getBeanClassLoder() {
        return (beanClassLoder != null ? beanClassLoder : ClassUtils.getDefaultClassLoader());
    }

    /**
     * 为什么不是这里加非空判断，而是get方法加非空判断--具体根据场景去分析
     * @param beanClassLoder
     */
    public void setBeanClassLoder(ClassLoader beanClassLoder) {
        this.beanClassLoder = beanClassLoder;
    }

    protected void registerBeanPostProcessor(ConfigableBeanFactory beanFactory) {
        AutowiredAnnotationProcessor postProcessor = new AutowiredAnnotationProcessor();
        postProcessor.setBeanFactory(beanFactory);
        beanFactory.addBeanPostProcessor(postProcessor);
    }
}
