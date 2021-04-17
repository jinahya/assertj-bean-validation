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

import java.util.Set;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for validating a bean and its properties.
 *
 * @param <ACTUAL>              actual bean type parameter
 * @param <CONSTRAINTVIOLATION> the type of {@code ConstraintViolation}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
abstract class AbstractBeanAssert<
        SELF extends AbstractBeanAssert<SELF, ACTUAL, VALIDATOR, CONSTRAINTVIOLATION>,
        ACTUAL,
        VALIDATOR,
        CONSTRAINTVIOLATION>
        extends AbstractValidationAssert<SELF, ACTUAL, VALIDATOR> {

    /**
     * Creates a new instance with specified bean.
     *
     * @param actual the actual bean to verify.
     * @see #actual
     */
    AbstractBeanAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    // -------------------------------------------------------------------------------------------------------- validate
    abstract Set<CONSTRAINTVIOLATION> validate(ACTUAL actual);

    /**
     * Verifies that the {@link #actual actual} is valid.
     *
     * @return {@link #myself self}.
     * @see #isNotValid(Consumer)
     * @see #isNotValid()
     */
    public SELF isValid() {
        return isNotNull().satisfies(a -> {
            assertThat(validate(a)).isEmpty();
        });
    }

    /**
     * Verifies that the {@link #actual} is not valid and accepts a non-empty set of constraint violations to specified
     * consumer.
     *
     * @param consumer the consumer accepts and verifies the set of constraint violations.
     * @return {@link #myself self}.
     * @see #isNotValid()
     */
    public SELF isNotValid(final Consumer<? super Set<CONSTRAINTVIOLATION>> consumer) {
        return isNotNull().satisfies(a -> {
            final Set<CONSTRAINTVIOLATION> constraintViolations = validate(a);
            assertThat(constraintViolations).isNotEmpty();
            if (consumer != null) {
                consumer.accept(constraintViolations);
            }
        });
    }

    /**
     * Verifies that the {@link #actual} is not valid.
     *
     * @return {@link #myself self}.
     * @see #isNotValid(Consumer)
     */
    public SELF isNotValid() {
        return isNotValid(null);
    }

    // ------------------------------------------------------------------------------------------------ validateProperty
    abstract Set<CONSTRAINTVIOLATION> validateProperty(final ACTUAL actual, final String propertyName);

    /**
     * Verifies that the {@link #actual actual}'s current property of specified name is valid.
     *
     * @param propertyName the name of the property to validate.
     * @return {@link #myself self}
     */
    public SELF hasValidProperty(final String propertyName) {
        requireNonNull(propertyName, "propertyName is null");
        return isNotNull().satisfies(a -> {
            final Set<CONSTRAINTVIOLATION> constraintViolations = validateProperty(a, propertyName);
            assertThat(constraintViolations).isEmpty();
        });
    }

    /**
     * Verifies that the {@link #actual actual}'s current property of specified name is not valid and accepts a
     * non-empty set of constraint violations to specified consumer.
     *
     * @param propertyName the name of the property whose value is validated.
     * @param consumer     the consumer accepts a non-empty set of constraint violations whose elements are all
     *                     instances of {@code T<ACTUAL>}.
     * @return {@link #myself self}.
     * @see #hasValidProperty(String)
     * @see #doesNotHaveValidProperty(String)
     */
    public SELF doesNotHaveValidProperty(
            final String propertyName, final Consumer<? super Set<CONSTRAINTVIOLATION>> consumer) {
        requireNonNull(propertyName, "propertyName is null");
        return isNotNull().satisfies(a -> {
            final Set<CONSTRAINTVIOLATION> constraintViolations = validateProperty(a, propertyName);
            assertThat(constraintViolations).isNotEmpty();
            if (consumer != null) {
                consumer.accept(constraintViolations);
            }
        });
    }

    /**
     * Verifies that the {@link #actual actual}'s current property of specified name is not valid.
     *
     * @param propertyName the name of the property to validate.
     * @return {@link #myself self}.
     */
    public SELF doesNotHaveValidProperty(final String propertyName) {
        return doesNotHaveValidProperty(propertyName, null);
    }
}
