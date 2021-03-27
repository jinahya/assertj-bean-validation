package com.github.jinahya.assertj.validation;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.IterableAssert;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PathAssert extends IterableAssert<Object> {

    abstract static class AbstractNodeAssert<SELF extends AbstractNodeAssert<SELF>>
            extends AbstractAssert<SELF, Object> {

        AbstractNodeAssert(final Object actual, final Class<? extends SELF> selfType) {
            super(actual, selfType);
        }

        public SELF hasIndex(final Integer expected) {
            isNotNull();
            assertThat(PathUtils.NodeUtils.getIndex(actual)).isEqualTo(expected);
            return myself;
        }

        public SELF hasKey(final Object expected) {
            isNotNull();
            assertThat(PathUtils.NodeUtils.getKey(actual)).isEqualTo(expected);
            return myself;
        }

        public SELF hasKind(final Object expected) {
            isNotNull();
            assertThat(PathUtils.NodeUtils.getKind(actual)).isEqualTo(expected);
            return myself;
        }

        /**
         * Verifies that {@code actual#getName()} returns specified value.
         *
         * @param expected the expected value.
         * @return {@link #myself self}.
         */
        public SELF hasName(final String expected) {
            isNotNull();
            assertThat(PathUtils.NodeUtils.getName(actual)).isEqualTo(expected);
            return myself;
        }

        /**
         * Verifies that {@code actual#isIterable()} is {@code true}.
         *
         * @return {@link #myself self}.
         */
        public SELF isIterable() {
            isNotNull();
            assertThat(PathUtils.NodeUtils.isIterable(actual)).isTrue();
            return myself;
        }

        /**
         * Verifies that {@code actual#isIterable()} is {@code false}.
         *
         * @return {@link #myself self}.
         */
        public SELF isNotIterable() {
            isNotNull();
            assertThat(PathUtils.NodeUtils.isIterable(actual)).isFalse();
            return myself;
        }
    }

    public static class NodeAssert extends AbstractNodeAssert<NodeAssert> {

        NodeAssert(final Object actual, final Class<? extends NodeAssert> selfType) {
            super(actual, selfType);
        }

        public NodeAssert(final Object actual) {
            this(actual, NodeAssert.class);
        }
    }

    public static class BeanNodeAssert extends AbstractNodeAssert<BeanNodeAssert> {

        public BeanNodeAssert(final Object actual) {
            super(actual, BeanNodeAssert.class);
        }

        public BeanNodeAssert hasContainerClass(final Class<?> expected) {
            isNotNull();
            assertThat(PathUtils.BeanNodeUtils.getContainerClass(actual)).isSameAs(expected);
            return myself;
        }

        public BeanNodeAssert hasTypeArgumentIndex(final Integer expected) {
            isNotNull();
            assertThat(PathUtils.BeanNodeUtils.getTypeArgumentIndex(actual)).isEqualTo(expected);
            return myself;
        }
    }

    public static class ConstructorNodeAssert extends AbstractNodeAssert<ConstructorNodeAssert> {

        public ConstructorNodeAssert(final Object actual) {
            super(actual, ConstructorNodeAssert.class);
        }

        public ConstructorNodeAssert hasParameterTypes(final List<Class<?>> expected) {
            isNotNull();
            assertThat(PathUtils.ConstructorNodeUtils.getParameterTypes(actual)).isEqualTo(expected);
            return myself;
        }
    }

    public static class ContainerElementNodeAssert extends AbstractNodeAssert<ContainerElementNodeAssert> {

        public ContainerElementNodeAssert(final Object actual) {
            super(actual, ContainerElementNodeAssert.class);
        }

        public ContainerElementNodeAssert hasContainerClass(final Class<?> expected) {
            isNotNull();
            assertThat(PathUtils.ContainerElementNodeUtils.getContainerClass(actual)).isSameAs(expected);
            return myself;
        }

        public ContainerElementNodeAssert hasTypeArgumentIndex(final Integer expected) {
            isNotNull();
            assertThat(PathUtils.ContainerElementNodeUtils.getTypeArgumentIndex(actual)).isEqualTo(expected);
            return myself;
        }
    }

    public static class CrossParameterNodeAssert extends AbstractNodeAssert<CrossParameterNodeAssert> {

        public CrossParameterNodeAssert(final Object actual) {
            super(actual, CrossParameterNodeAssert.class);
        }
    }

    public static class MethodNodeAssert extends AbstractNodeAssert<MethodNodeAssert> {

        public MethodNodeAssert(final Object actual) {
            super(actual, MethodNodeAssert.class);
        }

        public MethodNodeAssert hasParameterTypes(final List<Class<?>> expected) {
            isNotNull();
            assertThat(PathUtils.MethodNodeUtils.getParameterTypes(actual)).isEqualTo(expected);
            return myself;
        }
    }

    public static class ParameterNodeAssert extends AbstractNodeAssert<ParameterNodeAssert> {

        public ParameterNodeAssert(final Object actual) {
            super(actual, ParameterNodeAssert.class);
        }

        public ParameterNodeAssert hasParameterIndex(final Integer expected) {
            isNotNull();
            assertThat(PathUtils.ParameterNodeUtils.getParameterIndex(actual)).isEqualTo(expected);
            return myself;
        }
    }

    public static class PropertyNodeAssert extends AbstractNodeAssert<PropertyNodeAssert> {

        public PropertyNodeAssert(final Object actual) {
            super(actual, PropertyNodeAssert.class);
        }

        public PropertyNodeAssert hasContainerClass(final Class<?> expected) {
            isNotNull();
            assertThat(PathUtils.PropertyNodeUtils.getContainerClass(actual)).isSameAs(expected);
            return myself;
        }

        public PropertyNodeAssert hasTypeArgumentIndex(final Integer expected) {
            isNotNull();
            assertThat(PathUtils.PropertyNodeUtils.getTypeArgumentIndex(actual)).isEqualTo(expected);
            return myself;
        }
    }

    public static class ReturnValueNodeAssert extends AbstractNodeAssert<ReturnValueNodeAssert> {

        public ReturnValueNodeAssert(final Object actual) {
            super(actual, ReturnValueNodeAssert.class);
        }
    }

    public PathAssert(final Iterable<Object> actual) {
        super(actual);
    }
}
