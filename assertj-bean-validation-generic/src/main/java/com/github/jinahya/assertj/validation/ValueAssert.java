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
 * @param <ACTUAL> the value of actual property value.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public class ValueAssert<ACTUAL>
        extends AbstractValueAssert<ValueAssert<ACTUAL>, Object, Object> {

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to verify.
     */
    public ValueAssert(final ACTUAL actual) {
        super(actual, ValueAssert.class);
    }

    @Override
    Object getDefaultValidator() {
        return ValidationUtils.validator();
    }

    @Override
    <T> Set<Object> validateValue(Object actual, Class<T> beanType, String propertyName) {
        return BeanUtils.validateValue(validator(), beanType, propertyName, actual, groups());
    }
}
