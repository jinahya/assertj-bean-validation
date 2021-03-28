package com.github.jinahya.assertj.validation;

public class ElementKindWrapper extends Wrapper<Object> {

    public static ElementKindWrapper elementKind(final Object actual) {
        return new ElementKindWrapper(actual);
    }

    private ElementKindWrapper(final Object wrapped) {
        super(wrapped);
    }
}
