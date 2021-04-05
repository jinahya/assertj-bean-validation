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

@SuppressWarnings({"java:S125"})
final class PathUtils {

    static final class NodeUtils {

        private static final String SUFFIX = "Path$Node";

        /**
         * Applies the class of {@code ....validation.Path.Node} to specified function and returns the result.
         *
         * @param function the function to applied with the class of {@code ....validation.Path.Node}.
         * @param <R>      result type parameter
         * @return the result of the {@code function}.
         */
        static <R> R applyNodeClass(final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassForSuffix(SUFFIX, function);
        }

        /**
         * Returns the class of {@code ....validation.Path.Node}.
         *
         * @return the class of {@code ....validation.Path.Node}.
         */
        static Class<?> getNodeClass() {
            return applyNodeClass(Function.identity());
        }

        private static <R> R applyClassFor(final Object actual,
                                           final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassFor(SUFFIX, actual, function);
        }

        private static Class<?> getClassFor(final Object actual) {
            return applyClassFor(actual, Function.identity());
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * Indicates whether specified object is an instance of either {@code ....validation.Path.Node}.
         *
         * @param actual the object to be tested.
         * @return {@code true} if {@code actual} is an instance of {@code ....validation.Path.Node}; {@code false}
         * otherwise.
         */
        static boolean isNodeInstance(final Object actual) {
            if (actual == null) {
                return true;
            }
            return applyClassFor(actual, c -> true);
        }

        /**
         * Checks whether specified object is an instance of either {@code ....validation.Path.Node}.
         *
         * @param actual the object to be tested.
         */
        static <T> T requireNodeInstance(final T actual) {
            if (actual == null) {
                return null;
            }
            return applyClassFor(actual, c -> actual);
        }

        // ------------------------------------------------------------------------------- getIndex()Ljava.lang.Integer;
        static Integer getIndex(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (Integer) c.getMethod("getIndex").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        // ---------------------------------------------------------------------------------- getKey()Ljava.lang.Object;
        static Object getKey(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return c.getMethod("getKey").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        // ----------------------------------------------------------------------- getKind()L....validation.ElementKind;
        static Object getKind(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return c.getMethod("getKind").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        // --------------------------------------------------------------------------------- getName()Ljava.lang.String;
        static String getName(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (String) c.getMethod("getName").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        // --------------------------------------------------------------------------------------------- isInIterable()Z
        static boolean isInIterable(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (boolean) c.getMethod("isInIterable").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private NodeUtils() {
            throw new NonInstantiatableAssertionError();
        }
    }

    static final class BeanNodeUtils {

        private static final String SUFFIX = "Path$BeanNode";

        /**
         * Applies the class of {@code ....validation.Path.BeanNode} to specified function and returns the result.
         *
         * @param function the function to applied with the class of {@code ....validation.Path.BeanNode}.
         * @param <R>      result type parameter
         * @return the result of the {@code function}.
         */
        static <R> R applyNodeClass(final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassForSuffix(SUFFIX, function);
        }

        /**
         * Returns the class of {@code ....validation.Path.BeanNode}.
         *
         * @return the class of {@code ....validation.Path.BeanNode}.
         */
        static Class<?> getNodeClass() {
            return applyNodeClass(Function.identity());
        }

        private static <R> R applyClassFor(final Object actual,
                                           final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassFor(SUFFIX, actual, function);
        }

        private static Class<?> getClassFor(final Object actual) {
            return applyClassFor(actual, Function.identity());
        }

        // -------------------------------------------------------------------------------------------------------------

        /**
         * Indicates whether specified object is an instance of either {@code ....validation.Path.Node}.
         *
         * @param actual the object to be tested.
         * @return {@code true} if {@code actual} is an instance of {@code ....validation.Path.Node}; {@code false}
         * otherwise.
         */
        static boolean isBeanNodeInstance(final Object actual) {
            if (actual == null) {
                return true;
            }
            return applyClassFor(actual, c -> true);
        }

        /**
         * Checks whether specified object is an instance of either {@code ....validation.Path.Node}.
         *
         * @param actual the object to be tested.
         */
        static <T> T requireBeanNodeInstance(final T actual) {
            if (actual == null) {
                return null;
            }
            return applyClassFor(actual, c -> actual);
        }

        // ------------------------------------------------------------------------ getContainerClass()Ljava.lang.Class;
        static Class<?> getContainerClass(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (Class<?>) c.getMethod("getContainerClass").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        // ------------------------------------------------------------------- getTypeArgumentIndex()Ljava.lang.Integer;
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
            throw new NonInstantiatableAssertionError();
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

        // getParameterIndex()I
        static int getParameterIndex(final Object actual) {
            return applyClassFor(actual, c -> {
                try {
                    return (int) c.getMethod("getParameterIndex").invoke(actual);
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

        private static final String SUFFIX = "Path$PropertyNode";

        /**
         * Applies the class of {@code ....validation.Path.PropertyNode} to specified function and returns the result.
         *
         * @param function the function to applied with the class of {@code ....validation.Path.PropertyNode}.
         * @param <R>      result type parameter
         * @return the result of the {@code function}.
         */
        static <R> R applyPropertyNodeClass(final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassForSuffix(SUFFIX, function);
        }

        /**
         * Returns the class of {@code ....validation.Path.PropertyNode}.
         *
         * @return the class of {@code ....validation.Path.PropertyNode}.
         */
        static Class<?> getPropertyNodeNodeClass() {
            return applyPropertyNodeClass(Function.identity());
        }

        private static <R> R applyPropertyNodeClassFor(final Object actual,
                                                       final Function<? super Class<?>, ? extends R> function) {
            return Utils.applyClassFor(SUFFIX, actual, function);
        }

        private static Class<?> getPropertyNodeClassFor(final Object actual) {
            return applyPropertyNodeClassFor(actual, Function.identity());
        }

        /**
         * Indicates whether specified object is an instance of either {@code ....validation.Path.PropertyNode}.
         *
         * @param actual the object to be tested.
         * @return {@code true} if {@code actual} is an instance of {@code ....validation.Path.PropertyNode}; {@code
         * false} otherwise.
         */
        static boolean isPropertyNodeInstance(final Object actual) {
            if (actual == null) {
                return true;
            }
            return applyPropertyNodeClassFor(actual, c -> true);
        }

        /**
         * Checks whether specified object is an instance of either {@code ....validation.Path.PropertyNode}.
         *
         * @param actual the object to be tested.
         */
        static <T> T requirePropertyNodeInstance(final T actual) {
            if (actual == null) {
                return null;
            }
            return applyPropertyNodeClassFor(actual, c -> actual);
        }

        // ------------------------------------------------------------------------ getContainerClass()Ljava.lang.Class;
        static Class<?> getContainerClass(final Object actual) {
            return applyPropertyNodeClassFor(actual, c -> {
                try {
                    return (Class<?>) c.getMethod("getContainerClass").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        // ------------------------------------------------------------------- getTypeArgumentIndex()Ljava.lang.Integer;
        static Integer getTypeArgumentIndex(final Object actual) {
            return applyPropertyNodeClassFor(actual, c -> {
                try {
                    return (Integer) c.getMethod("getTypeArgumentIndex").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        private PropertyNodeUtils() {
            throw new NonInstantiatableAssertionError();
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
