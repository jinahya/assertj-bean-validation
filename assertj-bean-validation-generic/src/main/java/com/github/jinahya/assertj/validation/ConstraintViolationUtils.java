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

    private static final String SUFFIX = "ConstraintViolation";

    static <R> R applyConstraintViolationClass(final Function<? super Class<?>, ? extends R> function) {
        return ValidationReflectionUtils.applyClassForSuffix("ConstraintViolation", function);
    }

    /**
     * Indicates whether specified object is an instance of either {@code javax.validation.ConstraintViolation} or
     * {@code jakarta.validation.ConstraintViolation}.
     *
     * @param object the object to be tested.
     * @return {@code true} if {@code object} is an instance of {@code ConstraintViolation}; {@code false} otherwise.
     */
    static boolean isNullOrInstanceOfConstraintViolationClass(final Object object) {
        if (object == null) {
            return true;
        }
        return ValidationReflectionUtils.isInstanceOfClassForSuffix(SUFFIX, object);
    }

    /**
     * Checks whether specified object is an instance of {@code ....validation.ConstraintViolation}.
     *
     * @param object the object to be tested.
     */
    static <ACTUAL> ACTUAL requireNullOrInstanceOfConstraintViolationClass(final ACTUAL object) {
        if (object == null) {
            return null;
        }
        return ValidationReflectionUtils.requireInstanceOfClassForSuffix(SUFFIX, object);
    }

    // ------------------------------------------------------------------------------------------------- getInvalidValue
    static Object getInvalidValue(final Object actual) {
        return applyConstraintViolationClass(c -> {
            try {
                return c.getMethod("getInvalidValue").invoke(actual);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        });
    }

    // ----------------------------------------------------------------------------------------------------- getLeafBean
    static Object getLeafBean(final Object actual) {
        return applyConstraintViolationClass(c -> {
            try {
                return c.getMethod("getLeafBean").invoke(actual);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        });
    }

    // ------------------------------------------------------------------------------------------------------ getMessage
    static String getMessage(final Object actual) {
        return applyConstraintViolationClass(c -> {
            try {
                return (String) c.getMethod("getMessage").invoke(actual);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        });
    }

    // ------------------------------------------------------------------------------------------------- getPropertyPath
    @SuppressWarnings({"unchecked"})
    static <PATH extends Iterable<?>> PATH getPropertyPath(final Object actual) {
        return applyConstraintViolationClass(c -> {
            try {
                return (PATH) c.getMethod("getPropertyPath").invoke(actual);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        });
    }

    // ----------------------------------------------------------------------------------------------------- getRootBean
    @SuppressWarnings({"unchecked"})
    static <T> T getRootBean(final Object actual) {
        return applyConstraintViolationClass(c -> {
            try {
                return (T) c.getMethod("getRootBean").invoke(actual);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        });
    }

    // ------------------------------------------------------------------------------------------------ getRootBeanClass
    @SuppressWarnings({"unchecked"})
    static <T> Class<T> getRootBeanClass(final Object actual) {
        return applyConstraintViolationClass(c -> {
            try {
                return (Class<T>) c.getMethod("getRootBeanClass").invoke(actual);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ConstraintViolationUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
