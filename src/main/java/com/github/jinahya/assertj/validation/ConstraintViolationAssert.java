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
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ClassAssert;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ObjectArrayAssert;
import org.assertj.core.api.ObjectAssert;
import org.assertj.core.api.ObjectAssertFactory;

import javax.validation.ConstraintViolation;
import java.util.function.Function;

@SuppressWarnings({
        "java:S119" // <SELF>, <ACTUAL>
})
public abstract class ConstraintViolationAssert<SELF extends ConstraintViolationAssert<SELF, T>, T>
        extends AbstractAssert<SELF, ConstraintViolation<T>>
        implements ValidationAssert<SELF, ConstraintViolation<T>> {

    protected ConstraintViolationAssert(final ConstraintViolation<T> actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    public ObjectArrayAssert<Object> extractingExecutableParameters() {
        return isNotNull()
                .extracting(ConstraintViolation::getExecutableParameters, InstanceOfAssertFactories.ARRAY);
    }

    /**
     * Verifies that the actual {@link ConstraintViolation}'s
     * {@link ConstraintViolation#getExecutableParameters() executableParameters} is equal to specified value.
     *
     * @param expectedExecutableParameters the expected value of {@code actual.executableParameters}.
     * @return this assertion object.
     * @see ConstraintViolation#getExecutableParameters()
     */
    public SELF hasExecutableParameters(final Object[] expectedExecutableParameters) {
        extractingExecutableParameters()
                .isEqualTo(expectedExecutableParameters);
        return myself;
    }

    public <U, ASSERT extends AbstractObjectAssert<?, U>> ASSERT extractingExecutableReturnValue(
            final AssertFactory<U, ASSERT> assertFactory) {
        @SuppressWarnings({"unchecked"})
        final Function<? super ConstraintViolation<T>, U> extractor = a -> (U) a.getExecutableReturnValue();
        return isNotNull()
                .extracting(extractor, assertFactory);
    }

    public <U> ObjectAssert<U> extractingExecutableReturnValue() {
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
    public SELF hasExecutableReturnValue(final Object expectedExecutableReturnValue) {
        extractingExecutableReturnValue()
                .isEqualTo(expectedExecutableReturnValue);
        return myself;
    }

    @SuppressWarnings({"unchecked"})
    public <U, ASSERT extends AbstractObjectAssert<?, U>> ASSERT extractingInvalidValue(
            final AssertFactory<U, ASSERT> assertFactory) {
        return isNotNull()
                .extracting(a -> (U) a.getInvalidValue(), assertFactory);
    }

    public <U> ObjectAssert<U> extractingInvalidValue() {
        return extractingInvalidValue(new ObjectAssertFactory<>());
    }

    /**
     * Verifies that the actual {@link ConstraintViolation}'s {@link ConstraintViolation#getInvalidValue() invalidValue}
     * is equal to specified value.
     *
     * @param expectedInvalidValue the expected value of {@code actual.invalidValue}.
     * @return this assertion object.
     * @see ConstraintViolation#getInvalidValue()
     */
    public SELF hasInvalidValue(final Object expectedInvalidValue) {
        extractingInvalidValue()
                .isEqualTo(expectedInvalidValue);
        return myself;
    }

    @SuppressWarnings({"unchecked"})
    public <U, ASSERT extends AbstractObjectAssert<?, U>> ASSERT extractingLeafBean(
            final AssertFactory<U, ASSERT> assertFactory) {
        return isNotNull().extracting(a -> (U) a.getLeafBean(), assertFactory);
    }

    public <U> ObjectAssert<U> extractingLeafBean() {
        return extractingLeafBean(new ObjectAssertFactory<>());
    }

    /**
     * Verifies that the actual {@link ConstraintViolation}'s {@link ConstraintViolation#getLeafBean() leafBean} is
     * equal to specified value.
     *
     * @param expectedLeafBean the expected value of {@code actual.leafBean}.
     * @return this assertion object.
     * @see ConstraintViolation#getLeafBean()
     */
    public SELF hasLeafBean(final Object expectedLeafBean) {
        extractingLeafBean()
                .isEqualTo(expectedLeafBean);
        return myself;
    }

    public <ASSERT extends AbstractObjectAssert<?, T>> ASSERT extractingRootBean(
            final AssertFactory<T, ASSERT> assertFactory) {
        return isNotNull()
                .extracting(ConstraintViolation::getRootBean, assertFactory);
    }

    public ObjectAssert<T> extractingRootBean() {
        return extractingRootBean(new ObjectAssertFactory<>());
    }

    /**
     * Verifies that the actual {@link ConstraintViolation}'s {@link ConstraintViolation#getRootBean() rootBean} is
     * equal to specified value.
     *
     * @param expectedRootBean the expected value of {@code actual.rootBean}.
     * @return this assertion object.
     * @see ConstraintViolation#getRootBean()
     */
    public SELF hasRootBean(final T expectedRootBean) {
        extractingRootBean()
                .isEqualTo(expectedRootBean);
        return myself;
    }

    public ClassAssert extractingRootBeanClass(final AssertFactory<Class<T>, ClassAssert> assertFactory) {
        return isNotNull().extracting(ConstraintViolation::getRootBeanClass, assertFactory);
    }

    public ClassAssert extractingRootBeanClass() {
        return extractingRootBeanClass(Assertions::assertThat);
    }

    /**
     * Verifies that the actual {@link ConstraintViolation}'s
     * {@link ConstraintViolation#getRootBeanClass() rootBeanClass} is equal to specified value.
     *
     * @param expectedRootBeanClass the expected value of {@code actual.rootBeanClass}.
     * @return this assertion object.
     * @see ConstraintViolation#getRootBeanClass()
     */
    public SELF hasRootBeanClass(final Class<T> expectedRootBeanClass) {
        extractingRootBeanClass()
                .isEqualTo(expectedRootBeanClass);
        return myself;
    }
}
