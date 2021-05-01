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

import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.requireNullOrInstanceOfConstraintViolationClass;

/**
 * An assertion class for verifying instances of {@code ....validation.ConstraintViolation}.
 *
 * @param <T> the type of the root bean of the constraint violation
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintViolationAssert<T>
        extends AbstractConstraintViolationAssert<ConstraintViolationAssert<T>, Object, Object, T> {

    protected static class AccessorImpl<T>
            implements Accessor<Object, Object, T> {

        @Override
        public Object getInvalidValue(final Object actual) {
            return ConstraintViolationUtils.getInvalidValue(actual);
        }

        @Override
        public Object getLeafBean(final Object actual) {
            return ConstraintViolationUtils.getLeafBean(actual);
        }

        @Override
        public String getMessage(final Object actual) {
            return ConstraintViolationUtils.getMessage(actual);
        }

        @Override
        public Object getPropertyPath(final Object actual) {
            return ConstraintViolationUtils.getPropertyPath(actual);
        }

        @Override
        public T getRootBean(final Object actual) {
            return ConstraintViolationUtils.getRootBean(actual);
        }

        @Override
        public Class<T> getRootBeanClass(Object actual) {
            return ConstraintViolationUtils.getRootBeanClass(actual);
        }
    }

    /**
     * Creates a new instance for specified actual value.
     *
     * @param actual the actual value to verify; must not be {@code null} and must be an instance of either {@code
     *               javax.validation.ConstraintViolation} or {@code jakarta.validation.ConstraintViolation}.
     */
    public ConstraintViolationAssert(final Object actual) {
        super(requireNullOrInstanceOfConstraintViolationClass(actual), ConstraintViolationAssert.class,
              new AccessorImpl<>());
    }
}
