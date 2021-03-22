package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

public class BeanPropertyWrapper<T> {

    public static <T> BeanPropertyWrapper<T> bean(final Class<T> type, final String name) {
        requireNonNull(type, "object is null");
        requireNonNull(name, "name is null");
        return new BeanPropertyWrapper<>(type, name);
    }

    private BeanPropertyWrapper(final Class<T> type, final String name) {
        super();
        this.type = requireNonNull(type, "object is null");
        this.name = requireNonNull(name, "name is null");
    }

    final Class<T> type;

    final String name;
}
