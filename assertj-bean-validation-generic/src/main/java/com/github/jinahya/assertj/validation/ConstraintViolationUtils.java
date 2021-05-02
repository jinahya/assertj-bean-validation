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

import static com.github.jinahya.assertj.validation.ReflectionUtils.getClassForSuffixOrExceptionInInitializerError;
import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Objects.requireNonNull;

final class ConstraintViolationUtils {

    private static final Class<?> CONSTRAINT_VIOLATION_CLASS
            = getClassForSuffixOrExceptionInInitializerError("ConstraintViolation");

    static boolean isConstraintViolation(final Object object, final boolean nullable) {
        if (nullable && object == null) {
            return true;
        }
        return CONSTRAINT_VIOLATION_CLASS.isInstance(requireNonNull(object, "object is null"));
    }

    static <T> T requireConstraintViolation(final T object, final boolean nullable) {
        if (!isConstraintViolation(object, nullable)) {
            throw new IllegalArgumentException("not an instance of " + CONSTRAINT_VIOLATION_CLASS + ": " + object);
        }
        return object;
    }

    // ----------------------------------------------------------------------------------------------- getInvalidValue()
    private static final Method GET_INVALID_VALUE_METHOD;

    private static final MethodHandle GET_INVALID_VALUE_METHOD_HANDLE;

    static {
        try {
            GET_INVALID_VALUE_METHOD = CONSTRAINT_VIOLATION_CLASS.getMethod("getInvalidValue");
            GET_INVALID_VALUE_METHOD_HANDLE = lookup().unreflect(GET_INVALID_VALUE_METHOD);
        } catch (final Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    static Object getInvalidValue(final Object actual) {
        try {
            return GET_INVALID_VALUE_METHOD_HANDLE.invoke(actual);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    // --------------------------------------------------------------------------------------------------- getLeafBean()
    private static final Method GET_LEAF_BEAN_METHOD;

    private static final MethodHandle GET_LEAF_BEAN_METHOD_HANDLE;

    static {
        try {
            GET_LEAF_BEAN_METHOD = CONSTRAINT_VIOLATION_CLASS.getMethod("getLeafBean");
            GET_LEAF_BEAN_METHOD_HANDLE = lookup().unreflect(GET_LEAF_BEAN_METHOD);
        } catch (final Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    static Object getLeafBean(final Object actual) {
        try {
            return GET_LEAF_BEAN_METHOD_HANDLE.invoke(actual);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    // ------------------------------------------------------------------------------------------------------ getMessage
    private static final Method GET_MESSAGE_METHOD;

    private static final MethodHandle GET_MESSAGE_METHOD_HANDLE;

    static {
        try {
            GET_MESSAGE_METHOD = CONSTRAINT_VIOLATION_CLASS.getMethod("getMessage");
            GET_MESSAGE_METHOD_HANDLE = lookup().unreflect(GET_MESSAGE_METHOD);
        } catch (final Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    static String getMessage(final Object actual) {
        try {
            return (String) GET_MESSAGE_METHOD_HANDLE.invoke(actual);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    // ----------------------------------------------------------------------------------------------- getPropertyPath()
    private static final Method GET_PROPERTY_PATH_METHOD;

    private static final MethodHandle GET_PROPERTY_PATH_METHOD_HANDLE;

    static {
        try {
            GET_PROPERTY_PATH_METHOD = CONSTRAINT_VIOLATION_CLASS.getMethod("getPropertyPath");
            GET_PROPERTY_PATH_METHOD_HANDLE = lookup().unreflect(GET_PROPERTY_PATH_METHOD);
        } catch (final Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @SuppressWarnings({"unchecked"})
    static <T> T getPropertyPath(final Object actual) {
        try {
            return (T) GET_PROPERTY_PATH_METHOD_HANDLE.invoke(actual);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    // --------------------------------------------------------------------------------------------------- getRootBean()
    private static final Method GET_ROOT_BEAN_METHOD;

    private static final MethodHandle GET_ROOT_BEAN_METHOD_HANDLE;

    static {
        try {
            GET_ROOT_BEAN_METHOD = CONSTRAINT_VIOLATION_CLASS.getMethod("getRootBean");
            GET_ROOT_BEAN_METHOD_HANDLE = lookup().unreflect(GET_ROOT_BEAN_METHOD);
        } catch (final Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @SuppressWarnings({"unchecked"})
    static <T> T getRootBean(final Object actual) {
        try {
            return (T) GET_ROOT_BEAN_METHOD_HANDLE.invoke(actual);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    // ---------------------------------------------------------------------------------------------- getRootBeanClass()
    private static final Method GET_ROOT_BEAN_CLASS_METHOD;

    private static final MethodHandle GET_ROOT_BEAN_CLASS_METHOD_HANDLE;

    static {
        try {
            GET_ROOT_BEAN_CLASS_METHOD = CONSTRAINT_VIOLATION_CLASS.getMethod("getRootBeanClass");
            GET_ROOT_BEAN_CLASS_METHOD_HANDLE = lookup().unreflect(GET_ROOT_BEAN_CLASS_METHOD);
        } catch (final Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @SuppressWarnings({"unchecked"})
    static <T> Class<T> getRootBeanClass(final Object actual) {
        try {
            return (Class<T>) GET_ROOT_BEAN_CLASS_METHOD_HANDLE.invoke(actual);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ConstraintViolationUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
