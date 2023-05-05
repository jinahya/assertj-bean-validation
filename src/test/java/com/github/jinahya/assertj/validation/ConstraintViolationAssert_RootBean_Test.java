package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class ConstraintViolationAssert_RootBean_Test
        extends ConstraintViolationAssert_$Test {

    @Test
    void hasRootBean__() {
        final var actual = spy(ConstraintViolation.class);
        final var rootBeen = this;
        when(actual.getRootBean()).thenReturn(rootBeen);
        final var assertion = assertion(actual);
        assertThatCode(() -> assertion.hasRootBean(rootBeen)).doesNotThrowAnyException();
    }

    @Test
    void doesNotHaveRootBean__() {
        final var actual = spy(ConstraintViolation.class);
        when(actual.getRootBean()).thenReturn(null);
        final var assertion = assertion(actual);
        assertThatCode(() -> assertion.doesNotHaveRootBean()).doesNotThrowAnyException();
    }
}
