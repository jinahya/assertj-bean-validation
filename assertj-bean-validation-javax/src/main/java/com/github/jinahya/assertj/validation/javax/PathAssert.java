package com.github.jinahya.assertj.validation.javax;

import com.github.jinahya.assertj.validation.AbstractPathAssert;
import org.assertj.core.api.FactoryBasedNavigableIterableAssert;

import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.List;

/**
 * An assertion class for verifying instances of {@link Path}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class PathAssert
        extends AbstractPathAssert<PathAssert, Path, Path.Node> {

    // -----------------------------------------------------------------------------------------------------------------
    static class NodeAssertAccessorImpl<NODE extends Path.Node>
            implements AbstractNodeAccessor<NODE, ElementKind> {

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
            extends AbstractNodeAssert<NodeAssert, Path.Node, ElementKind> {

        public NodeAssert(final Path.Node actual) {
            super(actual, NodeAssert.class, new NodeAssertAccessorImpl<>());
        }

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

        @Override
        public NodeAssert isConstructorNode() {
            return isInstanceOf(Path.ConstructorNode.class);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public ConstructorNodeAssert asConstructorNode() {
            isConstructorNode();
            return new ConstructorNodeAssert((Path.ConstructorNode) actual);
        }

        @Override
        public NodeAssert isCrossParameterNode() {
            return isInstanceOf(Path.CrossParameterNode.class);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public CrossParameterNodeAssert asCrossParameterNode() {
            isCrossParameterNode();
            return new CrossParameterNodeAssert((Path.CrossParameterNode) actual);
        }

        @Override
        public NodeAssert isMethodNode() {
            return isInstanceOf(Path.MethodNode.class);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public MethodNodeAssert asMethodNode() {
            isMethodNode();
            return new MethodNodeAssert((Path.MethodNode) actual);
        }

        @Override
        public NodeAssert isParameterNode() {
            return isInstanceOf(Path.ParameterNode.class);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public ParameterNodeAssert asParameterNode() {
            isParameterNode();
            return new ParameterNodeAssert((Path.ParameterNode) actual);
        }

        @Override
        public NodeAssert isPropertyNode() {
            return isInstanceOf(Path.PropertyNode.class);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public PropertyNodeAssert asPropertyNode() {
            isPropertyNode();
            return new PropertyNodeAssert((Path.PropertyNode) actual);
        }

        @Override
        public NodeAssert isReturnValueNode() {
            return isInstanceOf(Path.ReturnValueNode.class);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public ReturnValueNodeAssert asReturnValueNode() {
            isReturnValueNode();
            return new ReturnValueNodeAssert((Path.ReturnValueNode) actual);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class BeanNodeAccessorImpl
            extends NodeAssertAccessorImpl<Path.BeanNode>
            implements BeanNodeAccessor<Path.BeanNode, ElementKind> {

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
            super(actual, BeanNodeAssert.class, new BeanNodeAccessorImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class ConstructorNodeAccessorImpl
            extends NodeAssertAccessorImpl<Path.ConstructorNode>
            implements ConstructorNodeAccessor<Path.ConstructorNode, ElementKind> {

        @Override
        public List<Class<?>> getParameterTypes(final Path.ConstructorNode constructorNode) {
            return constructorNode.getParameterTypes();
        }
    }

    public static class ConstructorNodeAssert
            extends AbstractConstructorNodeAssert<ConstructorNodeAssert, Path.ConstructorNode, ElementKind> {

        public ConstructorNodeAssert(final Path.ConstructorNode actual) {
            super(actual, ConstructorNodeAssert.class, new ConstructorNodeAccessorImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class ContainerElementNodeAccessorImpl
            extends NodeAssertAccessorImpl<Path.ContainerElementNode>
            implements ContainerElementNodeAccessor<Path.ContainerElementNode, ElementKind> {

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
            super(actual, ContainerElementNodeAssert.class, new ContainerElementNodeAccessorImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class CrossParameterNodeAccessorImpl
            extends NodeAssertAccessorImpl<Path.CrossParameterNode>
            implements CrossParameterNodeAccessor<Path.CrossParameterNode, ElementKind> {

    }

    public static class CrossParameterNodeAssert
            extends AbstractCrossParameterNodeAssert<CrossParameterNodeAssert, Path.CrossParameterNode, ElementKind> {

        public CrossParameterNodeAssert(final Path.CrossParameterNode actual) {
            super(actual, CrossParameterNodeAssert.class, new CrossParameterNodeAccessorImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class MethodNodeAccessorImpl
            extends NodeAssertAccessorImpl<Path.MethodNode>
            implements MethodNodeAccessor<Path.MethodNode, ElementKind> {

        @Override
        public List<Class<?>> getParameterTypes(final Path.MethodNode actual) {
            return actual.getParameterTypes();
        }
    }

    public static class MethodNodeAssert
            extends AbstractMethodNodeAssert<MethodNodeAssert, Path.MethodNode, ElementKind> {

        public MethodNodeAssert(final Path.MethodNode actual) {
            super(actual, MethodNodeAssert.class, new MethodNodeAccessorImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class ParameterNodeAccessorImpl
            extends NodeAssertAccessorImpl<Path.ParameterNode>
            implements ParameterNodeAccessor<Path.ParameterNode, ElementKind> {

        @Override
        public int getParameterIndex(final Path.ParameterNode actual) {
            return actual.getParameterIndex();
        }
    }

    public static class ParameterNodeAssert
            extends AbstractParameterNodeAssert<ParameterNodeAssert, Path.ParameterNode, ElementKind> {

        public ParameterNodeAssert(final Path.ParameterNode actual) {
            super(actual, ParameterNodeAssert.class, new ParameterNodeAccessorImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class PropertyNodeAccessorImpl
            extends NodeAssertAccessorImpl<Path.PropertyNode>
            implements PropertyNodeAccessor<Path.PropertyNode, ElementKind> {

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
            super(actual, PropertyNodeAssert.class, new PropertyNodeAccessorImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    static class ReturnValueNodeAccessorImpl
            extends NodeAssertAccessorImpl<Path.ReturnValueNode>
            implements ReturnValueNodeAccessor<Path.ReturnValueNode, ElementKind> {

    }

    public static class ReturnValueNodeAssert
            extends AbstractReturnValueNodeAssert<ReturnValueNodeAssert, Path.ReturnValueNode, ElementKind> {

        public ReturnValueNodeAssert(final Path.ReturnValueNode actual) {
            super(actual, ReturnValueNodeAssert.class, new ReturnValueNodeAccessorImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // should be public for accessing methods defined AbstractIterableAssert
    // say, asIterable().hasSize(1)
    public static class PathAsIterableNodeAssert
            extends FactoryBasedNavigableIterableAssert<PathAsIterableNodeAssert, Path, Path.Node, NodeAssert> {

        PathAsIterableNodeAssert(final Path path) {
            super(path, PathAsIterableNodeAssert.class, NodeAssert::new);
        }
    }

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to verify.
     */
    public PathAssert(final Path actual) {
        super(actual, PathAssert.class);
    }

    @Override
    public PathAsIterableNodeAssert asIterable() {
        isNotNull();
        return new PathAsIterableNodeAssert(actual);
    }

    @Override
    public NodeAssert node(final int index) {
        return (NodeAssert) super.node(index);
    }

    @Override
    public BeanNodeAssert beanNode(final int index) {
        return (BeanNodeAssert) super.beanNode(index);
    }

    @Override
    public ConstructorNodeAssert constructorNode(final int index) {
        return (ConstructorNodeAssert) super.constructorNode(index);
    }

    @Override
    public CrossParameterNodeAssert crossParameterNode(final int index) {
        return (CrossParameterNodeAssert) super.crossParameterNode(index);
    }

    @Override
    public MethodNodeAssert methodNode(int index) {
        return (MethodNodeAssert) super.methodNode(index);
    }

    @Override
    public ParameterNodeAssert parameterNode(int index) {
        return (ParameterNodeAssert) super.parameterNode(index);
    }

    @Override
    public PropertyNodeAssert propertyNode(int index) {
        return (PropertyNodeAssert) super.propertyNode(index);
    }

    @Override
    public ReturnValueNodeAssert returnValueNode(int index) {
        return (ReturnValueNodeAssert) super.returnValueNode(index);
    }
}
