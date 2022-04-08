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

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

/**
 * An abstract assertion class for validating a value against constraints defined on a bean property.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractPropertyAssert<SELF extends AbstractPropertyAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractValidationAssert<SELF, ACTUAL, Validator> {

    /**
     * Creates a new instance with specified actual value and self type.
     *
     * @param actual   the actual value to verify.
     * @param selfType the self type.
     */
    protected AbstractPropertyAssert(final ACTUAL actual, final Class<SELF> selfType) {
        super(actual, selfType);
    }

    /**
     * {@inheritDoc}
     *
     * @return {@inheritDoc}
     * @implNote Overridden to return {@code Validation.buildDefaultValidatorFactory().getValidator()}.
     */
    @Override
    protected @NotNull Validator getValidator() {
        return Optional.ofNullable(super.getValidator())
                .orElseGet(Validation.buildDefaultValidatorFactory()::getValidator);
    }

    /**
     * Verifies that the {@link #actual actual} value is valid for the property of specified name of specified bean type
     * while accepting constraint violations, if any populated, to specified consumer.
     *
     * @param beanType     the bean type.
     * @param propertyName the name of the property.
     * @param consumer     the consumer accepts constraint violations.
     * @param <T>          type of the bean
     * @return this assertion instance.
     * @see #isValidFor(Class, String)
     */
    public @NotNull <T> SELF isValidFor(final @NotNull Class<T> beanType, final @NotNull String propertyName,
                                        final @NotNull Consumer<? super ConstraintViolation<T>> consumer) {
        Objects.requireNonNull(beanType, "beanType is null");
        Objects.requireNonNull(propertyName, "propertyName is null");
        Objects.requireNonNull(consumer, "consumer is null");
        return myself
                .satisfies(a -> {
                    final Validator validator = getValidator();
                    final Class<?>[] groups = getGroups();
                    final Set<ConstraintViolation<T>> violations
                            = validator.validateValue(beanType, propertyName, actual, groups);
                    violations.forEach(consumer);
                    Assertions.assertThat(violations)
                            .as("no constraint violations")
                            .withFailMessage("expected, for %s#%s, but got %s", beanType.getSimpleName(), propertyName, violations)
                            .isEmpty();
                });
    }

    /**
     * Verifies that the {@link #actual actual} value is valid for the property of specified name of specified bean
     * type.
     *
     * @param beanType     the bean type.
     * @param propertyName the name of the property.
     * @param <T>          type of the bean
     * @return this assertion instance.
     * @implNote This method invokes {@link #isValidFor(Class, String, Consumer)} method with {@code beanType}, {@code
     * propertyName}, and an empty consumer.
     * @see #isValidFor(Class, String, Consumer)
     */
    public @NotNull <T> SELF isValidFor(final @NotNull Class<T> beanType, final @NotNull String propertyName) {
        return isValidFor(
                beanType,
                propertyName,
                v -> {
                    // does nothing.
                }
        );
    }
}
