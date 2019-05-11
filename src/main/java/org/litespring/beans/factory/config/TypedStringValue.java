package org.litespring.beans.factory.config;

/**
 * @author chenjianrong-lhq 2019年03月03日 10:42:09
 * @Description:
 * @ClassName: RuntimeBeanReference
 */
public class TypedStringValue {

    private String value;

    public TypedStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
