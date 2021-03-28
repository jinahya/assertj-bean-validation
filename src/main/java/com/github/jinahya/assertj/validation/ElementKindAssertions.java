package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

public class ElementKindAssertions {

    public static ElementKindAssert assertElementKind(final Object actual) {
        return new ElementKindAssert(actual);
    }

    public static ElementKindAssert assertThat(final ElementKindWrapper wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertElementKind(wrapper.getWrapped());
    }

    public ElementKindAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
