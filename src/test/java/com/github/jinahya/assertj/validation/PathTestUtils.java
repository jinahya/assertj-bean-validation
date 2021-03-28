package com.github.jinahya.assertj.validation;

public final class PathTestUtils {

    public static final class NodeTestUtils {

        public static Integer getIndex(final Object actual) {
            return PathUtils.NodeUtils.getIndex(actual);
        }

        public static Object getKey(final Object actual) {
            return PathUtils.NodeUtils.getKey(actual);
        }

        public static Object getKind(final Object actual) {
            return PathUtils.NodeUtils.getKind(actual);
        }

        public static String getName(final Object actual) {
            return PathUtils.NodeUtils.getName(actual);
        }

        public static boolean isInIterable(final Object actual) {
            return PathUtils.NodeUtils.isInIterable(actual);
        }

        private NodeTestUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    private PathTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}