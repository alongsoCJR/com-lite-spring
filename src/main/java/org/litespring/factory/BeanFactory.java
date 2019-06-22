package org.litespring.factory;

import java.util.List;

/**
 * @author chenjianrong-lhq 2019年02月23日 22:06:26
 * @Description:
 * @ClassName: BeanFactory
 */
public interface BeanFactory {
    /**
     * 根据beanid获取对象
     * @param String
     * @return
     */
    Object getBean(String beanId);

    /**
     * @Author chenjianrong-lhq
     * @Description
     * @Date 2019-06-03 22:42
     * @Param [targetBeanName]
     * @return java.lang.Class<?>
     **/
    Class<?> getType(String targetBeanName) throws NoSuchBeanDefinitionException ;

    /**
     * @Author chenjianrong-lhq
     * @Description 按照类型获取bean的实例
     * @Date 2019-06-15 18:31
     * @Param [adviceClass]
     * @return java.util.List<java.lang.Object>
     **/
    List<Object> getBeansByType(Class<?> type);
}
