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

import static com.github.jinahya.assertj.validation.BeanValidationUtils.validateValue;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for validating property values.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class BeanPropertyValidationAssert extends AbstractBeanValidationAssert<BeanPropertyValidationAssert> {

    /**
     * Creates a new instance with specified property value.
     *
     * @param value the property value.
     */
    public BeanPropertyValidationAssert(final Object value) {
        super(value, BeanPropertyValidationAssert.class);
    }

    /**
     * Asserts the {@link #actual} value is a valid property value for specified property of specified class.
     *
     * @param beanType     the class whose property are examined.
     * @param propertyName the name of the property.
     * @return {@link #myself}.
     */
    public BeanPropertyValidationAssert isValidFor(final Class<?> beanType, final String propertyName) {
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        assertThat(validateValue(validator(), beanType, propertyName, actual, groups())).isEmpty();
        return this;
    }
}
