package com.github.jinahya.assertj.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class BeanPropertyValueWrapper<T> {

    public static <T> BeanPropertyValueWrapper<T> beanPropertyValue(final Class<T> type, final String name,
                                                                    final Object value) {
        requireNonNull(type, "type is null");
        requireNonNull(name, "name is null");
        return new BeanPropertyValueWrapper<>(type, name, value);
    }

    BeanPropertyValueWrapper(final Class<T> beanType, final String propertyName, final Object value) {
        super();
        this.beanType = requireNonNull(beanType, "beanType is null");
        this.propertyName = requireNonNull(propertyName, "propertyName is null");
        this.value = value;
    }

    @NotNull final Class<T> beanType;

    @NotBlank final String propertyName;

    final Object value;
}
