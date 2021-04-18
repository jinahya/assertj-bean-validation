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

@SuppressWarnings({"java:S125"})
final class ConstraintDeclarationExceptionUtils {

    private static final String SUFFIX = "ConstraintDeclarationException";

    static <R> R applyConstraintDeclarationExceptionClass(final Function<? super Class<?>, ? extends R> function) {
        return ReflectionUtils.applyClassForSuffix(SUFFIX, function);
    }

    private static Class<?> constraintViolationExceptionClass = null;

    static Class<?> getConstraintDeclarationExceptionClass() {
        if (constraintViolationExceptionClass == null) {
            constraintViolationExceptionClass = applyConstraintDeclarationExceptionClass(Function.identity());
        }
        return constraintViolationExceptionClass;
    }

    static boolean isNullOrInstanceOfConstraintDeclarationExceptionClass(final Object object) {
        if (object == null) {
            return true;
        }
        return getConstraintDeclarationExceptionClass().isInstance(object);
    }

    static <T> T requireNullOrInstanceOfConstraintDeclarationExceptionClass(final T object) {
        if (object == null) {
            return null;
        }
        if (!isNullOrInstanceOfConstraintDeclarationExceptionClass(object)) {
            throw new IllegalArgumentException("not an instance of ConstraintViolationException: " + object);
        }
        return object;
    }

    private ConstraintDeclarationExceptionUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
