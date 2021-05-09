package com.github.jinahya.assertj.validation;

import org.mockito.Mockito;

final class PathTestUtils {

    static class NodeTestUtils {

        static Object newNodeMock() {
            return Mockito.mock(PathUtils.NodeUtils.NODE_CLASS);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static Object newPathMock() {
        return Mockito.mock(PathUtils.PATH_CLASS);
    }

    private PathTestUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
