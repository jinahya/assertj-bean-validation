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

import static com.github.jinahya.assertj.validation.ReflectionUtils.getClassForSuffixOrExceptionInInitializerError;
import static java.lang.invoke.MethodHandles.lookup;
import static java.util.Objects.requireNonNull;

/**
 * A utility class for Bean-Validation.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
final class ValidatorUtils {

    private static final Class<?> VALIDATOR_CLASS = getClassForSuffixOrExceptionInInitializerError("Validator");

    static boolean isInstanceOfValidatorClass(final Object object, final boolean nullable) {
        if (nullable && object == null) {
            return true;
        }
        return VALIDATOR_CLASS.isInstance(requireNonNull(object, "object is null"));
    }

    static <T> T requireInstanceOfValidatorClass(final T object, final boolean nullable) {
        if (!isInstanceOfValidatorClass(object, nullable)) {
            throw new IllegalArgumentException("not an instance of " + VALIDATOR_CLASS + ": " + object);
        }
        return object;
    }

    // -------------------------------------------------------------------------------------------------------- validate
    private static final Method VALIDATE_METHOD;

    private static final MethodHandle VALIDATE_METHOD_HANDLE;

    static {
        try {
            VALIDATE_METHOD = VALIDATOR_CLASS.getMethod("validate", Object.class, Class[].class);
            VALIDATE_METHOD_HANDLE = lookup().unreflect(VALIDATE_METHOD);
        } catch (final Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @SuppressWarnings({"unchecked"})
    static <CONSTRAINT_VIOLATION> Set<CONSTRAINT_VIOLATION> validate(
            final Object validator, final Object object, final Class<?>... groups) {
        requireInstanceOfValidatorClass(validator, false);
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        try {
            return (Set<CONSTRAINT_VIOLATION>) VALIDATE_METHOD_HANDLE.invoke(validator, object, groups);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    // ------------------------------------------------------------------------------------------------ validateProperty
    private static final Method VALIDATE_PROPERTY_METHOD;

    private static final MethodHandle VALIDATE_PROPERTY_METHOD_HANDLE;

    static {
        try {
            VALIDATE_PROPERTY_METHOD = VALIDATOR_CLASS.getMethod(
                    "validateProperty", Object.class, String.class, Class[].class);
            VALIDATE_PROPERTY_METHOD_HANDLE = lookup().unreflect(VALIDATE_PROPERTY_METHOD);
        } catch (final Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @SuppressWarnings({"unchecked"})
    static <CONSTRAINT_VIOLATION> Set<CONSTRAINT_VIOLATION> validateProperty(
            final Object validator, final Object object, final String propertyName, final Class<?>... groups) {
        requireInstanceOfValidatorClass(validator, false);
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        try {
            return (Set<CONSTRAINT_VIOLATION>) VALIDATE_PROPERTY_METHOD_HANDLE
                    .invoke(validator, object, propertyName, groups);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    // --------------------------------------------------------------------------------------------------- validateValue
    private static final Method VALIDATE_VALUE_METHOD;

    private static final MethodHandle VALIDATE_VALUE_METHOD_HANDLE;

    static {
        try {
            VALIDATE_VALUE_METHOD = VALIDATOR_CLASS.getMethod(
                    "validateValue", Class.class, String.class, Object.class, Class[].class);
            VALIDATE_VALUE_METHOD_HANDLE = lookup().unreflect(VALIDATE_VALUE_METHOD);
        } catch (final Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @SuppressWarnings({"unchecked"})
    static <T, CONSTRAINT_VIOLATION> Set<CONSTRAINT_VIOLATION> validateValue(
            final Object validator, final Class<T> beanType, final String propertyName, final Object value,
            final Class<?>... groups) {
        requireInstanceOfValidatorClass(validator, false);
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        requireNonNull(groups, "groups is null");
        try {
            return (Set<CONSTRAINT_VIOLATION>) VALIDATE_VALUE_METHOD_HANDLE
                    .invoke(validator, beanType, propertyName, value, groups);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private ValidatorUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
