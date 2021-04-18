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

import static com.github.jinahya.assertj.validation.ReflectionUtils.applyClassForSuffix;
import static java.util.Objects.requireNonNull;

@SuppressWarnings({"java:S125"})
final class PathUtils {

    static final class NodeUtils {

        private static final String SUFFIX = "Path$Node";

        static <R> R applyNodeClass(final Function<? super Class<?>, ? extends R> function) {
            return ReflectionUtils.applyClassForSuffix(SUFFIX, function);
        }

        static boolean isNullOrNodeInstance(final Object actual) {
            if (actual == null) {
                return true;
            }
            return ReflectionUtils.isInstanceOfClassForSuffix(SUFFIX, actual);
        }

        static <T> T requireNullOrNodeInstance(final T actual) {
            if (actual == null) {
                return null;
            }
            return ReflectionUtils.requireInstanceOfClassForSuffix(SUFFIX, actual);
        }

        // ------------------------------------------------------------------------------- getIndex()Ljava.lang.Integer;
        static Integer getIndex(final Object actual) {
            requireNonNull(actual, "actual is null");
            return applyNodeClass(c -> {
                try {
                    return (Integer) c.getMethod("getIndex").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        // ---------------------------------------------------------------------------------- getKey()Ljava.lang.Object;
        static Object getKey(final Object actual) {
            requireNonNull(actual, "actual is null");
            return applyNodeClass(c -> {
                try {
                    return c.getMethod("getKey").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        // ----------------------------------------------------------------------- getKind()L....validation.ElementKind;
        static Object getKind(final Object actual) {
            requireNonNull(actual, "actual is null");
            return applyNodeClass(c -> {
                try {
                    return c.getMethod("getKind").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        // --------------------------------------------------------------------------------- getName()Ljava.lang.String;
        static String getName(final Object actual) {
            requireNonNull(actual, "actual is null");
            return applyNodeClass(c -> {
                try {
                    return (String) c.getMethod("getName").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        // --------------------------------------------------------------------------------------------- isInIterable()Z
        static boolean isInIterable(final Object actual) {
            requireNonNull(actual, "actual is null");
            return applyNodeClass(c -> {
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

    // -----------------------------------------------------------------------------------------------------------------
    static final class BeanNodeUtils {

        private static final String SUFFIX = "Path$BeanNode";

        static <R> R applyBeanNodeClass(final Function<? super Class<?>, ? extends R> function) {
            return ReflectionUtils.applyClassForSuffix(SUFFIX, function);
        }

        static boolean isNullOrBeanNodeInstance(final Object actual) {
            if (actual == null) {
                return true;
            }
            return ReflectionUtils.isInstanceOfClassForSuffix(SUFFIX, actual);
        }

        static <T> T requireNullOrBeanNodeInstance(final T actual) {
            if (actual == null) {
                return null;
            }
            return ReflectionUtils.requireInstanceOfClassForSuffix(SUFFIX, actual);
        }

        static Class<?> getContainerClass(final Object actual) {
            requireNonNull(actual, "actual is null");
            return applyBeanNodeClass(c -> {
                try {
                    return (Class<?>) c.getMethod("getContainerClass").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Integer getTypeArgumentIndex(final Object actual) {
            requireNonNull(actual, "actual is null");
            return applyBeanNodeClass(c -> {
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

        private static final String SUFFIX = "Path$ConstructorNode";

        private static <R> R applyConstructorNodeClass(final Function<? super Class<?>, ? extends R> function) {
            return ReflectionUtils.applyClassForSuffix(SUFFIX, function);
        }

        static boolean isNullOrInstanceOfConstructorNodeClass(final Object actual) {
            if (actual == null) {
                return true;
            }
            return ReflectionUtils.isInstanceOfClassForSuffix(SUFFIX, actual);
        }

        static <T> T requireNullOrInstanceOfConstructorNodeClass(final T actual) {
            if (actual == null) {
                return null;
            }
            return ReflectionUtils.requireInstanceOfClassForSuffix(SUFFIX, actual);
        }

        @SuppressWarnings({"unchecked"})
        static List<Class<?>> getParameterTypes(final Object actual) {
            return applyConstructorNodeClass(c -> {
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

        private static final String SUFFIX = "Path$ContainerElementNode";

        private static <R> R applyContainerElementNodeClass(final Function<? super Class<?>, ? extends R> function) {
            return ReflectionUtils.applyClassForSuffix(SUFFIX, function);
        }

        static boolean isNullOrInstanceOfContainerElementNodeClass(final Object object) {
            if (object == null) {
                return true;
            }
            return ReflectionUtils.isInstanceOfClassForSuffix(SUFFIX, object);
        }

        static <T> T requireNullOrInstanceOfContainerElementNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return ReflectionUtils.requireInstanceOfClassForSuffix(SUFFIX, object);
        }

        static Class<?> getContainerClass(final Object actual) {
            return applyContainerElementNodeClass(c -> {
                try {
                    return (Class<?>) c.getMethod("getContainerClass").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        static Integer getTypeArgumentIndex(final Object actual) {
            return applyContainerElementNodeClass(c -> {
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

        private static final String SUFFIX = "Path$CrossParameterNode";

        private static <R> R applyCrossParameterNodeClass(final Function<? super Class<?>, ? extends R> function) {
            return ReflectionUtils.applyClassForSuffix(SUFFIX, function);
        }

        static boolean isNullOrInstanceOfCrossParameterNodeClass(final Object object) {
            if (object == null) {
                return true;
            }
            return ReflectionUtils.isInstanceOfClassForSuffix(SUFFIX, object);
        }

        static <T> T requireNullOrInstanceOfCrossParameterNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return ReflectionUtils.requireInstanceOfClassForSuffix(SUFFIX, object);
        }

        private CrossParameterNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    static final class MethodNodeUtils {

        private static final String SUFFIX = "Path$MethodNode";

        private static <R> R applyMethodNodeClass(final Function<? super Class<?>, ? extends R> function) {
            return ReflectionUtils.applyClassForSuffix(SUFFIX, function);
        }

        static boolean isNullOrInstanceOfMethodNodeClass(final Object object) {
            if (object == null) {
                return true;
            }
            return ReflectionUtils.isInstanceOfClassForSuffix(SUFFIX, object);
        }

        static <T> T requireNullOrInstanceOfMethodNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return ReflectionUtils.requireInstanceOfClassForSuffix(SUFFIX, object);
        }

        @SuppressWarnings({"unchecked"})
        static List<Class<?>> getParameterTypes(final Object actual) {
            return applyMethodNodeClass(c -> {
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

        private static final String SUFFIX = "Path$ParameterNode";

        private static <R> R applyParameterNodeClass(final Function<? super Class<?>, ? extends R> function) {
            return ReflectionUtils.applyClassForSuffix(SUFFIX, function);
        }

        static boolean isNullOrInstanceOfParameterNodeClass(final Object object) {
            if (object == null) {
                return true;
            }
            return ReflectionUtils.isInstanceOfClassForSuffix(SUFFIX, object);
        }

        static <T> T requireNullOrInstanceOfParameterNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return ReflectionUtils.requireInstanceOfClassForSuffix(SUFFIX, object);
        }

        // getParameterIndex()I
        static int getParameterIndex(final Object actual) {
            return applyParameterNodeClass(c -> {
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

        static <R> R applyPropertyNodeClass(final Function<? super Class<?>, ? extends R> function) {
            return applyClassForSuffix(SUFFIX, function);
        }

        static boolean isNullOrInstanceOfPropertyNodeClass(final Object object) {
            if (object == null) {
                return true;
            }
            return ReflectionUtils.isInstanceOfClassForSuffix(SUFFIX, object);
        }

        static <T> T requireNullOrInstanceOfPropertyNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return ReflectionUtils.requireInstanceOfClassForSuffix(SUFFIX, object);
        }

        static Class<?> getContainerClass(final Object actual) {
            return applyPropertyNodeClass(c -> {
                try {
                    return (Class<?>) c.getMethod("getContainerClass").invoke(actual);
                } catch (final ReflectiveOperationException roe) {
                    throw new RuntimeException(roe);
                }
            });
        }

        // ------------------------------------------------------------------- getTypeArgumentIndex()Ljava.lang.Integer;
        static Integer getTypeArgumentIndex(final Object actual) {
            return applyPropertyNodeClass(c -> {
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

        private static final String SUFFIX = "Path$ReturnValueNode";

        static <R> R applyReturnValueNodeClass(final Function<? super Class<?>, ? extends R> function) {
            return ReflectionUtils.applyClassForSuffix(SUFFIX, function);
        }

        private static Class<?> returnValueNodeClass = null;

        static Class<?> getReturnValueNodeClass() {
            if (returnValueNodeClass == null) {
                returnValueNodeClass = applyReturnValueNodeClass(Function.identity());
            }
            return returnValueNodeClass;
        }

        static boolean isNullOrInstanceOfReturnValueNodeClass(final Object object) {
            if (object == null) {
                return true;
            }
            return getReturnValueNodeClass().isInstance(object);
        }

        static <T> T requireNullOrInstanceOfReturnValueNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            final Class<?> returnValueNodeClass = getReturnValueNodeClass();
            if (returnValueNodeClass.isInstance(object)) {
                throw new IllegalArgumentException(object + " is not an instance of " + returnValueNodeClass);
            }
            return object;
        }

        private ReturnValueNodeUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final String SUFFIX = "Path";

    static <R> R applyPathClass(final Function<? super Class<?>, ? extends R> function) {
        return ReflectionUtils.applyClassForSuffix(SUFFIX, function);
    }

    private static Class<?> pathClass = null;

    static Class<?> getPathClass() {
        if (pathClass == null) {
            pathClass = applyPathClass(Function.identity());
        }
        return pathClass;
    }

    static boolean isNullOrInstanceOfPathClass(final Object object) {
        if (object == null) {
            return true;
        }
        return getPathClass().isInstance(object);
    }

    static <T> T requireNullOrInstanceOfPathClass(final T object) {
        if (object == null) {
            return null;
        }
        final Class<?> pathClass = getPathClass();
        if (pathClass.isInstance(object)) {
            throw new IllegalArgumentException(object + " is not an instance of " + pathClass);
        }
        return object;
    }

    private PathUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
