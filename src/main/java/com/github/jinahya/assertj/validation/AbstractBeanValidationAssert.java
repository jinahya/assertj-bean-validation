/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2021 the original author or authors.
 */
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

import javax.validation.Validator;
import javax.validation.constraints.NotNull;

/**
 * An abstract class for Bean-Validation assertion classes.
 *
 * @param <SELF> self type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
abstract class AbstractBeanValidationAssert<SELF extends AbstractBeanValidationAssert<SELF>>
        extends AbstractAssert<SELF, Object> {

    AbstractBeanValidationAssert(final Object actual, final @NotNull Class<SELF> selfType) {
        super(actual, selfType);
    }

    /**
     * Replaces current validator being used with specified value.
     *
     * @param validator new validator.
     * @return {@link #myself}.
     */
    @SuppressWarnings({"unchecked"})
    public @NotNull SELF using(final Validator validator) {
        this.validator = validator;
        return (SELF) this;
    }

    /**
     * Replaces current targeting groups with specified values.
     *
     * @param groups new targeting groups.
     * @return {@link #myself}.
     */
    @SuppressWarnings({"unchecked"})
    public @NotNull SELF targeting(final Class<?>... groups) {
        this.groups = groups;
        return (SELF) this;
    }

    /**
     * Returns current validator being used.
     *
     * @return current validator being used.
     */
    protected @NotNull Validator validator() {
        if (validator == null) {
            validator = BeanValidationUtils.validator();
        }
        return validator;
    }

    /**
     * Returns current targeting groups.
     *
     * @return current targeting groups.
     */
    protected @NotNull Class<?>[] groups() {
        if (groups == null) {
            groups = new Class<?>[0];
        }
        return groups;
    }

    private Validator validator;

    private Class<?>[] groups;
}
