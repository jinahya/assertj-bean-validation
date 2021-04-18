package com.github.jinahya.assertj.validation;

import javax.validation.ElementKind;
import javax.validation.Path;
import javax.validation.Path.Node;
import java.util.List;

/**
 * An assertion class for verifying instances of {@link Path}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class PathAssert
        extends com.github.jinahya.assertj.validation.AbstractPathAssert<Path, Node> {

    // -----------------------------------------------------------------------------------------------------------------
    static class NodeAssertDelegateImpl<NODE extends Node>
            implements AbstractNodeAssertDelegate<NODE, ElementKind> {

        @Override
        public Integer getIndex(final NODE actual) {
            return actual.getIndex();
        }

        @Override
        public Object getKey(final NODE actual) {
            return actual.getKey();
        }

        @Override
        public ElementKind getKind(final NODE actual) {
            return actual.getKind();
        }

        @Override
        public String getName(final NODE actual) {
            return actual.getName();
        }

        @Override
        public boolean isInIterable(final NODE actual) {
            return actual.isInIterable();
        }
    }

    /**
     * An assertion class for verifying instances of {@link Path.Node}.
     *
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public static class NodeAssert
            extends AbstractNodeAssert<NodeAssert, Node, ElementKind> {

        public NodeAssert(final Node actual) {
            super(actual, NodeAssert.class, new NodeAssertDelegateImpl());
        }

        // -------------------------------------------------------------------------------------------------------------
        @Override
        public NodeAssert isBeanNode() {
            return isInstanceOf(Path.BeanNode.class);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public BeanNodeAssert asBeanNode() {
            isBeanNode();
            return new BeanNodeAssert((Path.BeanNode) actual);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class BeanNodeDelegateImpl
            extends NodeAssertDelegateImpl<Path.BeanNode>
            implements BeanNodeAssertDelegate<Path.BeanNode, ElementKind> {

        @Override
        public Class<?> getContainerClass(final Path.BeanNode beanNode) {
            return beanNode.getContainerClass();
        }

        @Override
        public Integer getTypeArgumentIndex(final Path.BeanNode beanNode) {
            return beanNode.getTypeArgumentIndex();
        }
    }

    public static class BeanNodeAssert
            extends AbstractBeanNodeAssert<BeanNodeAssert, Path.BeanNode, ElementKind> {

        public BeanNodeAssert(final Path.BeanNode actual) {
            super(actual, BeanNodeAssert.class, new BeanNodeDelegateImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class ConstructorNodeDelegateImpl
            extends NodeAssertDelegateImpl<Path.ConstructorNode>
            implements ConstructorNodeAssertDelegate<Path.ConstructorNode, ElementKind> {

        @Override
        public List<Class<?>> getParameterTypes(final Path.ConstructorNode constructorNode) {
            return constructorNode.getParameterTypes();
        }
    }

    public static class ConstructorNodeAssert
            extends AbstractConstructorNodeAssert<ConstructorNodeAssert, Path.ConstructorNode, ElementKind> {

        public ConstructorNodeAssert(final Path.ConstructorNode actual) {
            super(actual, ConstructorNodeAssert.class, new ConstructorNodeDelegateImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class ContainerElementNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl<Path.ContainerElementNode>
            implements ContainerElementNodeAssertDelegate<Path.ContainerElementNode, ElementKind> {

        @Override
        public Class<?> getContainerClass(final Path.ContainerElementNode actual) {
            return actual.getContainerClass();
        }

        @Override
        public Integer getTypeArgumentIndex(final Path.ContainerElementNode actual) {
            return actual.getTypeArgumentIndex();
        }
    }

    public static class ContainerElementNodeAssert
            extends AbstractContainerElementNodeAssert<
            ContainerElementNodeAssert,
            Path.ContainerElementNode,
            ElementKind> {

        public ContainerElementNodeAssert(final Path.ContainerElementNode actual) {
            super(actual, ContainerElementNodeAssert.class, new ContainerElementNodeAssertDelegateImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class CrossParameterNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl<Path.CrossParameterNode>
            implements CrossParameterNodeAssertDelegate<Path.CrossParameterNode, ElementKind> {

    }

    public static class CrossParameterNodeAssert
            extends AbstractCrossParameterNodeAssert<CrossParameterNodeAssert, Path.CrossParameterNode, ElementKind> {

        public CrossParameterNodeAssert(final Path.CrossParameterNode actual) {
            super(actual, CrossParameterNodeAssert.class, new CrossParameterNodeAssertDelegateImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class MethodNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl<Path.MethodNode>
            implements MethodNodeAssertDelegate<Path.MethodNode, ElementKind> {

        @Override
        public List<Class<?>> getParameterTypes(final Path.MethodNode actual) {
            return actual.getParameterTypes();
        }
    }

    public static class MethodNodeAssert
            extends AbstractMethodNodeAssert<MethodNodeAssert, Path.MethodNode, ElementKind> {

        public MethodNodeAssert(final Path.MethodNode actual) {
            super(actual, MethodNodeAssert.class, new MethodNodeAssertDelegateImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class ParameterNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl<Path.ParameterNode>
            implements ParameterNodeAssertDelegate<Path.ParameterNode, ElementKind> {

        @Override
        public int getParameterIndex(final Path.ParameterNode actual) {
            return actual.getParameterIndex();
        }
    }

    public static class ParameterNodeAssert
            extends AbstractParameterNodeAssert<ParameterNodeAssert, Path.ParameterNode, ElementKind> {

        public ParameterNodeAssert(final Path.ParameterNode actual) {
            super(actual, ParameterNodeAssert.class, new ParameterNodeAssertDelegateImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class PropertyNodeDelegateImpl
            extends NodeAssertDelegateImpl<Path.PropertyNode>
            implements PropertyNodeAssertDelegate<Path.PropertyNode, ElementKind> {

        @Override
        public Class<?> getContainerClass(final Path.PropertyNode propertyNode) {
            return propertyNode.getContainerClass();
        }

        @Override
        public Integer getTypeArgumentIndex(final Path.PropertyNode propertyNode) {
            return propertyNode.getTypeArgumentIndex();
        }
    }

    public static class PropertyNodeAssert
            extends AbstractPropertyNodeAssert<PropertyNodeAssert, Path.PropertyNode, ElementKind> {

        public PropertyNodeAssert(final Path.PropertyNode actual) {
            super(actual, PropertyNodeAssert.class, new PropertyNodeDelegateImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class ReturnValueNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl<Path.ReturnValueNode>
            implements ReturnValueNodeAssertDelegate<Path.ReturnValueNode, ElementKind> {

    }

    public static class ReturnValueNodeAssert
            extends AbstractReturnValueNodeAssert<ReturnValueNodeAssert, Path.ReturnValueNode, ElementKind> {

        public ReturnValueNodeAssert(final Path.ReturnValueNode actual) {
            super(actual, ReturnValueNodeAssert.class, new ReturnValueNodeAssertDelegateImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public PathAssert(final Path actual) {
        super(actual);
    }
}