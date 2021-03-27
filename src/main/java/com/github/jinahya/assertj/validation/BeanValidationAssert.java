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
import java.util.function.Consumer;

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
     * Creates a new instance with specified bean.
     *
     * @param actual the actual bean to verify.
     * @see #actual
     */
    public BeanValidationAssert(final Object actual) {
        super(actual, BeanValidationAssert.class);
    }

    /**
     * Verifies that the {@link #actual actual} is valid.
     * <p>
     * This method is equivalent to
     * <blockquote><pre>{@code
     * assertThat(actual).isNotNull();
     * assertThat(
     *     validator()
     *         .validate(actual, groups())
     * ).isEmpty();
     * }</pre></blockquote>.
     * <p>
     * Which is, in its default state, equivalent to
     * <blockquote><pre>{@code
     * assertThat(actual).isNotNull();
     * assertThat(
     *     Validation.buildDefaultValidatorFactory().getValidator()
     *         .validate(actual, new Class<?>[0])
     * ).isEmpty();
     * }</pre></blockquote>.
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
        return myself;
    }

    /**
     * Verifies that the {@link #actual} object is invalid. This method is an alias of {@link #isNotValid(Consumer)}
     * method.
     *
     * @param consumer a consumer for consuming a set of constraint violations of either {@link
     *                 javax.validation.ConstraintViolation} or {@link jakarta.validation.ConstraintVaiolation}; may be
     *                 {@code null}.
     * @return {@link #myself self}.
     */
    public BeanValidationAssert isInvalid(final Consumer<? super Set<?>> consumer) {
        return isNotValid(consumer);
    }

    /**
     * Verifies that the {@link #actual} object is not valid.
     *
     * @param consumer a consumer for as set of constraint violations of either {@link javax.validation.ConstraintViolation}
     *                 or {@link jakarta.validation.ConstraintVaiolation}; may be {@code null}.
     * @return {@link #myself self}.
     */
    public BeanValidationAssert isNotValid(final Consumer<? super Set<?>> consumer) {
        isNotNull();
        final Set<Object> violations = validate(validator(), actual, groups());
        assertThat(violations).isNotEmpty();
        if (consumer != null) {
            consumer.accept(violations);
        }
        return myself;
    }

    /**
     * Verifies that the {@link #actual actual}'s current property of specified name is valid.
     * <p>
     * This method is equivalent to
     * <blockquote><pre>{@code
     * assertThat(actual).isNotNull();
     * assertThat(
     *     validator()
     *         .validateProperty(actual, propertyName, groups())
     * ).isEmpty();
     * }</pre></blockquote>.
     * <p>
     * Which is, in its default state, equivalent to
     * <blockquote><pre>{@code
     * assertThat(actual).isNotNull();
     * assertThat(
     *     Validation.buildDefaultValidatorFactory().getValidator()
     *         .validateProperty(actual, propertyName, new Class<?>[0])
     * ).isEmpty();
     * }</pre></blockquote>.
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
        return myself;
    }

//    public BeanValidationAssert doesNotHaveValidProperty(final String propertyName, final Consumer<Set<Object>> consumer) {
//        requireNonNull(propertyName, "propertyName is null");
//        isNotNull();
//        final Set<Object> violations = validateProperty(validator(), actual, propertyName, groups());
//        assertThat(violations).isNotEmpty().satisfies(consumer);
//        return myself;
//    }
}
