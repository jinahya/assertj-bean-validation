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

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * A utility class for fluently creating instances of {@link BeanAssertImpl}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public final class ValidationAssertions {

    /**
     * Creates a new assertion object for verifying specified bean object.
     *
     * @param <ACTUAL> type of actual bean
     * @param actual   the actual bean object to verify.
     * @return a new assertion object for verifying {@code actual}.
     */
    public static <ACTUAL> AbstractBeanAssert<?, ACTUAL> assertBean(final ACTUAL actual) {
        return new BeanAssertImpl<>(actual);
    }

    /**
     * Creates a new assertion object for verifying specified bean object.
     *
     * @param <SELF>     assertion type parameter
     * @param <ACTUAL>   actual type parameter
     * @param selfClass  type of assertion.
     * @param actualType type of actual.
     * @param actual     the actual bean object to verify.
     * @return a new assertion object for verifying {@code actual}.
     */
    public static <SELF extends AbstractBeanAssert<SELF, ACTUAL>, ACTUAL> SELF assertBean(
            final @NotNull Class<SELF> selfClass, final @NotNull Class<ACTUAL> actualType, final ACTUAL actual) {
        try {
            final Constructor<SELF> constructor = selfClass.getDeclaredConstructor(actualType);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    private static <SELF extends AbstractBeanAssert<SELF, ACTUAL>, ACTUAL> SELF assertBeanHelper(
            final @NotNull Class<SELF> selfClass, final Class<ACTUAL> actualClass, final @NotNull Object actual) {
        Objects.requireNonNull(actual, "actual is null");
        return assertBean(selfClass, actualClass, actualClass.cast(actual));
    }

    public static <SELF extends AbstractBeanAssert<SELF, ACTUAL>, ACTUAL> SELF assertBean(
            final @NotNull Class<SELF> selfClass, final @NotNull ACTUAL actual) {
        Objects.requireNonNull(actual, "actual is null");
        @SuppressWarnings({"unchecked"})
        final Class<ACTUAL> actualClass = (Class<ACTUAL>) actual.getClass();
        return assertBeanHelper(selfClass, actualClass, actual);
    }

    public static <SELF extends AbstractBeanAssert<SELF, ACTUAL>, ACTUAL> SELF assertVirtualBean(
            final @NotNull Object actual) {
        Objects.requireNonNull(actual, "actual is null");
        @SuppressWarnings({"unchecked"})
        final Class<ACTUAL> actualClass = (Class<ACTUAL>) actual.getClass();
        final String selfClassName = actualClass.getName() + "Assert";
        try {
            @SuppressWarnings({"unchecked"})
            final Class<SELF> selfClass = (Class<SELF>) Class.forName(selfClassName);
            return assertBean(selfClass, actualClass.cast(actual));
        } catch (final ClassNotFoundException cnfe) {
            throw new RuntimeException("unable to find class for " + selfClassName, cnfe);
        }
    }

    /**
     * Creates a new assertion object for verifying specified value against a property of specific bean type.
     *
     * @param <ACTUAL> type of actual value
     * @param actual   the actual property value to verify.
     * @return a new assertion instance for {@code actual}.
     */
    public static <ACTUAL> AbstractPropertyAssert<?, ACTUAL> assertProperty(final ACTUAL actual) {
        return new PropertyAssertImpl<>(actual);
    }

    public static <SELF extends AbstractPropertyAssert<SELF, ACTUAL>, ACTUAL> @NotNull SELF assertProperty(
            final @NotNull Class<SELF> selfClass, final @NotNull Class<ACTUAL> actualClass, final ACTUAL actual) {
        try {
            final Constructor<SELF> constructor = selfClass.getDeclaredConstructor(actualClass);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to instantiate " + selfClass + " with " + actual, roe);
        }
    }

    private static <SELF extends AbstractPropertyAssert<SELF, ACTUAL>, ACTUAL> SELF assertPropertyHelper(
            final @NotNull Class<SELF> selfClass, final @NotNull Class<ACTUAL> actualClass, final Object actual) {
        return assertProperty(selfClass, actualClass, actualClass.cast(actual));
    }

    public static <SELF extends AbstractPropertyAssert<SELF, ACTUAL>, ACTUAL> SELF assertProperty(
            final @NotNull Class<SELF> selfClass, final @NotNull ACTUAL actual) {
        Objects.requireNonNull(actual, "actual is null");
        @SuppressWarnings({"unchecked"})
        final Class<ACTUAL> actualClass = (Class<ACTUAL>) actual.getClass();
        return assertPropertyHelper(selfClass, actualClass, actual);
    }

    public static <SELF extends AbstractPropertyAssert<SELF, ACTUAL>, ACTUAL> SELF assertVirtualProperty(
            final @NotNull Object actual) {
        Objects.requireNonNull(actual, "actual is null");
        @SuppressWarnings({"unchecked"})
        final Class<ACTUAL> actualClass = (Class<ACTUAL>) actual.getClass(); // ClassCastException!
        final String selfClassName = actualClass.getName() + "Assert";
        try {
            @SuppressWarnings({"unchecked"})
            final Class<SELF> selfClass = (Class<SELF>) Class.forName(selfClassName);
            return assertProperty(selfClass, actualClass.cast(actual));
        } catch (final ClassNotFoundException cnfe) {
            throw new RuntimeException("unable to find class for " + selfClassName, cnfe);
        }
    }

    /**
     * Creates a new instance.
     */
    private ValidationAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
