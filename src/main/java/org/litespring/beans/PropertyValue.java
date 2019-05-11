package org.litespring.beans;

/**
 * @author chenjianrong-lhq 2019年03月03日 10:33:27
 * @Description:
 * @ClassName: PropertyValue
 */
public class PropertyValue {
    private final String name;

    private final Object value;

    private boolean converted=false;

    private Object convertedValue;

    public PropertyValue(String name,Object value){
        this.name=name;
        this.value=value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }

    public boolean isConverted() {
        return converted;
    }

    public synchronized void setConverted(boolean converted) {
        this.converted = converted;
    }

    public Object getConvertedValue() {
        return convertedValue;
    }

    public synchronized void setConvertedValue(Object convertedValue) {
        this.converted=true;
        this.convertedValue = convertedValue;
    }
}
