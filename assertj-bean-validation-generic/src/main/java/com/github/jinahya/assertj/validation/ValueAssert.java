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
 * An assertion class for validating values against constraints defined on property of specified bean type.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ValueAssert
        extends AbstractValueAssert<ValueAssert, Object, Object> {

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to verify.
     */
    public ValueAssert(final Object actual) {
        super(actual, ValueAssert.class);
    }

    @Override
    protected Object getDefaultValidator() {
        return ValidationUtils.getValidator();
    }

    @Override
    protected <T> Set<Object> validateValue(final Object actual, final Class<T> beanType, final String propertyName) {
        return ValidatorUtils.validateValue(validator(), beanType, propertyName, actual, groups());
    }
}
