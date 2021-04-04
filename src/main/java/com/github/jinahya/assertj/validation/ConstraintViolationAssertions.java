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
 * A class for creating assertion instance for constraint violations.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class ConstraintViolationAssertions {

    /**
     * Creates a new assertion instance for specified constraint violation value.
     *
     * @param actual the constraint violation value to assert.
     * @param <T>    constraint violation type parameter
     * @return a new assertion instance.
     */
    public static <T> ConstraintViolationAssert<T> assertConstraintViolation(final T actual) {
        return new ConstraintViolationAssert<>(actual);
    }

    /**
     * Creates a new assertion instance for the constraint violation value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the constraint violation value.
     * @param <T>     constraint violation type parameter
     * @return a new assertion instance.
     */
    public static <T> ConstraintViolationAssert<T> assertThat(final ConstraintViolationWrapper<? extends T> wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertConstraintViolation(wrapper.getActual());
    }

    private ConstraintViolationAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
