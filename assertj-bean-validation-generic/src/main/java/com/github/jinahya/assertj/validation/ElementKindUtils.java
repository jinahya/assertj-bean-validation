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

final class ElementKindUtils {

    private static final String SUFFIX = "ElementKind";

    private static <R> R applyElementKindClass(final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassForSuffix(SUFFIX, function);
    }

    private static Class<?> getElementKindClass() {
        return applyElementKindClass(Function.identity());
    }

    private static <R> R applyElementKindClassFor(final Object instance,
                                                  final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassFor(SUFFIX, instance, function);
    }

    private static Class<?> getElementKindClassFor(final Object instance) {
        return applyElementKindClassFor(instance, Function.identity());
    }

    /**
     * Indicates whether specified object is an instance of {@code ....validation.ElementKind}.
     *
     * @param actual the object to be tested.
     * @return {@code true} if {@code actual} is an instance of {@code ....validation.ElementKind}; {@code false}
     * otherwise.
     */
    static boolean isElementKindInstance(final Object actual) {
        return applyElementKindClassFor(actual, c -> true);
    }

    /**
     * Checks whether specified object is an instance of {@code ....validation.ElementKind}.
     *
     * @param <ACTUAL> the type of actual {@code ....validation.ElementKind}
     * @param actual   the object to be tested.
     */
    static <ACTUAL> ACTUAL requireElementKindInstance(final ACTUAL actual) {
        if (actual == null) {
            return null;
        }
        return applyElementKindClassFor(actual, c -> actual);
    }

    /**
     * Returns the value of {@code actual#getName()}.
     *
     * @param actual the actual value.
     * @return the value of {@code actual#getName()}.
     */
    static String getName(final Object actual) {
        requireNonNull(actual, "actual is null");
        if (!actual.getClass().isEnum()) {
            throw new IllegalArgumentException("not an enum constant: " + actual);
        }
        return EnumUtils.name(actual.getClass(), actual);
    }

    private ElementKindUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
