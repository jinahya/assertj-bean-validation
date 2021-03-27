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

/**
 * An abstract class for Bean-Validation assertion classes.
 *
 * @param <SELF> self type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractBeanValidationAssert<SELF extends AbstractBeanValidationAssert<SELF>>
        extends AbstractAssert<SELF, Object> {

    /**
     * Creates a new instance with specified actual value and self type.
     *
     * @param actual   the actual value.
     * @param selfType the self type.
     */
    protected AbstractBeanValidationAssert(final Object actual, final Class<SELF> selfType) {
        super(actual, selfType);
    }

    /**
     * Sets a validator for subsequent verifications. This method is an alias of {@link #validator(Object)} method.
     *
     * @param validator the validator; may be {@code null}.
     * @return {@link #myself self}.
     * @see #validator(Object)
     */
    public SELF using(final Object validator) {
        return validator(validator);
    }

    /**
     * Sets a validator for subsequent verifications.
     *
     * @param validator the validator; may be {@code null} yet must be an instance of either {@code
     *                  javax.validation.Validator} or {@code jakarta.validation.Validator}.
     * @return {@link #myself self}.
     * @see #using(Object)
     */
    @SuppressWarnings({"unchecked"})
    public SELF validator(final Object validator) {
        if (validator != null && !BeanValidationUtils.isValidatorInstance(validator)) {
            throw new IllegalArgumentException("wrong validator: " + validator);
        }
        this.validator = validator;
        return (SELF) this;
    }

    /**
     * Returns current validator instance being used.
     *
     * @return current validator instance being used; never {@code null}.
     */
    protected Object validator() {
        if (validator == null) {
            validator = BeanValidationUtils.validatorReflected();
        }
        return validator;
    }

    /**
     * Sets targeting groups for subsequent verifications. This method is an alias of {@link #groups(Class[])} method.
     *
     * @param groups the targeting groups; may be {@code null}.
     * @return {@link #myself self}.
     * @see #groups(Class[])
     */
    public SELF targeting(final Class<?>... groups) {
        return groups(groups);
    }

    /**
     * Sets targeting groups for subsequent verifications.
     *
     * @param groups the targeting groups; may be {@code null}.
     * @return {@link #myself self}.
     * @see #targeting(Class[])
     */
    @SuppressWarnings({"unchecked"})
    public SELF groups(final Class<?>... groups) {
        this.groups = groups;
        return (SELF) this;
    }

    /**
     * Returns current targeting groups being used.
     *
     * @return current targeting groups being used; never {@code null} yet may be empty.
     */
    protected Class<?>[] groups() {
        if (groups == null) {
            groups = new Class<?>[0];
        }
        return groups;
    }

    /**
     * The validator being used.
     *
     * @see #validator()
     */
    private Object validator;

    /**
     * The targeting groups being used.
     *
     * @see #groups()
     */
    private Class<?>[] groups;
}
