package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

public class PathAssertions {

    public static class NodeAssertions {

        public static PathAssert.NodeAssert assertNode(final Object actual) {
            return new PathAssert.NodeAssert(actual);
        }

        public static PathAssert.NodeAssert assertThat(final PathWrapper.NodeWrapper wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertNode(wrapper.getWrapped());
        }

        private NodeAssertions() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    public static class BeanNodeAssertions {

        public static PathAssert.BeanNodeAssert assertBeanNode(final Object actual) {
            return new PathAssert.BeanNodeAssert(actual);
        }

        public static PathAssert.BeanNodeAssert assertThat(final PathWrapper.BeanNodeWrapper wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertBeanNode(wrapper.getWrapped());
        }

        private BeanNodeAssertions() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    /**
     * Creates a new instance for specified {@link jakarta.validation.Path} value.
     *
     * @param actual     the value of {@link jakarta.validation.Path}.
     * @param <NodeType> type parameter for either {@code javax.validation.Path.Node} or {@link
     *                   jakarta.validation.Path.Node}
     * @param <PathType> type parameter for either {@code javax.validation.Path} or {@link jakarta.validation.Path}.
     * @return a new assertion instance.
     */
    public static <PathType extends Iterable<NodeType>, NodeType> PathAssert<NodeType> assertPath(
            final PathType actual) {
        return new PathAssert<>(actual);
    }

    /**
     * Creates a new path assertion for the value wrapped in specified wrapper.
     *
     * @param <NodeType> type parameter of either {@code javax.validation.Path.Node} or {@link
     *                   jakarta.validation.Path.Node}.
     * @param wrapper    the wrapper wraps the actual value.
     * @return a new assertion instance.
     */
    public static <PathType extends Iterable<NodeType>, NodeType> PathAssert<NodeType> assertThat(
            final PathWrapper<PathType, NodeType> wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertPath(wrapper.getWrapped());
    }

    private PathAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
