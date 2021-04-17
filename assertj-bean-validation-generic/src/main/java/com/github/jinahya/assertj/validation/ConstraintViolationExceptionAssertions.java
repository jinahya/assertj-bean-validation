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

public final class ConstraintViolationExceptionAssertions {

    /**
     * Creates a new assertion instance for specified actual value.
     *
     * @param actual   the actual value to verify.
     * @param <ACTUAL> actual value type parameter
     * @return a new assertion instance.
     */
    public static <ACTUAL extends RuntimeException> ConstraintViolationExceptionAssert<ACTUAL>
    assertConstraintViolationException(final ACTUAL actual) {
        return new ConstraintViolationExceptionAssert<>(actual);
    }

    /**
     * Creates a new assertion instance for the actual value wrapped in specified wrapper.
     *
     * @param wrapper  the wrapper wraps the actual value; must not be {@code null}.
     * @param <ACTUAL> actual value type parameter
     * @return a new assertion instance.
     * @see ConstraintViolationExceptionWrapper#constraintViolationException(Object)
     */
    public static <ACTUAL extends RuntimeException> ConstraintViolationExceptionAssert<ACTUAL> assertThat(
            final ConstraintViolationExceptionWrapper<? extends ACTUAL> wrapper) {
        return assertConstraintViolationException(requireNonNull(wrapper).getActual());
    }

    private ConstraintViolationExceptionAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
