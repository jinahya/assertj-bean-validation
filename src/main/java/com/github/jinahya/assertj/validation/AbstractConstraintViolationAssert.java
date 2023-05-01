package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation
 * %%
 * Copyright (C) 2021 - 2022 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractClassAssert;
import org.assertj.core.api.AbstractObjectArrayAssert;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ObjectAssertFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import java.util.function.Function;

/**
 * An abstract assertion class for verifying {@link ConstraintViolation} values.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @param <T>      root bean type parameter
 */
public abstract class AbstractConstraintViolationAssert<
        SELF extends AbstractConstraintViolationAssert<SELF, ACTUAL, T>, ACTUAL extends ConstraintViolation<T>, T>
        extends AbstractValidationAssert<SELF, ACTUAL>
        implements ConstraintViolationAssert<SELF, ACTUAL, T> {

    /**
     * Creates a new instance with specified arguments.
     *
     * @param actual   an actual value to verify.
     * @param selfType self type.
     */
    protected AbstractConstraintViolationAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    <ASSERT extends AbstractObjectArrayAssert<?, Object>> ASSERT extractingExecutableParameters(
            final AssertFactory<Object, ? extends ASSERT> assertFactory) {
        return isNotNull()
                .extracting(ConstraintViolation::getExecutableParameters, assertFactory);
    }

    AbstractObjectArrayAssert<?, Object> extractingExecutableParameters() {
        return extractingExecutableParameters(InstanceOfAssertFactories.ARRAY);
    }

    /**
     * Verifies that the {@link ConstraintViolation#getExecutableParameters() actual.executableParameters} is equal to
     * specified value.
     *
     * @param expectedExecutableParameters the expected value of {@code actual.executableParameters}.
     * @return this assertion object.
     * @see ConstraintViolation#getExecutableParameters()
     * @see #extractingExecutableParameters()
     */
    SELF hasExecutableParameters(final Object[] expectedExecutableParameters) {
        extractingExecutableParameters()
                .isEqualTo(expectedExecutableParameters);
        return myself;
    }

    @SuppressWarnings({"unchecked"})
    <U, ASSERT extends AbstractObjectAssert<?, U>> ASSERT extractingExecutableReturnValue(
            final AssertFactory<? super U, ? extends ASSERT> assertFactory) {
        return isNotNull()
                .extracting(a -> (U) a.getExecutableReturnValue(), assertFactory);
    }

    <U> AbstractObjectAssert<?, U> extractingExecutableReturnValue() {
        return extractingExecutableReturnValue(new ObjectAssertFactory<>());
    }

    /**
     * Verifies that the actual {@link ConstraintViolation}'s
     * {@link ConstraintViolation#getExecutableReturnValue() executableReturnValue} is equal to specified value.
     *
     * @param expectedExecutableReturnValue the expected value of {@code actual.executableReturnValue}.
     * @return this assertion object.
     * @see ConstraintViolation#getExecutableReturnValue()
     */
    SELF hasExecutableReturnValue(final Object expectedExecutableReturnValue) {
        extractingExecutableReturnValue()
                .isEqualTo(expectedExecutableReturnValue);
        return myself;
    }

    // ---------------------------------------------------------------------------------------------------- invalidValue
    @Override
    public <U, A extends AbstractObjectAssert<?, U>> A extractingInvalidValue(
            final Function<? super ACTUAL, ? extends U> valueExtractor,
            final AssertFactory<? super U, ? extends A> assertFactory) {
        return isNotNull()
                .extracting(valueExtractor, assertFactory);
    }

    // -------------------------------------------------------------------------------------------------------- leafBean
    @SuppressWarnings({
            "unchecked"
    })
    <U, A extends AbstractObjectAssert<?, U>> A extractingLeafBean(
            final AssertFactory<? super U, ? extends A> assertFactory) {
        return isNotNull().extracting(a -> (U) a.getLeafBean(), assertFactory);
    }

    <U> AbstractObjectAssert<?, U> extractingLeafBean() {
        return extractingLeafBean(new ObjectAssertFactory<>());
    }

    /**
     * Verifies that the {@link ConstraintViolation#getLeafBean() actual.leafBean} is equal to specified value.
     *
     * @param expectedLeafBean the expected value of {@code actual.leafBean}.
     * @return this assertion object.
     * @see ConstraintViolation#getLeafBean()
     * @see #extractingLeafBean()
     */
    SELF hasLeafBean(final Object expectedLeafBean) {
        extractingLeafBean()
                .isEqualTo(expectedLeafBean);
        return myself;
    }

    // -------------------------------------------------------------------------------------------------------- rootBean
    @Override
    public <A extends AbstractObjectAssert<?, T>> A extractingRootBean(
            final AssertFactory<? super T, ? extends A> assertFactory) {
        return isNotNull()
                .extracting(ConstraintViolation::getRootBean, assertFactory);
    }

    // --------------------------------------------------------------------------------------------------- rootBeanClass
    @Override
    public <A extends AbstractClassAssert<?>> A extractingRootBeanClass(
            final AssertFactory<? super Class<T>, ? extends A> assertFactory) {
        return isNotNull()
                .extracting(ConstraintViolation::getRootBeanClass, assertFactory);
    }

    <ASSERT extends AbstractAssert<?, ? extends Path>> ASSERT extractingPropertyPath(
            final AssertFactory<? super Path, ? extends ASSERT> assertFactory) {
        return isNotNull()
                .extracting(ConstraintViolation::getPropertyPath, assertFactory);
    }

    public AbstractPathAssert<?> extractingPropertyPath() {
        return extractingPropertyPath(ValidationAssertions::assertThatPath);
    }
}
