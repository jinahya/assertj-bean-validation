package com.github.jinahya.assertj.validation;

import org.assertj.core.api.AbstractAssert;

import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getInvalidValue;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getLeafBean;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getRootBean;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getRootBeanClass;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConstraintViolationAssert extends AbstractAssert<ConstraintViolationAssert, Object> {

    public ConstraintViolationAssert(final Object actual) {
        super(actual, ConstraintViolationAssert.class);
    }

    public ConstraintViolationAssert hasInvalidValue(final Object expected) {
        isNotNull()
                .satisfies(violation -> {
                    assertThat(getInvalidValue(violation)).isEqualTo(expected);
                });
        return myself;
    }

    public ConstraintViolationAssert hasLeafBean(final Object expected) {
        isNotNull()
                .satisfies(violation -> {
                    assertThat(getLeafBean(violation)).isEqualTo(expected);
                });
        return myself;
    }

    public ConstraintViolationAssert hasMessage(final String expected) {
        isNotNull()
                .satisfies(violation -> {
                    assertThat(ConstraintViolationUtils.getMessage(violation)).isEqualTo(expected);
                });
        return myself;
    }

    public ConstraintViolationAssert hasRootBean(final Object expected) {
        isNotNull()
                .satisfies(violation -> {
                    assertThat(getRootBean(violation)).isSameAs(expected);
                });
        return myself;
    }

    public ConstraintViolationAssert hasRootBeanClass(final Class<?> expected) {
        isNotNull()
                .satisfies(violation -> {
                    assertThat(getRootBeanClass(violation)).isSameAs(expected);
                });
        return myself;
    }
}
