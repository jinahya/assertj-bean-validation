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

final class ValidationReflectionUtils {

    static <R> R applyClassForSuffix(final String suffix, final Function<? super Class<?>, ? extends R> function) {
        requireNonNull(suffix, "suffix is null");
        requireNonNull(function, "function is null");
        try {
            return function.apply(Class.forName(ValidationSpiUtils.getPrefix() + suffix));
        } catch (final ClassNotFoundException cnfe) {
            throw new RuntimeException(cnfe);
        }
    }

    static Class<?> getClassForSuffix(final String suffix) {
        return applyClassForSuffix(suffix, Function.identity());
    }

    static boolean isInstanceOfClassForSuffix(final String suffix, final Object object) {
        return applyClassForSuffix(suffix, c -> c.isInstance(object));
    }

    static <T> T requireInstanceOfClassForSuffix(final String suffix, final T object) {
        if (!isInstanceOfClassForSuffix(suffix, object)) {
            throw new IllegalArgumentException(object + " is not an instance of the class for suffix(" + suffix + ")");
        }
        return object;
    }

    private ValidationReflectionUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
