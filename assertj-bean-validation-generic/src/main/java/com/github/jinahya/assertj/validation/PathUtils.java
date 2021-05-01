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

import static com.github.jinahya.assertj.validation.ReflectionUtils.getClassForSuffix;

//@SuppressWarnings({"java:S125"})
@SuppressWarnings({"java:S112"}) // throw new RuntimeException(roe)
final class PathUtils {

    static final class NodeUtils {

        private static final Class<?> NODE_CLASS = getClassForSuffix("Path$Node");

        private static <T> T requireInstanceOfNodeClass(final T object) {
            return LangUtils.requireInstanceOf(NODE_CLASS, object);
        }

        static <T> T requireNullOrInstanceOfNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return requireInstanceOfNodeClass(object);
        }

        static Integer getIndex(final Object object) {
            requireInstanceOfNodeClass(object);
            try {
                return (Integer) NODE_CLASS.getMethod("getIndex").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        static Object getKey(final Object object) {
            requireInstanceOfNodeClass(object);
            try {
                return NODE_CLASS.getMethod("getKey").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        static Object getKind(final Object object) {
            requireInstanceOfNodeClass(object);
            try {
                return NODE_CLASS.getMethod("getKind").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        static String getName(final Object object) {
            requireInstanceOfNodeClass(object);
            try {
                return (String) NODE_CLASS.getMethod("getName").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        static boolean isInIterable(final Object object) {
            requireInstanceOfNodeClass(object);
            try {
                return (boolean) NODE_CLASS.getMethod("isInIterable").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        private NodeUtils() {
            throw new NonInstantiatableAssertionError();
        }
    }

    static final class BeanNodeUtils {

        static final Class<?> BEAN_NODE_CLASS = getClassForSuffix("Path$BeanNode");

        private static <T> T requireInstanceOfBeanNodeClass(final T object) {
            return LangUtils.requireInstanceOf(BEAN_NODE_CLASS, object);
        }

        static <T> T requireNullOrInstanceOfBeanNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return requireInstanceOfBeanNodeClass(object);
        }

        static Class<?> getContainerClass(final Object object) {
            requireInstanceOfBeanNodeClass(object);
            try {
                return (Class<?>) BEAN_NODE_CLASS.getMethod("getContainerClass").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        static Integer getTypeArgumentIndex(final Object object) {
            requireInstanceOfBeanNodeClass(object);
            try {
                return (Integer) BEAN_NODE_CLASS.getMethod("getTypeArgumentIndex").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        private BeanNodeUtils() {
            throw new NonInstantiatableAssertionError();
        }
    }

    static final class ConstructorNodeUtils {

        static final Class<?> CONSTRUCTOR_NODE_CLASS = getClassForSuffix("Path$ConstructorNode");

        private static <T> T requireInstanceOfConstructorNodeClass(final T object) {
            return LangUtils.requireInstanceOf(CONSTRUCTOR_NODE_CLASS, object);
        }

        static <T> T requireNullOrInstanceOfConstructorNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return requireInstanceOfConstructorNodeClass(object);
        }

        @SuppressWarnings({"unchecked"})
        static List<Class<?>> getParameterTypes(final Object object) {
            requireInstanceOfConstructorNodeClass(object);
            try {
                return (List<Class<?>>) CONSTRUCTOR_NODE_CLASS.getMethod("getParameterTypes").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        private ConstructorNodeUtils() {
            throw new NonInstantiatableAssertionError();
        }
    }

    static final class ContainerElementNodeUtils {

        private static final Class<?> CONTAINER_ELEMENT_NODE_CLASS = getClassForSuffix("Path$ContainerElementNode");

        private static <T> T requireInstanceOfContainerElementNodeClass(final T object) {
            return LangUtils.requireInstanceOf(CONTAINER_ELEMENT_NODE_CLASS, object);
        }

        static <T> T requireNullOrInstanceOfContainerElementNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return requireInstanceOfContainerElementNodeClass(object);
        }

        static Class<?> getContainerClass(final Object object) {
            requireInstanceOfContainerElementNodeClass(object);
            try {
                return (Class<?>) CONTAINER_ELEMENT_NODE_CLASS.getMethod("getContainerClass").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        static Integer getTypeArgumentIndex(final Object object) {
            requireInstanceOfContainerElementNodeClass(object);
            try {
                return (Integer) CONTAINER_ELEMENT_NODE_CLASS.getMethod("getTypeArgumentIndex").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        private ContainerElementNodeUtils() {
            throw new NonInstantiatableAssertionError();
        }
    }

    static final class CrossParameterNodeUtils {

        static final Class<?> CROSS_PARAMETER_NODE_CLASS = getClassForSuffix("Path$CrossParameterNode");

        private static <T> T requireInstanceOfCrossParameterNodeClass(final T object) {
            return LangUtils.requireInstanceOf(CROSS_PARAMETER_NODE_CLASS, object);
        }

        static <T> T requireNullOrInstanceOfCrossParameterNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return requireInstanceOfCrossParameterNodeClass(object);
        }

        private CrossParameterNodeUtils() {
            throw new NonInstantiatableAssertionError();
        }
    }

    static final class MethodNodeUtils {

        static final Class<?> METHOD_NODE_CLASS = getClassForSuffix("Path$MethodNode");

        private static <T> T requireInstanceOfMethodNodeClass(final T object) {
            return LangUtils.requireInstanceOf(METHOD_NODE_CLASS, object);
        }

        static <T> T requireNullOrInstanceOfMethodNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return requireInstanceOfMethodNodeClass(object);
        }

        @SuppressWarnings({"unchecked"})
        static List<Class<?>> getParameterTypes(final Object actual) {
            requireInstanceOfMethodNodeClass(actual);
            try {
                return (List<Class<?>>) METHOD_NODE_CLASS.getMethod("getParameterTypes").invoke(actual);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        private MethodNodeUtils() {
            throw new NonInstantiatableAssertionError();
        }
    }

    static final class ParameterNodeUtils {

        static final Class<?> PARAMETER_NODE_CLASS = getClassForSuffix("Path$ParameterNode");

        private static <T> T requireInstanceOfParameterNodeClass(final T object) {
            return LangUtils.requireInstanceOf(PARAMETER_NODE_CLASS, object);
        }

        static <T> T requireNullOrInstanceOfParameterNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return requireInstanceOfParameterNodeClass(object);
        }

        static int getParameterIndex(final Object object) {
            requireInstanceOfParameterNodeClass(object);
            try {
                return (int) PARAMETER_NODE_CLASS.getMethod("getParameterIndex").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        private ParameterNodeUtils() {
            throw new NonInstantiatableAssertionError();
        }
    }

    static final class PropertyNodeUtils {

        static final Class<?> PROPERTY_NODE_CLASS = getClassForSuffix("Path$PropertyNode");

        private static <T> T requireInstanceOfPropertyNodeClass(final T object) {
            return LangUtils.requireInstanceOf(PROPERTY_NODE_CLASS, object);
        }

        static <T> T requireNullOrInstanceOfPropertyNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return requireInstanceOfPropertyNodeClass(object);
        }

        static Class<?> getContainerClass(final Object object) {
            requireNullOrInstanceOfPropertyNodeClass(object);
            try {
                return (Class<?>) PROPERTY_NODE_CLASS.getMethod("getContainerClass").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        static Integer getTypeArgumentIndex(final Object object) {
            requireNullOrInstanceOfPropertyNodeClass(object);
            try {
                return (Integer) PROPERTY_NODE_CLASS.getMethod("getTypeArgumentIndex").invoke(object);
            } catch (final ReflectiveOperationException roe) {
                throw new RuntimeException(roe);
            }
        }

        private PropertyNodeUtils() {
            throw new NonInstantiatableAssertionError();
        }
    }

    static final class ReturnValueNodeUtils {

        static final Class<?> RETURN_VALUE_NODE_CLASS = getClassForSuffix("Path$ReturnValueNode");

        private static <T> T requireInstanceOfReturnValueNodeClass(final T object) {
            return LangUtils.requireInstanceOf(RETURN_VALUE_NODE_CLASS, object);
        }

        static <T> T requireNullOrInstanceOfReturnValueNodeClass(final T object) {
            if (object == null) {
                return null;
            }
            return requireInstanceOfReturnValueNodeClass(object);
        }

        private ReturnValueNodeUtils() {
            throw new NonInstantiatableAssertionError();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private static final Class<?> PATH_CLASS = getClassForSuffix("Path");

    private static <T> T requireInstanceOfPathClass(final T object) {
        return LangUtils.requireInstanceOf(PATH_CLASS, object);
    }

    static <T> T requireNullOrInstanceOfPathClass(final T object) {
        if (object == null) {
            return null;
        }
        return requireInstanceOfPathClass(object);
    }

    private PathUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
