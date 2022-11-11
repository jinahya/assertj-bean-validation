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
import org.assertj.core.api.AbstractObjectArrayAssert;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ObjectAssertFactory;

import javax.validation.ConstraintViolation;
import java.util.function.Function;

@SuppressWarnings({
        "java:S119" // <SELF>, <ACTUAL>
})
public abstract class ConstraintViolationAssert2<SELF extends ConstraintViolationAssert2<SELF>>
        extends AbstractAssert<SELF, ConstraintViolation<?>>
        implements ValidationAssert<SELF, ConstraintViolation<?>> {

    protected ConstraintViolationAssert2(final ConstraintViolation<?> actual, Class<?> selfType) {
        super(actual, selfType);
    }

    public AbstractObjectArrayAssert<?, Object> extractingExecutableParameters() {
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

    public <R, ASSERT extends AbstractAssert<?, R>> ASSERT extractingExecutableReturnValue(
            final AssertFactory<R, ASSERT> assertFactory) {
        @SuppressWarnings({"unchecked"})
        final Function<ConstraintViolation<?>, R> extractor = a -> (R) a.getExecutableReturnValue();
        return isNotNull()
                .extracting(extractor, assertFactory);
    }

    public <R> AbstractObjectAssert<?, R> extractingExecutableReturnValue() {
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

    private static final Function<ConstraintViolation<?>, ?> INVALID_VALUE_EXTRACTOR
            = ConstraintViolation::getInvalidValue;

    @SuppressWarnings({"unchecked"})
    public <T, ASSERT extends AbstractObjectAssert<?, T>> ASSERT extractingInvalidValue(
            final AssertFactory<T, ASSERT> assertFactory) {
        return isNotNull()
                .extracting(a -> (T) INVALID_VALUE_EXTRACTOR.apply(a), assertFactory);
    }

    public <T> AbstractObjectAssert<?, T> extractingInvalidValue() {
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
        extractingInvalidValue().isEqualTo(expectedInvalidValue);
        return myself;
    }

    private static final Function<ConstraintViolation<?>, ?> LEAF_BEAN_EXTRACTOR
            = ConstraintViolation::getLeafBean;

    @SuppressWarnings({"unchecked"})
    public <T, ASSERT extends AbstractObjectAssert<?, T>> ASSERT extractingLeafBean(
            final AssertFactory<T, ASSERT> assertFactory) {
        return isNotNull()
                .extracting(a -> (T) LEAF_BEAN_EXTRACTOR.apply(a), assertFactory);
    }

    public <T> AbstractObjectAssert<?, T> extractingLeafBean() {
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

    //
//    /**
//     * Verifies that the actual {@link ConstraintViolation}'s {@link ConstraintViolation#getRootBean() leafBean} is
//     * equal to specified value.
//     *
//     * @param expectedRootBean the expected value of {@code actual.rootBean}.
//     * @return this assertion object.
//     * @see ConstraintViolation#getRootBean()
//     */
//    <T> SELF hasRootBean(T expectedRootBean);
//
//    /**
//     * Verifies that the actual {@link ConstraintViolation}'s
//     * {@link ConstraintViolation#getRootBeanClass() leafBeanClass} is equal to specified value.
//     *
//     * @param expectedRootBean the expected value of {@code actual.rootBeanClass}.
//     * @return this assertion object.
//     * @see ConstraintViolation#getRootBeanClass()
//     */
//    <T> SELF hasRootBeanClass(Class<T> expectedRootBean);
}
