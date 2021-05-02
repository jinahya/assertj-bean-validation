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

final class ReflectionUtils {

    static Class<?> getClassForSuffixOrError(
            final String suffix, final Function<? super ClassNotFoundException, ? extends Error> function) {
        requireNonNull(suffix, "suffix is null");
        requireNonNull(function, "function is null");
        try {
            return Class.forName(ValidationSpiUtils.getPrefix() + suffix);
        } catch (final ClassNotFoundException cnfe) {
            throw function.apply(cnfe);
        }
    }

    static Class<?> getClassForSuffixOrExceptionInInitializerError(final String suffix) {
        return getClassForSuffixOrError(suffix, ExceptionInInitializerError::new);
    }

    static Class<?> getClassForSuffixOrException(
            final String suffix,
            final Function<? super ClassNotFoundException, ? extends RuntimeException> function) {
        requireNonNull(suffix, "suffix is null");
        requireNonNull(function, "function is null");
        try {
            return Class.forName(ValidationSpiUtils.getPrefix() + suffix);
        } catch (final ClassNotFoundException cnfe) {
            throw function.apply(cnfe);
        }
    }

    private ReflectionUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
