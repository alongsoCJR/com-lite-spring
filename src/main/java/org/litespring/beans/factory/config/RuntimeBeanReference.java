package org.litespring.beans.factory.config;

/**
 * @author chenjianrong-lhq 2019年03月03日 10:42:09
 * @Description:
 * @ClassName: RuntimeBeanReference
 */
public class RuntimeBeanReference {

    private String beanName;

    public RuntimeBeanReference(String beanName){
        this.beanName=beanName;
    }
    public String getBeanName() {
        return beanName;
    }
}
