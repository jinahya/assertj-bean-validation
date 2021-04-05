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
final class ConstraintDefinitionExceptionUtils {

    private static final String SUFFIX = "ConstraintDefinitionException";

    /**
     * Applies the class of {@code ....validation.ConstraintDefinitionException} to specified function and returns the
     * result.
     *
     * @param function the function to applied with the class of {@code ....validation.ConstraintDefinitionException}.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     */
    static <R> R applyConstraintDefinitionExceptionClass(final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassForSuffix(SUFFIX, function);
    }

    /**
     * Returns the class of {@code ....validation.Path.PropertyNode}.
     *
     * @return the class of {@code ....validation.Path.PropertyNode}.
     */
    static Class<?> getConstraintDefinitionExceptionClass() {
        return applyConstraintDefinitionExceptionClass(Function.identity());
    }

    private static <R> R applyConstraintDefinitionExceptionClassFor(
            final Object actual, final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassFor(SUFFIX, actual, function);
    }

    private static Class<?> getConstraintDefinitionExceptionClassFor(final Object actual) {
        return applyConstraintDefinitionExceptionClassFor(actual, Function.identity());
    }

    /**
     * Indicates whether specified object is an instance of {@code ....validation.ConstraintDefinitionException}.
     *
     * @param actual the object to be tested.
     * @return {@code true} if {@code actual} is an instance of {@code ....validation.ConstraintDefinitionException};
     * {@code false} otherwise.
     */
    static boolean isConstraintDefinitionExceptionInstance(final Object actual) {
        if (actual == null) {
            return true;
        }
        return applyConstraintDefinitionExceptionClassFor(actual, c -> true);
    }

    /**
     * Checks whether specified object is an instance of {@code ....validation.ConstraintDefinitionException}.
     *
     * @param actual the object to be tested.
     */
    static <T> T requireConstraintDefinitionExceptionInstance(final T actual) {
        if (actual == null) {
            return null;
        }
        return applyConstraintDefinitionExceptionClassFor(
                ValidationExceptionUtils.requireValidationExceptionInstance(actual),
                c -> actual
        );
    }

    private ConstraintDefinitionExceptionUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
