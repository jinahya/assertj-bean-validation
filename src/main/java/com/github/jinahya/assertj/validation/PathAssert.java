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
import org.assertj.core.api.AbstractBooleanAssert;
import org.assertj.core.api.IterableAssert;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import static com.github.jinahya.assertj.validation.PathUtils.NodeUtils.getKind;
import static com.github.jinahya.assertj.validation.PathUtils.NodeUtils.getName;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for verifying instances of {@code ....validation.Path}.
 *
 * @param <ACTUAL> actual type parameter
 * @param <NODE>   the type of {@code ....validation.Path.Node}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119", "java:S125"})
public class PathAssert<ACTUAL extends Iterable<NODE>, NODE>
        extends IterableAssert<NODE> {

    @SuppressWarnings({"java:S119"})
    abstract static class AbstractNodeAssert<SELF extends AbstractNodeAssert<SELF, ACTUAL>, ACTUAL>
            extends AbstractAssert<SELF, ACTUAL> {

        AbstractNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        // ------------------------------------------------------------------------------- getIndex()Ljava.lang.Integer;
        public SELF hasIndexSatisfying(final Consumer<? super Integer> requirements) {
            isNotNull();
            assertThat(PathUtils.NodeUtils.getIndex(actual))
                    .satisfies(requirements::accept);
            return myself;
        }

        public SELF hasIndexEqualTo(final Object expected) {
            return hasIndexSatisfying(v -> {
                assertThat(v).isEqualTo(expected);
            });
        }

        // ---------------------------------------------------------------------------------- getKey()Ljava.lang.Object;
        public SELF hasKeySatisfying(final Consumer<Object> requirements) {
            isNotNull();
            assertThat(PathUtils.NodeUtils.getKey(actual))
                    .satisfies(requirements);
            return myself;
        }

        public SELF hasKeyEqualTo(final Object expected) {
            return hasKeySatisfying(v -> {
                assertThat(v).isEqualTo(expected);
            });
        }

        // ------------------------------------------------------------------------ getKind()L...validation.ElementKind;
        public SELF hasKindSatisfying(final Consumer<Object> requirements) {
            isNotNull();
            assertThat(getKind(actual))
                    .satisfies(requirements);
            return myself;
        }

        public SELF hasKindSameAs(final Object expected) {
            return hasKindSatisfying(v -> {
                assertThat(v).isSameAs(expected);
            });
        }

        // --------------------------------------------------------------------------------- getName()Ljava.lang.String;

        /**
         * Verifies that the value of {@code actual#getName()} satisfies requirements expressed in specified consumer.
         *
         * @param requirements the consumer accepts and verifies the value of {@code actual#getName()}.
         * @return {@link #myself self}.
         * @see #hasNameEqualTo(Object)
         * @see javax.validation.Path.Node#getName()
         * @see jakarta.validation.Path.Node#getName()
         */
        public SELF hasNameSatisfying(final Consumer<? super String> requirements) {
            isNotNull();
            assertThat(getName(actual))
                    .satisfies(requirements::accept);
            return myself;
        }

        /**
         * Verifies that the value of {@code actual#getName()} is {@link AbstractAssert#isEqualTo(Object) equal} to
         * specified value.
         *
         * @param expected the expected value of {@code actual#getName()}.
         * @return {@link #myself self}.
         * @see #hasNameSatisfying(Consumer)
         * @see javax.validation.Path.Node#getName()
         * @see jakarta.validation.Path.Node#getName()
         */
        public SELF hasNameEqualTo(final Object expected) {
            return hasNameSatisfying(v -> {
                assertThat(v).isEqualTo(expected);
            });
        }

        // --------------------------------------------------------------------------------------------- inInIterable()Z

        /**
         * Verifies that the value of {@code actual.isInIterable()} satisfies requirements expressed in specified
         * consumer.
         *
         * @param requirements the consumer accepts and verifies the value of {@code actual.isInIterable()}.
         * @return {@link #myself self}.
         * @see #isInIterable()
         * @see #isNotInIterable()
         * @see javax.validation.Path.Node#isInIterable()
         * @see jakarta.validation.Path.Node#isInIterable()
         */
        public SELF hasInIterableSatisfying(final Consumer<? super Boolean> requirements) {
            isNotNull();
            assertThat(PathUtils.NodeUtils.isInIterable(actual))
                    .satisfies(requirements::accept);
            return myself;
        }

        /**
         * Verifies that the value of {@code actual.isInIterable()} {@link AbstractBooleanAssert#isTrue() is true}.
         *
         * @return {@link #myself self}.
         * @see #hasInIterableSatisfying(Consumer)
         * @see #isNotInIterable()
         * @see javax.validation.Path.Node#isInIterable()
         * @see jakarta.validation.Path.Node#isInIterable()
         */
        public SELF isInIterable() {
            return hasInIterableSatisfying(v -> {
                assertThat(v).isTrue();
            });
        }

        /**
         * Verifies that the value of {@code actual.isInIterable()} {@link AbstractBooleanAssert#isFalse() is false}.
         *
         * @return {@link #myself self}.
         * @see #hasInIterableSatisfying(Consumer)
         * @see #isInIterable()
         * @see javax.validation.Path.Node#isInIterable()
         * @see jakarta.validation.Path.Node#isInIterable()
         */
        public SELF isNotInIterable() {
            return hasInIterableSatisfying(v -> {
                assertThat(v).isFalse();
            });
        }

        // ---------------------------------------------------------------------------------------------------- BeanNode

        /**
         * Verifies that the {@link #actual} is an instance of {@code ....validation.Path.BeanNode}.
         *
         * @return {@link #myself self}.
         */
        public SELF isBeanNode() {
            assertThat(PathUtils.BeanNodeUtils.isBeanNodeInstance(actual)).isTrue();
            return myself;
        }

        @SuppressWarnings({"unchecked"})
        public <T> BeanNodeAssert<T> asBeanNode() {
            isBeanNode();
            return new BeanNodeAssert<>((T) actual);
        }

        // ------------------------------------------------------------------------------------------------ PropertyNode

        /**
         * Verifies that the {@link #actual} is an instance of {@code ....validation.Path.PropertyNode}.
         *
         * @return {@link #myself self}.
         */
        public SELF isPropertyNode() {
            isNotNull();
            assertThat(PathUtils.PropertyNodeUtils.isPropertyNodeInstance(actual))
                    .isTrue();
            return myself;
        }

        @SuppressWarnings({"unchecked"})
        public <T> PropertyNodeAssert<T> asPropertyNode() {
            isPropertyNode();
            return new PropertyNodeAssert<>((T) actual);
        }
    }

    /**
     * An assertion class for verifying instance of {@code ....validation.Path.Node}.
     *
     * @param <ACTUAL> actual type parameter
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public static class NodeAssert<ACTUAL>
            extends AbstractNodeAssert<NodeAssert<ACTUAL>, ACTUAL> {

        /**
         * Creates a new instance for verifying specified value.
         *
         * @param actual the value to verify.
         */
        public NodeAssert(final ACTUAL actual) {
            super(actual, NodeAssert.class);
        }
    }

    /**
     * An assertion class for verifying instances of {@code ....validation.Path.BeanNode}.
     *
     * @param <ACTUAL> actual type parameter
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public static class BeanNodeAssert<ACTUAL>
            extends AbstractNodeAssert<BeanNodeAssert<ACTUAL>, ACTUAL> {

        /**
         * Creates a new instance for verifying specified value.
         *
         * @param actual the value to verify.
         */
        public BeanNodeAssert(final ACTUAL actual) {
            super(actual, BeanNodeAssert.class);
        }

        /**
         * Verifies that the value of {@code actual.getContainerClass()} satisfies requirements by being accepted to
         * specified consumer.
         *
         * @param requirements the consumer accepts and verifies the value of {@code actual.getContainerClass()}.
         * @return {@link #myself self}.
         * @see #hasContainerClassSameAs(Object)
         * @see javax.validation.Path.BeanNode#getContainerClass()
         * @see jakarta.validation.Path.BeanNode#getContainerClass()
         */
        public BeanNodeAssert<ACTUAL> hasContainerClassSatisfying(final Consumer<? super Class<?>> requirements) {
            isNotNull();
            assertThat(PathUtils.BeanNodeUtils.getContainerClass(actual))
                    .satisfies(requirements::accept);
            return myself;
        }

        /**
         * Verifies that the value of {@code actual.getContainerClass()} {@link AbstractAssert#isSameAs(Object) is same
         * as} specified value.
         *
         * @param expected the value of {@code actual.getContainerClass()} expected to be same as.
         * @return {@link #myself self}.
         * @see #hasContainerClassSatisfying(Consumer)
         * @see javax.validation.Path.BeanNode#getContainerClass()
         * @see jakarta.validation.Path.BeanNode#getContainerClass()
         */
        public BeanNodeAssert<ACTUAL> hasContainerClassSameAs(final Object expected) {
            return hasContainerClassSatisfying(v -> {
                assertThat(v).isSameAs(expected);
            });
        }

        public BeanNodeAssert<ACTUAL> hasTypeArgumentIndexSatisfying(final Consumer<? super Integer> requirements) {
            isNotNull();
            assertThat(PathUtils.BeanNodeUtils.getTypeArgumentIndex(actual))
                    .satisfies(requirements::accept);
            return myself;
        }

        public BeanNodeAssert<ACTUAL> hasTypeArgumentIndexEqualTo(final Object expected) {
            return hasTypeArgumentIndexSatisfying(v -> {
                assertThat(v).isEqualTo(expected);
            });
        }
    }

    public static class ConstructorNodeAssert<ACTUAL>
            extends AbstractNodeAssert<ConstructorNodeAssert<ACTUAL>, ACTUAL> {

        public ConstructorNodeAssert(final ACTUAL actual) {
            super(actual, ConstructorNodeAssert.class);
        }

        // ------------------------------------------------------------------------------------------- getParameterTypes
        public ConstructorNodeAssert<ACTUAL> hasParameterTypesSatisfying(
                final Consumer<? super List<? extends Class<?>>> requirements) {
            isNotNull();
            assertThat(PathUtils.ConstructorNodeUtils.getParameterTypes(actual))
                    .satisfies(requirements::accept);
            return myself;
        }

        public ConstructorNodeAssert<ACTUAL> hasParameterTypesEqual(final Object expected) {
            return hasParameterTypesSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    public static class ContainerElementNodeAssert<ACTUAL>
            extends AbstractNodeAssert<ContainerElementNodeAssert<ACTUAL>, ACTUAL> {

        public ContainerElementNodeAssert(final ACTUAL actual) {
            super(actual, ContainerElementNodeAssert.class);
        }

        // ----------------------------------------------------------------------------------------- getContainerClass()
        public ContainerElementNodeAssert<ACTUAL> hasContainerClassSatisfying(
                final Consumer<? super Class<?>> requirements) {
            isNotNull();
            assertThat(PathUtils.ContainerElementNodeUtils.getContainerClass(actual)).satisfies(requirements::accept);
            return myself;
        }

        public ContainerElementNodeAssert<ACTUAL> hasContainerClassSameAs(final Object expected) {
            return hasContainerClassSatisfying(v -> assertThat(v).isSameAs(expected));
        }

        // -------------------------------------------------------------------------------------- getTypeArgumentIndex()
        public ContainerElementNodeAssert<ACTUAL> hasTypeArgumentIndexSatisfying(
                final Consumer<? super Integer> requirements) {
            isNotNull();
            assertThat(PathUtils.ContainerElementNodeUtils.getTypeArgumentIndex(actual))
                    .satisfies(requirements::accept);
            return myself;
        }

        public ContainerElementNodeAssert<ACTUAL> hasTypeArgumentIndexEqualTo(final Object expected) {
            return hasTypeArgumentIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    public static class CrossParameterNodeAssert<ACTUAL>
            extends AbstractNodeAssert<CrossParameterNodeAssert<ACTUAL>, ACTUAL> {

        public CrossParameterNodeAssert(final ACTUAL actual) {
            super(actual, CrossParameterNodeAssert.class);
        }
    }

    public static class MethodNodeAssert<ACTUAL>
            extends AbstractNodeAssert<MethodNodeAssert<ACTUAL>, ACTUAL> {

        public MethodNodeAssert(final ACTUAL actual) {
            super(actual, MethodNodeAssert.class);
        }

        public MethodNodeAssert<ACTUAL> hasParameterTypesSatisfying(
                final Consumer<? super List<? extends Class<?>>> requirements) {
            isNotNull();
            assertThat(PathUtils.MethodNodeUtils.getParameterTypes(actual)).satisfies(requirements::accept);
            return myself;
        }

        public MethodNodeAssert<ACTUAL> hasParameterTypesEqualTo(final Object expected) {
            return hasParameterTypesSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    public static class ParameterNodeAssert<ACTUAL>
            extends AbstractNodeAssert<ParameterNodeAssert<ACTUAL>, ACTUAL> {

        public ParameterNodeAssert(final ACTUAL actual) {
            super(actual, ParameterNodeAssert.class);
        }

        public ParameterNodeAssert<ACTUAL> hasParameterIndexSatisfying(final IntConsumer requirements) {
            isNotNull();
            assertThat(PathUtils.ParameterNodeUtils.getParameterIndex(actual)).satisfies(requirements::accept);
            return myself;
        }

        public ParameterNodeAssert<ACTUAL> hasParameterIndexEqualTo(final Object expected) {
            return hasParameterIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    public static class PropertyNodeAssert<ACTUAL>
            extends AbstractNodeAssert<PropertyNodeAssert<ACTUAL>, ACTUAL> {

        public PropertyNodeAssert(final ACTUAL actual) {
            super(actual, PropertyNodeAssert.class);
        }

        public PropertyNodeAssert<ACTUAL> hasContainerClassSatisfying(final Consumer<? super Class<?>> requirements) {
            isNotNull();
            assertThat(PathUtils.PropertyNodeUtils.getContainerClass(actual))
                    .satisfies(requirements::accept);
            return myself;
        }

        public PropertyNodeAssert<ACTUAL> hasContainerClassSameAs(final Object expected) {
            return hasContainerClassSatisfying(v -> assertThat(v).isSameAs(expected));
        }

        /**
         * Verifies that the value of {@code actual#getTypeArgumentIndex()} satisfies some requirements by being
         * accepted to specified consumer.
         *
         * @param requirements the consumer accepts and verifies the value of {@code actual#getTypeArgumentIndex()}.
         * @return {@link #myself self}.
         * @see #hasTypeArgumentIndexEqualTo(Object)
         * @see javax.validation.Path.PropertyNode#getTypeArgumentIndex()
         * @see jakarta.validation.Path.PropertyNode#getTypeArgumentIndex()
         */
        public PropertyNodeAssert<ACTUAL> hasTypeArgumentIndexSatisfying(final Consumer<? super Integer> requirements) {
            isNotNull();
            assertThat(PathUtils.PropertyNodeUtils.getTypeArgumentIndex(actual))
                    .satisfies(requirements::accept);
            return myself;
        }

        /**
         * Verifies that the value of {@code actual#getTypeArgumentIndex()} is {@link AbstractAssert#isEqualTo(Object)
         * equal} to specified value.
         *
         * @param expected the value of {@code actual#getTypeArgumentIndex()} expected to be equal.
         * @return {@link #myself self}.
         * @see #hasTypeArgumentIndexSatisfying(Consumer)
         * @see javax.validation.Path.PropertyNode#getTypeArgumentIndex()
         * @see jakarta.validation.Path.PropertyNode#getTypeArgumentIndex()
         */
        public PropertyNodeAssert<ACTUAL> hasTypeArgumentIndexEqualTo(final Object expected) {
            return hasTypeArgumentIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    /**
     * An assertion class for verifying {@code ....validation.Path.ReturnValueNode}.
     *
     * @param <ACTUAL> actual type parameter
     */
    public static class ReturnValueNodeAssert<ACTUAL>
            extends AbstractNodeAssert<ReturnValueNodeAssert<ACTUAL>, ACTUAL> {

        /**
         * Creates a new instance with specified value.
         *
         * @param actual the value to verify.
         */
        public ReturnValueNodeAssert(final ACTUAL actual) {
            super(actual, ReturnValueNodeAssert.class);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance with specified value.
     *
     * @param actual the value to verify.
     */
    public PathAssert(final ACTUAL actual) {
        super(actual);
    }
}
