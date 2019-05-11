package org.litespring.factory;

import org.litespring.beans.BeansException;

/**
 * @author chenjianrong-lhq 2019年02月24日 10:18:21
 * @Description: 创建Bean时异常
 * @ClassName: BeanCreationException
 */
public class BeanCreationException extends BeansException {
    public BeanCreationException(String msg) {
        super(msg);
    }

    public BeanCreationException(String msg,Exception e) {
        super(msg,e);
    }
}
