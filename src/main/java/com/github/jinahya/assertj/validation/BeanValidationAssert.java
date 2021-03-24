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

import static com.github.jinahya.assertj.validation.BeanValidationUtils.validate;
import static com.github.jinahya.assertj.validation.BeanValidationUtils.validateProperty;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for validating a bean and its properties.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class BeanValidationAssert extends AbstractBeanValidationAssert<BeanValidationAssert> {

    /**
     * Creates a new instance with specified actual bean object.
     *
     * @param actual the actual bean object; must be not {@code null}.
     * @see #actual
     */
    public BeanValidationAssert(final Object actual) {
        super(requireNonNull(actual, "actual is null"), BeanValidationAssert.class);
    }

    /**
     * Verifies that the {@link #actual} bean object is valid against current {@link #validator() validator} and {@link
     * #groups() targeting groups}.
     *
     * @return {@link #myself}.
     */
    public BeanValidationAssert isValid() {
        isNotNull();
        assertThat(validate(validator(), actual, groups())).isEmpty();
        return this;
    }

    /**
     * Verifies that current value of the property of specified name is valid against current {@link #validator()
     * validator} and {@link #groups() targeting groups}.
     *
     * @param propertyName the name of the property to validate.
     * @return {@link #myself}
     */
    public BeanValidationAssert hasValidProperty(final String propertyName) {
        requireNonNull(propertyName, "propertyName is null");
        isNotNull();
        assertThat(validateProperty(validator(), actual, propertyName, groups())).isEmpty();
        return this;
    }
}
