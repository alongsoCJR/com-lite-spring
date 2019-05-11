package org.litespring.factory;

import org.litespring.beans.BeansException;

/**
 * @author chenjianrong-lhq 2019年03月03日 22:27:41
 * @Description:
 * @ClassName: TypeMismatchException
 */
public class TypeMismatchException extends BeansException {
    private transient Object value;

    private Class<?> requiredType;

    public TypeMismatchException( Object value, Class<?> requiredType) {
        super("Failed to convert value :"+value + "to type "+requiredType);
        this.value = value;
        this.requiredType = requiredType;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getRequiredType() {
        return requiredType;
    }


}