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

/**
 * A class for wrapping {@code ja....validation.Path}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public class PathWrapper
        extends AbstractPathWrapper<Object> {

    public static class NodeWrapper
            extends AbstractPathWrapper.AbstractNodeWrapper<Object> {

        public static NodeWrapper node(final Object actual) {
            return new NodeWrapper(actual);
        }

        private NodeWrapper(final Object wrapped) {
            super(wrapped);
        }
    }

    public static class BeanNodeWrapper
            extends AbstractPathWrapper.AbstractBeanNodeWrapper<Object> {

        public static BeanNodeWrapper beanNode(final Object actual) {
            return new BeanNodeWrapper(actual);
        }

        private BeanNodeWrapper(final Object wrapped) {
            super(wrapped);
        }
    }

    public static class ConstructorNodeWrapper
            extends AbstractPathWrapper.AbstractConstructorNodeWrapper<Object> {

        public static ConstructorNodeWrapper constructorNode(final Object actual) {
            return new ConstructorNodeWrapper(actual);
        }

        private ConstructorNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class ContainerElementNodeWrapper
            extends AbstractPathWrapper.AbstractContainerElementNodeWrapper<Object> {

        public static ContainerElementNodeWrapper containerElementNode(final Object actual) {
            return new ContainerElementNodeWrapper(actual);
        }

        private ContainerElementNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class CrossParameterNodeWrapper
            extends AbstractPathWrapper.AbstractCrossParameterNodeWrapper<Object> {

        public static CrossParameterNodeWrapper crossParameterNode(final Object actual) {
            return new CrossParameterNodeWrapper(actual);
        }

        private CrossParameterNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class MethodNodeWrapper
            extends AbstractPathWrapper.AbstractMethodNodeWrapper<Object> {

        public static MethodNodeWrapper methodNode(final Object actual) {
            return new MethodNodeWrapper(actual);
        }

        private MethodNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class ParameterNodeWrapper
            extends AbstractPathWrapper.AbstractNodeWrapper<Object> {

        public static ParameterNodeWrapper parameterNode(final Object actual) {
            return new ParameterNodeWrapper(actual);
        }

        private ParameterNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class PropertyNodeWrapper
            extends AbstractPathWrapper.AbstractPropertyNodeWrapper<Object> {

        public static PropertyNodeWrapper propertyNode(final Object actual) {
            return new PropertyNodeWrapper(actual);
        }

        private PropertyNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class ReturnValueNodeWrapper
            extends AbstractPathWrapper.AbstractReturnValueNodeWrapper<Object> {

        public static ReturnValueNodeWrapper returnValueNode(final Object actual) {
            return new ReturnValueNodeWrapper(actual);
        }

        private ReturnValueNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static PathWrapper path(final Object actual) {
        return new PathWrapper(actual);
    }

    private PathWrapper(final Object actual) {
        super(actual);
    }
}
