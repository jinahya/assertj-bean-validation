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
 * A class for wrapping {@code ....validation.Path}.
 *
 * @param <ACTUAL> the type of {@code ....validation.Path}.
 * @param <NODE>   the type of {@code ....validation.Path.Node}
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public final class PathWrapper<ACTUAL extends Iterable<NODE>, NODE>
        extends Wrapper<ACTUAL> {

    abstract static class AbstractNodeWrapper
            extends AbstractWrapper<Object> {

        protected AbstractNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class NodeWrapper
            extends AbstractNodeWrapper {

        public static NodeWrapper node(final Object actual) {
            return new NodeWrapper(actual);
        }

        private NodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class BeanNodeWrapper
            extends AbstractNodeWrapper {

        public static BeanNodeWrapper beanNode(final Object actual) {
            return new BeanNodeWrapper(actual);
        }

        private BeanNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class ConstructorNodeWrapper
            extends AbstractNodeWrapper {

        public static ConstructorNodeWrapper constructorNode(final Object actual) {
            return new ConstructorNodeWrapper(actual);
        }

        private ConstructorNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class ContainerElementNodeWrapper
            extends AbstractNodeWrapper {

        public static ContainerElementNodeWrapper containerElementNode(final Object actual) {
            return new ContainerElementNodeWrapper(actual);
        }

        private ContainerElementNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class CrossParameterNodeWrapper
            extends AbstractNodeWrapper {

        public static CrossParameterNodeWrapper crossParameterNode(final Object actual) {
            return new CrossParameterNodeWrapper(actual);
        }

        private CrossParameterNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class MethodNodeWrapper
            extends AbstractNodeWrapper {

        public static MethodNodeWrapper methodNode(final Object actual) {
            return new MethodNodeWrapper(actual);
        }

        private MethodNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class ParameterNodeWrapper
            extends AbstractNodeWrapper {

        public static ParameterNodeWrapper parameterNode(final Object actual) {
            return new ParameterNodeWrapper(actual);
        }

        private ParameterNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class PropertyNodeWrapper
            extends AbstractNodeWrapper {

        public static PropertyNodeWrapper propertyNode(final Object actual) {
            return new PropertyNodeWrapper(actual);
        }

        private PropertyNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    public static class ReturnValueNodeWrapper
            extends AbstractNodeWrapper {

        public static ReturnValueNodeWrapper returnValueNode(final Object actual) {
            return new ReturnValueNodeWrapper(actual);
        }

        private ReturnValueNodeWrapper(final Object actual) {
            super(actual);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <ACTUAL extends Iterable<NODE>, NODE> PathWrapper<ACTUAL, NODE> path(final ACTUAL actual) {
        return new PathWrapper<>(actual);
    }

    private PathWrapper(final ACTUAL actual) {
        super(actual);
    }
}
