/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2021 the original author or authors.
 */
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

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Method;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static javax.validation.Validation.buildDefaultValidatorFactory;

/**
 * A utility class for Bean-Validation.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class BeanValidationUtils {

    private static Class<?> validatorClassJavax() throws ReflectiveOperationException {
        return Class.forName("javax.validation.Validator");
    }

    private static Class<?> validatorClassJakarta() throws ReflectiveOperationException {
        return Class.forName("jakarta.validation.Validator");
    }

    // -----------------------------------------------------------------------------------------------------------------
    static Object validatorJavax() throws ReflectiveOperationException {
        final Class<?> validationClass = Class.forName("javax.validation.Validation");
        final Method factoryMethod = validationClass.getMethod("buildDefaultValidatorFactory");
        final Object validatorFactory = factoryMethod.invoke(null);
        final Class<?> factoryClass = Class.forName("javax.validation.ValidatorFactory");
        final Method validatorMethod = factoryClass.getMethod("getValidator");
        return validatorMethod.invoke(validatorFactory);
    }

    static Object validatorJakarta() throws ReflectiveOperationException {
        final Class<?> validationClass = Class.forName("jakarta.validation.Validation");
        final Method factoryMethod = validationClass.getMethod("buildDefaultValidatorFactory");
        final Object factory = factoryMethod.invoke(null);
        final Class<?> factoryClass = Class.forName("jakarta.validation.ValidatorFactory");
        final Method validatorMethod = factoryClass.getMethod("getValidator");
        return validatorMethod.invoke(factory);
    }

    static @NotNull Object validatorReflected() {
        try {
            return validatorJavax();
        } catch (final ReflectiveOperationException roe) {
            try {
                return validatorJakarta();
            } catch (final ReflectiveOperationException roe2) {
                throw new RuntimeException(roe2);
            }
        }
    }

    /**
     * Returns a validator initialized from the {@link Validation#buildDefaultValidatorFactory()
     * default-validator-factory}.
     *
     * @return a validator initialized from the {@link Validation#buildDefaultValidatorFactory()
     * default-validator-factory}.
     */
    static @NotNull Validator validator() {
        return buildDefaultValidatorFactory().getValidator();
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Method validateMethod() throws ReflectiveOperationException {
        try {
            return validatorClassJavax().getMethod("validate", Object.class, Class[].class);
        } catch (final ReflectiveOperationException roe) {
            return validatorClassJakarta().getMethod("validate", Object.class, Class[].class);
        }
    }

    static <T> @NotNull Set<@NotNull ConstraintViolation<T>> validate(
            final @NotNull Object validator, final @NotNull T object, final @NotNull Class<?>... groups)
            throws ReflectiveOperationException {
        requireNonNull(validator, "validator is null");
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        return (Set<ConstraintViolation<T>>) validateMethod().invoke(validator, object, groups);
    }

    /**
     * Checks that the specified object reference is valid using specified validator and groups.
     *
     * @param validator the validator.
     * @param object    the object to validate.
     * @param groups    the groups.
     * @param <T>       object type parameter
     * @throws ReflectiveOperationException if failed to reflect.
     * @throws ConstraintViolationException if one or more constraint violations resulted while validating.
     * @see #validate(Validator, Object, Class[])
     */
    static <T> void requireValid(final @NotNull Object validator, final @NotNull T object,
                                 final @NotNull Class<?>... groups)
            throws ReflectiveOperationException {
        final Set<ConstraintViolation<T>> violations = validate(validator, object, groups);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    /**
     * Validates specified bean object using specified validator and groups.
     *
     * @param validator the validator.
     * @param object    the object to validate.
     * @param groups    the groups.
     * @param <T>       object type parameter
     * @return the result of {@link Validator#validate(Object, Class[])} method invoked on {@code validator} with {@code
     * object} and {@code groups}.
     */
    static <T> @NotNull Set<@NotNull ConstraintViolation<T>> validate(
            final @NotNull Validator validator, final @NotNull T object, final @NotNull Class<?>... groups) {
        requireNonNull(validator, "validator is null");
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        return validator.validate(object, groups);
    }

    /**
     * Checks that the specified object reference is valid using specified validator and groups.
     *
     * @param validator the validator.
     * @param object    the object to validate.
     * @param groups    the groups.
     * @param <T>       object type parameter
     * @throws ConstraintViolationException if one or more constraint violations resulted while validating.
     * @see #validate(Validator, Object, Class[])
     */
    static <T> void requireValid(final @NotNull Validator validator, final @NotNull T object,
                                 final @NotNull Class<?>... groups) {
        final Set<ConstraintViolation<T>> violations = validate(validator, object, groups);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Method validatePropertyMethod() throws ReflectiveOperationException {
        try {
            return validatorClassJavax().getMethod("validateProperty", Object.class, String.class, Class[].class);
        } catch (final ReflectiveOperationException roe) {
            return validatorClassJakarta().getMethod("validateProperty", Object.class, String.class, Class[].class);
        }
    }

    @SuppressWarnings({"unchecked"})
    static <T> @NotNull Set<@NotNull ConstraintViolation<T>> validateProperty(
            final @NotNull Object validator, final @NotNull T object, final @NotBlank String propertyName,
            final @NotNull Class<?>... groups)
            throws ReflectiveOperationException {
        requireNonNull(validator, "validator is null");
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        return (Set<ConstraintViolation<T>>) validatePropertyMethod().invoke(validator, object, propertyName, groups);
    }

    static <T> @NotNull Set<@NotNull ConstraintViolation<T>> validateProperty(
            final @NotNull Validator validator, final @NotNull T object, final @NotBlank String propertyName,
            final @NotNull Class<?>... groups) {
        requireNonNull(validator, "validator is null");
        requireNonNull(object, "object is null");
        requireNonNull(propertyName, "propertyName is null");
        requireNonNull(groups, "groups is null");
        return validator.validateProperty(object, propertyName, groups);
    }

    static <T> void requirePropertyValid(final @NotNull Validator validator, final @NotNull T object,
                                         final @NotBlank String propertyName,
                                         final @NotNull Class<?>... groups) {
        final Set<ConstraintViolation<T>> violations = validateProperty(validator, object, propertyName, groups);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Method validateValueMethod() throws ReflectiveOperationException {
        try {
            return validatorClassJavax().getMethod(
                    "validateValue", Class.class, String.class, Object.class, Class[].class);
        } catch (final ReflectiveOperationException roe) {
            return validatorClassJakarta().getMethod(
                    "validateValue", Class.class, String.class, Object.class, Class[].class);
        }
    }

    @SuppressWarnings({"unchecked"})
    static <T> @NotNull Set<@NotNull ConstraintViolation<T>> validateValue(
            final @NotNull Object validator, final Class<T> beanType, final @NotBlank String propertyName,
            final Object value, final @NotNull Class<?>... groups)
            throws ReflectiveOperationException {
        requireNonNull(validator, "validator is null");
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        requireNonNull(groups, "groups is null");
        return (Set<ConstraintViolation<T>>) validateValueMethod().invoke(
                validator, beanType, propertyName, value, groups);
    }

    static <T> @NotNull Set<@NotNull ConstraintViolation<T>> validateValue(
            final @NotNull Validator validator, final @NotNull Class<T> beanType, final @NotBlank String propertyName,
            final Object value, final @NotNull Class<?>... groups) {
        requireNonNull(validator, "validator is null");
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        requireNonNull(groups, "groups is null");
        return validator.validateValue(beanType, propertyName, value, groups);
    }

    static <T> void requireValueValid(final @NotNull Validator validator, final @NotNull Class<T> beanType,
                                      final @NotBlank String propertyName, final Object value,
                                      final @NotNull Class<?>... groups) {
        final Set<ConstraintViolation<T>> violations = validateValue(validator, beanType, propertyName, value, groups);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private BeanValidationUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
