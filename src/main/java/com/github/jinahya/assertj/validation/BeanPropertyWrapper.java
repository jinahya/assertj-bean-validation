package com.github.jinahya.assertj.validation;

public class BeanPropertyWrapper {

    public static BeanPropertyWrapper beanProperty(final Object value) {
        return new BeanPropertyWrapper(value);
    }

    private BeanPropertyWrapper(final Object value) {
        super();
        this.value = value;
    }

    Object getValue() {
        return value;
    }

    private final Object value;
}
