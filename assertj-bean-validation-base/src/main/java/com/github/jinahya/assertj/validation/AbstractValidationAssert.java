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

import org.assertj.core.api.AbstractAssert;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

abstract class AbstractValidationAssert<
        SELF extends AbstractValidationAssert<SELF, ACTUAL, VALIDATOR>,
        ACTUAL,
        VALIDATOR>
        extends AbstractAssert<SELF, ACTUAL>
        implements ValidationAssert<SELF, VALIDATOR> {

    protected AbstractValidationAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    protected VALIDATOR getValidator() {
        return validator;
    }

    protected void setValidator(VALIDATOR validator) {
        this.validator = validator;
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public @NotNull SELF usingValidator(final @Nullable VALIDATOR validator) {
        setValidator(validator);
        return (SELF) this;
    }

    protected @Nullable Class<?>[] getGroups() {
        return groups.toArray(new Class<?>[0]);
    }

    protected void setGroups(final @Nullable Class<?>[] groups) {
        this.groups.clear();
        if (groups != null) {
            Arrays.stream(groups)
                    .filter(Objects::nonNull)
                    .forEach(this.groups::add);
        }
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public @NotNull SELF targetingGroups(final Class<?>... groups) {
        setGroups(groups);
        return (SELF) this;
    }

    /**
     * The validator being used.
     */
    private VALIDATOR validator;

    /**
     * The targeting groups being used.
     */
    private final Set<@NotNull Class<?>> groups = new HashSet<>();
}
