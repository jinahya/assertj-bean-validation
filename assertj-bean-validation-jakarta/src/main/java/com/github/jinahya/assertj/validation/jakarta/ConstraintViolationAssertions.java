package com.github.jinahya.assertj.validation.jakarta;

/*-
 * #%L
 * assertj-bean-validation-jakarta
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

import com.github.jinahya.assertj.validation.AbstractWrapper;
import jakarta.validation.ConstraintViolation;

import static java.util.Objects.requireNonNull;

/**
 * A class for creating assertions for {@link ConstraintViolation}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class ConstraintViolationAssertions {

    /**
     * Creates a new instance for verifying specified actual value.
     *
     * @param actual the actual value to verify.
     * @param <T>    the type of the root bean of {@code actual}.
     * @return a new assertion instance for {@code actual}.
     */
    public static <T> ConstraintViolationAssert<T> assertThat(final ConstraintViolation<T> actual) {
        return new ConstraintViolationAssert<>(actual);
    }

    /**
     * Creates a new instance for an actual value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the actual value; must not be {@code null}.
     * @param <T>     the type of the root bean of {@code wrapper.actual}.
     * @return a new assertion instance for {@code wrapper.actual}.
     */
    public static <T> ConstraintViolationAssert<T> assertThat(
            final AbstractWrapper<? extends ConstraintViolation<T>> wrapper) {
        return assertThat(requireNonNull(wrapper, "wrapper is null").getActual());
    }

    /**
     * Creates a new instance for verifying specified actual value.
     *
     * @param actual the actual value to verify.
     * @param <T>    the type of the root bean of {@code actual}.
     * @return a new assertion instance for {@code actual}.
     * @see #assertThat(ConstraintViolation)
     */
    public static <T> ConstraintViolationAssert<T> assertConstraintViolation(final ConstraintViolation<T> actual) {
        return assertThat(actual);
    }

    private ConstraintViolationAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
