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

import static java.util.Objects.requireNonNull;

/**
 * A utility class for fluently creating instances of {@link BeanValidationAssert}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see BeanValidationAssert
 */
public final class BeanValidationAssertions {

    /**
     * A class for wrapping bean object.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public static class BeanWrapper extends Wrapper<Object> {

        /**
         * Creates a new instance wraps specified bean object.
         *
         * @param wrapped the bean object to wrap; must not be {@code null}.
         * @return a new instance wraps {@code object}.
         */
        public static BeanWrapper bean(final Object wrapped) {
            requireNonNull(wrapped, "object is null");
            return new BeanWrapper(wrapped);
        }

        /**
         * Creates a new instance with specified bean object.
         *
         * @param wrapped the bean object to wrap; must not be {@code null}.
         */
        private BeanWrapper(final Object wrapped) {
            super(wrapped);
        }
    }

    /**
     * Creates a new assertion instance for specified bean object reference.
     *
     * @param object the bean object to be verified.
     * @return a new assertion instance for {@code object}.
     * @see #assertThat(BeanWrapper)
     */
    public static BeanValidationAssert assertBean(final Object object) {
        return new BeanValidationAssert(object);
    }

    /**
     * Creates a new assertion instance for the bean object reference wrapped in specified wrapper. This method invokes
     * {@link #assertBean(Object)} method with {@link BeanWrapper#getWrapped()} wrapper.wrapped} and returns the
     * result.
     *
     * @param wrapper the wrapper wraps the bean object reference; must not be {@code null}.
     * @return a assertion instance for {@link BeanWrapper#getWrapped()} wrapper.wrapped}.
     * @see BeanWrapper#bean(Object)
     * @see #assertBean(Object)
     */
    public static BeanValidationAssert assertThat(final BeanWrapper wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertBean(wrapper.getWrapped());
    }

    /**
     * Creates a new instance.
     */
    private BeanValidationAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
