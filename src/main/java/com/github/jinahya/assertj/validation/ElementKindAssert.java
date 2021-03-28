package com.github.jinahya.assertj.validation;

import org.assertj.core.api.AbstractAssert;

import static com.github.jinahya.assertj.validation.ElementKindUtils.requireElementKindInstance;
import static org.assertj.core.api.Assertions.assertThat;

public class ElementKindAssert extends AbstractAssert<ElementKindAssert, Object> {

    public ElementKindAssert(final Object actual) {
        super(requireElementKindInstance(actual), ElementKindAssert.class);
    }

    public ElementKindAssert hasName(final String expected) {
        isNotNull();
        assertThat(ElementKindUtils.name(actual)).isEqualTo(expected);
        return myself;
    }
}
