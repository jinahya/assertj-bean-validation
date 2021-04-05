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

public class ConstraintViolationTestUtils {

    public static Object getInvalidValue(final Object violation) {
        return ConstraintViolationUtils.getInvalidValue(violation);
    }

    public static Object getLeafBean(final Object violation) {
        return ConstraintViolationUtils.getLeafBean(violation);
    }

    public static String getMessage(final Object violation) {
        return ConstraintViolationUtils.getMessage(violation);
    }

    public static <PATH extends Iterable<NODE>, NODE> PATH getPropertyPath(final Object actual) {
        return ConstraintViolationUtils.getPropertyPath(actual);
    }

    public static Object getRootBean(final Object violation) {
        return ConstraintViolationUtils.getRootBean(violation);
    }

    public static Class<?> getRootBeanClass(final Object violation) {
        return ConstraintViolationUtils.getRootBeanClass(violation);
    }

    private ConstraintViolationTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
