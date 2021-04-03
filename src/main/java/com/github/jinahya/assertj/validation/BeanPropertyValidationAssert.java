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

import static com.github.jinahya.assertj.validation.BeanValidationUtils.validateValue;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for validating a value against constraints defined on a bean property.
 *
 * @param <ACTUAL> actual property value type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public class BeanPropertyValidationAssert<ACTUAL>
        extends AbstractBeanValidationAssert<BeanPropertyValidationAssert<ACTUAL>, ACTUAL> {

    /**
     * Creates a new instance with specified value.
     *
     * @param actual the value for a property.
     */
    public BeanPropertyValidationAssert(final ACTUAL actual) {
        super(actual, BeanPropertyValidationAssert.class);
    }

    /**
     * Verifies that the {@link #actual actual} would be valid for specified property of specified class.
     *
     * @param beanType     the class whose all constraints placed on specified property are examined.
     * @param propertyName the name of the property.
     * @param <T>          bean type parameter
     * @return {@link #myself self}.
     */
    public <T> BeanPropertyValidationAssert<ACTUAL> isValidFor(final Class<T> beanType, final String propertyName) {
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        assertThat(validateValue(validator(), beanType, propertyName, actual, groups())).isEmpty();
        return myself;
    }

    /**
     * Verifies that {@link #actual actual} is not valid for specified property of specified class and accepts a
     * non-empty set of constraint violations to specified consumer.
     *
     * @param beanType     the class whose all constraints places on specified property are examined.
     * @param propertyName the name of the property.
     * @param consumer     the consumer accepts a set of constraint violations whose elements are all instances of
     *                     either {@code javax.validation.ConstraintViolation} or {@code jakarta.validation.ConstraintViolation};
     *                     may be {@code null}.
     * @param <T>          bean type parameter
     * @return {@link #myself self}.
     * @see #isValidFor(Class, String)
     * @see #isNotValidFor(Class, String)
     */
    public <T> BeanPropertyValidationAssert<ACTUAL> isNotValidFor(final Class<T> beanType, final String propertyName,
                                                                  final Consumer<? super Set<?>> consumer) {
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        final Set<Object> violations = validateValue(validator(), beanType, propertyName, actual, groups());
        assertThat(violations).isNotEmpty();
        if (consumer != null) {
            consumer.accept(violations);
        }
        return myself;
    }

    /**
     * Verifies that {@link #actual actual} is not valid for specified property of specified class.
     *
     * @param beanType     the class whose all constraints places on specified property are examined.
     * @param propertyName the name of the property.
     * @param <T>          bean type parameter
     * @return {@link #myself self}.
     * @see #isValidFor(Class, String)
     * @see #isNotValidFor(Class, String, Consumer)
     */
    public <T> BeanPropertyValidationAssert<ACTUAL> isNotValidFor(final Class<T> beanType, final String propertyName) {
        return isNotValidFor(beanType, propertyName, null);
    }
}
