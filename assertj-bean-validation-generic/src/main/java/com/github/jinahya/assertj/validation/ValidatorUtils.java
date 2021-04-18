/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2021 the original author or authors.
 */
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

import java.util.Set;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * A utility class for Bean-Validation.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class ValidatorUtils {

    private static final String SUFFIX = "Validator";

    static <R> R applyValidatorClass(final Function<? super Class<?>, ? extends R> function) {
        return ValidationReflectionUtils.applyClassForSuffix(SUFFIX, function);
    }

    static Class<?> getValidatorClass() {
        return applyValidatorClass(Function.identity());
    }

    static boolean isNullOrInstanceOfValidatorClass(final Object object) {
        if (object == null) {
            return true;
        }
        return ValidationReflectionUtils.isInstanceOfClassForSuffix(SUFFIX, object);
    }

    static <T> T requireNullOrInstanceOfValidatorClass(final T object) {
        if (object == null) {
            return null;
        }
        return ValidationReflectionUtils.requireInstanceOfClassForSuffix(SUFFIX, object);
    }

    @SuppressWarnings({"unchecked"})
    static <ACTUAL, CONSTRAINT_VIOLATION> Set<CONSTRAINT_VIOLATION> validate(
            final Object validator, final ACTUAL object, final Class<?>... groups) {
        requireNonNull(validator, "validator is null");
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        return applyValidatorClass(c -> {
            try {
                return (Set<CONSTRAINT_VIOLATION>) c.getMethod("validate", Object.class, Class[].class)
                        .invoke(validator, object, groups);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        });
    }

    /**
     * Invokes {@code Validator#validateProperty(T, String, Class...)} method on specified validator with given
     * arguments and returns the result.
     *
     * @param validator    the validator on which the {@code validateProperty(...)} method is invokes.
     * @param object       a value for {@code object} parameter.
     * @param propertyName a value for {@code propertyName} parameter.
     * @param groups       a value for {@code groups} parameter.
     * @param <ACTUAL>     {@code object} type parameter
     * @return the result of the invocation which is a set of {@code ConstraintViolation}s
     */
    @SuppressWarnings({"unchecked"})
    static <ACTUAL, CONSTRAINT_VIOLATION> Set<CONSTRAINT_VIOLATION> validateProperty(
            final Object validator, final ACTUAL object, final String propertyName, final Class<?>... groups) {
        requireNonNull(validator, "validator is null");
        requireNonNull(object, "object is null");
        requireNonNull(groups, "groups is null");
        return applyValidatorClass(c -> {
            try {
                return (Set<CONSTRAINT_VIOLATION>) c.getMethod(
                        "validateProperty", Object.class, String.class, Class[].class)
                        .invoke(validator, object, propertyName, groups);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        });
    }

    @SuppressWarnings({"unchecked"})
    static <T, CONSTRAINT_VIOLATION> Set<CONSTRAINT_VIOLATION> validateValue(
            final Object validator, final Class<T> beanType, final String propertyName, final Object value,
            final Class<?>... groups) {
        requireNonNull(validator, "validator is null");
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        requireNonNull(groups, "groups is null");
        return applyValidatorClass(c -> {
            try {
                return (Set<CONSTRAINT_VIOLATION>) c.getMethod(
                        "validateValue", Class.class, String.class, Object.class, Class[].class)
                        .invoke(validator, beanType, propertyName, value, groups);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ValidatorUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
