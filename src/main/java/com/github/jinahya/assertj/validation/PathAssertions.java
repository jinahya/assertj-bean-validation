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

    abstract static class AbstractNodeAssertions {

        AbstractNodeAssertions() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    public static class NodeAssertions extends AbstractNodeAssertions {

        public static <T> PathAssert.NodeAssert<T> assertNode(final T actual) {
            return new PathAssert.NodeAssert<>(actual);
        }

        public static <T> PathAssert.NodeAssert<T> assertThat(final PathWrapper.NodeWrapper<? extends T> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertNode(wrapper.getActual());
        }

        private NodeAssertions() {
            super();
        }
    }

    public static class BeanNodeAssertions extends AbstractNodeAssertions {

        public static <T> PathAssert.BeanNodeAssert<T> assertBeanNode(final T actual) {
            return new PathAssert.BeanNodeAssert<>(actual);
        }

        public static <T> PathAssert.BeanNodeAssert<T> assertThat(
                final PathWrapper.BeanNodeWrapper<? extends T> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertBeanNode(wrapper.getActual());
        }

        private BeanNodeAssertions() {
            super();
        }
    }

    public static class ConstructorNodeAssertions extends AbstractNodeAssertions {

        public static <T> PathAssert.ConstructorNodeAssert<T> assertConstructorNode(final T actual) {
            return new PathAssert.ConstructorNodeAssert<>(actual);
        }

        public static <T> PathAssert.ConstructorNodeAssert<T> assertThat(
                final PathWrapper.ConstructorNodeWrapper<? extends T> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertConstructorNode(wrapper.getActual());
        }

        private ConstructorNodeAssertions() {
            super();
        }
    }

    public static class ContainerElementNodeAssertions extends AbstractNodeAssertions {

        public static <T> PathAssert.ContainerElementNodeAssert<T> assertContainerElementNode(final T actual) {
            return new PathAssert.ContainerElementNodeAssert<>(actual);
        }

        public static <T> PathAssert.ContainerElementNodeAssert<T> assertThat(
                final PathWrapper.ContainerElementNodeWrapper<? extends T> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertContainerElementNode(wrapper.getActual());
        }

        private ContainerElementNodeAssertions() {
            super();
        }
    }

    public static class CrossParameterNodeAssertions extends AbstractNodeAssertions {

        public static <T> PathAssert.CrossParameterNodeAssert<T> assertCrossParameterNode(final T actual) {
            return new PathAssert.CrossParameterNodeAssert<>(actual);
        }

        public static <T> PathAssert.CrossParameterNodeAssert<T> assertThat(
                final PathWrapper.CrossParameterNodeWrapper<? extends T> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertCrossParameterNode(wrapper.getActual());
        }

        private CrossParameterNodeAssertions() {
            super();
        }
    }

    public static class MethodNodeAssertions extends AbstractNodeAssertions {

        public static <T> PathAssert.MethodNodeAssert<T> assertMethodNode(final T actual) {
            return new PathAssert.MethodNodeAssert<>(actual);
        }

        public static <T> PathAssert.MethodNodeAssert<T> assertThat(
                final PathWrapper.MethodNodeWrapper<? extends T> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertMethodNode(wrapper.getActual());
        }

        private MethodNodeAssertions() {
            super();
        }
    }

    public static class ParameterNodeAssertions extends AbstractNodeAssertions {

        public static <T> PathAssert.ParameterNodeAssert<T> assertParameterNode(final T actual) {
            return new PathAssert.ParameterNodeAssert<>(actual);
        }

        public static <T> PathAssert.ParameterNodeAssert<T> assertThat(
                final PathWrapper.ParameterNodeWrapper<? extends T> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertParameterNode(wrapper.getActual());
        }

        private ParameterNodeAssertions() {
            super();
        }
    }

    public static class PropertyNodeAssertions extends AbstractNodeAssertions {

        public static <T> PathAssert.PropertyNodeAssert<T> assertPropertyNode(final T actual) {
            return new PathAssert.PropertyNodeAssert<>(actual);
        }

        public static <T> PathAssert.PropertyNodeAssert<T> assertThat(
                final PathWrapper.PropertyNodeWrapper<? extends T> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertPropertyNode(wrapper.getActual());
        }

        private PropertyNodeAssertions() {
            super();
        }
    }

    public static class ReturnValueNodeAssertions extends AbstractNodeAssertions {

        public static <T> PathAssert.ReturnValueNodeAssert<T> assertReturnValueNode(final T actual) {
            return new PathAssert.ReturnValueNodeAssert<>(actual);
        }

        public static <T> PathAssert.ReturnValueNodeAssert<T> assertThat(
                final PathWrapper.ReturnValueNodeWrapper<? extends T> wrapper) {
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
     * @param actual the value of {@code .....validation.Path} to assert.
     * @return a new assertion instance for {@code actual}.
     */
    public static <PATH extends Iterable<NODE>, NODE> PathAssert<PATH, NODE> assertPath(final PATH actual) {
        return new PathAssert<>(actual);
    }

    /**
     * Creates a new assertion for the actual value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the actual value.
     * @return a new assertion instance for {@code wrapper.actual}.
     */
    public static <PATH extends Iterable<NODE>, NODE> PathAssert<PATH, NODE> assertThat(
            final PathWrapper<PATH, NODE> wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertPath(wrapper.getActual());
    }

    private PathAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
