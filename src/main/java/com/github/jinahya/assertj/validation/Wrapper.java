package com.github.jinahya.assertj.validation;

abstract class Wrapper<T> {

    Wrapper(final T wrapped) {
        super();
        this.wrapped = wrapped;
    }

    T getWrapped() {
        return wrapped;
    }

    private final T wrapped;
}
