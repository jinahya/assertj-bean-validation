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

import static com.github.jinahya.assertj.validation.BeanValidationUtils.validate;
import static com.github.jinahya.assertj.validation.BeanValidationUtils.validateProperty;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for validating a bean and its properties.
 *
 * @param <ACTUAL> actual bean type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public class BeanValidationAssert<ACTUAL>
        extends AbstractBeanValidationAssert<BeanValidationAssert<ACTUAL>, ACTUAL> {

    /**
     * Creates a new instance with specified bean.
     *
     * @param actual the actual bean to verify.
     * @see #actual
     */
    public BeanValidationAssert(final ACTUAL actual) {
        super(actual, BeanValidationAssert.class);
    }

    // -------------------------------------------------------------------------------------------------------- validate

    /**
     * Verifies that the {@link #actual actual} is valid.
     *
     * @return {@link #myself self}.
     * @see #isNotValid(Consumer)
     * @see #isNotValid()
     */
    public BeanValidationAssert<ACTUAL> isValid() {
        isNotNull();
        assertThat(validate(validator(), actual, groups())).isEmpty();
        return myself;
    }

    /**
     * Verifies that the {@link #actual} is not valid and accepts a non-empty set of constraint violations to specified
     * consumer.
     *
     * @param consumer the consumer accepts a set of constraint violations whose elements are all instances of either
     *                 {@code javax.validation.ConstraintViolation} or {@code jakarta.validation.ConstraintViolation};
     *                 may be {@code null}.
     * @return {@link #myself self}.
     * @see #isNotValid()
     */
    public BeanValidationAssert<ACTUAL> isNotValid(final Consumer<? super Set<?>> consumer) {
        isNotNull();
        final Set<Object> violations = validate(validator(), actual, groups());
        assertThat(violations).isNotEmpty();
        if (consumer != null) {
            consumer.accept(violations);
        }
        return myself;
    }

    /**
     * Verifies that the {@link #actual} is not valid.
     *
     * @return {@link #myself self}.
     * @see #isNotValid(Consumer)
     */
    public BeanValidationAssert<ACTUAL> isNotValid() {
        return isNotValid(null);
    }

    // ------------------------------------------------------------------------------------------------ validateProperty

    /**
     * Verifies that the {@link #actual actual}'s current property of specified name is valid.
     *
     * @param propertyName the name of the property to validate.
     * @return {@link #myself self}
     * @see #doesNotHaveValidProperty(String, Consumer)
     * @see #doesNotHaveValidProperty(String)
     */
    public BeanValidationAssert<ACTUAL> hasValidProperty(final String propertyName) {
        requireNonNull(propertyName, "propertyName is null");
        isNotNull();
        assertThat(validateProperty(validator(), actual, propertyName, groups())).isEmpty();
        return myself;
    }

    /**
     * Verifies that the {@link #actual actual}'s current property of specified name is not valid and accepts a
     * non-empty set of constraint violations to specified consumer.
     *
     * @param propertyName the name of the property to validate.
     * @param consumer     the consumer accepts a set of constraint violations whose elements are all instances of
     *                     either {@code javax.validation.ConstraintViolation} or {@code jakarta.validation.ConstraintViolation};
     *                     may be {@code null}.
     * @return {@link #myself self}.
     * @see #hasValidProperty(String)
     * @see #doesNotHaveValidProperty(String)
     */
    public BeanValidationAssert<ACTUAL> doesNotHaveValidProperty(final String propertyName,
                                                                 final Consumer<? super Set<Object>> consumer) {
        requireNonNull(propertyName, "propertyName is null");
        isNotNull();
        final Set<Object> violations = validateProperty(validator(), actual, propertyName, groups());
        assertThat(violations).isNotEmpty();
        if (consumer != null) {
            consumer.accept(violations);
        }
        return myself;
    }

    /**
     * Verifies that the {@link #actual actual}'s current property of specified name is not valid.
     *
     * @param propertyName the name of the property to validate.
     * @return {@link #myself self}.
     * @see #hasValidProperty(String)
     * @see #doesNotHaveValidProperty(String, Consumer)
     */
    public BeanValidationAssert<ACTUAL> doesNotHaveValidProperty(final String propertyName) {
        return doesNotHaveValidProperty(propertyName, null);
    }
}
