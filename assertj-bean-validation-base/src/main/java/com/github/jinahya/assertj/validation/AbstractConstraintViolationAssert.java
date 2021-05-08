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
import org.assertj.core.api.AbstractClassAssert;
import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ObjectAssert;
import org.assertj.core.api.StringAssert;

import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.CLASS;
import static org.assertj.core.api.InstanceOfAssertFactories.STRING;
import static org.assertj.core.api.InstanceOfAssertFactories.type;

/**
 * An abstract assertion class for verifying instances of {@code ConstraintViolation} class.
 *
 * @param <SELF>      self type parameter
 * @param <ACTUAL>    the type of {@code ConstraintViolation} class
 * @param <PATH>      the type of {@code Path} class
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
     * An interface for accessing values from an actual value of {@code ConstraintViolation} class.
     *
     * @param <CONSTRAINT_VIOLATION> the type of {@code ConstraintViolation} class
     * @param <PATH>                 the type of {@code Path} class
     * @param <ROOT_BEAN>            the type of the root bean of {@link CONSTRAINT_VIOLATION}
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

    // ----------------------------------------------------------------------------------------------- getInvalidValue()

    /**
     * Returns an assertion for the value of {@code actual.getInvalidValue()}.
     *
     * @return an assertion for the value of {@code actual.getInvalidValue()}.
     * @see Accessor#getInvalidValue(Object)
     */
    public ObjectAssert<Object> invalidValue() {
        return isNotNull()
                .extracting(accessor::getInvalidValue, type(Object.class))
                ;
    }

    /**
     * Verifies that the value of {@code actual.getInvalidValue()} satisfies given requirements expressed as a {@link
     * Consumer}.
     *
     * @param requirements the consumer accepts the value of {@code actual.getInvalidValue()}.
     * @return {@link #myself self}
     * @see #invalidValue()
     */
    public SELF hasInvalidValueSatisfying(final Consumer<? super Object> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull()
                .satisfies(a -> {
                    final Object invalidValue = accessor.getInvalidValue(a);
                    assertThat(invalidValue)
                            .satisfies(requirements)
                    ;
                })
                ;
    }

    /**
     * Verifies that the value of {@code actual.getInvalidValue()} {@link AbstractAssert#isEqualTo(Object) is equal to}
     * specified value.
     *
     * @param expected the expected value of {@code actual.getInvalidValue()}.
     * @return {@link #myself self}
     */
    public SELF hasInvalidValueEqualTo(final Object expected) {
        return hasInvalidValueSatisfying(v -> {
            assertThat(v)
                    .isEqualTo(expected);
        });
    }

    // --------------------------------------------------------------------------------------------------- getLeafBean()

    /**
     * Returns an assertion for the value of {@code actual.getRootBean()}.
     *
     * @return an assertion for the value of {@code actual.getRootBean()}.
     * @see Accessor#getInvalidValue(Object)
     */
    public ObjectAssert<Object> leafBean() {
        return isNotNull()
                .extracting(accessor::getRootBean, type(Object.class))
                ;
    }

    /**
     * Verifies that the value of {@code actual.getLeafBean()} satisfies given requirements expresses as a {@link
     * Consumer}.
     *
     * @param requirements the consumer accepts and verifies the value of {@code actual.getLeafBean()}.
     * @return {@link #myself self}
     */
    public SELF hasLeafBeanSatisfying(final Consumer<Object> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull()
                .satisfies(a -> {
                    final Object leafBean = accessor.getLeafBean(a);
                    assertThat(leafBean)
                            .satisfies(requirements);
                })
                ;
    }

    /**
     * Verifies that the value of {@code actual.getLeafBean()} is same as specified value.
     *
     * @param expected the expected value of {@code actual.getLeafBean()}.
     * @return {@link #myself self}
     */
    public SELF hasLeafBeanSameAs(final Object expected) {
        return hasLeafBeanSatisfying(v -> {
            assertThat(v)
                    .isSameAs(expected);
        });
    }

    // --------------------------------------------------------------------------------------------- actual.getMessage()

    /**
     * Returns an assertion for the value of {@code actual.getMessage()}.
     *
     * @return an assertion for the value of {@code actual.getMessage()}.
     */
    public StringAssert message() {
        return (StringAssert) isNotNull()
                .extracting(accessor::getMessage, Assertions.as(STRING))
                ;
    }

    /**
     * Verifies that the value of {@code actual.getMessage()} satisfies given requirements expresses as a {@link
     * Consumer}.
     *
     * @param requirements the consumer accepts and verifies the value of {@code actual.getMessage()}.
     * @return {@link #myself self}
     */
    public SELF hasMessageSatisfying(final Consumer<? super String> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull().satisfies(a -> {
            final String message = accessor.getMessage(a);
            assertThat(message)
                    .satisfies(requirements::accept);
        });
    }

    /**
     * Verifies that the value of {@code actual.getMessage()} {@link AbstractAssert#isEqualTo(Object) is equal to}
     * specified value.
     *
     * @param expected the expected value of {@code actual.getMessage()}.
     * @return {@link #myself self}
     */
    public SELF hasMessageEqualTo(final Object expected) {
        return hasMessageSatisfying(v -> {
            assertThat(v)
                    .isEqualTo(expected);
        });
    }

    // ----------------------------------------------------------------------------------------------- getPropertyPath()

    /**
     * Returns an assertion for the value of {@code actual.getPropertyPath()}.
     *
     * @return an assertion for the value of {@code actual.getPropertyPath()}.
     */
    public abstract AbstractPathAssert<?, PATH, ?> propertyPath();

    /**
     * Verifies that the value of {@code actual.getPropertyPath()} satisfies given requirements expresses as a {@link
     * Consumer}.
     *
     * @param requirements the consumer accepts and verifies the value of {@code actual.getPropertyPath()}.
     * @return {@link #myself self}
     */
    public SELF hasPropertyPathSatisfying(final Consumer<? super PATH> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull()
                .satisfies(a -> {
                    final PATH propertyPath = accessor.getPropertyPath(a);
                    assertThat(propertyPath)
                            .satisfies(requirements::accept)
                    ;
                })
                ;
    }

    /**
     * Verifies that the value of {@code actual.getPropertyPath()} is {@link AbstractIterableAssert#isEqualTo(Object)
     * equal} to specified value.
     *
     * @param expected the expected value of {@code actual.getPropertyPath()}.
     * @return {@link #myself self}
     */
    public SELF hasPropertyPathEqualTo(final Object expected) {
        return hasPropertyPathSatisfying(v -> {
            assertThat(v)
                    .isEqualTo(expected);
        });
    }

    // --------------------------------------------------------------------------------------------------- getRootBean()

    /**
     * Returns an assertion for the value of {@code actual.getRootBean()}.
     *
     * @return an assertion for the value of {@code actual.getRootBean()}.
     */
    public AbstractObjectAssert<?, ROOT_BEAN> rootBean() {
        return isNotNull()
                .extracting(accessor::getRootBean, type(accessor.getRootBeanClass(actual)))
                ;
    }

    /**
     * Verifies that the value of {@code actual.getRootBean()} satisfies given requirements expresses as a {@link
     * Consumer}.
     *
     * @param requirements the consumer accepts and verifies the value of {@code actual.getRootBean()}.
     * @return {@link #myself self}
     */
    public SELF hasRootBeanSatisfying(final Consumer<? super ROOT_BEAN> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull()
                .satisfies(a -> {
                    final ROOT_BEAN rootBean = accessor.getRootBean(a);
                    assertThat(rootBean)
                            .satisfies(requirements::accept)
                    ;
                })
                ;
    }

    /**
     * Verifies that the value of {@code actual.getRootBean()} is {@link AbstractAssert#isEqualTo(Object) equal} to
     * specified value.
     *
     * @param expected the expected value of {@code actual.getRootBean()}.
     * @return {@link #myself self}
     */
    public SELF hasRootBeanSameAs(final Object expected) {
        return hasRootBeanSatisfying(v -> {
            assertThat(v)
                    .isSameAs(expected);
        });
    }

    // ------------------------------------------------------------------------------------------------ getRootBeanClass

    /**
     * Returns an assertion for the value of {@code actual.getRootBeanClass()}.
     *
     * @return an assertion for the value of {@code actual.getRootBeanClass()}.
     */
    public AbstractClassAssert<?> rootBeanClass() {
        return isNotNull()
                .extracting(accessor::getRootBeanClass, Assertions.as(CLASS));
    }

    /**
     * Verifies that the {@code actual.rootBeanClass()} satisfies given requirements expresses as a {@link Consumer}.
     *
     * @param requirements the consumer accepts and verifies the {@code actual.getRootBeanClass()}.
     * @return {@link #myself self}
     */
    @SuppressWarnings({"unchecked"})
    public SELF hasRootBeanClassSatisfying(final Consumer<? super Class<ROOT_BEAN>> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull().satisfies(a -> {
            final Class<ROOT_BEAN> rootBeanClass = accessor.getRootBeanClass(a);
            assertThat(rootBeanClass)
                    .satisfies(v -> requirements.accept((Class<ROOT_BEAN>) v));
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
            assertThat(v)
                    .isSameAs(expected);
        });
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * The accessor for getting values from {@link #actual}.
     */
    protected final Accessor<ACTUAL, PATH, ROOT_BEAN> accessor;
}
