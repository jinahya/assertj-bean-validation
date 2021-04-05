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

    abstract static class AbstractNodeWrapper<ACTUAL> extends Wrapper<ACTUAL> {

        protected AbstractNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public static class NodeWrapper<ACTUAL> extends AbstractNodeWrapper<ACTUAL> {

        public static <ACTUAL> NodeWrapper<ACTUAL> node(final ACTUAL actual) {
            return new NodeWrapper<>(actual);
        }

        private NodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public static class BeanNodeWrapper<ACTUAL> extends AbstractNodeWrapper<ACTUAL> {

        public static <ACTUAL> BeanNodeWrapper<ACTUAL> beanNode(final ACTUAL actual) {
            return new BeanNodeWrapper<>(actual);
        }

        private BeanNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public static class ConstructorNodeWrapper<ACTUAL> extends AbstractNodeWrapper<ACTUAL> {

        public static <T> ConstructorNodeWrapper<T> constructorNode(final T actual) {
            return new ConstructorNodeWrapper<>(actual);
        }

        private ConstructorNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public static class ContainerElementNodeWrapper<ACTUAL> extends AbstractNodeWrapper<ACTUAL> {

        public static <ACTUAL> ContainerElementNodeWrapper<ACTUAL> containerElementNode(final ACTUAL actual) {
            return new ContainerElementNodeWrapper<>(actual);
        }

        private ContainerElementNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public static class CrossParameterNodeWrapper<ACTUAL> extends AbstractNodeWrapper<ACTUAL> {

        public static <ACTUAL> CrossParameterNodeWrapper<ACTUAL> crossParameterNode(final ACTUAL actual) {
            return new CrossParameterNodeWrapper<>(actual);
        }

        private CrossParameterNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public static class MethodNodeWrapper<ACTUAL> extends AbstractNodeWrapper<ACTUAL> {

        public static <ACTUAL> MethodNodeWrapper<ACTUAL> methodNode(final ACTUAL actual) {
            return new MethodNodeWrapper<>(actual);
        }

        private MethodNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public static class ParameterNodeWrapper<ACTUAL> extends AbstractNodeWrapper<ACTUAL> {

        public static <ACTUAL> ParameterNodeWrapper<ACTUAL> parameterNode(final ACTUAL actual) {
            return new ParameterNodeWrapper<>(actual);
        }

        private ParameterNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public static class PropertyNodeWrapper<ACTUAL> extends AbstractNodeWrapper<ACTUAL> {

        public static <ACTUAL> PropertyNodeWrapper<ACTUAL> propertyNode(final ACTUAL actual) {
            return new PropertyNodeWrapper<>(actual);
        }

        private PropertyNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public static class ReturnValueNodeWrapper<ACTUAL> extends AbstractNodeWrapper<ACTUAL> {

        public static <ACTUAL> ReturnValueNodeWrapper<ACTUAL> returnValueNode(final ACTUAL actual) {
            return new ReturnValueNodeWrapper<>(actual);
        }

        private ReturnValueNodeWrapper(final ACTUAL actual) {
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
