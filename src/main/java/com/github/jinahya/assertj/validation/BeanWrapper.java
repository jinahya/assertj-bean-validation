package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

public class BeanWrapper<T> implements ActualWrapper {

    public static <T> BeanWrapper<T> bean(final T object) {
        requireNonNull(object, "object is null");
        return new BeanWrapper<>(object);
    }

    BeanWrapper(final T object) {
        super();
        this.object = requireNonNull(object, "object is null");
    }

    final T object;
}
