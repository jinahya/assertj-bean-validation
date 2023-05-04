package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class ConstraintViolationAssert_RootBeanClass_Test
        extends ConstraintViolationAssert_$Test {

    @Test
    void hasRootBeanClass__() {
        final var actual = spy(ConstraintViolation.class);
        final var rootBeenClass = getClass();
        when(actual.getRootBeanClass()).thenReturn(rootBeenClass);
        final var assertion = assertion(actual);
        assertThatCode(() -> assertion.hasRootBeanClass(rootBeenClass)).doesNotThrowAnyException();
    }
}
