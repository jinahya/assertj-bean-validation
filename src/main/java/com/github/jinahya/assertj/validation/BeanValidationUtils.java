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

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

import static java.util.Objects.requireNonNull;

/**
 * Utility class for Bean-Validation.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class BeanValidationUtils {

    static <T> Set<ConstraintViolation<T>> validate(final T object, final Validator validator,
                                                    final Class<?>... groups) {
        requireNonNull(object, "object is null");
        requireNonNull(validator, "validator is null");
        requireNonNull(groups, "groups is null");
        return validator.validate(object, groups);
    }

    static <T> void requireValid(final T object, final Validator validator, final Class<?>... groups) {
        final Set<ConstraintViolation<T>> constraintViolations = validate(object, validator, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    private BeanValidationUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
