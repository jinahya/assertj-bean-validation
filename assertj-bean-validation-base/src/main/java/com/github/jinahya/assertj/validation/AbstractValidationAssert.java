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

/**
 * An abstract base class for verifying beans and values.
 *
 * @param <SELF>      self type parameter
 * @param <ACTUAL>    actual type parameter
 * @param <VALIDATOR> type of validator
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
abstract class AbstractValidationAssert<
        SELF extends AbstractValidationAssert<SELF, ACTUAL, VALIDATOR>,
        ACTUAL,
        VALIDATOR>
        extends AbstractAssert<SELF, ACTUAL>
        implements ValidationAssert<SELF, ACTUAL, VALIDATOR> {

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual   the actual value to verify.
     * @param selfType self type class.
     */
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
     * Replaces currently configured validator with specified value.
     *
     * @param validator new validator to use.
     */
    protected void setValidator(@Nullable VALIDATOR validator) {
        this.validator = validator;
    }

    @Override
    public @NotNull SELF usingValidator(final @Nullable VALIDATOR validator) {
        setValidator(validator);
        return myself;
    }

    /**
     * Returns currently configured groups targeted for validation.
     *
     * @return an array of targeting groups; may be {@code empty}.
     */
    protected @NotNull Class<?>[] getGroups() {
        return groups.toArray(new Class<?>[0]);
    }

    /**
     * Replaces currently configured groups for validation with specified value.
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
