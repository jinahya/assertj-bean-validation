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

import org.assertj.core.api.AbstractClassAssert;
import org.assertj.core.api.AbstractObjectArrayAssert;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ObjectAssertFactory;

import javax.validation.ConstraintViolation;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.function.Function;

/**
 * An assertion interface for verifying {@link ConstraintViolation} values.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @param <T>      root bean type parameter
 */
public interface ConstraintViolationAssert<
        SELF extends ConstraintViolationAssert<SELF, ACTUAL, T>, ACTUAL extends ConstraintViolation<T>, T>
        extends Assert<SELF, ACTUAL> {

    // -------------------------------------------------------------------------------------------- constraintDescriptor
    <DESCRIPTOR extends ConstraintDescriptor<?>,
            ASSERT extends AbstractConstraintDescriptorAssert<?, DESCRIPTOR, ?>>
    ASSERT extractingConstraintDescriptor(
            final Function<? super ACTUAL, ? extends DESCRIPTOR> descriptorExtractor,
            final AssertFactory<? super DESCRIPTOR, ? extends ASSERT> assertFactory);

    @SuppressWarnings({
            "unchecked"
    })
    default <DESCRIPTOR extends ConstraintDescriptor<?>,
            ASSERT extends AbstractConstraintDescriptorAssert<?, DESCRIPTOR, ?>>
    ASSERT extractingConstraintDescriptor(
            final AssertFactory<? super DESCRIPTOR, ? extends ASSERT> assertFactory) {
        return extractingConstraintDescriptor(a -> (DESCRIPTOR) a.getConstraintDescriptor(), assertFactory);
    }

    default ConstraintDescriptorAssert<?, ?, ?> extractingConstraintDescriptor() {
        return extractingConstraintDescriptor(
                // do not change following line into a method reference!!!
                (ConstraintDescriptor<?> cd) -> ValidationAssertions.assertThatConstraintDescriptor(cd)
        );
    }

    // -------------------------------------------------------------------------------------------- executableParameters
    <ASSERT extends AbstractObjectArrayAssert<?, Object>> ASSERT extractingExecutableParameters(
            final AssertFactory<Object, ? extends ASSERT> assertFactory);

    default AbstractObjectArrayAssert<?, Object> extractingExecutableParameters() {
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
    @SuppressWarnings({
            "unchecked"
    })
    default SELF hasExecutableParameters(final Object[] expectedExecutableParameters) {
        extractingExecutableParameters().isEqualTo(expectedExecutableParameters);
        return (SELF) this;
    }

    default SELF hasNoExecutableParameters() {
        return hasExecutableParameters(null);
    }

    // ------------------------------------------------------------------------------------------- executableReturnValue
    <U, A extends AbstractObjectAssert<?, U>> A extractingExecutableReturnValue(
            final Function<? super ACTUAL, ? extends U> valueExtractor,
            final AssertFactory<? super U, ? extends A> assertFactory);

    @SuppressWarnings({"unchecked"})
    default <U, A extends AbstractObjectAssert<?, U>> A extractingExecutableReturnValue(
            final AssertFactory<? super U, ? extends A> assertFactory) {
        return extractingExecutableReturnValue(a -> (U) a.getExecutableReturnValue(), assertFactory);
    }

    default <U> AbstractObjectAssert<?, U> extractingExecutableReturnValue() {
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
    @SuppressWarnings({
            "unchecked"
    })
    default SELF hasExecutableReturnValue(final Object expectedExecutableReturnValue) {
        extractingExecutableReturnValue().isEqualTo(expectedExecutableReturnValue);
        return (SELF) this;
    }

    default SELF hasNoExecutableReturnValue() {
        return hasExecutableReturnValue(null);
    }

    // ---------------------------------------------------------------------------------------------------- invalidValue
    <U, A extends AbstractObjectAssert<?, U>> A extractingInvalidValue(
            final Function<? super ACTUAL, ? extends U> valueExtractor,
            final AssertFactory<? super U, ? extends A> assertFactory);

    @SuppressWarnings({
            "unchecked"
    })
    default <U, A extends AbstractObjectAssert<?, U>> A extractingInvalidValue(
            final AssertFactory<? super U, ? extends A> assertFactory) {
        return extractingInvalidValue(a -> (U) a.getInvalidValue(), assertFactory);
    }

    default <U> AbstractObjectAssert<?, U> extractingInvalidValue() {
        return extractingInvalidValue(new ObjectAssertFactory<>());
    }

    /**
     * Verifies that the {@link ConstraintViolation#getInvalidValue() actual.invalidValue} is equal to specified value.
     *
     * @param expectedInvalidValue the expected value of {@code actual.invalidValue}.
     * @return this assertion object.
     * @see ConstraintViolation#getInvalidValue()
     */
    @SuppressWarnings({
            "unchecked"
    })
    default SELF hasInvalidValue(final Object expectedInvalidValue) {
        extractingInvalidValue().isEqualTo(expectedInvalidValue);
        return (SELF) this;
    }

    // -------------------------------------------------------------------------------------------------------- leafBean
    <U, A extends AbstractObjectAssert<?, U>> A extractingLeafBean(
            final Function<? super ACTUAL, ? extends U> beanExtractor,
            final AssertFactory<? super U, ? extends A> assertFactory);

    @SuppressWarnings({
            "unchecked"
    })
    default <U, A extends AbstractObjectAssert<?, U>> A extractingLeafBean(
            final AssertFactory<? super U, ? extends A> assertFactory) {
        return extractingLeafBean(a -> (U) a.getLeafBean(), assertFactory);
    }

    default <U> AbstractObjectAssert<?, U> extractingLeafBean() {
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
    @SuppressWarnings({
            "unchecked"
    })
    default SELF hasLeafBean(final Object expectedLeafBean) {
        extractingLeafBean()
                .isEqualTo(expectedLeafBean);
        return (SELF) this;
    }

    /**
     * Verifies that the {@code actual} constraint violation has no {@link ConstraintViolation#getLeafBean() leafBean}.
     *
     * @return this assertion object.
     * @apiNote This method is equivalent to invoking {@link #hasLeafBean(Object)} method with {@code null}.
     */
    default SELF hasNoLeafBean() {
        return hasLeafBean(null);
    }

    // -------------------------------------------------------------------------------------------------------- rootBean
    <A extends AbstractObjectAssert<?, T>> A extractingRootBean(
            final AssertFactory<? super T, ? extends A> assertFactory);

    default AbstractObjectAssert<?, T> extractingRootBean() {
        return extractingRootBean(Assertions::assertThat);
    }

    /**
     * Verifies that the {@link ConstraintViolation#getRootBean() actual.rootBean} is equal to specified value.
     *
     * @param expectedRootBean the expected value of {@code actual.rootBean}.
     * @return this assertion object.
     * @see ConstraintViolation#getRootBean()
     */
    @SuppressWarnings({
            "unchecked"
    })
    default SELF hasRootBean(final T expectedRootBean) {
        extractingRootBean().isEqualTo(expectedRootBean);
        return (SELF) this;
    }

    /**
     * Verifies that the {@code actual} constraint violation has no {@link ConstraintViolation#getRootBean() rootBean}.
     *
     * @return this assertion object.
     * @apiNote This method is equivalent to invoking {@link #hasRootBean(Object)} method with {@code null}.
     */
    default SELF hasNoRootBean() {
        return hasRootBean(null);
    }

    // --------------------------------------------------------------------------------------------------- rootBeanClass
    <A extends AbstractClassAssert<?>> A extractingRootBeanClass(
            final AssertFactory<? super Class<T>, ? extends A> assertFactory);

    default AbstractClassAssert<?> extractingRootBeanClass() {
        return extractingRootBeanClass(Assertions::assertThat);
    }

    /**
     * Verifies that the {@link ConstraintViolation#getRootBeanClass() actual.rootBeanClass} is equal to specified
     * value.
     *
     * @param expectedRootBeanClass the expected value of {@code actual.rootBeanClass}.
     * @return this assertion object.
     * @see ConstraintViolation#getRootBeanClass()
     */
    @SuppressWarnings({
            "unchecked"
    })
    default SELF hasRootBeanClass(final Class<T> expectedRootBeanClass) {
        extractingRootBeanClass().isEqualTo(expectedRootBeanClass);
        return (SELF) this;
    }
}
