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

import org.assertj.core.api.AbstractAssert;

import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract base class for verifying an instance of {@code ConstraintViolation}.
 *
 * @param <SELF> self type parameter
 * @param <T>    constraint violation type parameter
 * @param <U>    root bean type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119", "java:S2160"})
public abstract class AbstractConstraintViolationAssert<
        SELF extends AbstractConstraintViolationAssert<SELF, T, U>, T, U>
        extends AbstractAssert<SELF, T> {

    /**
     * Creates a new instance for specified actual value.
     *
     * @param actual the actual value to verify; may be {@code null} yet must be an instance of either {@code
     *               javax.validation.ConstraintViolation} or {@code jakarta.validation.ConstraintViolation}.
     * @see #actual
     */
    protected AbstractConstraintViolationAssert(final T actual, Class<?> selfType, final Class<?> actualType) {
        super(actual, selfType);
        this.actualType = requireNonNull(actualType, "actualType is null");
    }

    // ------------------------------------------------------------------------------------------------- getInvalidValue

    /**
     * Returns {@code actual.getInvalidValue()}.
     *
     * @return {@code actual.getInvalidValue()}.
     */
    protected Object getInvalidValue() {
        isNotNull();
        try {
            return actualType.getMethod("getInvalidValue").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    /**
     * Verifies that the {@link jakarta.validation.ConstraintViolation#getInvalidValue() actual.invalidValue} satisfies
     * given requirements expressed as a {@link Consumer}.
     *
     * @param requirements the consumer accepts {@code actual.invalidValue}.
     * @return {@link #myself self}.
     */
    public SELF hasInvalidValueSatisfying(final Consumer<Object> requirements) {
        isNotNull();
        assertThat(getInvalidValue()).satisfies(requirements);
        return myself;
    }

    /**
     * Verifies that the {@link jakarta.validation.ConstraintViolation#getInvalidValue() actual.invalidValue} is equal
     * to specified value.
     *
     * @param expected the expected value of {@code actual.invalidValue}.
     * @return {@link #myself self}.
     */
    public SELF hasInvalidValueEqualTo(final Object expected) {
        return hasInvalidValueSatisfying(v -> assertThat(v).isEqualTo(expected));
    }

    // ----------------------------------------------------------------------------------------------------- getLeafBean

    /**
     * Returns the value of {@code actual.getLeafBean()}.
     *
     * @return the value of {@code actual.getLeafBean()}.
     */
    protected Object getLeafBean() {
        isNotNull();
        try {
            return actualType.getMethod("getLeafBean").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    /**
     * Verifies that the {@link jakarta.validation.ConstraintViolation#getLeafBean() actual.leafBean} satisfies given
     * requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.leafBean}.
     * @return {@link #myself self}.
     */
    public SELF hasLeafBeanSatisfying(final Consumer<Object> requirements) {
        isNotNull();
        assertThat(getLeafBean()).satisfies(requirements);
        return myself;
    }

    /**
     * Verifies that the {@link jakarta.validation.ConstraintViolation#getLeafBean() actual.leafBean} is same as
     * specified value.
     *
     * @param expected the expected value of {@code actual.leafBean} method.
     * @return {@link #myself self}.
     */
    public SELF hasLeafBeanSameAs(final Object expected) {
        return hasLeafBeanSatisfying(v -> assertThat(v).isSameAs(expected));
    }

    // ------------------------------------------------------------------------------------------------------ getMessage

    /**
     * Returns the value of {@code actual.getMessage()}.
     *
     * @return the value of {@code actual.getMessage()}.
     */
    protected String getMessage() {
        isNotNull();
        try {
            return (String) actualType.getMethod("getMessage").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    /**
     * Verifies that the {@link jakarta.validation.ConstraintViolation#getMessage() actual.message} satisfies given
     * requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.message}.
     * @return {@link #myself self}.
     */
    public SELF hasMessageSatisfying(final Consumer<? super String> requirements) {
        isNotNull();
        assertThat(getMessage()).satisfies(requirements::accept);
        return myself;
    }

    /**
     * Verifies that the {@link jakarta.validation.ConstraintViolation#getMessage() actual.message} is {@link
     * AbstractAssert#isEqualTo(Object) equal} to specified value.
     *
     * @param expected the expected value of {@code actual.message}.
     * @return {@link #myself self}.
     */
    public SELF hasMessageEqualTo(final String expected) {
        return hasMessageSatisfying(v -> assertThat(v).isEqualTo(expected));
    }

    // ------------------------------------------------------------------------------------------------- getPropertyPath

    /**
     * Returns the value of {@code actual.getPropertyPath()}.
     *
     * @return the value of {@code actual.getPropertyPath()}.
     */
    protected Object getPropertyPath() {
        isNotNull();
        try {
            return actualType.getMethod("getPropertyPath").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    public SELF hasPropertyPathSatisfying(final Consumer<? super Object> requirements) {
        requireNonNull(requirements, "requirements is null");
        isNotNull();
        assertThat(getPropertyPath()).satisfies(requirements);
        return myself;
    }

    public SELF hasPropertyPathEqualTo(final Object expected) {
        return hasPropertyPathSatisfying(v -> assertThat(v).isEqualTo(expected));
    }

    // ----------------------------------------------------------------------------------------------------- getRootBean

    /**
     * Returns the value of {@code actual.getRootBean()}.
     *
     * @return the value of {@code actual.getRootBean()}.
     */
    @SuppressWarnings({"unchecked"})
    protected U getRootBean() {
        isNotNull();
        try {
            return (U) actualType.getMethod("getRootBean").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    public SELF hasRootBeanSatisfying(final Consumer<? super U> requirements) {
        requireNonNull(requirements, "requirements is null");
        isNotNull();
        assertThat(getRootBean()).satisfies(requirements::accept);
        return myself;
    }

    public SELF hasRootBeanSameAs(final Object expected) {
        return hasRootBeanSatisfying(v -> assertThat(v).isSameAs(expected));
    }

    // ------------------------------------------------------------------------------------------------ getRootBeanClass

    /**
     * Returns the value of {@code actual.getRootBeanClass()}.
     *
     * @return the value of {@code actual.getRootBeanClass()}.
     */
    @SuppressWarnings({"unchecked"})
    protected Class<U> getRootBeanClass() {
        isNotNull();
        try {
            return (Class<U>) actualType.getMethod("getRootBeanClass").invoke(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    @SuppressWarnings({"unchecked"})
    public SELF hasRootBeanClassSatisfying(final Consumer<? super Class<U>> requirements) {
        requireNonNull(requirements, "requirements is null");
        isNotNull();
        assertThat(getRootBeanClass()).satisfies(v -> requirements.accept((Class<U>) v));
        return myself;
    }

    public SELF hasRootBeanClassSameAs(final Object expected) {
        return hasRootBeanClassSatisfying(v -> assertThat(v).isSameAs(expected));
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected final Class<?> actualType;
}
