package com.github.jinahya.assertj.validation;

import javax.validation.Path;

import static java.util.Objects.requireNonNull;

public class PathAssertions {

    public static class NodeAssertions {

        public static PathAssert.NodeAssert assertThat(final Path.Node actual) {
            return new PathAssert.NodeAssert(actual);
        }

        public static PathAssert.NodeAssert assertNode(final Path.Node actual) {
            return assertThat(actual);
        }

        public static PathAssert.NodeAssert assertThat(final AbstractWrapper<? extends Path.BeanNode> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertNode(wrapper.getActual());
        }

        private NodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class BeanNodeAssertions {

        public static PathAssert.BeanNodeAssert assertThat(final Path.BeanNode actual) {
            return new PathAssert.BeanNodeAssert(actual);
        }

        public static PathAssert.BeanNodeAssert assertBeanNode(final Path.BeanNode actual) {
            return assertThat(actual);
        }

        public static PathAssert.BeanNodeAssert assertThat(final AbstractWrapper<? extends Path.BeanNode> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertBeanNode(wrapper.getActual());
        }

        private BeanNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ConstructorNodeAssertions {

        public static PathAssert.ConstructorNodeAssert assertThat(final Path.ConstructorNode actual) {
            return new PathAssert.ConstructorNodeAssert(actual);
        }

        public static PathAssert.ConstructorNodeAssert assertConstructorNode(final Path.ConstructorNode actual) {
            return assertThat(actual);
        }

        public static PathAssert.ConstructorNodeAssert assertThat(
                final AbstractWrapper<? extends Path.ConstructorNode> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertConstructorNode(wrapper.getActual());
        }

        private ConstructorNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ContainerElementNodeAssertions {

        public static PathAssert.ContainerElementNodeAssert assertThat(final Path.ContainerElementNode actual) {
            return new PathAssert.ContainerElementNodeAssert(actual);
        }

        public static PathAssert.ContainerElementNodeAssert assertContainerElementNode(
                final Path.ContainerElementNode actual) {
            return assertThat(actual);
        }

        public static PathAssert.ContainerElementNodeAssert assertThat(
                final AbstractWrapper<? extends Path.ContainerElementNode> wrapper) {
            return assertContainerElementNode(requireNonNull(wrapper).getActual());
        }

        private ContainerElementNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class CrossParameterNodeAssertions {

        public static PathAssert.CrossParameterNodeAssert assertThat(final Path.CrossParameterNode actual) {
            return new PathAssert.CrossParameterNodeAssert(actual);
        }

        public static PathAssert.CrossParameterNodeAssert assertCrossParameterNode(
                final Path.CrossParameterNode actual) {
            return assertThat(actual);
        }

        public static PathAssert.CrossParameterNodeAssert assertThat(
                final AbstractWrapper<? extends Path.CrossParameterNode> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertCrossParameterNode(wrapper.getActual());
        }

        private CrossParameterNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class MethodNodeAssertions {

        public static PathAssert.MethodNodeAssert assertThat(final Path.MethodNode actual) {
            return new PathAssert.MethodNodeAssert(actual);
        }

        public static PathAssert.MethodNodeAssert assertMethodNodeNode(final Path.MethodNode actual) {
            return assertThat(actual);
        }

        public static PathAssert.MethodNodeAssert assertThat(final AbstractWrapper<? extends Path.MethodNode> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertMethodNodeNode(wrapper.getActual());
        }

        private MethodNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ParameterNodeAssertions {

        public static PathAssert.ParameterNodeAssert assertThat(final Path.ParameterNode actual) {
            return new PathAssert.ParameterNodeAssert(actual);
        }

        public static PathAssert.ParameterNodeAssert assertParameterNode(final Path.ParameterNode actual) {
            return assertThat(actual);
        }

        public static PathAssert.ParameterNodeAssert assertThat(
                final AbstractWrapper<? extends Path.ParameterNode> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertParameterNode(wrapper.getActual());
        }

        private ParameterNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class PropertyNodeAssertions {

        public static PathAssert.PropertyNodeAssert assertThat(final Path.PropertyNode actual) {
            return new PathAssert.PropertyNodeAssert(actual);
        }

        public static PathAssert.PropertyNodeAssert assertPropertyNode(final Path.PropertyNode actual) {
            return assertThat(actual);
        }

        public static PathAssert.PropertyNodeAssert assertThat(
                final AbstractWrapper<? extends Path.PropertyNode> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertPropertyNode(wrapper.getActual());
        }

        private PropertyNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static class ReturnValueNodeAssertions {

        public static PathAssert.ReturnValueNodeAssert assertThat(final Path.ReturnValueNode actual) {
            return new PathAssert.ReturnValueNodeAssert(actual);
        }

        public static PathAssert.ReturnValueNodeAssert assertReturnValueNode(final Path.ReturnValueNode actual) {
            return assertThat(actual);
        }

        public static PathAssert.ReturnValueNodeAssert assertThat(
                final AbstractWrapper<? extends Path.ReturnValueNode> wrapper) {
            requireNonNull(wrapper, "wrapper is null");
            return assertReturnValueNode(wrapper.getActual());
        }

        private ReturnValueNodeAssertions() {
            throw new NonInstantiatableAssertionError();
        }
    }

    public static PathAssert assertPath(final Path actual) {
        return new PathAssert(actual);
    }

    public static PathAssert assertThat(final AbstractWrapper<? extends Path> wrapper) {
        return assertPath(requireNonNull(wrapper).getActual());
    }

    private PathAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
