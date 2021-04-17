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

    /**
     * Applies the class of {@code ....validation.ConstraintDeclarationException} to specified function and returns the
     * result.
     *
     * @param function the function to applied with the class of {@code ....validation.ConstraintDeclarationException}.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     */
    static <R> R applyConstraintDeclarationExceptionClass(final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassForSuffix(SUFFIX, function);
    }

    /**
     * Returns the class of {@code ....validation.Path.PropertyNode}.
     *
     * @return the class of {@code ....validation.Path.PropertyNode}.
     */
    static Class<?> getConstraintDeclarationExceptionClass() {
        return applyConstraintDeclarationExceptionClass(Function.identity());
    }

    private static <R> R applyConstraintDeclarationExceptionClassFor(final Object actual,
                                                                     final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassFor(SUFFIX, actual, function);
    }

    private static Class<?> getConstraintDeclarationExceptionClassFor(final Object actual) {
        return applyConstraintDeclarationExceptionClassFor(actual, Function.identity());
    }

    /**
     * Indicates whether specified object is an instance of {@code ....validation.ConstraintDeclarationException}.
     *
     * @param actual the object to be tested.
     * @return {@code true} if {@code actual} is an instance of {@code ....validation.ConstraintDeclarationException};
     * {@code false} otherwise.
     */
    static boolean isConstraintDeclarationExceptionInstance(final Object actual) {
        if (actual == null) {
            return true;
        }
        return applyConstraintDeclarationExceptionClassFor(actual, c -> true);
    }

    /**
     * Checks whether specified object is an instance of {@code ....validation.ConstraintDeclarationException}.
     *
     * @param actual the object to be tested.
     */
    static <T> T requireConstraintDeclarationExceptionInstance(final T actual) {
        if (actual == null) {
            return null;
        }
        return applyConstraintDeclarationExceptionClassFor(
                ValidationExceptionUtils.requireValidationExceptionInstance(actual),
                c -> actual
        );
    }

    private ConstraintDeclarationExceptionUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
