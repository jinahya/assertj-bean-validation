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

import java.util.Set;

/**
 * An assertion class for validating a bean and/or its properties.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public class BeanAssert
        extends AbstractBeanAssert<BeanAssert, Object, Object, Object> {

    /**
     * Creates a new instance with specified bean.
     *
     * @param actual the actual bean to verify.
     */
    public BeanAssert(final Object actual) {
        super(actual, BeanAssert.class);
    }

    // ------------------------------------------------------------------------------------------------------- validator
    @Override
    protected Object getDefaultValidator() {
        return ValidationUtils.getValidator();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    protected Set<Object> validate(final Object actual) {
        return ValidatorUtils.validate(validator(), actual, groups());
    }

    @Override
    protected Set<Object> validateProperty(final Object actual, String propertyName) {
        return ValidatorUtils.validateProperty(validator(), actual, propertyName, groups());
    }
}
