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

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.function.Function;

import static com.github.jinahya.assertj.validation.ReflectionUtils.applyClassForSuffix;
import static com.github.jinahya.assertj.validation.ReflectionUtils.getClassForSuffix;
import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Objects.requireNonNull;

/**
 * A utility class for Bean-Validation.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
final class ValidatorUtils {

    private static Class<?> validatorClass;

    private static Class<?> validatorClass() {
        Class<?> c = validatorClass;
        if (c == null) {
            validatorClass = c = getClassForSuffix("Validator");
        }
        return c;
    }

    static boolean isInstanceOfValidatorClass(final Object object) {
        return validatorClass().isInstance(requireNonNull(object, "object is null"));
    }

    static <T> T requireInstanceOfValidatorClass(final T object) {
        if (!isInstanceOfValidatorClass(object)) {
            throw new IllegalArgumentException("not an instance of " + validatorClass() + ": " + object);
        }
        return object;
    }

    static <T> T requireNullOrInstanceOfValidatorClass(final T object) {
        if (object == null) {
            return null;
        }
        return requireInstanceOfValidatorClass(object);
    }

    // -------------------------------------------------------------------------------------------------------- validate
    private static Method validateMethod = null;

    private static Method validateMethod() {
        Method m = validateMethod;
        if (m == null) {
            try {
                validateMethod = m = validatorClass.getMethod("validate", Object.class, Class[].class);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }
        return m;
    }

    private static MethodHandle validateMethodHandle = null;

    private static MethodHandle validateMethodHandle() {
        MethodHandle h = validateMethodHandle;
        if (h == null) {
            try {
                validateMethodHandle = h = lookup().unreflect(validateMethod());
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }
        return h;
    }

    @SuppressWarnings({"unchecked"})
    static <CONSTRAINT_VIOLATION> Set<CONSTRAINT_VIOLATION> validate(
            final Object validator, final Object object, final Class<?>... groups) {
        requireInstanceOfValidatorClass(validator);
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        try {
            return (Set<CONSTRAINT_VIOLATION>) validateMethodHandle().invoke(validator, object, groups);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    // ------------------------------------------------------------------------------------------------ validateProperty
    private static Method validatePropertyMethod = null;

    private static Method validatePropertyMethod() {
        Method m = validatePropertyMethod;
        if (m == null) {
            try {
                validatePropertyMethod = m = validatorClass.getMethod(
                        "validateProperty", Object.class, String.class, Class[].class);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }
        return m;
    }

    private static MethodHandle validatePropertyMethodHandle = null;

    private static MethodHandle validatePropertyMethodHandle() {
        MethodHandle h = validatePropertyMethodHandle;
        if (h == null) {
            try {
                validatePropertyMethodHandle = h = lookup().unreflect(validatePropertyMethod());
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }
        return h;
    }

    @SuppressWarnings({"unchecked"})
    static <CONSTRAINT_VIOLATION> Set<CONSTRAINT_VIOLATION> validateProperty(
            final Object validator, final Object object, final String propertyName, final Class<?>... groups) {
        requireInstanceOfValidatorClass(validator);
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        try {
            return (Set<CONSTRAINT_VIOLATION>) validateMethodHandle().invoke(validator, object, propertyName, groups);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    // --------------------------------------------------------------------------------------------------- validateValue
    private static Method VALIDATE_VALUE_METHOD = null;

    private static Method validateValueMethod() {
        Method m = VALIDATE_VALUE_METHOD;
        if (m == null) {
            try {
                VALIDATE_VALUE_METHOD = m = validatorClass.getMethod(
                        "validateValue", Class.class, String.class, Object.class, Class[].class);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }
        return m;
    }

    private static MethodHandle VALIDATE_VALUE_METHOD_HANDLE = null;

    private static MethodHandle validateValueMethodHandle() {
        MethodHandle h = VALIDATE_VALUE_METHOD_HANDLE;
        if (h == null) {
            try {
                VALIDATE_VALUE_METHOD_HANDLE = h = lookup().unreflect(validateValueMethod());
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }
        return h;
    }

    @SuppressWarnings({"unchecked"})
    static <T, CONSTRAINT_VIOLATION> Set<CONSTRAINT_VIOLATION> validateValue(
            final Object validator, final Class<T> beanType, final String propertyName, final Object value,
            final Class<?>... groups) {
        requireInstanceOfValidatorClass(validator);
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        requireNonNull(groups, "groups is null");
        try {
            return (Set<CONSTRAINT_VIOLATION>) validateValueMethodHandle()
                    .invoke(validator, beanType, propertyName, value, groups);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private ValidatorUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
