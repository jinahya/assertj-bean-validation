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

import java.util.List;
import java.util.function.Function;

final class PathUtils {

    static final class NodeUtils {

        private static <R> R applyClassFor(final Object actual,
                                           final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassFor("Path$Node", actual, function);
        }

        private static Class<?> getClass(final Object instance) {
            return applyClassFor(instance, Function.identity());
        }

        /**
         * Indicates whether specified object is an instance of either {@code javax.validation.Path.Node} or {@link
         * jakarta.validation.Path.Node}.
         *
         * @param actual the object to be tested.
         * @return {@code true} if {@code actual} is an instance of {@code Node}; {@code false} otherwise.
         */
        static boolean isNodeInstance(final Object actual) {
            return applyClassFor(actual, c -> true);
        }

        /**
         * Checks whether specified object is an instance of either {@code javax.validation.Path.Node} or {@link
         * jakarta.validation.Path.Node}.
         *
         * @param actual the object to be tested.
         */
        static <T> T requireNodeInstance(final T actual) {
            return applyClassFor(actual, c -> actual);
        }

        static Integer getIndex(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (Integer) c.getMethod("getIndex").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Object getKey(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return c.getMethod("getKey").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Object getKind(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (Object) c.getMethod("getKind").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static String getName(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (String) c.getMethod("getName").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static boolean isInIterable(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (boolean) c.getMethod("isInIterable").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        NodeUtils() {
            super();
        }
    }

    static final class BeanNodeUtils {

        private static <R> R applyClassFor(final Object actual,
                                           final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassFor("Path$BeanNode", actual, function);
        }

        static Class<?> getContainerClass(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (Class<?>) c.getMethod("getContainerClass").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Integer getTypeArgumentIndex(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (Integer) c.getMethod("getTypeArgumentIndex").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private BeanNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static final class ConstructorNodeUtils {

        private static <R> R applyClassFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassFor("Path$ConstructorNode", node, function);
        }

        @SuppressWarnings({"unchecked"})
        static List<Class<?>> getParameterTypes(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (List<Class<?>>) c.getMethod("getParameterTypes").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private ConstructorNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static final class ContainerElementNodeUtils {

        private static <R> R applyClassFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassFor("Path$ContainerElementNode", node, function);
        }

        static Class<?> getContainerClass(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (Class<?>) c.getMethod("getContainerClass").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Integer getTypeArgumentIndex(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (Integer) c.getMethod("getTypeArgumentIndex").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private ContainerElementNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static final class CrossParameterNodeUtils {

        private CrossParameterNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static final class MethodNodeUtils {

        private static <R> R applyClassFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassFor("Path$MethodNode", node, function);
        }

        @SuppressWarnings({"unchecked"})
        static List<Class<?>> getParameterTypes(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (List<Class<?>>) c.getMethod("getParameterTypes").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private MethodNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static final class ParameterNodeUtils {

        private static <R> R applyClassFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassFor("Path$ParameterNode", node, function);
        }

        static Integer getParameterIndex(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (Integer) c.getMethod("getParameterIndex").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private ParameterNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static final class PropertyNodeUtils {

        private static <R> R applyClassFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassFor("Path$PropertyNode", node, function);
        }

        static Class<?> getContainerClass(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (Class<?>) c.getMethod("getContainerClass").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Integer getTypeArgumentIndex(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (Integer) c.getMethod("getTypeArgumentIndex").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private PropertyNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static final class ReturnValueNodeUtils {

        private static <R> R applyClassFor(final Object node, final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassFor("Path$ReturnValueNode", node, function);
        }

        private ReturnValueNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static <R> R applyClassFor(final Object actual, final Function<? super Class<?>, ? extends R> function) {
        return Utils.applyClassFor("Path", actual, function);
    }

    private PathUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
