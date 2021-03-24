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

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public final class BeanValidationTestUtils {

    public static Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    static <T> Set<ConstraintViolation<T>> validate(final T object, final Class<?>... groups) {
        return validator().validate(object, groups);
    }

    public static <T> boolean isValid(final T object, final Class<?>... groups) {
        return validate(object, groups).isEmpty();
    }

    public static <T> T requireValid(final T object, final Class<?>... groups) {
        final Set<ConstraintViolation<T>> constraintViolations = validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
        return object;
    }

    BeanValidationTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
