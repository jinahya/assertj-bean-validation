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

import org.assertj.core.internal.bytebuddy.implementation.auxiliary.AuxiliaryType;

import java.util.Set;
import java.util.function.Function;

@SuppressWarnings({"java:S125"})
final class ConstraintViolationExceptionUtils {

    private static final String SUFFIX = "ConstraintViolationException";

    /**
     * Applies the class of {@code ....validation.ConstraintViolationException} to specified function and returns the
     * result.
     *
     * @param function the function to applied with the class of {@code ....validation.ConstraintViolationException}.
     * @param <R>      result type parameter
     * @return the result of the {@code function}.
     */
    static <R> R applyConstraintViolationExceptionClass(final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassForSuffix(SUFFIX, function);
    }

    /**
     * Returns the class of {@code ....validation.Path.PropertyNode}.
     *
     * @return the class of {@code ....validation.Path.PropertyNode}.
     */
    static Class<?> getConstraintViolationExceptionClass() {
        return applyConstraintViolationExceptionClass(Function.identity());
    }

    private static <R> R applyConstraintViolationExceptionClassFor(final Exception actual,
                                                                   final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassFor(SUFFIX, actual, function);
    }

    private static Class<?> getConstraintViolationExceptionClassFor(final Exception actual) {
        return applyConstraintViolationExceptionClassFor(actual, Function.identity());
    }

    /**
     * Indicates whether specified object is an instance of {@code ....validation.ConstraintViolationException}.
     *
     * @param actual the object to be tested.
     * @return {@code true} if {@code actual} is an instance of {@code ....validation.ConstraintViolationException};
     * {@code false} otherwise.
     */
    static boolean isConstraintViolationExceptionInstance(final Exception actual) {
        if (actual == null) {
            return true;
        }
        return applyConstraintViolationExceptionClassFor(actual, c -> true);
    }

    /**
     * Checks whether specified object is an instance of {@code ....validation.ConstraintViolationException}.
     *
     * @param actual the object to be tested.
     */
    static <T extends Exception> T requireConstraintViolationExceptionInstance(final T actual) {
        if (actual == null) {
            return null;
        }
        return applyConstraintViolationExceptionClassFor(actual, c -> actual);
    }

    static <T> Set<T> getConstraintViolations(final Exception actual) {
        return applyConstraintViolationExceptionClassFor(actual, c -> {
            try {
                @SuppressWarnings({"unchecked"})
                final Set<T> constraintViolations = (Set<T>) c.getMethod("getConstraintViolations").invoke(actual);
                return constraintViolations;
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        });
    }

    private ConstraintViolationExceptionUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
