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

import static java.util.Objects.requireNonNull;

final class EnumUtils {

    static String name(final Class<?> enumClass, final Object enumConstant) {
        requireNonNull(enumClass, "enumClass is null");
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException("not enum: " + enumClass);
        }
        try {
            return (String) enumClass.getMethod("name").invoke(enumConstant);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to invoke name() on " + enumConstant, roe);
        }
    }

    @SuppressWarnings("unchecked")
    static Object valueOf(final Class<?> enumClass, final String name) {
        requireNonNull(enumClass, "enumClass is null");
        if (!enumClass.isEnum()) {
            throw new IllegalArgumentException("not enum: " + enumClass);
        }
        requireNonNull(name, "name is null");
        return Enum.valueOf((Class<? extends Enum>) enumClass, name);
    }

    private EnumUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
