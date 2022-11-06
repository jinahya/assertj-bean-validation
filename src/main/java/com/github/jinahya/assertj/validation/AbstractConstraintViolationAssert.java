package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation
 * %%
 * Copyright (C) 2021 Jinahya, Inc.
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

import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ObjectAssertFactory;

import javax.validation.ConstraintViolation;

/**
 * An abstract assertion class for validating a {@link ConstraintViolation}.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual value type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractConstraintViolationAssert<
        SELF extends AbstractConstraintViolationAssert<SELF, ACTUAL>, ACTUAL extends ConstraintViolation<?>>
        extends AbstractValidationAssert<SELF, ACTUAL>
        implements ConstraintViolationAssert<SELF, ACTUAL> {

    /**
     * Creates a new instance with specified actual value and self type.
     *
     * @param actual   the actual value to verify.
     * @param selfType the self type.
     */
    protected AbstractConstraintViolationAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    public SELF hasExecutableParameters(final Object[] expectedExecutableParameters) {
        isNotNull()
                .extracting(ConstraintViolation::getExecutableParameters, InstanceOfAssertFactories.ARRAY)
                .isEqualTo(expectedExecutableParameters);
        return myself;
    }

    @Override
    public SELF hasExecutableReturnValue(final Object expectedExecutableReturnValue) {
        isNotNull()
                .extracting(ConstraintViolation::getExecutableReturnValue, new ObjectAssertFactory<>())
                .isEqualTo(expectedExecutableReturnValue);
        return myself;
    }

    @Override
    public SELF hasInvalidValue(final Object expectedInvalidValue) {
        isNotNull()
                .extracting(ConstraintViolation::getInvalidValue, new ObjectAssertFactory<>())
                .isEqualTo(expectedInvalidValue);
        return myself;
    }

    @Override
    public SELF hasLeafBean(final Object expectedLeafBean) {
        isNotNull()
                .extracting(ConstraintViolation::getLeafBean, new ObjectAssertFactory<>())
                .isEqualTo(expectedLeafBean);
        return myself;
    }

    @Override
    public <T> SELF hasRootBean(final T expectedRootBean) {
        isNotNull()
                .extracting(v -> v.<T>getRootBean(), new ObjectAssertFactory<>())
                .isEqualTo(expectedRootBean);
        return myself;
    }

    @Override
    public <T> SELF hasRootBeanClass(final Class<T> expectedRootBeanClass) {
        isNotNull()
                .extracting(v -> v.<T>getRootBeanClass(), InstanceOfAssertFactories.CLASS)
                .isEqualTo(expectedRootBeanClass);
        return myself;
    }
}
