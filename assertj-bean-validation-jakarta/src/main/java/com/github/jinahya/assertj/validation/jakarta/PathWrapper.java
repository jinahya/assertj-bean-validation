package com.github.jinahya.assertj.validation.jakarta;

import com.github.jinahya.assertj.validation.AbstractPathWrapper;
import com.github.jinahya.assertj.validation.AbstractWrapper;
import jakarta.validation.Path;

public final class PathWrapper
        extends AbstractWrapper<Path> {

    public static class NodeWrapper
            extends AbstractPathWrapper.AbstractNodeWrapper<Path.Node> {

        public static NodeWrapper node(final Path.Node actual) {
            return new NodeWrapper(actual);
        }

        private NodeWrapper(final Path.Node wrapped) {
            super(wrapped);
        }
    }

    public static class BeanNodeWrapper
            extends AbstractPathWrapper.AbstractBeanNodeWrapper<Path.BeanNode> {

        public static BeanNodeWrapper beanNode(final Path.BeanNode actual) {
            return new BeanNodeWrapper(actual);
        }

        private BeanNodeWrapper(final Path.BeanNode wrapped) {
            super(wrapped);
        }
    }

    public static class ConstructorNodeWrapper
            extends AbstractPathWrapper.AbstractConstructorNodeWrapper<Path.ConstructorNode> {

        public static ConstructorNodeWrapper constructorNode(final Path.ConstructorNode actual) {
            return new ConstructorNodeWrapper(actual);
        }

        private ConstructorNodeWrapper(final Path.ConstructorNode actual) {
            super(actual);
        }
    }

    public static class ContainerElementNodeWrapper
            extends AbstractPathWrapper.AbstractContainerElementNodeWrapper<Path.ContainerElementNode> {

        public static ContainerElementNodeWrapper containerElementNode(final Path.ContainerElementNode actual) {
            return new ContainerElementNodeWrapper(actual);
        }

        private ContainerElementNodeWrapper(final Path.ContainerElementNode actual) {
            super(actual);
        }
    }

    public static class CrossParameterNodeWrapper
            extends AbstractPathWrapper.AbstractCrossParameterNodeWrapper<Path.CrossParameterNode> {

        public static CrossParameterNodeWrapper crossParameterNode(final Path.CrossParameterNode actual) {
            return new CrossParameterNodeWrapper(actual);
        }

        private CrossParameterNodeWrapper(final Path.CrossParameterNode actual) {
            super(actual);
        }
    }

    public static class MethodNodeWrapper
            extends AbstractPathWrapper.AbstractMethodNodeWrapper<Path.MethodNode> {

        public static MethodNodeWrapper methodNode(final Path.MethodNode actual) {
            return new MethodNodeWrapper(actual);
        }

        private MethodNodeWrapper(final Path.MethodNode actual) {
            super(actual);
        }
    }

    public static class ParameterNodeWrapper
            extends AbstractPathWrapper.AbstractNodeWrapper<Path.ParameterNode> {

        public static ParameterNodeWrapper parameterNode(final Path.ParameterNode actual) {
            return new ParameterNodeWrapper(actual);
        }

        private ParameterNodeWrapper(final Path.ParameterNode actual) {
            super(actual);
        }
    }

    public static class PropertyNodeWrapper
            extends AbstractPathWrapper.AbstractPropertyNodeWrapper<Path.PropertyNode> {

        public static PropertyNodeWrapper propertyNode(final Path.PropertyNode actual) {
            return new PropertyNodeWrapper(actual);
        }

        private PropertyNodeWrapper(final Path.PropertyNode actual) {
            super(actual);
        }
    }

    public static class ReturnValueNodeWrapper
            extends AbstractPathWrapper.AbstractReturnValueNodeWrapper<Path.ReturnValueNode> {

        public static ReturnValueNodeWrapper returnValueNode(final Path.ReturnValueNode actual) {
            return new ReturnValueNodeWrapper(actual);
        }

        private ReturnValueNodeWrapper(final Path.ReturnValueNode actual) {
            super(actual);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static PathWrapper path(final Path actual) {
        return new PathWrapper(actual);
    }

    private PathWrapper(final Path actual) {
        super(actual);
    }
}
