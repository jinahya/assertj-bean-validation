package com.github.jinahya.assertj.validation.javax;

/*-
 * #%L
 * assertj-bean-validation-javax
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

import com.github.jinahya.assertj.validation.AbstractWrapper;

import javax.validation.Path;

import static java.util.Objects.requireNonNull;

public class PathAssertions {

    public static class NodeAssertions {

        public static PathAssert.NodeAssert assertThat(final Path.Node actual) {
            return new PathAssert.NodeAssert(actual);
        }

        public static PathAssert.NodeAssert assertThat(final AbstractWrapper<? extends Path.BeanNode> wrapper) {
            return assertThat(requireNonNull(wrapper).getActual());
        }

        public static PathAssert.NodeAssert assertNode(final Path.Node actual) {
            return assertThat(actual);
        }

        private NodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class BeanNodeAssertions {

        public static PathAssert.BeanNodeAssert assertThat(final Path.BeanNode actual) {
            return new PathAssert.BeanNodeAssert(actual);
        }

        public static PathAssert.BeanNodeAssert assertThat(final AbstractWrapper<? extends Path.BeanNode> wrapper) {
            return assertThat(requireNonNull(wrapper).getActual());
        }

        public static PathAssert.BeanNodeAssert assertBeanNode(final Path.BeanNode actual) {
            return assertThat(actual);
        }

        private BeanNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ConstructorNodeAssertions {

        public static PathAssert.ConstructorNodeAssert assertThat(final Path.ConstructorNode actual) {
            return new PathAssert.ConstructorNodeAssert(actual);
        }

        public static PathAssert.ConstructorNodeAssert assertThat(
                final AbstractWrapper<? extends Path.ConstructorNode> wrapper) {
            return assertThat(requireNonNull(wrapper).getActual());
        }

        public static PathAssert.ConstructorNodeAssert assertConstructorNode(final Path.ConstructorNode actual) {
            return assertThat(actual);
        }

        private ConstructorNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ContainerElementNodeAssertions {

        public static PathAssert.ContainerElementNodeAssert assertThat(final Path.ContainerElementNode actual) {
            return new PathAssert.ContainerElementNodeAssert(actual);
        }

        public static PathAssert.ContainerElementNodeAssert assertThat(
                final AbstractWrapper<? extends Path.ContainerElementNode> wrapper) {
            return assertThat(requireNonNull(wrapper).getActual());
        }

        public static PathAssert.ContainerElementNodeAssert assertContainerElementNode(
                final Path.ContainerElementNode actual) {
            return assertThat(actual);
        }

        private ContainerElementNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class CrossParameterNodeAssertions {

        public static PathAssert.CrossParameterNodeAssert assertThat(final Path.CrossParameterNode actual) {
            return new PathAssert.CrossParameterNodeAssert(actual);
        }

        public static PathAssert.CrossParameterNodeAssert assertThat(
                final AbstractWrapper<? extends Path.CrossParameterNode> wrapper) {
            return assertThat(requireNonNull(wrapper).getActual());
        }

        public static PathAssert.CrossParameterNodeAssert assertCrossParameterNode(
                final Path.CrossParameterNode actual) {
            return assertThat(actual);
        }

        private CrossParameterNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class MethodNodeAssertions {

        public static PathAssert.MethodNodeAssert assertThat(final Path.MethodNode actual) {
            return new PathAssert.MethodNodeAssert(actual);
        }

        public static PathAssert.MethodNodeAssert assertThat(final AbstractWrapper<? extends Path.MethodNode> wrapper) {
            return assertThat(requireNonNull(wrapper).getActual());
        }

        public static PathAssert.MethodNodeAssert assertMethodNode(final Path.MethodNode actual) {
            return assertThat(actual);
        }

        private MethodNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ParameterNodeAssertions {

        public static PathAssert.ParameterNodeAssert assertThat(final Path.ParameterNode actual) {
            return new PathAssert.ParameterNodeAssert(actual);
        }

        public static PathAssert.ParameterNodeAssert assertThat(
                final AbstractWrapper<? extends Path.ParameterNode> wrapper) {
            return assertThat(requireNonNull(wrapper).getActual());
        }

        public static PathAssert.ParameterNodeAssert assertParameterNode(final Path.ParameterNode actual) {
            return assertThat(actual);
        }

        private ParameterNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class PropertyNodeAssertions {

        public static PathAssert.PropertyNodeAssert assertThat(final Path.PropertyNode actual) {
            return new PathAssert.PropertyNodeAssert(actual);
        }

        public static PathAssert.PropertyNodeAssert assertThat(
                final AbstractWrapper<? extends Path.PropertyNode> wrapper) {
            return assertThat(requireNonNull(wrapper).getActual());
        }

        public static PathAssert.PropertyNodeAssert assertPropertyNode(final Path.PropertyNode actual) {
            return assertThat(actual);
        }

        private PropertyNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ReturnValueNodeAssertions {

        public static PathAssert.ReturnValueNodeAssert assertThat(final Path.ReturnValueNode actual) {
            return new PathAssert.ReturnValueNodeAssert(actual);
        }

        public static PathAssert.ReturnValueNodeAssert assertThat(
                final AbstractWrapper<? extends Path.ReturnValueNode> wrapper) {
            return assertThat(requireNonNull(wrapper).getActual());
        }

        public static PathAssert.ReturnValueNodeAssert assertReturnValueNode(final Path.ReturnValueNode actual) {
            return assertThat(actual);
        }

        private ReturnValueNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static PathAssert assertThat(final Path actual) {
        return new PathAssert(actual);
    }

    /**
     * Creates a new assertion instance for verifying an actual value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the actual to verify.
     * @return a new assertion instance for {@code wrapper.actual}.
     * @see PathWrapper#path(Path)
     */
    public static PathAssert assertThat(final AbstractWrapper<? extends Path> wrapper) {
        return assertThat(requireNonNull(wrapper).getActual());
    }

    public static PathAssert assertPath(final Path actual) {
        return assertThat(actual);
    }

    private PathAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
