package org.litespring.factory;

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
}
