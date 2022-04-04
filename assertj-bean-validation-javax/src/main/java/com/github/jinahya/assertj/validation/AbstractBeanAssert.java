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

import org.jetbrains.annotations.NotNull;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"java:S119"})
public abstract class AbstractBeanAssert<SELF extends AbstractBeanAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractValidationAssert<SELF, ACTUAL, Validator> {

    protected AbstractBeanAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    protected @NotNull Validator getValidator() {
        return Optional.ofNullable(super.getValidator())
                .orElseGet(() -> {
                    setValidator(Validation.buildDefaultValidatorFactory().getValidator());
                    return getValidator();
                });
    }

    public @NotNull SELF isValid(final @NotNull Consumer<? super ConstraintViolation<ACTUAL>> consumer) {
        return isNotNull()
                .satisfies(a -> {
                    final Validator validator = getValidator();
                    final Class<?>[] groups = getGroups();
                    final Set<ConstraintViolation<ACTUAL>> violations = validator.validate(actual, groups);
                    violations.forEach(consumer);
                    assertThat(violations)
                            .as("validating actual...")
                            .withFailMessage("expected no constraint violations but got %s", violations)
                            .isEmpty();
                })
                ;
    }

    public @NotNull SELF isValid() {
        return isValid(
                v -> {
                }
        );
    }

    /**
     * Verifies that all constraints placed on the property of specified name, of {@link #actual}, are validated.
     *
     * @param propertyName the name of the property to validate.
     * @param consumer     the consumer accepts constraint violations.
     * @return this assertion object.
     */
    public @NotNull SELF hasValidProperty(final @NotNull String propertyName,
                                          final @NotNull Consumer<? super ConstraintViolation<ACTUAL>> consumer) {
        return isNotNull()
                .satisfies(a -> {
                    final Validator validator = getValidator();
                    final Class<?>[] groups = getGroups();
                    final Set<ConstraintViolation<ACTUAL>> violations
                            = validator.validateProperty(actual, propertyName, groups);
                    violations.forEach(consumer);
                    assertThat(violations)
                            .as("validating %s...", propertyName)
                            .withFailMessage("expected no constraint violations but got %s", violations)
                            .isEmpty();
                })
                ;
    }

    protected @NotNull SELF hasValidProperty(final @NotNull String propertyName) {
        return hasValidProperty(
                propertyName,
                v -> {
                    // does nothing.
                }
        );
    }
}
