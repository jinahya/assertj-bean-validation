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
 * @param <PATH> the type of {@code ....validation.Path}.
 * @param <NODE> the type of {@code ....validation.Path.Node}
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public final class PathWrapper<PATH extends Iterable<NODE>, NODE>
        extends Wrapper<PATH> {

    abstract static class AbstractNodeWrapper<T> extends Wrapper<T> {

        protected AbstractNodeWrapper(final T actual) {
            super(actual);
        }
    }

    public static class NodeWrapper<T> extends AbstractNodeWrapper<T> {

        public static <T> NodeWrapper<T> node(final T actual) {
            return new NodeWrapper<>(actual);
        }

        private NodeWrapper(final T actual) {
            super(actual);
        }
    }

    public static class BeanNodeWrapper<T> extends NodeWrapper<T> {

        public static <T> BeanNodeWrapper<T> beanNode(final T actual) {
            return new BeanNodeWrapper<>(actual);
        }

        private BeanNodeWrapper(final T actual) {
            super(actual);
        }
    }

    public static class ConstructorNodeWrapper<T> extends NodeWrapper<T> {

        public static <T> ConstructorNodeWrapper<T> constructorNode(final T actual) {
            return new ConstructorNodeWrapper<>(actual);
        }

        private ConstructorNodeWrapper(final T actual) {
            super(actual);
        }
    }

    public static class ContainerElementNodeWrapper<T> extends NodeWrapper<T> {

        public static <T> ContainerElementNodeWrapper<T> containerElementNode(final T actual) {
            return new ContainerElementNodeWrapper<>(actual);
        }

        private ContainerElementNodeWrapper(final T actual) {
            super(actual);
        }
    }

    public static class CrossParameterNodeWrapper<T> extends NodeWrapper<T> {

        public static <T> CrossParameterNodeWrapper<T> crossParameterNode(final T actual) {
            return new CrossParameterNodeWrapper<>(actual);
        }

        private CrossParameterNodeWrapper(final T actual) {
            super(actual);
        }
    }

    public static class MethodNodeWrapper<T> extends NodeWrapper<T> {

        public static <T> MethodNodeWrapper<T> methodNode(final T actual) {
            return new MethodNodeWrapper<>(actual);
        }

        private MethodNodeWrapper(final T actual) {
            super(actual);
        }
    }

    public static class ParameterNodeWrapper<T> extends NodeWrapper<T> {

        public static <T> ParameterNodeWrapper<T> parameterNode(final T actual) {
            return new ParameterNodeWrapper<>(actual);
        }

        private ParameterNodeWrapper(final T actual) {
            super(actual);
        }
    }

    public static class PropertyNodeWrapper<T> extends NodeWrapper<T> {

        public static <T> PropertyNodeWrapper<T> propertyNode(final T actual) {
            return new PropertyNodeWrapper<>(actual);
        }

        private PropertyNodeWrapper(final T actual) {
            super(actual);
        }
    }

    public static class ReturnValueNodeWrapper<T> extends NodeWrapper<T> {

        public static <T> ReturnValueNodeWrapper<T> returnValueNode(final T actual) {
            return new ReturnValueNodeWrapper<>(actual);
        }

        private ReturnValueNodeWrapper(final T actual) {
            super(actual);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <PATH extends Iterable<NODE>, NODE> PathWrapper<PATH, NODE> path(final PATH actual) {
        return new PathWrapper<>(actual);
    }

    private PathWrapper(final PATH actual) {
        super(actual);
    }
}
