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

import java.util.Set;

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
public abstract class AbstractBeanAssertBase<
        SELF extends AbstractBeanAssertBase<SELF, ACTUAL, VALIDATOR, CONSTRAINT_VIOLATION>,
        ACTUAL,
        VALIDATOR,
        CONSTRAINT_VIOLATION>
        extends AbstractValidationAssert<SELF, ACTUAL, VALIDATOR>
        implements BeanAssert<SELF, ACTUAL, VALIDATOR, CONSTRAINT_VIOLATION> {

    protected AbstractBeanAssertBase(final ACTUAL actual, final Class<?> selfClass) {
        super(actual, selfClass);
    }

    protected abstract Set<CONSTRAINT_VIOLATION> validate();

    @Override
    public SELF isValid() {
        return isNotNull()
                .satisfies(a -> {
                    assertThat(validate())
                            .isEmpty();
                });
    }

    protected abstract Set<CONSTRAINT_VIOLATION> validateProperty(@NotNull String propertyName);

    @Override
    public SELF hasValidProperty(final @NotNull String propertyName) {
        requireNonNull(propertyName, "propertyName is null");
        return isNotNull().satisfies(a -> {
            final Set<CONSTRAINT_VIOLATION> violations = validateProperty(propertyName);
            assertThat(violations)
                    .isEmpty();
        });
    }
}
