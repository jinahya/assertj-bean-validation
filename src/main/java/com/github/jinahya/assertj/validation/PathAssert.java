package com.github.jinahya.assertj.validation;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.IterableAssert;

import java.util.List;
import java.util.function.Consumer;

import static com.github.jinahya.assertj.validation.PathUtils.NodeUtils.getIndex;
import static com.github.jinahya.assertj.validation.PathUtils.NodeUtils.getKey;
import static com.github.jinahya.assertj.validation.PathUtils.NodeUtils.getKind;
import static org.assertj.core.api.Assertions.assertThat;

public class PathAssert<NodeType> extends IterableAssert<NodeType> {

    abstract static class AbstractNodeAssert<SELF extends AbstractNodeAssert<SELF>>
            extends AbstractAssert<SELF, Object> {

        AbstractNodeAssert(final Object actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        public SELF hasIndexSatisfying(final Consumer<? super Integer> requirements) {
            isNotNull();
            assertThat(getIndex(actual)).satisfies(requirements::accept);
            return myself;
        }

        public SELF hasIndex(final Integer expected) {
            return hasIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
        }

        public SELF hasKeySatisfying(final Consumer<Object> requirements) {
            isNotNull();
            assertThat(getKey(actual)).satisfies(requirements);
            return myself;
        }

        public SELF hasKey(final Object expected) {
            return hasKeySatisfying(v -> assertThat(v).isEqualTo(expected));
        }

        public SELF hasKindSatisfying(final Consumer<Object> requirements) {
            isNotNull();
            assertThat(getKind(actual)).satisfies(requirements);
            return myself;
        }

        public SELF hasKind(final Object expected) {
            return hasKindSatisfying(v -> assertThat(v).isEqualTo(expected));
        }

        public SELF hasNameSatisfying(final Consumer<? super String> requirements) {
            isNotNull();
            assertThat(PathUtils.NodeUtils.getName(actual)).satisfies(requirements::accept);
            return myself;
        }

        /**
         * Verifies that {@code actual#getName()} returns specified value.
         *
         * @param expected the expected value.
         * @return {@link #myself self}.
         */
        public SELF hasName(final String expected) {
            return hasNameSatisfying(v -> assertThat(v).isEqualTo(expected));
        }

        public SELF isInIterableSatisfies(final Consumer<? super Boolean> requirements) {
            isNotNull();
            assertThat(PathUtils.NodeUtils.isInIterable(actual)).satisfies(requirements::accept);
            return myself;
        }

        /**
         * Verifies that {@code actual#isIterable()} is {@code true}.
         *
         * @return {@link #myself self}.
         */
        public SELF isInIterable() {
            return isInIterableSatisfies(v -> assertThat(v).isTrue());
        }

        /**
         * Verifies that {@code actual#isIterable()} is {@code false}.
         *
         * @return {@link #myself self}.
         */
        public SELF isNotInIterable() {
            return isInIterableSatisfies(v -> assertThat(v).isFalse());
        }
    }

    public static class NodeAssert extends AbstractNodeAssert<NodeAssert> {

        public NodeAssert(final Object actual) {
            super(actual, NodeAssert.class);
        }
    }

    public static class BeanNodeAssert extends AbstractNodeAssert<BeanNodeAssert> {

        public BeanNodeAssert(final Object actual) {
            super(actual, BeanNodeAssert.class);
        }

        public BeanNodeAssert hasContainerClassSatisfying(final Consumer<? super Class<?>> requirements) {
            isNotNull();
            assertThat(PathUtils.BeanNodeUtils.getContainerClass(actual)).satisfies(requirements::accept);
            return myself;
        }

        public BeanNodeAssert hasContainerClass(final Class<?> expected) {
            return hasContainerClassSatisfying(v -> assertThat(v).isSameAs(expected));
        }

        public BeanNodeAssert hasTypeArgumentIndexSatisfying(final Consumer<? super Integer> requirements) {
            isNotNull();
            assertThat(PathUtils.BeanNodeUtils.getTypeArgumentIndex(actual)).satisfies(requirements::accept);
            return myself;
        }

        public BeanNodeAssert hasTypeArgumentIndex(final Integer expected) {
            return hasTypeArgumentIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    public static class ConstructorNodeAssert extends AbstractNodeAssert<ConstructorNodeAssert> {

        public ConstructorNodeAssert(final Object actual) {
            super(actual, ConstructorNodeAssert.class);
        }

        public ConstructorNodeAssert hasParameterTypesSatisfying(
                final Consumer<? super List<? extends Class<?>>> requirements) {
            isNotNull();
            assertThat(PathUtils.ConstructorNodeUtils.getParameterTypes(actual)).satisfies(requirements::accept);
            return myself;
        }

        public ConstructorNodeAssert hasParameterTypes(final List<Class<?>> expected) {
            return hasParameterTypesSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    public static class ContainerElementNodeAssert extends AbstractNodeAssert<ContainerElementNodeAssert> {

        public ContainerElementNodeAssert(final Object actual) {
            super(actual, ContainerElementNodeAssert.class);
        }

        public ContainerElementNodeAssert hasContainerClassSatisfying(final Consumer<? super Class<?>> requirements) {
            isNotNull();
            assertThat(PathUtils.ContainerElementNodeUtils.getContainerClass(actual)).satisfies(requirements::accept);
            return myself;
        }

        public ContainerElementNodeAssert hasContainerClass(final Class<?> expected) {
            return hasContainerClassSatisfying(v -> assertThat(v).isSameAs(expected));
        }

        public ContainerElementNodeAssert hasTypeArgumentIndexSatisfying(final Consumer<? super Integer> requirements) {
            isNotNull();
            assertThat(PathUtils.ContainerElementNodeUtils.getTypeArgumentIndex(actual))
                    .satisfies(requirements::accept);
            return myself;
        }

        public ContainerElementNodeAssert hasTypeArgumentIndex(final Integer expected) {
            return hasTypeArgumentIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
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

        public MethodNodeAssert hasParameterTypesSatisfying(
                final Consumer<? super List<? extends Class<?>>> requirements) {
            isNotNull();
            assertThat(PathUtils.MethodNodeUtils.getParameterTypes(actual)).satisfies(requirements::accept);
            return myself;
        }

        public MethodNodeAssert hasParameterTypes(final List<Class<?>> expected) {
            return hasParameterTypesSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    public static class ParameterNodeAssert extends AbstractNodeAssert<ParameterNodeAssert> {

        public ParameterNodeAssert(final Object actual) {
            super(actual, ParameterNodeAssert.class);
        }

        public ParameterNodeAssert hasParameterIndexSatisfying(final Consumer<? super Integer> requirements) {
            isNotNull();
            assertThat(PathUtils.ParameterNodeUtils.getParameterIndex(actual)).satisfies(requirements::accept);
            return myself;
        }

        public ParameterNodeAssert hasParameterIndex(final Integer expected) {
            return hasParameterIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    public static class PropertyNodeAssert extends AbstractNodeAssert<PropertyNodeAssert> {

        public PropertyNodeAssert(final Object actual) {
            super(actual, PropertyNodeAssert.class);
        }

        public PropertyNodeAssert hasContainerClassSatisfying(final Consumer<? super Class<?>> requirements) {
            isNotNull();
            assertThat(PathUtils.PropertyNodeUtils.getContainerClass(actual)).satisfies(requirements::accept);
            return myself;
        }

        public PropertyNodeAssert hasContainerClass(final Class<?> expected) {
            return hasContainerClassSatisfying(v -> assertThat(v).isSameAs(expected));
        }

        public PropertyNodeAssert hasTypeArgumentIndexSatisfying(final Consumer<? super Integer> requirements) {
            isNotNull();
            assertThat(PathUtils.PropertyNodeUtils.getTypeArgumentIndex(actual)).satisfies(requirements::accept);
            return myself;
        }

        public PropertyNodeAssert hasTypeArgumentIndex(final Integer expected) {
            return hasTypeArgumentIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    public static class ReturnValueNodeAssert extends AbstractNodeAssert<ReturnValueNodeAssert> {

        public ReturnValueNodeAssert(final Object actual) {
            super(actual, ReturnValueNodeAssert.class);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public PathAssert(final Iterable<? extends NodeType> actual) {
        super(actual);
    }
}
