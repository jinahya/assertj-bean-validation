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
 * An assertion class for validating values against constraints defined on property of specified bean type.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
abstract class AbstractValueAssertBase<
        SELF extends AbstractValueAssertBase<SELF, ACTUAL, VALIDATOR, CONSTRAINT_VIOLATION>,
        ACTUAL,
        VALIDATOR,
        CONSTRAINT_VIOLATION>
        extends AbstractValidationAssert<SELF, ACTUAL, VALIDATOR>
        implements ValueAssert<SELF, ACTUAL, VALIDATOR> {

    protected AbstractValueAssertBase(final @NotNull ACTUAL actual, final @NotNull Class<?> selfType) {
        super(actual, selfType);
    }

    protected abstract <T> Set<? extends CONSTRAINT_VIOLATION> validateValue(
            @NotNull Class<T> beanType, @NotNull String propertyName);

    @Override
    public <T> SELF isValidFor(final @NotNull Class<T> beanType, final @NotNull String propertyName) {
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        return satisfies(a -> {
            assertThat(validateValue(beanType, propertyName))
                    .isEmpty();
        });
    }
}
