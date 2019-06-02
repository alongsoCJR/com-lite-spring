package org.litespring.beans.propertyeditors;

import org.litespring.util.NumberUtils;
import org.litespring.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

public class CustomNumberEditor extends PropertyEditorSupport {
    private final Class<? extends Number> numberClass;
    private final NumberFormat numberFormat;
    private final boolean allowEmpty;

    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty) throws IllegalArgumentException {
        this(numberClass, (NumberFormat)null, allowEmpty);
    }

    public CustomNumberEditor(Class<? extends Number> numberClass, NumberFormat numberFormat, boolean allowEmpty) throws IllegalArgumentException {
        if (numberClass != null && Number.class.isAssignableFrom(numberClass)) {
            this.numberClass = numberClass;
            this.numberFormat = numberFormat;
            this.allowEmpty = allowEmpty;
        } else {
            throw new IllegalArgumentException("Property class must be a subclass of Number");
        }
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            this.setValue((Object)null);
        } else if (this.numberFormat != null) {
            this.setValue(NumberUtils.parseNumber(text, this.numberClass, this.numberFormat));
        } else {
            this.setValue(NumberUtils.parseNumber(text, this.numberClass));
        }

    }

    @Override
    public void setValue(Object value) {
        if (value instanceof Number) {
            super.setValue(NumberUtils.convertNumberToTargetClass((Number)value, this.numberClass));
        } else {
            super.setValue(value);
        }

    }

    @Override
    public String getAsText() {
        Object value = this.getValue();
        if (value == null) {
            return "";
        } else {
            return this.numberFormat != null ? this.numberFormat.format(value) : value.toString();
        }
    }
}
