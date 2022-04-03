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

import org.assertj.core.api.AbstractObjectAssert;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Optional;

abstract class AbstractValidationAssert<
        SELF extends AbstractValidationAssert<SELF, ACTUAL, VALIDATOR>,
        ACTUAL,
        VALIDATOR>
        extends AbstractObjectAssert<SELF, ACTUAL>
        implements ValidationAssert<SELF, VALIDATOR> {

    protected AbstractValidationAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public SELF usingValidator(final VALIDATOR validator) {
        this.validator = validator;
        return (SELF) this;
    }

    protected VALIDATOR validator() {
        return Optional.ofNullable(validator)
                .orElseGet(this::defaultValidator);
    }

    protected abstract @NotNull VALIDATOR defaultValidator();

    @SuppressWarnings({"unchecked"})
    @Override
    public SELF targetingGroups(final Class<?>... groups) {
        this.groups = groups;
        return (SELF) this;
    }

    protected abstract @NotNull Class<?> defaultGroup();

    protected Class<?>[] groups() {
        return Optional.ofNullable(groups)
                .map(v -> Arrays.copyOf(v, v.length))
                .orElseGet(() -> new Class<?>[] {defaultGroup()});
    }

    /**
     * The validator being used.
     *
     * @see #validator()
     */
    private VALIDATOR validator;

    /**
     * The targeting groups being used.
     *
     * @see #groups()
     */
    private Class<?>[] groups;
}
