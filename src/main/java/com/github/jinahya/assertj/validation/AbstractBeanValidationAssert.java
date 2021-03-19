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

import org.assertj.core.api.AbstractAssert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base class for all implementations of assertions for {@code bean}s.
 *
 * @param <SELF> self type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractBeanValidationAssert<SELF extends AbstractBeanValidationAssert<SELF>>
        extends AbstractAssert<SELF, Object> {

    protected AbstractBeanValidationAssert(final Object actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    // -----------------------------------------------------------------------------------------------------------------
    public SELF isValid() {
        final Set<ConstraintViolation<Object>> constraintViolations
                = BeanValidationUtils.validate(actual, validator(), groups());
        assertThat(constraintViolations).isEmpty();
        return (SELF) this;
    }

    public SELF isValidWith(final Validator validator) {
        return withValidator(validator)
                .isValid();
    }

    public SELF isValidFor(final Class<?>... groups) {
        return forGroups(groups)
                .isValid();
    }

    public SELF isValid(final Validator validator, final Class<?>... groups) {
        return withValidator(validator)
                .forGroups(groups)
                .isValid();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @SuppressWarnings({"unchecked"})
    public SELF withValidator(final Validator validator) {
        this.validator = validator;
        return (SELF) this;
    }

    @SuppressWarnings({"unchecked"})
    public SELF forGroups(final Class<?>... groups) {
        this.groups = groups;
        return (SELF) this;
    }

    protected Validator validator() {
        if (validator == null) {
            validator = Validation.buildDefaultValidatorFactory().getValidator();
        }
        return validator;
    }

    protected Class<?>[] groups() {
        if (groups == null) {
            groups = new Class<?>[0];
        }
        return groups;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private Validator validator;

    private Class<?>[] groups;
}
