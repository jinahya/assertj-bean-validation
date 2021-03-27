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
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * A utility class for Bean-Validation.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class BeanValidationUtils {

    // ---------------------------------------------------------------------------------- Class<...validation.Validator>

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
        Class<?> validatorClassJavax = null;
        try {
            validatorClassJavax = validatorClassJavax();
        } catch (final ClassNotFoundException cnfe) {
            // empty
        }
        Class<?> validatorClassJakarta = null;
        try {
            validatorClassJakarta = validatorClassJakarta();
        } catch (final ClassNotFoundException cnfe) {
            // empty
        }
        if (validatorClassJavax == null && validatorClassJakarta == null) {
            throw new RuntimeException("unable to find the ....validation.Validator class");
        }
        return (validatorClassJavax != null && validatorClassJavax.isInstance(object))
               || (validatorClassJakarta != null && validatorClassJakarta.isInstance(object));
    }

    // ---------------------------------------------------------------------------------------- ....validation.Validator
    private static Object validatorFactoryJavax = null;

    /**
     * Returns an instance of {@code javax.validation.Validator} created from the default validator factory.
     *
     * @return an instance of {@code javax.validation.Validator}
     * @throws ReflectiveOperationException if failed to reflect classes and methods.
     */
    private static Object validatorJavax() throws ReflectiveOperationException {
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
    private static Object validatorJakarta() throws ReflectiveOperationException {
        if (validatorFactoryJakarta == null) {
            final Class<?> clazz = Class.forName("jakarta.validation.Validation");
            final Method method = clazz.getMethod("buildDefaultValidatorFactory");
            validatorFactoryJakarta = method.invoke(null);
        }
        final Class<?> clazz = Class.forName("jakarta.validation.ValidatorFactory");
        final Method method = clazz.getMethod("getValidator");
        return method.invoke(validatorFactoryJakarta);
    }

    /**
     * Returns an instance of {@code ....validation.Validator}.
     *
     * @return an instance of {@code ....validation.Validator}.
     */
    static Object validatorReflected() {
        try {
            return validatorJavax();
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        try {
            return validatorJakarta();
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        throw new RuntimeException("failed to instantiated an instance of Validator");
    }

    // ---------------------------------------------------------------------------- Validator#validate(Object, Class...)
    private static Method validateMethod(final Object validator) {
        requireNonNull(validator, "validator is null");
        try {
            final Class<?> validatorClassJavax = validatorClassJavax();
            if (validatorClassJavax.isInstance(validator)) {
                return validatorClassJavax.getMethod("validate", Object.class, Class[].class);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        try {
            final Class<?> validatorClassJakarta = validatorClassJakarta();
            if (validatorClassJakarta.isInstance(validator)) {
                return validatorClassJakarta.getMethod("validate", Object.class, Class[].class);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        throw new RuntimeException("failed to get validate method for " + validator);
    }

    /**
     * Invokes the {@code validate(T, Class...)} method on specified validator with given arguments and returns the
     * result.
     *
     * @param validator the validator on which the {@code validate(...)} method is invoked; may be {@code null}.
     * @param object    a value for {@code object} parameter.
     * @param groups    a value for {@code groups} parameter.
     * @param <T>       {@code object} type parameter
     * @return the result of the invocation which is a set of {@code ConstraintViolation}s.
     */
    @SuppressWarnings({"unchecked"})
    static <T> Set<Object> validate(Object validator, final T object, final Class<?>... groups) {
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        if (validator == null) {
            validator = validatorReflected();
        }
        try {
            return (Set<Object>) validateMethod(validator).invoke(validator, object, groups);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ------------------------------------------------------------- Validator#validateProperty(Object String, Class...)
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

    /**
     * Invokes {@code Validator#validateProperty(T, String, Class...)} method on specified validator with given
     * arguments and returns the result.
     *
     * @param validator    the validator on which the {@code validateProperty(...)} method is invokes.
     * @param object       a value for {@code object} parameter.
     * @param propertyName a value for {@code propertyName} parameter.
     * @param groups       a value for {@code groups} parameter.
     * @param <T>          {@code object} type parameter
     * @return the result of the invocation which is a set of {@code ConstraintViolation}s
     */
    @SuppressWarnings({"unchecked"})
    static <T> Set<Object> validateProperty(Object validator, final T object, final String propertyName,
                                            final Class<?>... groups) {
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        if (validator == null) {
            validator = validatorReflected();
        }
        try {
            return (Set<Object>) validatePropertyMethod(validator).invoke(validator, object, propertyName, groups);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static Method validateValueMethod(final Object validator) {
        requireNonNull(validator, "validator is null");
        try {
            final Class<?> validatorClassJavax = validatorClassJavax();
            if (validatorClassJavax.isInstance(validator)) {
                return validatorClassJavax.getMethod(
                        "validateValue", Class.class, String.class, Object.class, Class[].class);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        try {
            final Class<?> validatorClassJakarta = validatorClassJakarta();
            if (validatorClassJakarta.isInstance(validator)) {
                return validatorClassJakarta.getMethod(
                        "validateValue", Class.class, String.class, Object.class, Class[].class);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        throw new RuntimeException("failed to get validateValue method for " + validator);
    }

    /**
     * Invokes the {@code Validator#validateValue(Class<T>, String, Object, Class...)} method on specified validator
     * with given arguments and returns the result.
     *
     * @param validator    the validator on which the {@code validateValue(...)} method is invoked.
     * @param beanType     a value for {@code beanType} parameter.
     * @param propertyName a value for {@code propertyName} parameter.
     * @param value        a value for {@code value} parameter.
     * @param groups       a value for {@code groups} parameter.
     * @param <T>          the type of the class modeled by the {@code beanType}.
     * @return the result of the invocation which is a set of {@code ConstraintViolation}s.
     */
    @SuppressWarnings({"unchecked"})
    static <T> Set<Object> validateValue(Object validator, final Class<T> beanType, final String propertyName,
                                         final Object value, final Class<?>... groups) {
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        requireNonNull(groups, "groups is null");
        if (validator == null) {
            validator = validatorReflected();
        }
        try {
            return (Set<Object>) validateValueMethod(validator)
                    .invoke(validator, beanType, propertyName, value, groups);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private BeanValidationUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
