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

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class ConstraintViolationUtils {

    // -----------------------------------------------------------------------------------------------------------------
    static <R> R applyConstraintViolationClass(final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassForSuffix("ConstraintViolation", function);
    }

    // -----------------------------------------------------------------------------------------------------------------
    static <R> R applyConstraintViolationClassFor(final Object instance,
                                                  final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassFor("ConstraintViolation", instance, function);
    }

    private static Class<?> getConstraintViolationClassFor(final Object instance) {
        return applyConstraintViolationClassFor(instance, Function.identity());
    }

    /**
     * Indicates whether specified object is an instance of either {@code javax.validation.ConstraintViolation} or
     * {@code jakarta.validation.ConstraintViolation}.
     *
     * @param object the object to be tested.
     * @return {@code true} if {@code object} is an instance of {@code ConstraintViolation}; {@code false} otherwise.
     */
    static boolean isConstraintViolationInstance(final Object object) {
        return applyConstraintViolationClassFor(object, c -> true);
    }

    /**
     * Checks whether specified object is an instance of either {@code javax.validation.ConstraintViolation} or {@code
     * jakarta.validation.ConstraintViolation}.
     *
     * @param object the object to be tested.
     */
    static <T> T requireConstraintViolationInstance(final T object) {
        requireNonNull(object, "object is null");
        return applyConstraintViolationClassFor(object, c -> object);
    }

    // ------------------------------------------------------------------------------------------------- getInvalidValue
    static Object getInvalidValue(final Object actual) {
        try {
            return getConstraintViolationClassFor(actual).getMethod("getInvalidValue").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ----------------------------------------------------------------------------------------------------- getLeafBean
    static Object getLeafBean(final Object actual) {
        try {
            return getConstraintViolationClassFor(actual).getMethod("getLeafBean").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ------------------------------------------------------------------------------------------------------ getMessage
    static String getMessage(final Object actual) {
        try {
            return (String) getConstraintViolationClassFor(actual).getMethod("getMessage").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ------------------------------------------------------------------------------------------------- getPropertyPath
    static Object getPropertyPath(final Object actual) {
        try {
            return getConstraintViolationClassFor(actual).getMethod("getPropertyPath").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ----------------------------------------------------------------------------------------------------- getRootBean
    static Object getRootBean(final Object actual) {
        try {
            return getConstraintViolationClassFor(actual).getMethod("getRootBean").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ------------------------------------------------------------------------------------------------ getRootBeanClass
    static Class<?> getRootBeanClass(final Object actual) {
        try {
            return (Class<?>) getConstraintViolationClassFor(actual).getMethod("getRootBeanClass").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ConstraintViolationUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
