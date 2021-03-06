package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation-base
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
import org.assertj.core.api.AbstractBooleanAssert;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.BooleanAssert;
import org.assertj.core.api.ClassAssert;
import org.assertj.core.api.IntegerAssert;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ObjectAssert;

import java.util.List;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.BOOLEAN;
import static org.assertj.core.api.InstanceOfAssertFactories.INTEGER;
import static org.assertj.core.api.InstanceOfAssertFactories.STRING;
import static org.assertj.core.api.InstanceOfAssertFactories.type;

/**
 * An abstract assertion class for verifying instances of {@code Path} which is an iterable of {@code Path.Node}s.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @param <NODE>   the type of {@code Path.Node}
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractPathAssert<SELF extends AbstractPathAssert<SELF, ACTUAL, NODE>, ACTUAL, NODE>
        extends AbstractAssert<SELF, ACTUAL> {

    interface NodeBaseAccessor<NODE, ELEMENT_KIND> {

        /**
         * Returns the value of {@code getIndex()} of specified actual value.
         *
         * @param actual the actual value.
         * @return the value of {@code actual.getIndex()}.
         */
        Integer getIndex(NODE actual);

        /**
         * Returns the value of {@code getKey()} of specified actual value.
         *
         * @param actual the actual value.
         * @return the value of {@code actual.getKey()}.
         */
        Object getKey(NODE actual);

        /**
         * Returns the value of {@code getKind()} of specified actual value.
         *
         * @param actual the actual value.
         * @return the value of {@code actual.getKind()}.
         */
        ELEMENT_KIND getKind(NODE actual);

        /**
         * Returns the value of {@code getName()} of specified actual value.
         *
         * @param actual the actual value.
         * @return the value of {@code actual.getName()}.
         */
        String getName(NODE actual);

        /**
         * Returns the value of {@code isInIterable()} of specified actual value.
         *
         * @param actual the actual value.
         * @return the value of {@code actual.isInIterable()}.
         */
        boolean isInIterable(NODE actual);
    }

    abstract static class NodeBaseAssert<
            SELF extends NodeBaseAssert<SELF, ACTUAL, ELEMENT_KIND, ACCESSOR>,
            ACTUAL,
            ELEMENT_KIND,
            ACCESSOR extends NodeBaseAccessor<ACTUAL, ELEMENT_KIND>>
            extends AbstractAssert<SELF, ACTUAL> {

        NodeBaseAssert(final ACTUAL actual, final Class<?> selfType, final ACCESSOR accessor) {
            super(actual, selfType);
            this.accessor = requireNonNull(accessor, "accessor is null");
        }

        // -------------------------------------------------------------------------------------------------- getIndex()

        /**
         * Returns an assertion for the value of {@code actual.getIndex()}.
         *
         * @return an assertion for the value of {@code actual.getIndex()}.
         */
        public AbstractIntegerAssert<?> index() {
            return isNotNull()
                    .extracting(accessor::getIndex, Assertions.as(INTEGER))
                    ;
        }

        /**
         * Verifies that the value of {@code actual.getIndex()} satisfies the requirements by being accepted to
         * specified consumer.
         *
         * @param requirements the consumer accepts and verifies the value of {@code actual.getIndex()}.
         * @return {@link #myself myself}.
         */
        public SELF hasIndexSatisfying(final Consumer<? super Integer> requirements) {
            return isNotNull()
                    .satisfies(a -> {
                        final Integer index = accessor.getIndex(a);
                        assertThat(index)
                                .satisfies(requirements::accept);
                    })
                    ;
        }

        /**
         * Verifies that the value of {@code actual.getIndex()} is {@link AbstractAssert#isEqualTo(Object) equal} to
         * specified value.
         *
         * @param expected the value expected to be equal to {@code actual.getIndex()}.
         * @return {@link #myself myself}.
         */
        public SELF hasIndexEqualTo(final Object expected) {
            return hasIndexSatisfying(v -> {
                assertThat(v)
                        .isEqualTo(expected);
            });
        }

        // ---------------------------------------------------------------------------------------------------- getKey()

        /**
         * Returns an assertion for the value of {@code actual.getKey()}.
         *
         * @return an assertion for the value of {@code actual.getKey()}.
         */
        public ObjectAssert<Object> key() {
            return isNotNull()
                    .extracting(accessor::getKey, type(Object.class))
                    ;
        }

        /**
         * Verifies that the value of {@code actual.getKey()} satisfies the requirements by being accepted to specified
         * consumer.
         *
         * @param requirements the consumer accepts and verifies {@code actual.key}.
         * @return {@link #myself myself}.
         */
        public SELF hasKeySatisfying(final Consumer<? super Object> requirements) {
            return isNotNull().satisfies(a -> {
                final Object key = accessor.getKey(a);
                assertThat(key).satisfies(requirements);
            });
        }

        /**
         * Verifies that the value of {@code actual.getKey()} is {@link AbstractAssert#isEqualTo(Object) equal} to
         * specified value.
         *
         * @param expected the value expected to be equal to {@code actual.key}.
         * @return {@link #myself myself}.
         */
        public SELF hasKeyEqualTo(final Object expected) {
            return hasKeySatisfying(v -> {
                assertThat(v).isEqualTo(expected);
            });
        }

        // --------------------------------------------------------------------------------------------------- getKind()

        /**
         * Returns an assertion for value of {@code actual.getKind()}.
         *
         * @return an assertion for value of {@code actual.getKind()}.
         */
        public ObjectAssert<ELEMENT_KIND> kind() {
            isNotNull();
            final ELEMENT_KIND kind = accessor.getKind(actual);
            return new ObjectAssert<>(kind);
        }

        /**
         * Verifies that the {@code actual.getKind()} satisfies the requirements by being accepted to specified
         * consumer.
         *
         * @param requirements the consumer accepts and verifies the value of {@code actual.getKind()}.
         * @return {@link #myself myself}.
         */
        public SELF hasKindSatisfying(final Consumer<? super ELEMENT_KIND> requirements) {
            requireNonNull(requirements, "requirements is null");
            return isNotNull()
                    .satisfies(a -> {
                        final ELEMENT_KIND kind = accessor.getKind(a);
                        assertThat(kind)
                                .satisfies(requirements::accept);
                    })
                    ;
        }

        /**
         * Verifies that the {@code actual.getKind()} is {@link AbstractAssert#isSameAs(Object) same} as specified
         * value.
         *
         * @param expected the value expected to be same as {@code actual.getKind()}.
         * @return {@link #myself myself}.
         */
        public SELF hasKindSameAs(final Object expected) {
            return hasKindSatisfying(v -> {
                assertThat(v)
                        .isSameAs(expected);
            });
        }

        /**
         * Verifies that the {@code actual.getKind().name()} is {@link AbstractAssert#isEqualTo(Object) equal} to
         * specified value.
         *
         * @param expected the value expected to be equal to {@code actual.getKind().name()}.
         * @return {@link #myself myself}.
         */
        public SELF hasKindNameEqualTo(final Object expected) {
            return hasKindSatisfying(v -> {
                final String kindName = ((Enum<?>) v).name();
                assertThat(kindName)
                        .isSameAs(expected);
            });
        }

        // --------------------------------------------------------------------------------------------------- getName()

        /**
         * Returns an assertion for the value of {@code actual.getName()}.
         *
         * @return an assertion for the value of {@code actual.getName()}.
         */
        public AbstractStringAssert<?> name() {
            return isNotNull()
                    .extracting(accessor::getName, Assertions.as(STRING))
                    ;
        }

        /**
         * Verifies that the value of {@code actual.getName()} satisfies the requirements by being accepted to specified
         * consumer.
         *
         * @param requirements the consumer accepts and verifies the value of {@code actual.getName()}.
         * @return {@link #myself myself}.
         */
        public SELF hasNameSatisfying(final Consumer<? super String> requirements) {
            requireNonNull(requirements, "requirements is null");
            return isNotNull()
                    .satisfies(a -> {
                        final String name = accessor.getName(a);
                        assertThat(name)
                                .satisfies(requirements::accept);
                    })
                    ;
        }

        /**
         * Verifies that {@code actual.getName()} is {@link AbstractAssert#isEqualTo(Object) equal} to specified value.
         *
         * @param expected the value expected to be equal to {@code actual.name}.
         * @return {@link #myself self}.
         */
        public SELF hasNameEqualTo(final Object expected) {
            return hasNameSatisfying(v -> {
                assertThat(v).isEqualTo(expected);
            });
        }

        // ---------------------------------------------------------------------------------------------- isInIterable()

        /**
         * Returns an assertion for the value of {@code actual.isInIterable()}.
         *
         * @return an assertion for the value of {@code actual.isInIterable()}.
         */
        public AbstractBooleanAssert<?> inIterable() {
            return isNotNull()
                    .extracting(accessor::isInIterable, Assertions.as(BOOLEAN))
                    ;
        }

        /**
         * Verifies that the value of {@code actual.isInIterable()} satisfies the requirements by being accepted to
         * specified consumer.
         *
         * @param requirements the consumer accepts and verifies the value of {@code actual.isInnIterable()}.
         * @return {@link #myself myself}.
         */
        public SELF hasInIterableSatisfying(final Consumer<? super Boolean> requirements) {
            return isNotNull()
                    .satisfies(a -> {
                        final boolean inIterable = accessor.isInIterable(a);
                        assertThat(inIterable)
                                .satisfies(requirements::accept)
                        ;
                    })
                    ;
        }

        /**
         * Verifies that the value of {@code actual.isInIterable()} {@link BooleanAssert#isTrue() is true}.
         *
         * @return {@link #myself self}.
         */
        public SELF isInIterable() {
            return hasInIterableSatisfying(v -> {
                assertThat(v).isTrue();
            });
        }

        /**
         * Verifies that the value of {@code actual.isInIterable()} {@link BooleanAssert#isFalse() is false}.
         *
         * @return {@link #myself self}.
         */
        public SELF isNotInIterable() {
            return hasInIterableSatisfying(v -> {
                assertThat(v).isFalse();
            });
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * The accessor for getting values from {@link #actual}.
         */
        protected final ACCESSOR accessor;
    }

    // ------------------------------------------------------------------------------------------------------------ Node

    /**
     * An interface for accessors getting values from an instance of {@code Node}.
     *
     * @param <NODE>         the type of {@code Node}
     * @param <ELEMENT_KIND> the type of {@code ElementKind}
     */
    protected interface AbstractNodeAccessor<NODE, ELEMENT_KIND>
            extends NodeBaseAccessor<NODE, ELEMENT_KIND> {

    }

    /**
     * An abstract assertion class for {@code Node}.
     *
     * @param <SELF>         self type parameter
     * @param <ACTUAL>       actual type parameter
     * @param <ELEMENT_KIND> the type of {@code ElementKind}
     */
    protected abstract static class AbstractNodeAssert<
            SELF extends AbstractNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends NodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            NodeBaseAccessor<ACTUAL, ELEMENT_KIND>> {

        /**
         * Creates a new instance for specified actual value.
         *
         * @param actual   the actual value to verify.
         * @param selfType the class of self.
         * @param accessor an object for getting values from {@code actual}.
         */
        protected AbstractNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                     final AbstractNodeAccessor<ACTUAL, ELEMENT_KIND> accessor) {
            super(actual, selfType, accessor);
        }

        /**
         * Verifies that the {@link #actual} is an instance of {@code BeanNode}.
         *
         * @return {@link #myself self}.
         */
        public abstract SELF isBeanNode();

        /**
         * Returns a new assertion for verifying {@link #actual} as an instance of {@code BeanNode}.
         *
         * @param <T> assertion type parameter
         * @return a new assertion for verifying {@link #actual} as an instance of {@code BeanNode}.
         */
        public abstract <T extends AbstractBeanNodeAssert<T, ACTUAL, ELEMENT_KIND>> T asBeanNode();

        public abstract SELF isConstructorNode();

        public abstract <T extends AbstractConstructorNodeAssert<T, ACTUAL, ELEMENT_KIND>> T asConstructorNode();

        public abstract SELF isCrossParameterNode();

        public abstract <T extends AbstractCrossParameterNodeAssert<T, ACTUAL, ELEMENT_KIND>> T asCrossParameterNode();

        public abstract SELF isMethodNode();

        public abstract <T extends AbstractMethodNodeAssert<T, ACTUAL, ELEMENT_KIND>> T asMethodNode();

        public abstract SELF isParameterNode();

        public abstract <T extends AbstractParameterNodeAssert<T, ACTUAL, ELEMENT_KIND>> T asParameterNode();

        public abstract SELF isPropertyNode();

        public abstract <T extends AbstractPropertyNodeAssert<T, ACTUAL, ELEMENT_KIND>> T asPropertyNode();

        public abstract SELF isReturnValueNode();

        public abstract <T extends AbstractReturnValueNodeAssert<T, ACTUAL, ELEMENT_KIND>> T asReturnValueNode();
    }

    // -------------------------------------------------------------------------------------------------------- BeanNode

    /**
     * An interface for getting values from an instance of {@code BeanNode}.
     *
     * @param <BEAN_NODE>    type of {@code BeanNode}
     * @param <ELEMENT_KIND> type of {@code ElementKind}.
     */
    protected interface BeanNodeAccessor<BEAN_NODE, ELEMENT_KIND>
            extends NodeBaseAccessor<BEAN_NODE, ELEMENT_KIND> {

        /**
         * Returns the value of {@code getContainerClass()} from specified actual value.
         *
         * @param a the actual value.
         * @return the value of {@code actual.getContainerClass()}.
         */
        Class<?> getContainerClass(BEAN_NODE a);

        /**
         * Returns the value of {@code getTypeArgumentIndex()} from specified actual value.
         *
         * @param a the actual value.
         * @return the value of {@code actual.getTypeArgumentIndex()}.
         */
        Integer getTypeArgumentIndex(BEAN_NODE a);
    }

    /**
     * An abstract assert class for verifying instances of {@code BeanNode}.
     *
     * @param <SELF>         self type parameter
     * @param <ACTUAL>       type of {@code BeanNode}
     * @param <ELEMENT_KIND> type of {@code ElementKind}
     */
    protected abstract static class AbstractBeanNodeAssert<
            SELF extends AbstractBeanNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends NodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            BeanNodeAccessor<ACTUAL, ELEMENT_KIND>> {

        /**
         * Creates a new instance with specified actual value.
         *
         * @param actual   the actual value to verify.
         * @param selfType a self class.
         * @param accessor an accessor for getting values from {@code actual}.
         */
        protected AbstractBeanNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                         final BeanNodeAccessor<ACTUAL, ELEMENT_KIND> accessor) {
            super(actual, selfType, accessor);
        }

        // ----------------------------------------------------------------------------------------- getContainerClass()
        public ClassAssert containerClass() {
            isNotNull();
            final Class<?> containerClass = accessor.getContainerClass(actual);
            return assertThat(containerClass);
        }

        public SELF hasContainerClassSatisfying(final Consumer<? super Class<?>> requirements) {
            return isNotNull().satisfies(a -> {
                final Class<?> containerClass = accessor.getContainerClass(a);
                assertThat(containerClass).satisfies(requirements::accept);
            });
        }

        public SELF hasContainerClassSameAs(final Object expected) {
            return hasContainerClassSatisfying(v -> assertThat(v).isSameAs(expected));
        }

        // -------------------------------------------------------------------------------------- getTypeArgumentIndex()
        public IntegerAssert typeArgumentIndex() {
            isNotNull();
            final Integer typeArgumentIndex = accessor.getTypeArgumentIndex(actual);
            return (IntegerAssert) assertThat(typeArgumentIndex);
        }

        public SELF hasTypeArgumentIndexSatisfying(final Consumer<? super Integer> requirements) {
            return isNotNull().satisfies(a -> {
                final Integer typeArgumentIndex = accessor.getTypeArgumentIndex(a);
                assertThat(typeArgumentIndex).satisfies(requirements::accept);
            });
        }

        public SELF hasTypeArgumentIndexEqualTo(final Object expected) {
            return hasTypeArgumentIndexSatisfying(v -> {
                assertThat(v).isEqualTo(expected);
            });
        }
    }

    // ------------------------------------------------------------------------------------------------- ConstructorNode
    protected interface ConstructorNodeAccessor<CONSTRUCTOR_NODE, ELEMENT_KIND>
            extends NodeBaseAccessor<CONSTRUCTOR_NODE, ELEMENT_KIND> {

        List<Class<?>> getParameterTypes(CONSTRUCTOR_NODE a);
    }

    protected abstract static class AbstractConstructorNodeAssert<
            SELF extends AbstractConstructorNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends NodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            ConstructorNodeAccessor<ACTUAL, ELEMENT_KIND>> {

        protected AbstractConstructorNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                                final ConstructorNodeAccessor<ACTUAL, ELEMENT_KIND> accessor) {
            super(actual, selfType, accessor);
        }

        // ----------------------------------------------------------------------------------------- getParameterTypes()
        public ListAssert<Class<?>> parameterTypes() {
            isNotNull();
            final List<Class<?>> parameterTypes = accessor.getParameterTypes(actual);
            return assertThat(parameterTypes);
        }

        public SELF hasParameterTypesSatisfying(final Consumer<? super List<? extends Class<?>>> requirements) {
            return isNotNull().satisfies(a -> {
                final List<Class<?>> parameterTypes = accessor.getParameterTypes(a);
                assertThat(parameterTypes).satisfies(requirements::accept);
            });
        }

        public SELF hasParameterTypesEqualTo(final Object expected) {
            return hasParameterTypesSatisfying(v -> {
                assertThat(v).isEqualTo(expected);
            });
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface ContainerElementNodeAccessor<CONTAINER_ELEMENT_NODE, ELEMENT_KIND>
            extends NodeBaseAccessor<CONTAINER_ELEMENT_NODE, ELEMENT_KIND> {

        Class<?> getContainerClass(CONTAINER_ELEMENT_NODE a);

        Integer getTypeArgumentIndex(CONTAINER_ELEMENT_NODE a);
    }

    protected abstract static class AbstractContainerElementNodeAssert<
            SELF extends AbstractContainerElementNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends NodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            ContainerElementNodeAccessor<ACTUAL, ELEMENT_KIND>> {

        protected AbstractContainerElementNodeAssert(
                final ACTUAL actual, final Class<?> selfType,
                final ContainerElementNodeAccessor<ACTUAL, ELEMENT_KIND> accessor) {
            super(actual, selfType, accessor);
        }

        // ----------------------------------------------------------------------------------------- getContainerClass()
        public ClassAssert containerClass() {
            isNotNull();
            final Class<?> containerClass = accessor.getContainerClass(actual);
            return assertThat(containerClass);
        }

        public SELF hasContainerClassSatisfying(final Consumer<? super Class<?>> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(accessor.getContainerClass(a)).satisfies(requirements::accept);
            });
        }

        public SELF hasContainerClassSameAs(final Object expected) {
            return hasContainerClassSatisfying(v -> assertThat(v).isSameAs(expected));
        }

        // -------------------------------------------------------------------------------------- getTypeArgumentIndex()
        public IntegerAssert typeArgumentIndex() {
            isNotNull();
            final Integer typeArgumentIndex = accessor.getTypeArgumentIndex(actual);
            return (IntegerAssert) assertThat(typeArgumentIndex);
        }

        public SELF hasTypeArgumentIndexSatisfying(final Consumer<? super Integer> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(accessor.getTypeArgumentIndex(a)).satisfies(requirements::accept);
            });
        }

        public SELF hasTypeArgumentIndexEqualTo(final Object expected) {
            return hasTypeArgumentIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface CrossParameterNodeAccessor<CROSS_PARAMETER_NODE, ELEMENT_KIND>
            extends NodeBaseAccessor<CROSS_PARAMETER_NODE, ELEMENT_KIND> {
        // empty
    }

    protected abstract static class AbstractCrossParameterNodeAssert<
            SELF extends AbstractCrossParameterNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends NodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            CrossParameterNodeAccessor<ACTUAL, ELEMENT_KIND>> {

        protected AbstractCrossParameterNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                                   final CrossParameterNodeAccessor<ACTUAL, ELEMENT_KIND> accessor) {
            super(actual, selfType, accessor);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface MethodNodeAccessor<METHOD_NODE, ELEMENT_KIND>
            extends NodeBaseAccessor<METHOD_NODE, ELEMENT_KIND> {

        List<Class<?>> getParameterTypes(METHOD_NODE a);
    }

    protected abstract static class AbstractMethodNodeAssert<
            SELF extends AbstractMethodNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends NodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            MethodNodeAccessor<ACTUAL, ELEMENT_KIND>> {

        protected AbstractMethodNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                           final MethodNodeAccessor<ACTUAL, ELEMENT_KIND> accessor) {
            super(actual, selfType, accessor);
        }

        public ListAssert<Class<?>> parameterTypes() {
            isNotNull();
            final List<Class<?>> parameterTypes = accessor.getParameterTypes(actual);
            return assertThat(parameterTypes);
        }

        public SELF hasParameterTypesSatisfying(
                final Consumer<? super List<? extends Class<?>>> requirements) {
            return isNotNull().satisfies(a -> {
                final List<Class<?>> parameterTypes = accessor.getParameterTypes(a);
                assertThat(parameterTypes).satisfies(requirements::accept);
            });
        }

        public SELF hasParameterTypesEqualTo(final Object expected) {
            return hasParameterTypesSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface ParameterNodeAccessor<PARAMETER_NODE, ELEMENT_KIND>
            extends NodeBaseAccessor<PARAMETER_NODE, ELEMENT_KIND> {

        int getParameterIndex(PARAMETER_NODE a);
    }

    protected abstract static class AbstractParameterNodeAssert<
            SELF extends AbstractParameterNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends NodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            ParameterNodeAccessor<ACTUAL, ELEMENT_KIND>> {

        protected AbstractParameterNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                              final ParameterNodeAccessor<ACTUAL, ELEMENT_KIND> accessor) {
            super(actual, selfType, accessor);
        }

        public IntegerAssert parameterIndex() {
            isNotNull();
            final int parameterIndex = accessor.getParameterIndex(actual);
            return (IntegerAssert) assertThat(parameterIndex);
        }

        public SELF hasParameterIndexSatisfying(final Consumer<? super Integer> requirements) {
            return isNotNull().satisfies(a -> {
                final int parameterIndex = accessor.getParameterIndex(a);
                assertThat(parameterIndex).satisfies(requirements::accept);
            });
        }

        public SELF hasParameterIndexEqualTo(final Object expected) {
            return hasParameterIndexSatisfying((Integer v) -> {
                assertThat(v).isSameAs(expected);
            });
        }
    }

    // ---------------------------------------------------------------------------------------------------- PropertyNode
    protected interface PropertyNodeAccessor<PROPERTY_NODE, ELEMENT_KIND>
            extends NodeBaseAccessor<PROPERTY_NODE, ELEMENT_KIND> {

        Class<?> getContainerClass(PROPERTY_NODE a);

        Integer getTypeArgumentIndex(PROPERTY_NODE a);
    }

    protected abstract static class AbstractPropertyNodeAssert<
            SELF extends AbstractPropertyNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends NodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            PropertyNodeAccessor<ACTUAL, ELEMENT_KIND>> {

        protected AbstractPropertyNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                             final PropertyNodeAccessor<ACTUAL, ELEMENT_KIND> accessor) {
            super(actual, selfType, accessor);
        }

        // ----------------------------------------------------------------------------------------- getContainerClass()

        /**
         * Returns an assertion for the value of {@code actual.getContainerClass()}.
         *
         * @return an assertion for the value of {@code actual.getContainerClass()}.
         */
        public ClassAssert containerClass() {
            isNotNull();
            final Class<?> containerClass = accessor.getContainerClass(actual);
            return assertThat(containerClass);
        }

        public SELF hasContainerClassSatisfying(final Consumer<? super Class<?>> requirements) {
            containerClass().satisfies(requirements::accept);
            return myself;
        }

        public SELF hasContainerClassSameAs(final Object expected) {
            return hasContainerClassSatisfying(v -> assertThat(v).isSameAs(expected));
        }

        // -------------------------------------------------------------------------------------- getTypeArgumentIndex()

        /**
         * Returns an assertion for the value of {@code actual.getTypeArgumentIndex()}.
         *
         * @return an assertion for the value of {@code actual.getTypeArgumentIndex()}.
         */
        public IntegerAssert typeArgumentIndex() {
            isNotNull();
            final Integer typeArgumentIndex = accessor.getTypeArgumentIndex(actual);
            return (IntegerAssert) assertThat(typeArgumentIndex);
        }

        public SELF hasTypeArgumentIndexSatisfying(final Consumer<? super Integer> requirements) {
            typeArgumentIndex().satisfies(requirements::accept);
            return myself;
        }

        public SELF hasTypeArgumentIndexEqualTo(final Object expected) {
            return hasTypeArgumentIndexSatisfying(v -> {
                assertThat(v).isEqualTo(expected);
            });
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface ReturnValueNodeAccessor<ACTUAL, ELEMENT_KIND>
            extends NodeBaseAccessor<ACTUAL, ELEMENT_KIND> {

    }

    protected abstract static class AbstractReturnValueNodeAssert<
            SELF extends AbstractReturnValueNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends NodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            ReturnValueNodeAccessor<ACTUAL, ELEMENT_KIND>> {

        protected AbstractReturnValueNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                                final ReturnValueNodeAccessor<ACTUAL, ELEMENT_KIND> accessor) {
            super(actual, selfType, accessor);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static <T> T requireNullOrInstanceOfIterable(final T actual) {
        if (actual == null) {
            return null;
        }
        if (!(actual instanceof Iterable)) {
            throw new IllegalArgumentException("not an Iterable: " + actual);
        }
        return actual;
    }

    protected AbstractPathAssert(final ACTUAL actual, final Class<?> selfType) {
        super(requireNullOrInstanceOfIterable(actual), selfType);
    }

    /**
     * Converts this assertion as an assertion of an iterable of {@link NODE}.
     *
     * @return an iterable assertion of {@link NODE}.
     */
    public abstract AbstractIterableAssert<?, ? extends Iterable<NODE>, NODE, ? extends AbstractNodeAssert<?, NODE, ?>> asIterable();

    /**
     * Returns an assertion for the {@code Path.Node} at specified index of {@link #actual}.
     *
     * @param index the node index.
     * @return an assertion instance.
     */
    public AbstractNodeAssert<?, ? extends NODE, ?> node(final int index) {
        return isNotNull().asIterable().element(index);
    }

    /**
     * Returns an assertion instance for the {@code Path.BeanNode} at specified index of {@link #actual}.
     *
     * @param index the node index.
     * @return an assertion instance.
     */
    public AbstractBeanNodeAssert<?, ? extends NODE, ?> beanNode(final int index) {
        return node(index).asBeanNode();
    }

    /**
     * Returns an assertion instance for the {@code Path.ConstructorNode} at specified index of {@link #actual}.
     *
     * @param index the node index.
     * @return an assertion instance.
     */
    public AbstractConstructorNodeAssert<?, ? extends NODE, ?> constructorNode(final int index) {
        return node(index).asConstructorNode();
    }

    public AbstractCrossParameterNodeAssert<?, ? extends NODE, ?> crossParameterNode(final int index) {
        return node(index).asCrossParameterNode();
    }

    public AbstractMethodNodeAssert<?, ? extends NODE, ?> methodNode(final int index) {
        return node(index).asMethodNode();
    }

    public AbstractParameterNodeAssert<?, ? extends NODE, ?> parameterNode(final int index) {
        return node(index).asParameterNode();
    }

    public AbstractPropertyNodeAssert<?, ? extends NODE, ?> propertyNode(final int index) {
        return node(index).asPropertyNode();
    }

    public AbstractReturnValueNodeAssert<?, ? extends NODE, ?> returnValueNode(final int index) {
        return node(index).asReturnValueNode();
    }
}
