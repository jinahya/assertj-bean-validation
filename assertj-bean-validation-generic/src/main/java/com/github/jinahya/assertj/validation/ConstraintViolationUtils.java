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
     * Checks whether specified object is an instance of {@code ....validation.ConstraintViolation}.
     *
     * @param actual the object to be tested.
     */
    static <ACTUAL> ACTUAL requireConstraintViolationInstance(final ACTUAL actual) {
        if (actual == null) {
            return actual;
        }
        return applyConstraintViolationClassFor(actual, c -> actual);
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
    @SuppressWarnings({"unchecked"})
    static <PATH extends Iterable<?>> PATH getPropertyPath(final Object actual) {
        try {
            return (PATH) getConstraintViolationClassFor(actual).getMethod("getPropertyPath").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ----------------------------------------------------------------------------------------------------- getRootBean
    @SuppressWarnings({"unchecked"})
    static <T> T getRootBean(final Object actual) {
        try {
            return (T) getConstraintViolationClassFor(actual).getMethod("getRootBean").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // ------------------------------------------------------------------------------------------------ getRootBeanClass
    @SuppressWarnings({"unchecked"})
    static <T> Class<T> getRootBeanClass(final Object actual) {
        try {
            return (Class<T>) getConstraintViolationClassFor(actual).getMethod("getRootBeanClass").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ConstraintViolationUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
