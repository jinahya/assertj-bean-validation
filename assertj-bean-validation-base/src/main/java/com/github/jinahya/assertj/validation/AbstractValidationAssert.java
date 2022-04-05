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

@SuppressWarnings({"java:S119"})
abstract class AbstractValidationAssert<
        SELF extends AbstractValidationAssert<SELF, ACTUAL, VALIDATOR>,
        ACTUAL,
        VALIDATOR>
        extends AbstractAssert<SELF, ACTUAL>
        implements ValidationAssert<SELF, ACTUAL, VALIDATOR> {

    protected AbstractValidationAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    /**
     * Returns the validator configured to use.
     *
     * @return the validator configured to use.
     */
    protected @Nullable VALIDATOR getValidator() {
        return validator;
    }

    /**
     * Replaces the validator, currently configured to use, with specified value.
     *
     * @param validator new validator to use.
     */
    protected void setValidator(@Nullable VALIDATOR validator) {
        this.validator = validator;
    }

    /**
     * Configure this assertion object to use specified validator.
     *
     * @param validator the validator to use; may be {@code null}.
     * @return this assertion object.
     */
    @Override
    public @NotNull SELF usingValidator(final @Nullable VALIDATOR validator) {
        setValidator(validator);
        return myself;
    }

    /**
     * Returns an array of targeting groups.
     *
     * @return an array of targeting groups; may be {@code empty}.
     */
    protected @NotNull Class<?>[] getGroups() {
        return groups.toArray(new Class<?>[0]);
    }

    /**
     * Replaces current targeting groups with specified value.
     *
     * @param groups new targeting groups; may be {@code null}.
     */
    protected void setGroups(final @Nullable Class<?>[] groups) {
        this.groups.clear();
        if (groups != null) {
            Arrays.stream(groups)
                    .filter(Objects::nonNull)
                    .forEach(this.groups::add);
        }
    }

    /**
     * Configure this assertion object to use specified groups targeted for validation.
     *
     * @param groups the groups targeted for validation; may be {@code null} or empty.
     * @return this assertion object.
     */
    @Override
    public @NotNull SELF targetingGroups(final @Nullable Class<?>... groups) {
        setGroups(groups);
        return myself;
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
