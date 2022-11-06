package com.github.jinahya.assertj.validation;

import javax.validation.ConstraintViolation;

public interface ConstraintViolationAssert<
        SELF extends ConstraintViolationAssert<SELF, ACTUAL>,
        ACTUAL extends ConstraintViolation<?>>
        extends ValidationAssert<SELF, ACTUAL> {

    /**
     * Verifies that the actual {@link ConstraintViolation}'s
     * {@link ConstraintViolation#getExecutableParameters() executableParameters} is equal to specified value.
     *
     * @param expectedExecutableParameters the expected value of {@code actual.executableParameters}.
     * @return this assertion object.
     */
    SELF hasExecutableParameters(Object[] expectedExecutableParameters);

    /**
     * Verifies that the actual {@link ConstraintViolation}'s
     * {@link ConstraintViolation#getExecutableReturnValue() executableReturnValue} is equal to specified value.
     *
     * @param expectedExecutableReturnValue the expected value of {@code actual.executableReturnValue}.
     * @return this assertion object.
     */
    SELF hasExecutableReturnValue(Object expectedExecutableReturnValue);

    /**
     * Verifies that the actual {@link ConstraintViolation}'s {@link ConstraintViolation#getInvalidValue() invalidValue}
     * is equal to specified value.
     *
     * @param expectedInvalidValue the expected value of {@code actual.invalidValue}.
     * @return this assertion object.
     */
    SELF hasInvalidValue(Object expectedInvalidValue);

    /**
     * Verifies that the actual {@link ConstraintViolation}'s {@link ConstraintViolation#getLeafBean() leafBean} is
     * equal to specified value.
     *
     * @param expectedLeafBean the expected value of {@code actual.leafBean}.
     * @return this assertion object.
     */
    SELF hasLeafBean(Object expectedLeafBean);

    /**
     * Verifies that the actual {@link ConstraintViolation}'s {@link ConstraintViolation#getRootBean() leafBean} is
     * equal to specified value.
     *
     * @param expectedRootBean the expected value of {@code actual.rootBean}.
     * @return this assertion object.
     */
    <T> SELF hasRootBean(T expectedRootBean);

    /**
     * Verifies that the actual {@link ConstraintViolation}'s
     * {@link ConstraintViolation#getRootBeanClass() leafBeanClass} is equal to specified value.
     *
     * @param expectedRootBean the expected value of {@code actual.rootBeanClass}.
     * @return this assertion object.
     */
    <T> SELF hasRootBeanClass(Class<T> expectedRootBean);
}
