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
 * An assertion class for validating values against constraints defined on property of specified bean type.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
abstract class AbstractValueAssert<
        SELF extends AbstractValueAssert<SELF, VALIDATOR, CONSTRAINTVIOLATION>,
        VALIDATOR,
        CONSTRAINTVIOLATION>
        extends AbstractValidationAssert<SELF, Object, VALIDATOR> {

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to verify.
     */
    AbstractValueAssert(final Object actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Validates given actual against specified bean type and property name.
     *
     * @param <T>          bean type parameter
     * @param actual       the {@link #actual} value.
     * @param beanType     the bean type.
     * @param propertyName the property name.
     */
    abstract <T> Set<? extends CONSTRAINTVIOLATION> validateValue(Object actual, Class<T> beanType,
                                                                  String propertyName);

    /**
     * Verifies that the {@link #actual actual} would be valid for specified property of specified class.
     *
     * @param beanType     the class whose all constraints placed on specified property are examined.
     * @param propertyName the name of the property.
     * @param <T>          bean type parameter
     * @return {@link #myself self}.
     * @see #isNotValidFor(Class, String, Consumer)
     * @see #isNotValidFor(Class, String)
     */
    public <T> SELF isValidFor(final Class<T> beanType, final String propertyName) {
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        return satisfies(a -> {
            assertThat(validateValue(a, beanType, propertyName)).isEmpty();
        });
    }

    /**
     * Verifies that {@link #actual actual} is not valid for specified property of specified bean type and accepts a
     * non-empty set of constraint violations to specified consumer.
     *
     * @param beanType     the bean type.
     * @param propertyName the name of the property.
     * @param consumer     the consumer accepts and verifies the non-empty set of {@code ConstraintViolation}s.
     * @param <T>          bean type parameter
     * @return {@link #myself self}.
     * @see #isNotValidFor(Class, String)
     * @see #isValidFor(Class, String)
     */
    public <T> SELF isNotValidFor(final Class<T> beanType, final String propertyName,
                                  final Consumer<? super Set<? extends CONSTRAINTVIOLATION>> consumer) {
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        return satisfies(a -> {
            final Set<? extends CONSTRAINTVIOLATION> constraintViolations = validateValue(a, beanType, propertyName);
            assertThat(constraintViolations).isNotEmpty();
            if (consumer != null) {
                consumer.accept(constraintViolations);
            }
        });
    }

    /**
     * Verifies that {@link #actual actual} is not valid for a property of specified name of specified bean type.
     *
     * @param beanType     the bean type.
     * @param propertyName the name of the property.
     * @return {@link #myself self}.
     * @see #isNotValidFor(Class, String, Consumer)
     * @see #isValidFor(Class, String)
     */
    public <T> SELF isNotValidFor(final Class<T> beanType, final String propertyName) {
        return isNotValidFor(beanType, propertyName, null);
    }
}
