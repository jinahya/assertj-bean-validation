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

import static java.util.Objects.requireNonNull;

public class PathAssertions {

    public static class NodeAssertions {

        public static <T> PathAssert.NodeAssert<T> assertNode(final T actual) {
            return new PathAssert.NodeAssert<>(actual);
        }

        public static <T> PathAssert.NodeAssert<T> assertThat(final PathWrapper.NodeWrapper<? extends T> wrapper) {
            return assertNode(requireNonNull(wrapper).getActual());
        }

        private NodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class BeanNodeAssertions {

        public static <T> PathAssert.BeanNodeAssert<T> assertBeanNode(final T actual) {
            return new PathAssert.BeanNodeAssert<>(actual);
        }

        public static <T> PathAssert.BeanNodeAssert<T> assertThat(
                final PathWrapper.BeanNodeWrapper<? extends T> wrapper) {
            return assertBeanNode(requireNonNull(wrapper).getActual());
        }

        private BeanNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ConstructorNodeAssertions {

        public static <T> PathAssert.ConstructorNodeAssert<T> assertConstructorNode(final T actual) {
            return new PathAssert.ConstructorNodeAssert<>(actual);
        }

        public static <T> PathAssert.ConstructorNodeAssert<T> assertThat(
                final PathWrapper.ConstructorNodeWrapper<? extends T> wrapper) {
            return assertConstructorNode(requireNonNull(wrapper).getActual());
        }

        private ConstructorNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ContainerElementNodeAssertions {

        public static <T> PathAssert.ContainerElementNodeAssert<T> assertContainerElementNode(final T actual) {
            return new PathAssert.ContainerElementNodeAssert<>(actual);
        }

        public static <T> PathAssert.ContainerElementNodeAssert<T> assertThat(
                final PathWrapper.ContainerElementNodeWrapper<? extends T> wrapper) {
            return assertContainerElementNode(requireNonNull(wrapper).getActual());
        }

        private ContainerElementNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class CrossParameterNodeAssertions {

        public static <T> PathAssert.CrossParameterNodeAssert<T> assertCrossParameterNode(final T actual) {
            return new PathAssert.CrossParameterNodeAssert<>(actual);
        }

        public static <T> PathAssert.CrossParameterNodeAssert<T> assertThat(
                final PathWrapper.CrossParameterNodeWrapper<? extends T> wrapper) {
            return assertCrossParameterNode(requireNonNull(wrapper).getActual());
        }

        private CrossParameterNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class MethodNodeAssertions {

        public static <T> PathAssert.MethodNodeAssert<T> assertMethodNode(final T actual) {
            return new PathAssert.MethodNodeAssert<>(actual);
        }

        public static <T> PathAssert.MethodNodeAssert<T> assertThat(
                final PathWrapper.MethodNodeWrapper<? extends T> wrapper) {
            return assertMethodNode(requireNonNull(wrapper).getActual());
        }

        private MethodNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ParameterNodeAssertions {

        public static <T> PathAssert.ParameterNodeAssert<T> assertParameterNode(final T actual) {
            return new PathAssert.ParameterNodeAssert<>(actual);
        }

        public static <T> PathAssert.ParameterNodeAssert<T> assertThat(
                final PathWrapper.ParameterNodeWrapper<? extends T> wrapper) {
            return assertParameterNode(requireNonNull(wrapper).getActual());
        }

        private ParameterNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class PropertyNodeAssertions {

        public static <T> PathAssert.PropertyNodeAssert<T> assertPropertyNode(final T actual) {
            return new PathAssert.PropertyNodeAssert<>(actual);
        }

        public static <T> PathAssert.PropertyNodeAssert<T> assertThat(
                final PathWrapper.PropertyNodeWrapper<? extends T> wrapper) {
            return assertPropertyNode(requireNonNull(wrapper).getActual());
        }

        private PropertyNodeAssertions() {
            super();
        }
    }

    public static class ReturnValueNodeAssertions {

        public static <T> PathAssert.ReturnValueNodeAssert<T> assertReturnValueNode(final T actual) {
            return new PathAssert.ReturnValueNodeAssert<>(actual);
        }

        public static <T> PathAssert.ReturnValueNodeAssert<T> assertThat(
                final PathWrapper.ReturnValueNodeWrapper<? extends T> wrapper) {
            return assertReturnValueNode(requireNonNull(wrapper).getActual());
        }

        private ReturnValueNodeAssertions() {
            super();
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance for specified {@code ....validation.Path} value.
     *
     * @param <ACTUAL> actual type parameter
     * @param <NODE>   node type parameter
     * @param actual   the value of {@code .....validation.Path} to assert.
     * @return a new assertion instance for {@code actual}.
     */
    public static <ACTUAL extends Iterable<NODE>, NODE> PathAssert<ACTUAL, NODE> assertPath(final ACTUAL actual) {
        return new PathAssert<>(actual);
    }

    /**
     * Creates a new assertion for the actual value wrapped in specified wrapper.
     *
     * @param <ACTUAL> the type of {@code ....validation.Path}
     * @param <NODE>   the type of {@code ....validation.Path.Node}
     * @param wrapper  the wrapper wraps the actual value.
     * @return a new assertion instance for {@code wrapper.actual}.
     */
    public static <ACTUAL extends Iterable<NODE>, NODE> PathAssert<ACTUAL, NODE> assertThat(
            final PathWrapper<? extends ACTUAL, NODE> wrapper) {
        return assertPath(requireNonNull(wrapper).getActual());
    }

    private PathAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
