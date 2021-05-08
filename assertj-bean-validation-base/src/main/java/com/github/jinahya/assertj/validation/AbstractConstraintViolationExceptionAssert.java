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

import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.IterableAssert;

import java.util.Set;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract assertion class for verifying values of {@code ConstraintViolationException} class.
 *
 * @param <SELF>                 self type parameter
 * @param <ACTUAL>               the type of {@code ConstraintViolationException}.
 * @param <CONSTRAINT_VIOLATION> the type of {@code ConstraintViolation}.
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractConstraintViolationExceptionAssert<
        SELF extends AbstractConstraintViolationExceptionAssert<SELF, ACTUAL, CONSTRAINT_VIOLATION>,
        ACTUAL extends RuntimeException,
        CONSTRAINT_VIOLATION>
        extends AbstractExtendedValidationExceptionAssert<SELF, ACTUAL> {

    /**
     * An interface for getting values from an actual value of {@code ConstraintViolationException}.
     *
     * @param <CONSTRAINT_VIOLATION_EXCEPTION> actual type of {@code ConstraintViolationException}
     * @param <CONSTRAINT_VIOLATION>           the type of {@code ConstraintViolation} that {@code ACTUAL} holds.
     */
    @FunctionalInterface
    protected interface Accessor<CONSTRAINT_VIOLATION_EXCEPTION, CONSTRAINT_VIOLATION> {

        /**
         * Returns the value of {@code getConstraintViolation()} from specified {@code ConstraintViolationException}
         * value.
         *
         * @param actual the {@code ConstraintViolationException} value.
         * @return the value of {@code actual.getConstraintViolations()}.
         */
        Set<CONSTRAINT_VIOLATION> getConstraintViolations(CONSTRAINT_VIOLATION_EXCEPTION actual);
    }

    // ---------------------------------------------------------------------------------------------------- constructors

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual   the actual value to verify.
     * @param selfType a self type.
     * @param accessor an accessor for getting values from {@code actual}.
     */
    protected AbstractConstraintViolationExceptionAssert(final ACTUAL actual, final Class<?> selfType,
                                                         final Accessor<ACTUAL, CONSTRAINT_VIOLATION> accessor) {
        super(actual, selfType);
        this.accessor = requireNonNull(accessor, "accessor is null");
    }

    // --------------------------------------------------------------------------------------- getConstraintViolations()

    /**
     * Returns an assertion for the value of {@code actual.getConstraintViolations()}.
     *
     * @return an assertion of {@code actual.getConstraintViolations()}.
     */
    public IterableAssert<CONSTRAINT_VIOLATION> constraintViolations() {
        isNotNull();
        final Set<CONSTRAINT_VIOLATION> constraintViolations = accessor.getConstraintViolations(actual);
        return new IterableAssert<>(constraintViolations);
    }

    /**
     * Verifies that the value of {@code actual.getConstraintViolations()} satisfies given requirements expressed as a
     * {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the value of {@code actual.getConstraintViolations()}.
     * @return {@link #myself self}.
     */
    public SELF hasConstraintViolationsSatisfying(
            final Consumer<? super Set<CONSTRAINT_VIOLATION>> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull()
                .satisfies(a -> {
                    final Set<CONSTRAINT_VIOLATION> constraintViolations = accessor.getConstraintViolations(a);
                    requirements.accept(constraintViolations);
                })
                ;
    }

    /**
     * Verifies that the value of {@code actual.getConstraintViolations()} is {@link
     * AbstractIterableAssert#isEqualTo(Object) equal} to specified value.
     *
     * @param expected the expected value of {@code actual#getConstraintViolations()}.
     * @return {@link #myself self}.
     */
    public SELF hasConstraintViolationsEqualTo(final Object expected) {
        return hasConstraintViolationsSatisfying(v -> {
            assertThat(v)
                    .isEqualTo(expected);
        });
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The accessor for getting values from {@link #actual}.
     */
    protected final Accessor<ACTUAL, CONSTRAINT_VIOLATION> accessor;
}
