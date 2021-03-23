package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

public class BeanPropertyWrapper<T> extends BeanWrapper<T> {

    public static <T> BeanPropertyWrapper<T> beanProperty(final T object, final String propertyName) {
        requireNonNull(object, "object is null");
        requireNonNull(propertyName, "propertyName is null");
        return new BeanPropertyWrapper<>(object, propertyName);
    }

    BeanPropertyWrapper(final T object, final String propertyName) {
        super(object);
        this.propertyName = requireNonNull(propertyName, "propertyName is null");
    }

    final String propertyName;
}
