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
import org.assertj.core.api.AbstractIterableAssert;

import java.util.function.Consumer;

import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getInvalidValue;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getLeafBean;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getMessage;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getPropertyPath;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getRootBean;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getRootBeanClass;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for verifying an instance of {@code ConstraintViolation}.
 *
 * @param <T> the type of the root bean of the constraint violation
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintViolationAssert<T>
        extends AbstractAssert<ConstraintViolationAssert<T>, Object> {

    /**
     * Creates a new instance for specified actual value.
     *
     * @param actual the actual value to verify; must not be {@code null} and must be an instance of either {@code
     *               javax.validation.ConstraintViolation} or {@code jakarta.validation.ConstraintViolation}.
     * @see #actual
     */
    public ConstraintViolationAssert(final Object actual) {
        super(actual, ConstraintViolationAssert.class);
    }

    // ------------------------------------------------------------------------------------------------- getInvalidValue

    /**
     * Verifies that the {@code actual.invalidValue} satisfies given requirements expressed as a {@link Consumer}.
     *
     * @param requirements the consumer accepts {@code actual.invalidValue}.
     * @return {@link #myself self}
     */
    public ConstraintViolationAssert<T> hasInvalidValueSatisfying(final Consumer<Object> requirements) {
        isNotNull();
        assertThat(getInvalidValue(actual)).satisfies(requirements);
        return myself;
    }

    /**
     * Verifies that the {@code actual.invalidValue} is {@link AbstractAssert#isEqualTo(Object) equal} to specified
     * value.
     *
     * @param expected the expected value of {@code actual.invalidValue}.
     * @return {@link #myself self}
     */
    public ConstraintViolationAssert<T> hasInvalidValueEqualTo(final Object expected) {
        return hasInvalidValueSatisfying(v -> assertThat(v).isEqualTo(expected));
    }

    // ----------------------------------------------------------------------------------------------------- getLeafBean

    /**
     * Verifies that the {@code actual.leafBean} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.leafBean}.
     * @return {@link #myself self}
     */
    public ConstraintViolationAssert<T> hasLeafBeanSatisfying(final Consumer<Object> requirements) {
        isNotNull();
        assertThat(getLeafBean(actual)).satisfies(requirements);
        return myself;
    }

    /**
     * Verifies that the {@code actual.leafBean} is same as specified value.
     *
     * @param expected the expected value of {@code actual.leafBean} method.
     * @return {@link #myself self}
     */
    public ConstraintViolationAssert<T> hasLeafBeanSameAs(final Object expected) {
        return hasLeafBeanSatisfying(v -> assertThat(v).isSameAs(expected));
    }

    // ------------------------------------------------------------------------------------------------------ getMessage

    /**
     * Verifies that the {@code actual.message} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.message}.
     * @return {@link #myself self}
     */
    public ConstraintViolationAssert<T> hasMessageSatisfying(final Consumer<? super String> requirements) {
        isNotNull();
        assertThat(getMessage(actual)).satisfies(requirements::accept);
        return myself;
    }

    /**
     * Verifies that the {@code actual.message} is {@link AbstractAssert#isEqualTo(Object) equal} to specified value.
     *
     * @param expected the expected value of {@code actual.message}.
     * @return {@link #myself self}
     */
    public ConstraintViolationAssert<T> hasMessageEqualTo(final Object expected) {
        return hasMessageSatisfying(v -> assertThat(v).isEqualTo(expected));
    }

    // ------------------------------------------------------------------------------------------------- getPropertyPath

    /**
     * Verifies that the {@code actual.propertyPath} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.propertyPath}.
     * @return {@link #myself self}
     */
    @SuppressWarnings({"unchecked"})
    public <U extends Iterable<?>> ConstraintViolationAssert<T> hasPropertyPathSatisfying(
            final Consumer<? super U> requirements) {
        requireNonNull(requirements, "requirements is null");
        isNotNull();
        assertThat(getPropertyPath(actual)).satisfies(v -> requirements.accept((U) v));
        return myself;
    }

    /**
     * Verifies that the {@code actual.propertyPath} is {@link AbstractIterableAssert#isEqualTo(Object) equal} to
     * specified value.
     *
     * @param expected the expected value of {@code actual.message}.
     * @return {@link #myself self}
     */
    public ConstraintViolationAssert<T> hasPropertyPathEqualTo(final Object expected) {
        return hasPropertyPathSatisfying(v -> assertThat(v).isEqualTo(expected));
    }

    // ----------------------------------------------------------------------------------------------------- getRootBean

    /**
     * Verifies that the {@code actual.rootBean} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.rootBean}.
     * @return {@link #myself self}
     */
    @SuppressWarnings({"unchecked"})
    public ConstraintViolationAssert<T> hasRootBeanSatisfying(final Consumer<? super T> requirements) {
        requireNonNull(requirements, "requirements is null");
        isNotNull();
        assertThat(getRootBean(actual)).satisfies(v -> requirements.accept((T) v));
        return myself;
    }

    /**
     * Verifies that the {@code actual.rootBean} is {@link AbstractAssert#isEqualTo(Object) equal} to specified value.
     *
     * @param expected the expected value of {@code actual.rootBean}.
     * @return {@link #myself self}
     */
    public ConstraintViolationAssert<T> hasRootBeanSameAs(final Object expected) {
        return hasRootBeanSatisfying(v -> assertThat(v).isSameAs(expected));
    }

    // ------------------------------------------------------------------------------------------------ getRootBeanClass

    /**
     * Verifies that the {@code actual.rootBeanClass} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.rootBeanClass}.
     * @return {@link #myself self}
     */
    @SuppressWarnings({"unchecked"})
    public ConstraintViolationAssert<T> hasRootBeanClassSatisfying(final Consumer<? super Class<T>> requirements) {
        requireNonNull(requirements, "requirements is null");
        isNotNull();
        assertThat(getRootBeanClass(actual)).satisfies(v -> requirements.accept((Class<T>) v));
        return myself;
    }

    /**
     * Verifies that the {@code actual.rootBeanClass} is {@link AbstractAssert#isSameAs(Object) same} as specified
     * value.
     *
     * @param expected the expected value of {@code actual.rootBeanClass}.
     * @return {@link #myself self}
     */
    public ConstraintViolationAssert<T> hasRootBeanClassSameAs(final Object expected) {
        return hasRootBeanClassSatisfying(v -> assertThat(v).isSameAs(expected));
    }
}
