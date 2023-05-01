package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation-javax
 * %%
 * Copyright (C) 2021 - 2022 Jinahya, Inc.
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

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.AbstractBooleanAssert;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ObjectAssertFactory;

import javax.validation.Path;

/**
 * An abstract class for verifying {@link Path} values.
 *
 * @param <SELF> self type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S119" // <SELF>, <ACTUAL>
})
abstract class AbstractPathAssert<SELF extends AbstractPathAssert<SELF, NODE_ASSERT>, NODE_ASSERT extends AbstractAssert<NODE_ASSERT, Path.Node>>
        extends AbstractIterableAssert<SELF, Path, Path.Node, NODE_ASSERT>
        implements PathAssert<SELF> {

    public abstract static class AbstractNodeAssert<
            SELF extends AbstractNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.Node>
            extends AbstractObjectAssert<SELF, ACTUAL>
            implements PathAssert.NodeAssert<SELF, ACTUAL> {

        static class NodeAssert
                extends AbstractNodeAssert<NodeAssert, Path.Node> {

            NodeAssert(final Path.Node actual, Class<?> selfType) {
                super(actual, selfType);
            }

            NodeAssert(final Path.Node actual) {
                super(actual, NodeAssert.class);
            }
        }

        AbstractNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        @Override
        public <NODE extends Path.Node, ASSERT extends PathAssert.NodeAssert<?, NODE>> ASSERT extractingAs(
                final Class<NODE> nodeType,
                final AssertFactory<? super NODE, ? extends ASSERT> assertFactory) {
            // TODO: implement!
            return null;
        }

        @Override
        public AbstractIntegerAssert<?> extractingIndex() {
            return extracting(Path.Node::getIndex, InstanceOfAssertFactories.INTEGER);
        }

        @Override
        public AbstractObjectAssert<?, Object> extractingKey() {
            return extracting(Path.Node::getIndex, new ObjectAssertFactory<>());
        }

        @Override
        public AbstractStringAssert<?> extractingName() {
            return extracting(Path.Node::getName, InstanceOfAssertFactories.STRING);
        }

        @Override
        public AbstractBooleanAssert<?> extractingInIterable() {
            return extracting(Path.Node::getName, InstanceOfAssertFactories.BOOLEAN);
        }
    }

    static class AbstractParameterizedNodeAssert<
            SELF extends AbstractParameterizedNodeAssert<SELF, ACTUAL>,
            ACTUAL extends Path.ParameterNode>
            extends AbstractNodeAssert<SELF, ACTUAL> {

        static class DefaultParameterizedNodeAssert
                extends AbstractParameterizedNodeAssert<DefaultParameterizedNodeAssert, Path.ParameterNode> {

            DefaultParameterizedNodeAssert(final Path.ParameterNode actual, final Class<?> selfType) {
                super(actual, selfType);
            }
        }

        AbstractParameterizedNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    abstract static class AbstractBeanNodeAssert<
            SELF extends AbstractBeanNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.BeanNode>
            extends AbstractNodeAssert<SELF, ACTUAL> {

        static class DefaultBeanNodeAssert
                extends AbstractBeanNodeAssert<DefaultBeanNodeAssert, Path.BeanNode> {

            DefaultBeanNodeAssert(final Path.BeanNode actual) {
                super(actual, DefaultBeanNodeAssert.class);
            }
        }

        AbstractBeanNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }

//        abstract AbstractClassAssert<?> extractingContainerClass();
    }

    abstract static class AbstractConstructorNodeAssert<
            SELF extends AbstractConstructorNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.ConstructorNode>
            extends AbstractNodeAssert<SELF, ACTUAL>
            implements ConstructorNodeAssert<SELF, ACTUAL> {

        static class DefaultConstructorNodeAssert
                extends AbstractConstructorNodeAssert<DefaultConstructorNodeAssert, Path.ConstructorNode> {

            DefaultConstructorNodeAssert(final Path.ConstructorNode actual) {
                super(actual, DefaultConstructorNodeAssert.class);
            }
        }

        AbstractConstructorNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        @Override
        public AbstractIterableAssert<?, ?, ? super Class<?>, ?> extractingParameterTypes4() {
            return extracting(
                    Path.ConstructorNode::getParameterTypes, InstanceOfAssertFactories.list(Class.class)
            );
        }

        @Override
        public ListAssert<? super Class<?>> extractingParameterTypes5() {
            return extracting(
                    Path.ConstructorNode::getParameterTypes, InstanceOfAssertFactories.list(Class.class)
            );
        }
    }

    public abstract static class AbstractCrossParameterNodeAssert<
            SELF extends AbstractCrossParameterNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.CrossParameterNode>
            extends AbstractNodeAssert<SELF, ACTUAL> {

        static class DefaultCrossParameterNodeAssert
                extends AbstractCrossParameterNodeAssert<DefaultCrossParameterNodeAssert, Path.CrossParameterNode> {

            DefaultCrossParameterNodeAssert(final Path.CrossParameterNode actual) {
                super(actual, DefaultCrossParameterNodeAssert.class);
            }
        }

        AbstractCrossParameterNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    public abstract static class AbstractMethodNodeAssert<
            SELF extends AbstractMethodNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.MethodNode>
            extends AbstractNodeAssert<SELF, ACTUAL> {

        static class DefaultMethodNodeAssert
                extends AbstractMethodNodeAssert<DefaultMethodNodeAssert, Path.MethodNode> {

            DefaultMethodNodeAssert(final Path.MethodNode actual) {
                super(actual, DefaultMethodNodeAssert.class);
            }
        }

        protected AbstractMethodNodeAssert(final ACTUAL actual, Class<?> selfType) {
            super(actual, selfType);
        }

        @SuppressWarnings({"unchecked"})
        public ListAssert<Class<?>> extractingParameterTypes() {
            return (ListAssert<Class<?>>) (Object) isNotNull()
                    .extracting(v -> ((Path.MethodNode) v).getParameterTypes(), Assertions::assertThat);
        }
    }

    public abstract static class AbstractParameterNodeAssert<
            SELF extends AbstractParameterNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.ParameterNode>
            extends AbstractNodeAssert<SELF, ACTUAL> {

        public static class DefaultParameterNodeAssert
                extends AbstractParameterNodeAssert<DefaultParameterNodeAssert, Path.ParameterNode> {

            DefaultParameterNodeAssert(final Path.ParameterNode actual) {
                super(actual, DefaultParameterNodeAssert.class);
            }
        }

        protected AbstractParameterNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        public AbstractIntegerAssert<?> extractingParameterIndex() {
            return isNotNull()
                    .extracting(
                            v -> ((Path.ParameterNode) v).getParameterIndex(),
                            InstanceOfAssertFactories.INTEGER
                    );
        }

        /**
         * Asserts that the {@link Path.ParameterNode#getParameterIndex() actual.parameterIndex} is equal to specified
         * value.
         *
         * @param expectedParameterIndex the expected value of
         *                               {@link Path.ParameterNode#getParameterIndex() actual.parameterIndex}.
         * @return this assertion object.
         */
        public SELF hasParameterIndex(final int expectedParameterIndex) {
            extractingParameterIndex().isEqualTo(expectedParameterIndex);
            @SuppressWarnings({"unchecked"})
            final SELF self = (SELF) myself;
            return self;
        }
    }

    public abstract static class AbstractPropertyNodeAssert<
            SELF extends AbstractPropertyNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.PropertyNode>
            extends AbstractNodeAssert<SELF, ACTUAL> {

        public static class DefaultPropertyNodeAssert
                extends AbstractPropertyNodeAssert<DefaultPropertyNodeAssert, Path.PropertyNode> {

            public DefaultPropertyNodeAssert(final Path.PropertyNode actual) {
                super(actual, DefaultPropertyNodeAssert.class);
            }
        }

        protected AbstractPropertyNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    public abstract static class AbstractReturnValueNodeAssert<
            SELF extends AbstractReturnValueNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.ReturnValueNode>
            extends AbstractNodeAssert<SELF, Path.ReturnValueNode> {

        public static class DefaultReturnValueNodeAssert
                extends AbstractReturnValueNodeAssert<DefaultReturnValueNodeAssert, Path.ReturnValueNode> {

            public DefaultReturnValueNodeAssert(final Path.ReturnValueNode actual) {
                super(actual, DefaultReturnValueNodeAssert.class);
            }
        }

        protected AbstractReturnValueNodeAssert(final Path.ReturnValueNode actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    protected AbstractPathAssert(final Path actual, final Class<?> selfType) {
        super(actual, selfType);
    }

//    @Override
//    public SELF targetingGroups(Class<?>... groups) {
//        delegate.setGroups(groups);
//        return myself;
//    }
//
//    @Override
//    public SELF usingValidatorSuppliedBy(final Supplier<? extends Validator> validatorSupplier) {
//        delegate.setValidatorSupplier(validatorSupplier);
//        return myself;
//    }

    final ValidationAssertDelegate delegate = new ValidationAssertDelegate();
}
