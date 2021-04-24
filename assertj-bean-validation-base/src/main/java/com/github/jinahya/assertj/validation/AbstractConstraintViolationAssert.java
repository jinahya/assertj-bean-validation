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

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for verifying instances of {@code ConstraintViolation}.
 *
 * @param <SELF>      self type parameter
 * @param <ACTUAL>    the actual type of {@code ....validation.ConstraintViolation}
 * @param <PATH>      the actual type of {@code ....validation.Path}
 * @param <ROOT_BEAN> the type of the root bean of {@link ACTUAL}
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractConstraintViolationAssert<
        SELF extends AbstractConstraintViolationAssert<SELF, ACTUAL, PATH, ROOT_BEAN>,
        ACTUAL,
        PATH,
        ROOT_BEAN>
        extends AbstractAssert<SELF, ACTUAL> {

    /**
     * Creates a new instance for specified actual value.
     *
     * @param actual   the actual value to verify; must not be {@code null} and must be an instance of either {@code
     *                 javax.validation.ConstraintViolation} or {@code jakarta.validation.ConstraintViolation}.
     * @param selfType a self type.
     */
    protected AbstractConstraintViolationAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    // ---------------------------------------------------------------------------------------------------- invalidValue

    /**
     * Gets the invalid value of specified actual value.
     *
     * @param a the actual value on which the {@code getInvalidValue()} method is invoked.
     * @return the invalid value of {@code actual}.
     */
    protected abstract Object getInvalidValue(ACTUAL a);

    /**
     * Verifies that the {@code actual.invalidValue} satisfies given requirements expressed as a {@link Consumer}.
     *
     * @param requirements the consumer accepts {@code actual.invalidValue}.
     * @return {@link #myself self}
     */
    public SELF hasInvalidValueSatisfying(final Consumer<Object> requirements) {
        return isNotNull().satisfies(a -> {
            assertThat(getInvalidValue(a)).satisfies(requirements);
        });
    }

    /**
     * Verifies that the {@code actual.invalidValue} is {@link AbstractAssert#isEqualTo(Object) equal} to specified
     * value.
     *
     * @param expected the expected value of {@code actual.invalidValue}.
     * @return {@link #myself self}
     */
    public SELF hasInvalidValueEqualTo(final Object expected) {
        return hasInvalidValueSatisfying(v -> assertThat(v).isEqualTo(expected));
    }

    // -------------------------------------------------------------------------------------------------------- leafBean

    /**
     * Gets the leaf bean from specified actual value.
     *
     * @param actual the actual value whose leaf bean is returned.
     * @return the leaf bean of {@code actual}.
     */
    protected abstract Object getLeafBean(ACTUAL actual);

    /**
     * Verifies that the {@code actual.leafBean} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.leafBean}.
     * @return {@link #myself self}
     */
    public SELF hasLeafBeanSatisfying(final Consumer<Object> requirements) {
        return isNotNull().satisfies(a -> {
            assertThat(getLeafBean(a)).satisfies(requirements);
        });
    }

    /**
     * Verifies that the {@code actual.leafBean} is same as specified value.
     *
     * @param expected the expected value of {@code actual.leafBean} method.
     * @return {@link #myself self}
     */
    public SELF hasLeafBeanSameAs(final Object expected) {
        return hasLeafBeanSatisfying(v -> assertThat(v).isSameAs(expected));
    }

    // --------------------------------------------------------------------------------------------------------- message

    /**
     * Gets the message from specified actual value.
     *
     * @param actual the actual value.
     * @return the message of {@code actual}.
     */
    protected abstract String getMessage(ACTUAL actual);

    /**
     * Verifies that the {@code actual.message} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.message}.
     * @return {@link #myself self}
     */
    public SELF hasMessageSatisfying(final Consumer<? super String> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull().satisfies(a -> {
            assertThat(getMessage(a)).satisfies(requirements::accept);
        });
    }

    /**
     * Verifies that the {@code actual.message} is {@link AbstractAssert#isEqualTo(Object) equal} to specified value.
     *
     * @param expected the expected value of {@code actual.message}.
     * @return {@link #myself self}
     */
    public SELF hasMessageEqualTo(final Object expected) {
        return hasMessageSatisfying(v -> assertThat(v).isEqualTo(expected));
    }

    // ---------------------------------------------------------------------------------------------------- propertyPath

    /**
     * Returns the value of {@code getPropertyPath()} of the {@link #actual} constraint violation.
     *
     * @param actual the {@link #actual} constraint violation.
     * @return the value of {@code actual.getPropertyPath()}.
     */
    protected abstract PATH getPropertyPath(ACTUAL actual);

    /**
     * Verifies that the {@code actual.propertyPath} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.propertyPath}.
     * @return {@link #myself self}
     */
    public SELF hasPropertyPathSatisfying(final Consumer<? super PATH> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull().satisfies(a -> {
            assertThat(getPropertyPath(a)).satisfies(requirements::accept);
        });
    }

    /**
     * Verifies that the {@code actual.propertyPath} is {@link AbstractIterableAssert#isEqualTo(Object) equal} to
     * specified value.
     *
     * @param expected the expected value of {@code actual.message}.
     * @return {@link #myself self}
     */
    public SELF hasPropertyPathEqualTo(final Object expected) {
        return hasPropertyPathSatisfying(v -> assertThat(v).isEqualTo(expected));
    }

    // -------------------------------------------------------------------------------------------------------- rootBean

    /**
     * Returns the value of {@code getRootBean()} of the {@link #actual} constraint violation.
     *
     * @param actual the {@link #actual} constraint violation
     * @return the value of {@code actual.getRootBean()}.
     */
    protected abstract ROOT_BEAN getRootBean(ACTUAL actual);

    /**
     * Verifies that the {@code actual.rootBean} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.rootBean}.
     * @return {@link #myself self}
     */
    public SELF hasRootBeanSatisfying(final Consumer<? super ROOT_BEAN> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull().satisfies(a -> {
            assertThat(getRootBean(a)).satisfies(requirements::accept);
        });
    }

    /**
     * Verifies that the {@code actual.rootBean} is {@link AbstractAssert#isEqualTo(Object) equal} to specified value.
     *
     * @param expected the expected value of {@code actual.rootBean}.
     * @return {@link #myself self}
     */
    public SELF hasRootBeanSameAs(final Object expected) {
        return hasRootBeanSatisfying(v -> {
            assertThat(v).isSameAs(expected);
        });
    }

    // ------------------------------------------------------------------------------------------------ getRootBeanClass

    protected abstract Class<ROOT_BEAN> getRootBeanClass(ACTUAL a);

    /**
     * Verifies that the {@code actual.rootBeanClass} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.rootBeanClass}.
     * @return {@link #myself self}
     */
    @SuppressWarnings({"unchecked"})
    public SELF hasRootBeanClassSatisfying(final Consumer<? super Class<ROOT_BEAN>> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull().satisfies(a -> {
            assertThat(getRootBeanClass(a)).satisfies(v -> requirements.accept((Class<ROOT_BEAN>) v));
        });
    }

    /**
     * Verifies that the {@code actual.rootBeanClass} is {@link AbstractAssert#isSameAs(Object) same} as specified
     * value.
     *
     * @param expected the expected value of {@code actual.rootBeanClass}.
     * @return {@link #myself self}
     */
    public SELF hasRootBeanClassSameAs(final Object expected) {
        return hasRootBeanClassSatisfying(v -> {
            assertThat(v).isSameAs(expected);
        });
    }
}
