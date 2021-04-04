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

import org.assertj.core.api.AbstractThrowableAssert;

import static org.assertj.core.api.Assertions.assertThat;

public abstract class AbstractViolationExceptionAssert<
        SELF extends AbstractViolationExceptionAssert<SELF, ACTUAL>,
        ACTUAL extends Exception>
        extends AbstractThrowableAssert<SELF, ACTUAL> {

    protected AbstractViolationExceptionAssert(ACTUAL actual, final Class<?> selfClass) {
        super(actual, selfClass);
        ViolationExceptionUtils.requireViolationExceptionInstance(super.actual);
    }

    /**
     * Verifies that the {@link #actual} is an instance of {@code ....validation.ConstraintViolationException}.
     *
     * @return {@link #myself self}.
     */
    public SELF isConstraintViolationException() {
        return isNotNull().satisfies(a -> {
            assertThat(ConstraintViolationExceptionUtils.isConstraintViolationExceptionInstance(a)).isTrue();
        });
    }

    /**
     * Verifies that the {@link #actual} value is an instance of {@code ....validation.ConstraintViolationException},
     * and returns a {@link ConstraintViolationExceptionAssert} for subsequent assertions.
     *
     * @param <T> constraint violation exception type parameter
     * @return {@link #myself self}.
     */
    @SuppressWarnings({"unchecked"})
    public <T extends Exception> ConstraintViolationExceptionAssert<T> asConstraintViolationException() {
        isConstraintViolationException();
        return new ConstraintViolationExceptionAssert<>((T) actual);
    }
}
