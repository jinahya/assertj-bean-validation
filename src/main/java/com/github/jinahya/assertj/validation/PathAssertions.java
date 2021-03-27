package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

public class PathAssertions {

    public static class NodeAssertions {

        public static PathAssert.NodeAssert assertNode(final Object actual) {
            return new PathAssert.NodeAssert(actual);
        }

        public static PathAssert.NodeAssert assertAht(final PathWrapper.NodeWrapper wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertNode(wrapper.getWrapped());
        }

        private NodeAssertions() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    public static PathAssert assertPath(final Iterable<Object> path) {
        return new PathAssert(path);
    }

    public static PathAssert assertThat(final PathWrapper wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertPath(wrapper.getWrapped());
    }

    private PathAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
