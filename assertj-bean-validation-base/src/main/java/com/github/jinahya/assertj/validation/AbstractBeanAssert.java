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

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for validating a bean and its properties.
 *
 * @param <ACTUAL>               actual bean type parameter
 * @param <CONSTRAINT_VIOLATION> the type of {@code ConstraintViolation}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractBeanAssert<
        SELF extends AbstractBeanAssert<SELF, ACTUAL, VALIDATOR, CONSTRAINT_VIOLATION>,
        ACTUAL,
        VALIDATOR,
        CONSTRAINT_VIOLATION>
        extends AbstractValidationAssert<SELF, ACTUAL, VALIDATOR> {

    /**
     * Creates a new instance with specified bean.
     *
     * @param actual   the actual bean to verify.
     * @param selfType a self type.
     */
    protected AbstractBeanAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    // -------------------------------------------------------------------------------------------------------- validate

    /**
     * Validates specified {@link #actual} bean object and returns a set of constraint violations.
     *
     * @param actual the {@code actual} bean object to validate.
     * @return a set of constraint violations.
     */
    protected abstract Set<CONSTRAINT_VIOLATION> validate(ACTUAL actual);

    /**
     * Verifies that the {@link #actual} bean object is valid. This method invokes {@link #validate(Object)} with {@link
     * #actual} and verifies that the result set is {@link Collection#isEmpty() empty}.
     *
     * @return {@link #myself self}.
     * @see #validate(Object)
     * @see #isNotValid(Consumer)
     * @see #isNotValid()
     */
    public SELF isValid() {
        return isNotNull().satisfies(a -> {
            assertThat(validate(a)).isEmpty();
        });
    }

    /**
     * Verifies that the {@link #actual} bean object is not valid and accepts a non-empty set of constraint violations
     * to specified consumer.
     *
     * @param consumer the consumer accepts and verifies a non-empty set of constraint violations.
     * @return {@link #myself self}.
     * @see #isNotValid()
     * @see #isValid()
     */
    public SELF isNotValid(final Consumer<? super Set<CONSTRAINT_VIOLATION>> consumer) {
        return isNotNull().satisfies(a -> {
            final Set<CONSTRAINT_VIOLATION> constraintViolations = validate(a);
            assertThat(constraintViolations).isNotEmpty();
            if (consumer != null) {
                consumer.accept(constraintViolations);
            }
        });
    }

    /**
     * Verifies that the {@link #actual} bean object is not valid.
     *
     * @return {@link #myself self}.
     * @see #isNotValid(Consumer)
     * @see #isValid()
     */
    public SELF isNotValid() {
        return isNotValid(null);
    }

    // ------------------------------------------------------------------------------------------------ validateProperty

    /**
     * Validates a property of {@link #actual} bean object and returns a set of constraint violations.
     *
     * @param actual       the {@link #actual} bean object whose property is verified.
     * @param propertyName the name of the property to validate.
     * @return a set of constraint violations.
     */
    protected abstract Set<CONSTRAINT_VIOLATION> validateProperty(final ACTUAL actual, final String propertyName);

    /**
     * Verifies that the {@link #actual}'s current property of specified name is valid. This method invokes {@link
     * #validateProperty(Object, String)} method with {@link #actual} and specified property name and verifies that the
     * result set is empty.
     *
     * @param propertyName the name of the property to validate.
     * @return {@link #myself self}
     * @see #doesNotHaveValidProperty(String, Consumer)
     * @see #doesNotHaveValidProperty(String)
     */
    public SELF hasValidProperty(final String propertyName) {
        requireNonNull(propertyName, "propertyName is null");
        return isNotNull().satisfies(a -> {
            final Set<CONSTRAINT_VIOLATION> constraintViolations = validateProperty(a, propertyName);
            assertThat(constraintViolations).isEmpty();
        });
    }

    /**
     * Verifies that the {@link #actual}'s current property of specified name is not valid and accepts a non-empty set
     * of constraint violations to specified consumer.
     *
     * @param propertyName the name of the property to validate.
     * @param consumer     the consumer accepts and verifies a non-empty set of constraint violations.
     * @return {@link #myself self}.
     * @see #doesNotHaveValidProperty(String)
     * @see #hasValidProperty(String)
     */
    public SELF doesNotHaveValidProperty(
            final String propertyName, final Consumer<? super Set<CONSTRAINT_VIOLATION>> consumer) {
        requireNonNull(propertyName, "propertyName is null");
        return isNotNull().satisfies(a -> {
            final Set<CONSTRAINT_VIOLATION> constraintViolations = validateProperty(a, propertyName);
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
     * @see #doesNotHaveValidProperty(String, Consumer)
     * @see #hasValidProperty(String)
     */
    public SELF doesNotHaveValidProperty(final String propertyName) {
        return doesNotHaveValidProperty(propertyName, null);
    }
}
