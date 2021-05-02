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

import org.assertj.core.api.ClassBasedNavigableIterableAssert;

import java.util.List;

import static com.github.jinahya.assertj.validation.PathUtils.NodeUtils.requireNullOrInstanceOfNodeClass;
import static com.github.jinahya.assertj.validation.PathUtils.PropertyNodeUtils.requireNullOrInstanceOfPropertyNodeClass;
import static com.github.jinahya.assertj.validation.PathUtils.requireNullOrInstanceOfPathClass;

/**
 * An assertion class for verifying instances of {@code ....validation.Path}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119", "java:S125"})
public class PathAssert
        extends AbstractPathAssert<PathAssert, Object, Object> {

    // ------------------------------------------------------------------------------------------------------------ Node
    static class NodeAssertDelegateImpl
            implements AbstractNodeAccessor<Object, Object> {

        @Override
        public Integer getIndex(final Object actual) {
            return PathUtils.NodeUtils.getIndex(actual);
        }

        @Override
        public Object getKey(final Object actual) {
            return PathUtils.NodeUtils.getKey(actual);
        }

        @Override
        public Object getKind(final Object actual) {
            return PathUtils.NodeUtils.getKind(actual);
        }

        @Override
        public String getName(final Object actual) {
            return PathUtils.NodeUtils.getName(actual);
        }

        @Override
        public boolean isInIterable(final Object actual) {
            return PathUtils.NodeUtils.isInIterable(actual);
        }
    }

    public static class NodeAssert
            extends AbstractNodeAssert<NodeAssert, Object, Object> {

        public NodeAssert(final Object actual) {
            super(requireNullOrInstanceOfNodeClass(actual), NodeAssert.class, new NodeAssertDelegateImpl());
        }

        @Override
        public NodeAssert isBeanNode() {
            return isNotNull().isInstanceOf(PathUtils.BeanNodeUtils.BEAN_NODE_CLASS);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public BeanNodeAssert asBeanNode() {
            return new BeanNodeAssert(actual);
        }

        @Override
        public NodeAssert isConstructorNode() {
            return isNotNull().isInstanceOf(PathUtils.ConstructorNodeUtils.CONSTRUCTOR_NODE_CLASS);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public ConstructorNodeAssert asConstructorNode() {
            return new ConstructorNodeAssert(actual);
        }

        @Override
        public NodeAssert isCrossParameterNode() {
            return isNotNull().isInstanceOf(PathUtils.CrossParameterNodeUtils.CROSS_PARAMETER_NODE_CLASS);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public CrossParameterNodeAssert asCrossParameterNode() {
            return new CrossParameterNodeAssert(actual);
        }

        @Override
        public NodeAssert isMethodNode() {
            return isNotNull().isInstanceOf(PathUtils.MethodNodeUtils.METHOD_NODE_CLASS);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public MethodNodeAssert asMethodNode() {
            return new MethodNodeAssert(actual);
        }

        @Override
        public NodeAssert isParameterNode() {
            return isNotNull().isInstanceOf(PathUtils.ParameterNodeUtils.PARAMETER_NODE_CLASS);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public ParameterNodeAssert asParameterNode() {
            return new ParameterNodeAssert(actual);
        }

        @Override
        public NodeAssert isPropertyNode() {
            return isNotNull().isInstanceOf(PathUtils.PropertyNodeUtils.PROPERTY_NODE_CLASS);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public PropertyNodeAssert asPropertyNode() {
            return new PropertyNodeAssert(actual);
        }

        @Override
        public NodeAssert isReturnValueNode() {
            return isNotNull().isInstanceOf(PathUtils.ReturnValueNodeUtils.RETURN_VALUE_NODE_CLASS);
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public ReturnValueNodeAssert asReturnValueNode() {
            return new ReturnValueNodeAssert(actual);
        }
    }

    // -------------------------------------------------------------------------------------------------------- BeanNode
    static class BeanNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl
            implements BeanNodeAccessor<Object, Object> {

        @Override
        public Class<?> getContainerClass(final Object a) {
            return PathUtils.BeanNodeUtils.getContainerClass(a);
        }

        @Override
        public Integer getTypeArgumentIndex(Object a) {
            return PathUtils.BeanNodeUtils.getTypeArgumentIndex(a);
        }
    }

    public static class BeanNodeAssert
            extends AbstractBeanNodeAssert<BeanNodeAssert, Object, Object> {

        public BeanNodeAssert(Object actual) {
            super(PathUtils.BeanNodeUtils.requireNullOrInstanceOfBeanNodeClass(actual), BeanNodeAssert.class,
                  new BeanNodeAssertDelegateImpl());
        }
    }

    // ------------------------------------------------------------------------------------------------- ConstructorNode
    static class ConstructorNodeAccessorImpl
            extends NodeAssertDelegateImpl
            implements ConstructorNodeAccessor<Object, Object> {

        @Override
        public List<Class<?>> getParameterTypes(final Object a) {
            return PathUtils.ConstructorNodeUtils.getParameterTypes(a);
        }
    }

    public static class ConstructorNodeAssert
            extends AbstractConstructorNodeAssert<ConstructorNodeAssert, Object, Object> {

        public ConstructorNodeAssert(final Object actual) {
            super(PathUtils.ConstructorNodeUtils.requireNullOrInstanceOfConstructorNodeClass(actual),
                  ConstructorNodeAssert.class, new ConstructorNodeAccessorImpl());
        }
    }

    // -------------------------------------------------------------------------------------------- ContainerElementNode
    static class ContainerElementNodeAccessorImpl
            extends NodeAssertDelegateImpl
            implements ContainerElementNodeAccessor<Object, Object> {

        @Override
        public Class<?> getContainerClass(Object a) {
            return PathUtils.ContainerElementNodeUtils.getContainerClass(a);
        }

        @Override
        public Integer getTypeArgumentIndex(Object a) {
            return PathUtils.ContainerElementNodeUtils.getTypeArgumentIndex(a);
        }
    }

    public static class ContainerElementNodeAssert
            extends AbstractContainerElementNodeAssert<ContainerElementNodeAssert, Object, Object> {

        public ContainerElementNodeAssert(final Object actual) {
            super(PathUtils.ContainerElementNodeUtils.requireNullOrInstanceOfContainerElementNodeClass(actual),
                  ContainerElementNodeAssert.class, new ContainerElementNodeAccessorImpl());
        }
    }

    // ---------------------------------------------------------------------------------------------- CrossParameterNode
    static class CrossParameterNodeAccessorImpl
            extends NodeAssertDelegateImpl
            implements CrossParameterNodeAccessor<Object, Object> {

    }

    public static class CrossParameterNodeAssert
            extends AbstractCrossParameterNodeAssert<CrossParameterNodeAssert, Object, Object> {

        public CrossParameterNodeAssert(final Object actual) {
            super(PathUtils.CrossParameterNodeUtils.requireNullOrInstanceOfCrossParameterNodeClass(actual),
                  CrossParameterNodeAssert.class, new CrossParameterNodeAccessorImpl());
        }
    }

    // ------------------------------------------------------------------------------------------------------ MethodNode
    static class MethodNodeAccessorImpl
            extends NodeAssertDelegateImpl
            implements MethodNodeAccessor<Object, Object> {

        @Override
        public List<Class<?>> getParameterTypes(final Object a) {
            return PathUtils.MethodNodeUtils.getParameterTypes(a);
        }
    }

    public static class MethodNodeAssert
            extends AbstractMethodNodeAssert<MethodNodeAssert, Object, Object> {

        public MethodNodeAssert(final Object actual) {
            super(PathUtils.MethodNodeUtils.requireNullOrInstanceOfMethodNodeClass(actual), MethodNodeAssert.class,
                  new MethodNodeAccessorImpl());
        }
    }

    // --------------------------------------------------------------------------------------------------- ParameterNode
    static class ParameterNodeAccessorImpl
            extends NodeAssertDelegateImpl
            implements ParameterNodeAccessor<Object, Object> {

        @Override
        public int getParameterIndex(Object a) {
            return PathUtils.ParameterNodeUtils.getParameterIndex(a);
        }
    }

    public static class ParameterNodeAssert
            extends AbstractParameterNodeAssert<ParameterNodeAssert, Object, Object> {

        public ParameterNodeAssert(final Object actual) {
            super(PathUtils.ParameterNodeUtils.requireNullOrInstanceOfParameterNodeClass(actual),
                  ParameterNodeAssert.class, new ParameterNodeAccessorImpl());
        }
    }

    // ---------------------------------------------------------------------------------------------------- PropertyNode
    static class PropertyNodeAccessorImpl
            extends NodeAssertDelegateImpl
            implements PropertyNodeAccessor<Object, Object> {

        @Override
        public Class<?> getContainerClass(final Object a) {
            return PathUtils.PropertyNodeUtils.getContainerClass(a);
        }

        @Override
        public Integer getTypeArgumentIndex(Object a) {
            return PathUtils.PropertyNodeUtils.getTypeArgumentIndex(a);
        }
    }

    public static class PropertyNodeAssert
            extends AbstractPropertyNodeAssert<PropertyNodeAssert, Object, Object> {

        public PropertyNodeAssert(Object actual) {
            super(requireNullOrInstanceOfPropertyNodeClass(actual), PropertyNodeAssert.class,
                  new PropertyNodeAccessorImpl());
        }
    }

    // ------------------------------------------------------------------------------------------------- ReturnValueNode
    static class ReturnValueNodeAccessorImpl
            extends NodeAssertDelegateImpl
            implements ReturnValueNodeAccessor<Object, Object> {

    }

    public static class ReturnValueNodeAssert
            extends AbstractReturnValueNodeAssert<ReturnValueNodeAssert, Object, Object> {

        public ReturnValueNodeAssert(final Object actual) {
            super(PathUtils.ReturnValueNodeUtils.requireNullOrInstanceOfReturnValueNodeClass(actual),
                  ReturnValueNodeAssert.class, new ReturnValueNodeAccessorImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    // should be public for accessing methods defined AbstractIterableAssert
    // say, asIterable().hasSize(1)
    public static class PathAsIterableNodeAssert
            extends ClassBasedNavigableIterableAssert<PathAsIterableNodeAssert, Iterable<Object>, Object, NodeAssert> {

        PathAsIterableNodeAssert(final Iterable<Object> path) {
            super(path, PathAsIterableNodeAssert.class, NodeAssert.class);
        }
    }

    /**
     * Creates a new instance with specified value.
     *
     * @param actual the value to verify.
     */
    public PathAssert(final Object actual) {
        super(requireNullOrInstanceOfPathClass(actual), PathAssert.class);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public PathAsIterableNodeAssert asIterable() {
        return new PathAsIterableNodeAssert((Iterable<Object>) actual);
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
