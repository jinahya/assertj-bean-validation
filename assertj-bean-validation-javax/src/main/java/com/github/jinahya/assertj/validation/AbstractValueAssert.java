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
import javax.validation.groups.Default;
import java.util.Set;

/**
 * An assertion class for validating a value against constraints defined on a bean property.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractValueAssert<SELF extends AbstractValueAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractValueAssertBase<SELF, ACTUAL, Validator, ConstraintViolation<?>> {

    protected AbstractValueAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    protected Validator defaultValidator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    protected @NotNull Class<?> defaultGroup() {
        return Default.class;
    }

    @Override
    protected <T> Set<? extends ConstraintViolation<?>> validateValue(
            final @NotNull Class<T> beanType, final @NotNull String propertyName) {
        return null;
    }
}
