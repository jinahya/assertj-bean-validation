package com.github.jinahya.assertj.validation;

import java.util.List;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

final class PathUtils {

    static class NodeUtils {

        private static <R> R classFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.classFor("Path.Node", node, function);
        }

        static Integer getIndex(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (Integer) c.getMethod("getIndex").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Object getKey(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return c.getMethod("getKey").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Object getKind(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (Object) c.getMethod("getKind").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Object getName(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (String) c.getMethod("getName").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static boolean isIterable(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (boolean) c.getMethod("isIterable").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        NodeUtils() {
            super();
        }
    }

    static class BeanNodeUtils extends NodeUtils {

        private static <R> R classFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.classFor("Path.BeanNode", node, function);
        }

        static Class<?> getContainerClass(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (Class<?>) c.getMethod("getContainerClass").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Integer getTypeArgumentIndex(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (Integer) c.getMethod("getTypeArgumentIndex").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private BeanNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static class ConstructorNodeUtils extends NodeUtils {

        private static <R> R classFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.classFor("Path.ConstructorNode", node, function);
        }

        @SuppressWarnings({"unchecked"})
        static List<Class<?>> getParameterTypes(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (List<Class<?>>) c.getMethod("getParameterTypes").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private ConstructorNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static class ContainerElementNodeUtils extends NodeUtils {

        private static <R> R classFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.classFor("Path.ContainerElementNode", node, function);
        }

        static Class<?> getContainerClass(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (Class<?>) c.getMethod("getContainerClass").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Integer getTypeArgumentIndex(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (Integer) c.getMethod("getTypeArgumentIndex").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private ContainerElementNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static class CrossParameterNodeUtils extends NodeUtils {

        private CrossParameterNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static class MethodNodeUtils extends NodeUtils {

        private static <R> R classFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.classFor("Path.MethodNode", node, function);
        }

        @SuppressWarnings({"unchecked"})
        static List<Class<?>> getParameterTypes(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (List<Class<?>>) c.getMethod("getParameterTypes").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private MethodNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static class ParameterNodeUtils extends NodeUtils {

        private static <R> R classFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.classFor("Path.ParameterNode", node, function);
        }

        static Integer getParameterIndex(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (Integer) c.getMethod("getParameterIndex").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private ParameterNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static class PropertyNodeUtils extends NodeUtils {

        private static <R> R classFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.classFor("Path.PropertyNode", node, function);
        }

        static Class<?> getContainerClass(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (Class<?>) c.getMethod("getContainerClass").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Integer getTypeArgumentIndex(final Object node) {
            requireNonNull(node, "node is null");
            return classFor(node, c -> {
                try {
                    return (Integer) c.getMethod("getTypeArgumentIndex").invoke(node);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private PropertyNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static class ReturnValueNodeUtils extends NodeUtils {

        private static <R> R classFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.classFor("Path.ReturnValueNode", node, function);
        }

        private ReturnValueNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    private PathUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
