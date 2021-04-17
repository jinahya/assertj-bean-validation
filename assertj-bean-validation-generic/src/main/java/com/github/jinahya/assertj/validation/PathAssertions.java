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

        public static PathAssert.NodeAssert assertNode(final Object actual) {
            return new PathAssert.NodeAssert(actual);
        }

        public static PathAssert.NodeAssert assertThat(final PathWrapper.NodeWrapper wrapper) {
            return assertNode(requireNonNull(wrapper).getActual());
        }

        private NodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class BeanNodeAssertions {

        public static PathAssert.BeanNodeAssert assertBeanNode(final Object actual) {
            return new PathAssert.BeanNodeAssert(actual);
        }

        public static PathAssert.BeanNodeAssert assertThat(final PathWrapper.BeanNodeWrapper wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertBeanNode(wrapper.getActual());
        }

        private BeanNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ConstructorNodeAssertions {

        public static PathAssert.ConstructorNodeAssert assertConstructorNode(final Object actual) {
            return new PathAssert.ConstructorNodeAssert(actual);
        }

        public static PathAssert.ConstructorNodeAssert assertThat(final PathWrapper.ConstructorNodeWrapper wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertConstructorNode(wrapper.getActual());
        }

        private ConstructorNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ContainerElementNodeAssertions {

        public static PathAssert.ContainerElementNodeAssert assertContainerElementNode(final Object actual) {
            return new PathAssert.ContainerElementNodeAssert(actual);
        }

        public static PathAssert.ContainerElementNodeAssert assertThat(
                final PathWrapper.ContainerElementNodeWrapper wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertContainerElementNode(wrapper.getActual());
        }

        private ContainerElementNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class CrossParameterNodeAssertions {

        public static PathAssert.CrossParameterNodeAssert assertCrossParameterNode(final Object actual) {
            return new PathAssert.CrossParameterNodeAssert(actual);
        }

        public static PathAssert.CrossParameterNodeAssert assertThat(
                final PathWrapper.CrossParameterNodeWrapper wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertCrossParameterNode(wrapper.getActual());
        }

        private CrossParameterNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class MethodNodeAssertions {

        public static PathAssert.MethodNodeAssert assertMethodNode(final Object actual) {
            return new PathAssert.MethodNodeAssert(actual);
        }

        public static PathAssert.MethodNodeAssert assertThat(final PathWrapper.MethodNodeWrapper wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertMethodNode(wrapper.getActual());
        }

        private MethodNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ParameterNodeAssertions {

        public static PathAssert.ParameterNodeAssert assertParameterNode(final Object actual) {
            return new PathAssert.ParameterNodeAssert(actual);
        }

        public static PathAssert.ParameterNodeAssert assertThat(final PathWrapper.ParameterNodeWrapper wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertParameterNode(wrapper.getActual());
        }

        private ParameterNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class PropertyNodeAssertions {

        public static PathAssert.PropertyNodeAssert assertPropertyNode(final Object actual) {
            return new PathAssert.PropertyNodeAssert(actual);
        }

        public static PathAssert.PropertyNodeAssert assertThat(final PathWrapper.PropertyNodeWrapper wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertPropertyNode(wrapper.getActual());
        }

        private PropertyNodeAssertions() {
            super();
        }
    }

    public static class ReturnValueNodeAssertions {

        public static PathAssert.ReturnValueNodeAssert assertReturnValueNode(final Object actual) {
            return new PathAssert.ReturnValueNodeAssert(actual);
        }

        public static PathAssert.ReturnValueNodeAssert assertThat(final PathWrapper.ReturnValueNodeWrapper wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertReturnValueNode(wrapper.getActual());
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
