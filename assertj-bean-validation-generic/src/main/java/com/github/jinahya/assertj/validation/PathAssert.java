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

import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for verifying instances of {@code ....validation.Path}.
 *
 * @param <ACTUAL> the type of {@code ....validation.Path}.
 * @param <NODE>   the type of {@code ....validation.Path.Node}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119", "java:S125"})
public class PathAssert<ACTUAL extends Iterable<NODE>, NODE>
        extends AbstractPathAssert<ACTUAL, NODE> {

    // ------------------------------------------------------------------------------------------------------------ Node
    static class NodeAssertDelegateImpl
            implements AbstractNodeAssertDelegate<Object, Object> {

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
            super(PathUtils.NodeUtils.requireNullOrNodeInstance(actual), NodeAssert.class, new NodeAssertDelegateImpl());
        }

        // ---------------------------------------------------------------------------------------------------- BeanNode
        @Override
        public NodeAssert isBeanNode() {
            return isNotNull().satisfies(a -> {
                assertThat(PathUtils.BeanNodeUtils.isNullOrBeanNodeInstance(a)).isTrue();
            });
        }

        @SuppressWarnings({"unchecked"})
        @Override
        public BeanNodeAssert asBeanNode() {
            return new BeanNodeAssert(actual);
        }
    }

    // -------------------------------------------------------------------------------------------------------- BeanNode
    static class BeanNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl
            implements BeanNodeAssertDelegate<Object, Object> {

        @Override
        public Class<?> getContainerClass(final Object actual) {
            return PathUtils.BeanNodeUtils.getContainerClass(actual);
        }

        @Override
        public Integer getTypeArgumentIndex(Object actual) {
            return PathUtils.BeanNodeUtils.getTypeArgumentIndex(actual);
        }
    }

    public static class BeanNodeAssert
            extends AbstractBeanNodeAssert<BeanNodeAssert, Object, Object> {

        public BeanNodeAssert(Object actual) {
            super(PathUtils.BeanNodeUtils.requireNullOrBeanNodeInstance(actual), BeanNodeAssert.class,
                  new BeanNodeAssertDelegateImpl());
        }
    }

    // ------------------------------------------------------------------------------------------------- ConstructorNode
    static class ConstructorNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl
            implements ConstructorNodeAssertDelegate<Object, Object> {

        @Override
        public List<Class<?>> getParameterTypes(final Object actual) {
            return PathUtils.ConstructorNodeUtils.getParameterTypes(actual);
        }
    }

    public static class ConstructorNodeAssert
            extends AbstractConstructorNodeAssert<ConstructorNodeAssert, Object, Object> {

        public ConstructorNodeAssert(final Object actual) {
            super(PathUtils.ConstructorNodeUtils.requireNullOrInstanceOfConstructorNodeClass(actual),
                  ConstructorNodeAssert.class, new ConstructorNodeAssertDelegateImpl());
        }
    }

    // -------------------------------------------------------------------------------------------- ContainerElementNode
    static class ContainerElementNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl
            implements ContainerElementNodeAssertDelegate<Object, Object> {

        @Override
        public Class<?> getContainerClass(Object actual) {
            return PathUtils.ContainerElementNodeUtils.getContainerClass(actual);
        }

        @Override
        public Integer getTypeArgumentIndex(Object actual) {
            return PathUtils.ContainerElementNodeUtils.getTypeArgumentIndex(actual);
        }
    }

    public static class ContainerElementNodeAssert
            extends AbstractContainerElementNodeAssert<ContainerElementNodeAssert, Object, Object> {

        public ContainerElementNodeAssert(final Object actual) {
            super(PathUtils.ContainerElementNodeUtils.requireNullOrInstanceOfContainerElementNodeClass(actual),
                  ContainerElementNodeAssert.class, new ContainerElementNodeAssertDelegateImpl());
        }
    }

    // ---------------------------------------------------------------------------------------------- CrossParameterNode
    static class CrossParameterNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl
            implements CrossParameterNodeAssertDelegate<Object, Object> {

    }

    public static class CrossParameterNodeAssert
            extends AbstractCrossParameterNodeAssert<CrossParameterNodeAssert, Object, Object> {

        public CrossParameterNodeAssert(final Object actual) {
            super(PathUtils.CrossParameterNodeUtils.requireNullOrInstanceOfCrossParameterNodeClass(actual),
                  CrossParameterNodeAssert.class, new CrossParameterNodeAssertDelegateImpl());
        }
    }

    // ------------------------------------------------------------------------------------------------------ MethodNode
    static class MethodNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl
            implements MethodNodeAssertDelegate<Object, Object> {

        @Override
        public List<Class<?>> getParameterTypes(final Object actual) {
            return PathUtils.MethodNodeUtils.getParameterTypes(actual);
        }
    }

    public static class MethodNodeAssert
            extends AbstractMethodNodeAssert<MethodNodeAssert, Object, Object> {

        public MethodNodeAssert(final Object actual) {
            super(PathUtils.MethodNodeUtils.requireNullOrInstanceOfMethodNodeClass(actual), MethodNodeAssert.class,
                  new MethodNodeAssertDelegateImpl());
        }
    }

    // --------------------------------------------------------------------------------------------------- ParameterNode
    static class ParameterNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl
            implements ParameterNodeAssertDelegate<Object, Object> {

        @Override
        public int getParameterIndex(Object actual) {
            return PathUtils.ParameterNodeUtils.getParameterIndex(actual);
        }
    }

    public static class ParameterNodeAssert
            extends AbstractParameterNodeAssert<ParameterNodeAssert, Object, Object> {

        public ParameterNodeAssert(final Object actual) {
            super(PathUtils.ParameterNodeUtils.requireNullOrInstanceOfParameterNodeClass(actual),
                  ParameterNodeAssert.class, new ParameterNodeAssertDelegateImpl());
        }
    }

    // ---------------------------------------------------------------------------------------------------- PropertyNode
    static class PropertyNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl
            implements PropertyNodeAssertDelegate<Object, Object> {

        @Override
        public Class<?> getContainerClass(final Object actual) {
            return PathUtils.PropertyNodeUtils.getContainerClass(actual);
        }

        @Override
        public Integer getTypeArgumentIndex(Object actual) {
            return PathUtils.PropertyNodeUtils.getTypeArgumentIndex(actual);
        }
    }

    public static class PropertyNodeAssert
            extends AbstractPropertyNodeAssert<PropertyNodeAssert, Object, Object> {

        public PropertyNodeAssert(Object actual) {
            super(actual, PropertyNodeAssert.class, new PropertyNodeAssertDelegateImpl());
        }
    }

    // ------------------------------------------------------------------------------------------------- ReturnValueNode
    static class ReturnValueNodeAssertDelegateImpl
            extends NodeAssertDelegateImpl
            implements ReturnValueNodeAssertDelegate<Object, Object> {

    }

    public static class ReturnValueNodeAssert
            extends AbstractReturnValueNodeAssert<ReturnValueNodeAssert, Object, Object> {

        public ReturnValueNodeAssert(final Object actual) {
            super(PathUtils.ReturnValueNodeUtils.requireNullOrInstanceOfReturnValueNodeClass(actual),
                  ReturnValueNodeAssert.class, new ReturnValueNodeAssertDelegateImpl());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * Creates a new instance with specified value.
     *
     * @param actual the value to verify.
     */
    public PathAssert(final ACTUAL actual) {
        super(PathUtils.requireNullOrInstanceOfPathClass(actual));
    }
}
