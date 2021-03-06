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
 * An abstract assertion class for validating a value against constraints defined on a property of a bean type.
 *
 * @param <SELF>                self type parameter
 * @param <VALIDATOR>           the type of {@code Validator}
 * @param <CONSTRAINT_VIOLATION> the type of {@code ConstraintViolation}
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractValueAssert<
        SELF extends AbstractValueAssert<SELF, VALIDATOR, CONSTRAINT_VIOLATION>,
        VALIDATOR,
        CONSTRAINT_VIOLATION>
        extends AbstractValidationAssert<SELF, Object, VALIDATOR> {

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual   the actual value to verify.
     * @param selfType a self type.
     */
    protected AbstractValueAssert(final Object actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    /**
     * Validates given actual value against specified bean type and property name.
     *
     * @param <T>          bean type parameter
     * @param actual       the {@link #actual actual value} to validate.
     * @param beanType     the bean type.
     * @param propertyName the name of the property.
     * @return a set of constraint violations.
     */
    @SuppressWarnings({"java:S1452"})
    protected abstract <T> Set<? extends CONSTRAINT_VIOLATION> validateValue(Object actual, Class<T> beanType,
                                                                             String propertyName);

    /**
     * Verifies that the {@link #actual actual value} would be valid for a property of specified class.
     *
     * @param beanType     the class whose all constraints placed on specified property are examined.
     * @param propertyName the name of the property.
     * @param <T>          bean type parameter
     * @return {@link #myself self}.
     */
    public <T> SELF isValidFor(final Class<T> beanType, final String propertyName) {
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        return satisfies(a -> {
            final Set<? extends CONSTRAINT_VIOLATION> constraintViolations = validateValue(a, beanType, propertyName);
            assertThat(constraintViolations).isEmpty();
        });
    }

    /**
     * Verifies that {@link #actual actual value} is not valid for a property of specified bean type and accepts a
     * non-empty set of constraint violations to specified consumer.
     *
     * @param beanType     the bean type.
     * @param propertyName the name of the property.
     * @param consumer     the consumer accepts and verifies the non-empty set of constraint violations; may be {@code
     *                     null}.
     * @param <T>          bean type parameter
     * @return {@link #myself self}.
     */
    public <T> SELF isNotValidFor(final Class<T> beanType, final String propertyName,
                                  final Consumer<? super Set<? extends CONSTRAINT_VIOLATION>> consumer) {
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        return satisfies(a -> {
            final Set<? extends CONSTRAINT_VIOLATION> constraintViolations = validateValue(a, beanType, propertyName);
            assertThat(constraintViolations).isNotEmpty();
            if (consumer != null) {
                consumer.accept(constraintViolations);
            }
        });
    }

    /**
     * Verifies that {@link #actual actual value} is not valid for a property of specified bean type.
     *
     * @param <T>          bean type parameter
     * @param beanType     the bean type.
     * @param propertyName the name of the property.
     * @return {@link #myself self}.
     */
    public <T> SELF isNotValidFor(final Class<T> beanType, final String propertyName) {
        return isNotValidFor(beanType, propertyName, null);
    }
}
