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
     * Creates a new assertion instance for specified actual constraint violation value.
     *
     * @param actual the actual constraint violation value to verify.
     * @param <T>    the type of the root bean of {@code actual}
     * @return a new assertion instance.
     */
    public static <T> ConstraintViolationAssert<T> assertThat(final Object actual) {
        return new ConstraintViolationAssert<>(actual);
    }

    /**
     * Creates a new assertion instance for an actual constraint violation value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the actual constraint violation value; must not be {@code null}.
     * @param <T>     the type of the root bean of {@code actual}
     * @return a new assertion instance.
     */
    public static <T> ConstraintViolationAssert<T> assertThat(final ConstraintViolationWrapper<T> wrapper) {
        return assertThat(requireNonNull(wrapper, "wrapper is null").getActual());
    }

    /**
     * Creates a new assertion instance for specified actual constraint violation value.
     *
     * @param actual the constraint violation value to assert.
     * @param <T>    the type of the root bean of {@code <ACTUAL>}
     * @return a new assertion instance.
     */
    public static <T> ConstraintViolationAssert<T> assertConstraintViolation(final Object actual) {
        return assertThat(actual);
    }

    private ConstraintViolationAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
