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

import java.lang.reflect.Constructor;
import java.util.Set;

public final class ConstraintViolationExceptionTestUtils {

    public static Class<?> getConstraintViolationExceptionClass() {
        return ConstraintViolationExceptionUtils.getConstraintViolationExceptionClass();
    }

    public static Object getConstraintViolationExceptionInstance(final Set<Object> constraintViolations) {
        try {
            final Constructor<?> constructor = getConstraintViolationExceptionClass().getConstructor(Set.class);
            final Object instance = constructor.newInstance(constraintViolations);
            return ConstraintViolationExceptionUtils.requireConstraintViolationExceptionInstance(
                    (RuntimeException) instance);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    ConstraintViolationExceptionTestUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
