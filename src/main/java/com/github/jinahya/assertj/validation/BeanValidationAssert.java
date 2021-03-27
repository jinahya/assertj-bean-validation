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
 * An assertion class for validating a bean object and its properties.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class BeanValidationAssert extends AbstractBeanValidationAssert<BeanValidationAssert> {

    /**
     * Creates a new instance with specified bean object reference.
     *
     * @param actual the actual bean object; must be not {@code null}.
     * @see #actual
     */
    public BeanValidationAssert(final Object actual) {
        super(actual, BeanValidationAssert.class);
    }

    /**
     * Verifies that the {@link #actual actual} is valid.
     *
     * @return {@link #myself self}.
     * @see #using(Object)
     * @see #targeting(Class[])
     * @see <a href="https://javadoc.io/static/javax.validation/validation-api/2.0.1.Final/javax/validation/Validator.html#validate-T-java.lang.Class...-">javax...#validate(T,
     * Class...)</a>
     * @see <a href="https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validate-T-java.lang.Class...-">jakarta...#validate(T,
     * Class...)</a>
     */
    public BeanValidationAssert isValid() {
        isNotNull();
        assertThat(validate(validator(), actual, groups())).isEmpty();
        return this;
    }

    /**
     * Verifies that the {@link #actual actual}'s current property of specified name is valid.
     *
     * @param propertyName the name of the property to verify.
     * @return {@link #myself self}
     * @see #using(Object)
     * @see #targeting(Class[])
     * @see <a href="https://javadoc.io/static/javax.validation/validation-api/2.0.1.Final/javax/validation/Validator.html#validateProperty-T-java.lang.String-java.lang.Class...-">javax...#validateProperty(T,
     * String, Class...)</a>
     * @see <a href="https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validateProperty-T-java.lang.String-java.lang.Class...-">jakarta...#validateProperty(T,
     * String, Class...)</a>
     */
    public BeanValidationAssert hasValidProperty(final String propertyName) {
        requireNonNull(propertyName, "propertyName is null");
        isNotNull();
        assertThat(validateProperty(validator(), actual, propertyName, groups())).isEmpty();
        return this;
    }
}
