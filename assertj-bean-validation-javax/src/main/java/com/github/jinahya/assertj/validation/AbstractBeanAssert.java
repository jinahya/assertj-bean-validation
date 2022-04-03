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

@SuppressWarnings({"java:S119"})
public class AbstractBeanAssert<SELF extends AbstractBeanAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractBeanAssertBase<SELF, ACTUAL, Validator, ConstraintViolation<ACTUAL>> {

    protected AbstractBeanAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    protected Validator defaultValidator() {
        if (defaultValidator == null) {
            defaultValidator = Validation.buildDefaultValidatorFactory().getValidator();
        }
        return defaultValidator;
    }

    @Override
    protected Set<ConstraintViolation<ACTUAL>> validate() {
        isNotNull();
        return validator().validate(actual, groups());
    }

    @Override
    protected @NotNull Class<?> defaultGroup() {
        return Default.class;
    }

    @Override
    protected Set<ConstraintViolation<ACTUAL>> validateProperty(final @NotNull String propertyName) {
        return null;
    }

    private Validator defaultValidator;
}
