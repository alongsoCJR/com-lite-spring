package org.litespring.beans;



/**
 * @author chenjianrong-lhq 2019年02月24日 10:13:48
 * @Description: Spring异常规范类
 * @ClassName: BeansException
 */

public abstract class BeansException extends RuntimeException {
    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
