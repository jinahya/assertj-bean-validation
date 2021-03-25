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

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * A utility class for Bean-Validation.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class BeanValidationUtils {

    /**
     * Returns the class of {@code javax.validation.Validator}.
     *
     * @return the class of {@code javax.validation.Validator}.
     * @throws ClassNotFoundException if the class cannot be located.
     */
    private static Class<?> validatorClassJavax() throws ClassNotFoundException {
        return Class.forName("javax.validation.Validator");
    }

    /**
     * Returns the class of {@code jakarta.validation.Validator}.
     *
     * @return the class of {@code jakarta.validation.Validator}.
     * @throws ClassNotFoundException if the class cannot be located.
     */
    private static Class<?> validatorClassJakarta() throws ClassNotFoundException {
        return Class.forName("jakarta.validation.Validator");
    }

    /**
     * Indicates whether specified object is an instance of {@code javax.validation.Validator} or is an instance of
     * {@code jakarta.validation.Validator}.
     *
     * @param object the object to be tested.
     * @return {@code true} if {@code object} is an instance of {@code Validator}; {@code false} otherwise.
     * @throws RuntimeException if both class cannot be located.
     * @see #validatorClassJavax()
     * @see #validatorClassJakarta()
     */
    static boolean isValidatorInstance(final Object object) {
        requireNonNull(object, "object is null");
        try {
            return validatorClassJavax().isInstance(object) || validatorClassJakarta().isInstance(object);
        } catch (final ClassNotFoundException cnfe) {
            // empty
        }
        throw new RuntimeException("unable to detect validator class for " + object);
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Object validatorFactoryJavax = null;

    /**
     * Returns an instance of {@code javax.validation.Validator} created from the default validator factory.
     *
     * @return an instance of {@code javax.validation.Validator}
     * @throws ReflectiveOperationException if failed to reflect classes and methods.
     */
    static Object validatorJavax() throws ReflectiveOperationException {
        if (validatorFactoryJavax == null) {
            final Class<?> clazz = Class.forName("javax.validation.Validation");
            final Method method = clazz.getMethod("buildDefaultValidatorFactory");
            validatorFactoryJavax = method.invoke(null);
        }
        final Class<?> clazz = Class.forName("javax.validation.ValidatorFactory");
        final Method method = clazz.getMethod("getValidator");
        return method.invoke(validatorFactoryJavax);
    }

    private static Object validatorFactoryJakarta = null;

    /**
     * Returns an instance of {@code jakarta.validation.Validator} created from the default validator factory.
     *
     * @return an instance of {@code jakarta.validation.Validator}
     * @throws ReflectiveOperationException if failed to reflect classes and methods.
     */
    static Object validatorJakarta() throws ReflectiveOperationException {
        if (validatorFactoryJakarta == null) {
            final Class<?> clazz = Class.forName("jakarta.validation.Validation");
            final Method method = clazz.getMethod("buildDefaultValidatorFactory");
            validatorFactoryJakarta = method.invoke(null);
        }
        final Class<?> clazz = Class.forName("jakarta.validation.ValidatorFactory");
        final Method method = clazz.getMethod("getValidator");
        return method.invoke(validatorFactoryJakarta);
    }

    static Object validatorReflected() {
        try {
            return validatorJavax();
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        try {
            return validatorJakarta();
        } catch (final ReflectiveOperationException roe) {
            // empty;
        }
        throw new RuntimeException("failed to instantiated an instance of Validator");
    }

//    /**
//     * Returns a validator initialized from the {@link Validation#buildDefaultValidatorFactory()
//     * default-validator-factory}.
//     *
//     * @return a validator initialized from the {@link Validation#buildDefaultValidatorFactory()
//     * default-validator-factory}.
//     */
//    static @NotNull Validator validator() {
//        return buildDefaultValidatorFactory().getValidator();
//    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Method validateMethod(final Object validator) {
        requireNonNull(validator, "validator is null");
        try {
            if (validatorClassJavax().isInstance(validator)) {
                return validatorClassJavax().getMethod("validate", Object.class, Class[].class);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        try {
            if (validatorClassJakarta().isInstance(validator)) {
                return validatorClassJakarta().getMethod("validate", Object.class, Class[].class);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        throw new RuntimeException("failed to get validate method for " + validator);
    }

    @SuppressWarnings({"unchecked"})
    static <T> Set<Object> validate(final Object validator, final T object, final Class<?>... groups) {
        requireNonNull(validator, "validator is null");
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        try {
            return (Set<Object>) validateMethod(validator).invoke(validator, object, groups);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

//    /**
//     * Validates specified bean object using specified validator and groups.
//     *
//     * @param validator the validator.
//     * @param object    the object to validate.
//     * @param groups    the groups.
//     * @param <T>       object type parameter
//     * @return the result of {@link Validator#validate(Object, Class[])} method invoked on {@code validator} with {@code
//     * object} and {@code groups}.
//     */
//    static <T> @NotNull Set<@NotNull ConstraintViolation<T>> validate(
//            final @NotNull Validator validator, final @NotNull T object, final @NotNull Class<?>... groups) {
//        requireNonNull(validator, "validator is null");
//        requireNonNull(object, "object is null");
//        requireNonNull(groups, "groups is null");
//        return validator.validate(object, groups);
//    }
//

    /**
     * Checks that the specified object reference is valid.
     *
     * @param validator a validator.
     * @param object    the object to validate.
     * @param groups    a targeting groups.
     * @param <T>       object type parameter
     */
    static <T> T requireValid(Object validator, final T object, final Class<?>... groups) {
        if (validator == null) {
            validator = validatorReflected();
        }
        final Set<Object> violations = validate(validator, object, groups);
        if (!violations.isEmpty()) {
            throw new RuntimeException(String.format(
                    "invalid; validator: %1$s, object: %2$s, groups: %3$s, violations: %4$s",
                    validator, object, Arrays.toString(groups), violations));
        }
        return object;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Method validatePropertyMethod(final Object validator) {
        requireNonNull(validator, "validator is null");
        try {
            if (validatorClassJavax().isInstance(validator)) {
                return validatorClassJavax()
                        .getMethod("validateProperty", Object.class, String.class, Class[].class);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        try {
            if (validatorClassJakarta().isInstance(validator)) {
                return validatorClassJakarta()
                        .getMethod("validateProperty", Object.class, String.class, Class[].class);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        throw new RuntimeException("failed to get validateProperty method for " + validator);
    }

    @SuppressWarnings({"unchecked"})
    static <T> Set<Object> validateProperty(final Object validator, final T object, final String propertyName,
                                            final Class<?>... groups) {
        requireNonNull(validator, "validator is null");
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        try {
            return (Set<Object>) validatePropertyMethod(validator).invoke(validator, object, propertyName, groups);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    static <T> T requirePropertyValid(Object validator, final T object, final String propertyName,
                                      final Class<?>... groups) {
        if (validator == null) {
            validator = validatorReflected();
        }
        final Set<Object> violations = validateProperty(validator, object, propertyName, groups);
        if (!violations.isEmpty()) {
            throw new RuntimeException(String.format(
                    "invalid property; validator: %1$s, object: %2$s, propertyName: %3$s, groups: %4$s,"
                    + " violations: %5$s",
                    validator, object, propertyName, Arrays.toString(groups), violations));
        }
        return object;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Method validateValueMethod(final Object validator) throws ReflectiveOperationException {
        requireNonNull(validator, "validator is null");
        try {
            if (validatorClassJavax().isInstance(validator)) {
                return validatorClassJavax().getMethod(
                        "validateValue", Class.class, String.class, Object.class, Class[].class);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        try {
            if (validatorClassJakarta().isInstance(validator)) {
                return validatorClassJakarta().getMethod(
                        "validateValue", Class.class, String.class, Object.class, Class[].class);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        throw new RuntimeException("failed to get validateValue method for " + validator);
    }

    @SuppressWarnings({"unchecked"})
    static <T> Set<Object> validateValue(final Object validator, final Class<T> beanType,
                                         final String propertyName, final Object value, final Class<?>... groups) {
        requireNonNull(validator, "validator is null");
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        requireNonNull(groups, "groups is null");
        try {
            return (Set<Object>) validateValueMethod(validator)
                    .invoke(validator, beanType, propertyName, value, groups);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    static <T> Object requireValidValue(Object validator, final Class<T> beanType, final String propertyName,
                                        final Object value, final Class<?>... groups) {
        final Set<Object> violations = validateValue(validator, beanType, propertyName, value, groups);
        if (!violations.isEmpty()) {
            throw new RuntimeException(String.format(
                    "invalid value; validator: %1$s, beanType: %2$s, propertyName: %3$s, value: %4$s, groups: %5$s,"
                    + " violations: %6$s",
                    validator, beanType, propertyName, value, Arrays.toString(groups), violations));
        }
        return value;
    }

    // -----------------------------------------------------------------------------------------------------------------
    private BeanValidationUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
