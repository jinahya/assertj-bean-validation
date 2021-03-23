package com.github.jinahya.assertj.validation;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

public class BeanWrapper {

    public static BeanWrapper bean(final Object object) {
        return new BeanWrapper(object);
    }

    private BeanWrapper(final Object object) {
        super();
        this.object = requireNonNull(object, "object is null");
    }

    Object getObject() {
        return object;
    }

    @NotNull
    private final Object object;
}
