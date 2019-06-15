package org.litespring.aop.framework;

/**
 * @author chenjianrong-lhq 2019年06月09日 10:05:21
 * @Description:
 * @ClassName: AopConfigException
 */
public class AopConfigException extends Throwable {


    /**
     * Constructor for AopConfigException.
     *
     * @param msg the detail message
     */
    public AopConfigException(String msg) {
        super(msg);
    }

    /**
     * Constructor for AopConfigException.
     *
     * @param msg   the detail message
     * @param cause the root cause
     */
    public AopConfigException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
