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

/**
 * An assertion class for verifying instances of {@code ....validation.ConstraintViolation}.
 *
 * @param <ACTUAL> the actual type of {@code ....validation.ConstraintViolation}.
 * @param <ACTUAL> the actual type of {@code ....validation.Path}.
 * @param <T>      the type of the root bean of the constraint violation
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintViolationAssert<ACTUAL, PATH, T>
        extends AbstractConstraintViolationAssert<ConstraintViolationAssert<ACTUAL, PATH, T>, ACTUAL, PATH, T> {

    /**
     * Creates a new instance for specified actual value.
     *
     * @param actual the actual value to verify; must not be {@code null} and must be an instance of either {@code
     *               javax.validation.ConstraintViolation} or {@code jakarta.validation.ConstraintViolation}.
     */
    public ConstraintViolationAssert(final ACTUAL actual) {
        super(ConstraintViolationUtils.requireNullOrInstanceOfConstraintViolationClass(actual),
              ConstraintViolationAssert.class);
    }

    @Override
    protected Object getInvalidValue(final ACTUAL actual) {
        return ConstraintViolationUtils.getInvalidValue(actual);
    }

    @Override
    protected Object getLeafBean(final ACTUAL actual) {
        return ConstraintViolationUtils.getLeafBean(actual);
    }

    @Override
    protected String getMessage(final ACTUAL actual) {
        return ConstraintViolationUtils.getMessage(actual);
    }

    @Override
    protected PATH getPropertyPath(final ACTUAL actual) {
        return ConstraintViolationUtils.getPropertyPath(actual);
    }

    @Override
    protected T getRootBean(final ACTUAL actual) {
        return ConstraintViolationUtils.getRootBean(actual);
    }

    @Override
    protected Class<T> getRootBeanClass(ACTUAL actual) {
        return ConstraintViolationUtils.getRootBeanClass(actual);
    }
}
