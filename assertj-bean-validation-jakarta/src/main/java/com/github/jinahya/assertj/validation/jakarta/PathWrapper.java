package com.github.jinahya.assertj.validation.jakarta;

/*-
 * #%L
 * assertj-bean-validation-jakarta
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

    /**
     * Creates a new instance wraps specified actual value.
     *
     * @param actual the actual value to wrap.
     * @return a new instance wraps {@code actual}.
     */
    public static PathWrapper path(final Path actual) {
        return new PathWrapper(actual);
    }

    private PathWrapper(final Path actual) {
        super(actual);
    }
}
