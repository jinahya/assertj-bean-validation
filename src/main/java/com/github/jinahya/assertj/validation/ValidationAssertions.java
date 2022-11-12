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

import javax.validation.ConstraintViolation;

/**
 * A class for creating assertion instances.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S119" // <ACTUAL>
})
public final class ValidationAssertions {

    /**
     * Creates a new assertion object for verifying specified bean value.
     *
     * @param <ACTUAL> type of actual bean
     * @param actual   the bean value to verify.
     * @return a new assertion object for verifying {@code actual}.
     */
    public static <ACTUAL> BeanAssert<?, ACTUAL> assertThatBean(final ACTUAL actual) {
        return new BeanAssertImpl<>(actual);
    }

    /**
     * Creates a new assertion object for verifying specified value against a property of specific bean type.
     *
     * @param <ACTUAL> type of actual value
     * @param actual   the value of the property to verify.
     * @return a new assertion instance for {@code actual}.
     */
    public static <ACTUAL> PropertyAssert<?, ACTUAL> assertThatProperty(final ACTUAL actual) {
        return new PropertyAssertImpl<>(actual);
    }

    /**
     * Creates a new assertion object for verifying specified constraint violation value.
     *
     * @param actual the constraint violation value to verify.
     * @return a new assertion instance for {@code actual}.
     */
    static <T> ConstraintViolationAssert<?, T> assertThatConstraintViolation(
            final ConstraintViolation<T> actual) {
        return new ConstraintViolationAssertImpl<>(actual);
    }

    static <T> IterableConstraintViolationAssert<?, T> assertThatIterableConstraintViolations(
            final Iterable<? extends ConstraintViolation<T>> actual) {
        return new IterableConstraintViolationAssertImpl<>(actual);
    }

    /**
     * Creates a new instance.
     */
    private ValidationAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
