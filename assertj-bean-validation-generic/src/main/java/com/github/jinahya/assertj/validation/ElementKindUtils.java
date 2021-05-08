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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

import static com.github.jinahya.assertj.validation.ReflectionUtils.getClassForSuffixOrExceptionInInitializerError;
import static java.util.Objects.requireNonNull;

@SuppressWarnings({"java:S125"})
final class ElementKindUtils {

    private static final Class<?> ELEMENT_KIND_CLASS
            = getClassForSuffixOrExceptionInInitializerError("ElementKind");

    static boolean isElementKind(final Object object, final boolean nullable) {
        if (nullable && object == null) {
            return true;
        }
        return ELEMENT_KIND_CLASS.isInstance(requireNonNull(object, "object is null"));
    }

    static <T> T requireElementKind(final T object, final boolean nullable) {
        if (!isElementKind(object, nullable)) {
            throw new IllegalArgumentException("not an instance of " + ELEMENT_KIND_CLASS + ": " + object);
        }
        return object;
    }

    // ---------------------------------------------------------------------------------------------------- Enum#valueOf
    private static final Method VALUE_OF_METHOD;

    static {
        try {
            VALUE_OF_METHOD = Enum.class.getMethod("valueOf", Class.class, String.class);
        } catch (final NoSuchMethodException nsme) {
            throw new ExceptionInInitializerError(nsme);
        }
    }

    private static final MethodHandle VALUE_OF_METHOD_HANDLE;

    static {
        try {
            VALUE_OF_METHOD_HANDLE = MethodHandles.lookup().unreflect(VALUE_OF_METHOD);
        } catch (final IllegalAccessException iae) {
            throw new ExceptionInInitializerError(iae);
        }
    }

    static Object valueOf(final String name) {
        try {
            return VALUE_OF_METHOD_HANDLE.invoke(null, ELEMENT_KIND_CLASS, name);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ElementKindUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
