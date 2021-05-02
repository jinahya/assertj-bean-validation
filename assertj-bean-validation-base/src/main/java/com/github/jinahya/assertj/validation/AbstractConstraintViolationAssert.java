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
 * An abstract assertion class for verifying instances of {@code ConstraintViolation}.
 *
 * @param <SELF>      self type parameter
 * @param <ACTUAL>    the actual type of {@code ConstraintViolation}
 * @param <PATH>      the actual type of {@code Path}
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
     * An interface for accessing values from an actual value of constraint violation.
     *
     * @param <CONSTRAINT_VIOLATION> actual constraint violation type
     * @param <PATH>                 the actual type of {@code Path}
     * @param <ROOT_BEAN>            the type of root bean of {@link CONSTRAINT_VIOLATION}
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    protected interface Accessor<CONSTRAINT_VIOLATION, PATH, ROOT_BEAN> {

        /**
         * Returns the value of {@code getInvalidValue()} from specified actual value.
         *
         * @param actual the actual value.
         * @return the value of {@code actual.invalidValue}.
         */
        Object getInvalidValue(CONSTRAINT_VIOLATION actual);

        /**
         * Returns the value of {@code getLeafBean()} from specified actual value.
         *
         * @param actual the actual value.
         * @return the value of {@code actual.leafBean}.
         */
        Object getLeafBean(CONSTRAINT_VIOLATION actual);

        /**
         * Gets the value of {@code getMessage()} from specified actual value.
         *
         * @param actual the actual value.
         * @return the value of {@code actual.message}.
         */
        String getMessage(CONSTRAINT_VIOLATION actual);

        /**
         * Returns the value of {@code getPropertyPath()} from specified actual value.
         *
         * @param actual the actual value.
         * @return the value of {@code actual.getPropertyPath()}.
         */
        PATH getPropertyPath(CONSTRAINT_VIOLATION actual);

        /**
         * Returns the value of {@code getRootBean()} froms specified actual value.
         *
         * @param actual the actual value.
         * @return the value of {@code actual.getRootBean()}.
         */
        ROOT_BEAN getRootBean(CONSTRAINT_VIOLATION actual);

        /**
         * Returns the value of {@code getRootBeanClass()} from specified actual value.
         *
         * @param actual the actual value.
         * @return the value of {@code actual.rootBean}.
         */
        Class<ROOT_BEAN> getRootBeanClass(CONSTRAINT_VIOLATION actual);
    }

    // ---------------------------------------------------------------------------------------------------- constructors

    /**
     * Creates a new instance for specified actual value.
     *
     * @param actual   the actual value to verify.
     * @param selfType a self type.
     * @param accessor an accessor for getting values from {@code actual}.
     */
    protected AbstractConstraintViolationAssert(final ACTUAL actual, final Class<?> selfType,
                                                final Accessor<ACTUAL, PATH, ROOT_BEAN> accessor) {
        super(actual, selfType);
        this.accessor = requireNonNull(accessor, "accessor is null");
    }

    // ---------------------------------------------------------------------------------------------------- invalidValue

    /**
     * Verifies that the {@code actual.invalidValue} satisfies given requirements expressed as a {@link Consumer}.
     *
     * @param requirements the consumer accepts {@code actual.invalidValue}.
     * @return {@link #myself self}
     */
    public SELF hasInvalidValueSatisfying(final Consumer<? super Object> requirements) {
        return isNotNull().satisfies(a -> {
            assertThat(accessor.getInvalidValue(a)).satisfies(requirements);
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
     * Verifies that the {@code actual.leafBean} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.leafBean}.
     * @return {@link #myself self}
     */
    public SELF hasLeafBeanSatisfying(final Consumer<Object> requirements) {
        return isNotNull().satisfies(a -> {
            assertThat(accessor.getLeafBean(a)).satisfies(requirements);
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
     * Verifies that the {@code actual.message} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.message}.
     * @return {@link #myself self}
     */
    public SELF hasMessageSatisfying(final Consumer<? super String> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull().satisfies(a -> {
            assertThat(accessor.getMessage(a)).satisfies(requirements::accept);
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
     * Verifies that the {@code actual.propertyPath} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.propertyPath}.
     * @return {@link #myself self}
     */
    public SELF hasPropertyPathSatisfying(final Consumer<? super PATH> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull().satisfies(a -> {
            assertThat(accessor.getPropertyPath(a)).satisfies(requirements::accept);
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
     * Verifies that the {@code actual.rootBean} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.rootBean}.
     * @return {@link #myself self}
     */
    public SELF hasRootBeanSatisfying(final Consumer<? super ROOT_BEAN> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull().satisfies(a -> {
            assertThat(accessor.getRootBean(a)).satisfies(requirements::accept);
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
            assertThat(accessor.getRootBeanClass(a)).satisfies(v -> requirements.accept((Class<ROOT_BEAN>) v));
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

    /**
     * The accessor for getting values from {@link #actual}.
     */
    protected final Accessor<ACTUAL, PATH, ROOT_BEAN> accessor;
}
