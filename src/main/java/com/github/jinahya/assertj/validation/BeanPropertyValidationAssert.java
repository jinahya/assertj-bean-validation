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

import org.assertj.core.api.Assertions;

import static com.github.jinahya.assertj.validation.BeanValidationUtils.validateValue;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for validating a value against constraints defined on a bean property.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class BeanPropertyValidationAssert extends AbstractBeanValidationAssert<BeanPropertyValidationAssert> {

    /**
     * Creates a new instance with specified value.
     *
     * @param value the value for a property.
     */
    public BeanPropertyValidationAssert(final Object value) {
        super(value, BeanPropertyValidationAssert.class);
    }

    /**
     * Verifies that the {@link #actual actual} would be valid for specified property of specified class.
     * <p>
     * This method is equivalent to
     * <blockquote><pre>{@code
     * Assertions.assertThat(
     *         validator().validateValue(beanType, propertyName, actual, groups())
     * ).isEmpty();
     * }</pre></blockquote>.
     * <p>
     * Which is, in its default state, equivalent to
     * <blockquote><pre>{@code
     * Assertions.assertThat(
     *         Validation.buildDefaultValidatorFactory().getValidator()
     *             .validateValue(beanType, propertyName, actual)
     * ).isEmpty();
     * }</pre></blockquote>.
     *
     * @param beanType     the class whose all constraints placed on specified property are examined.
     * @param propertyName the name of the property.
     * @return {@link #myself self}.
     * @see #using(Object)
     * @see #targeting(Class[])
     * @see <a href="https://javadoc.io/static/javax.validation/validation-api/2.0.1.Final/javax/validation/Validator.html#validateValue-java.lang.Class-java.lang.String-java.lang.Object-java.lang.Class...-">javax....#validate(Class,
     * String, Object, Class...)</a>
     * @see <a href="https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validateValue-java.lang.Class-java.lang.String-java.lang.Object-java.lang.Class...-">jakarta....#validate(Class,
     * String, Object, Class...)</a>
     */
    public BeanPropertyValidationAssert isValidFor(final Class<?> beanType, final String propertyName) {
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        assertThat(validateValue(validator(), beanType, propertyName, actual, groups())).isEmpty();
        return this;
    }
}
