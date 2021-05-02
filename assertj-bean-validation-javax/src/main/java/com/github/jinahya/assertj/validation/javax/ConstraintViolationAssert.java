package com.github.jinahya.assertj.validation.javax;

/*-
 * #%L
 * assertj-bean-validation-javax
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

import com.github.jinahya.assertj.validation.AbstractConstraintViolationAssert;

import javax.validation.ConstraintViolation;
import javax.validation.Path;

/**
 * An assertion class for verifying instances of {@link ConstraintViolation}.
 *
 * @param <T> the type of the root bean
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintViolationAssert<T>
        extends AbstractConstraintViolationAssert<ConstraintViolationAssert<T>, ConstraintViolation<T>, Path, T> {

    protected static class AccessorImpl<T>
            implements Accessor<ConstraintViolation<T>, Path, T> {

        @Override
        public Object getInvalidValue(final ConstraintViolation<T> actual) {
            return actual.getInvalidValue();
        }

        @Override
        public Object getLeafBean(final ConstraintViolation<T> actual) {
            return actual.getLeafBean();
        }

        @Override
        public String getMessage(final ConstraintViolation<T> actual) {
            return actual.getMessage();
        }

        @Override
        public Path getPropertyPath(final ConstraintViolation<T> actual) {
            return actual.getPropertyPath();
        }

        @Override
        public T getRootBean(final ConstraintViolation<T> actual) {
            return actual.getRootBean();
        }

        @Override
        public Class<T> getRootBeanClass(final ConstraintViolation<T> actual) {
            return actual.getRootBeanClass();
        }
    }

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to verify
     */
    public ConstraintViolationAssert(final ConstraintViolation<T> actual) {
        super(actual, ConstraintViolationAssert.class, new AccessorImpl<>());
    }
}
