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

import java.util.Set;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for {@code ....validation.ConstraintViolationException} class.
 *
 * @param <ACTUAL>              the type of actual {@code ....validation.ConstraintViolationException}.
 * @param <CONSTRAINTVIOLATION> the type of {@code ConstraintViolation}.
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractConstraintViolationExceptionAssert<
        SELF extends AbstractConstraintViolationExceptionAssert<SELF, ACTUAL, CONSTRAINTVIOLATION>,
        ACTUAL extends RuntimeException,
        CONSTRAINTVIOLATION>
        extends AbstractExtendedValidationExceptionAssert<SELF, ACTUAL> {

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual   the actual value to verify.
     * @param selfType a self type.
     */
    protected AbstractConstraintViolationExceptionAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected abstract Set<? extends CONSTRAINTVIOLATION> getConstraintViolations();

    /**
     * Verifies that the {@code actual.getConstraintViolations()} satisfies specified requirements by being accepted to
     * specified consumer.
     *
     * @param requirements the consumer accepts and verifies the set of constraint violations contained in {@code
     *                     actual}.
     * @return {@link #myself self}.
     */
    public SELF hasConstraintViolationsSatisfying(
            final Consumer<? super Set<? extends CONSTRAINTVIOLATION>> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull().satisfies(a -> {
            final Set<? extends CONSTRAINTVIOLATION> constraintViolations = getConstraintViolations();
            requirements.accept(constraintViolations);
        });
    }

    /**
     * Verifies that the {@code actual.getConstraintViolations()} is {@link AbstractIterableAssert#isEqualTo(Object)
     * equal} to specified value.
     *
     * @param expected the expected value of {@code actual#getConstraintViolations()}.
     * @return {@link #myself self}.
     */
    public SELF hasConstraintViolationsEqualTo(final Object expected) {
        return hasConstraintViolationsSatisfying(v -> {
            assertThat(v).isEqualTo(expected);
        });
    }
}
