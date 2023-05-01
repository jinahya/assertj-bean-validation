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

    // -------------------------------------------------------------------------------------------- executableParameters
    @Override
    public <A extends AbstractObjectArrayAssert<?, Object>> A extractingExecutableParameters(
            final AssertFactory<Object, ? extends A> assertFactory) {
        return isNotNull()
                .extracting(ConstraintViolation::getExecutableParameters, assertFactory);
    }

    // ------------------------------------------------------------------------------------------- executableReturnValue
    @Override
    public <U, A extends AbstractObjectAssert<?, U>> A extractingExecutableReturnValue(
            final Function<? super ACTUAL, ? extends U> valueExtractor,
            final AssertFactory<? super U, ? extends A> assertFactory) {
        return isNotNull().extracting(valueExtractor, assertFactory);
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

    @Override
    public <U, A extends AbstractObjectAssert<?, U>> A extractingLeafBean(
            final Function<? super ACTUAL, ? extends U> beanExtractor,
            final AssertFactory<? super U, ? extends A> assertFactory) {
        return isNotNull()
                .extracting(beanExtractor, assertFactory);
    }

    // ---------------------------------------------------------------------------------------------------- propertyPath
    <A extends AbstractAssert<?, ? extends Path>> A extractingPropertyPath(
            final AssertFactory<? super Path, ? extends A> assertFactory) {
        return isNotNull()
                .extracting(ConstraintViolation::getPropertyPath, assertFactory);
    }
//    public AbstractPathAssert<?> extractingPropertyPath() {
//        return extractingPropertyPath(ValidationAssertions::assertThatPath);
//    }

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

}
