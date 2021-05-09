package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.PathAssertions.NodeAssertions;
import com.github.jinahya.assertj.validation.PathWrapper.NodeWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.PathAssertions.NodeAssertions.assertNode;
import static com.github.jinahya.assertj.validation.PathAssertions.assertPath;
import static com.github.jinahya.assertj.validation.PathAssertions.assertThat;
import static com.github.jinahya.assertj.validation.PathTestUtils.newPathMock;
import static com.github.jinahya.assertj.validation.PathWrapper.path;

class PathAssertionsTest {

    @Test
    void assertThat__() {
        final Object actual = newPathMock();
        final PathAssert a = assertThat(actual);
    }

    @Test
    void assertThat__Wrapper() {
        final Object actual = newPathMock();
        final PathWrapper w = path(actual);
        final PathAssert a = assertThat(w);
    }

    @Test
    void assertPath__() {
        final Object actual = newPathMock();
        final PathAssert a = assertPath(actual);
    }

    @DisplayName("PathAssertions.NodeAssertions")
    @Nested
    class NodeAssertionsTest {

        @DisplayName("assertThat(actual) passes")
        @Test
        void assertThat__() {
            final Object actual = PathTestUtils.NodeTestUtils.newNodeMock();
            final PathAssert.NodeAssert a = NodeAssertions.assertThat(actual);
        }

        @DisplayName("assertThat(wrapper) passes")
        @Test
        void assertThat__Wrapper() {
            final Object actual = PathTestUtils.NodeTestUtils.newNodeMock();
            final NodeWrapper w = NodeWrapper.node(actual);
            final PathAssert.NodeAssert a = NodeAssertions.assertThat(w);
        }

        @DisplayName("assertNode(actual) passes")
        @Test
        void assertPath__() {
            final Object actual = PathTestUtils.NodeTestUtils.newNodeMock();
            final PathAssert.NodeAssert a = assertNode(actual);
        }
    }
}