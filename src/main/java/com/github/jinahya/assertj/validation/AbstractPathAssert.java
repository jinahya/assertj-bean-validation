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
import org.assertj.core.api.AbstractComparableAssert;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ListAssert;

import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.Objects;
import java.util.function.Function;

/**
 * An abstract class for verifying {@link Path} values.
 *
 * @param <SELF> self type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S119" // <SELF>, <ACTUAL>
})
public abstract class AbstractPathAssert<
        SELF extends AbstractPathAssert<SELF, NODE_ASSERT>,
        NODE_ASSERT extends AbstractPathAssert.AbstractNodeAssert<NODE_ASSERT>>
        extends AbstractIterableAssert<SELF, Path, Path.Node, NODE_ASSERT>
        implements PathAssert<SELF> {

    abstract static class _AbstractNodeAssert<
            SELF extends _AbstractNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.Node>
            extends AbstractAssert<SELF, ACTUAL>
            implements PathAssert.NodeAssert<SELF, ACTUAL> {

        _AbstractNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        // ------------------------------------------------------------------------------------------------------- index
        @Override
        public <ASSERT extends AbstractIntegerAssert<? extends ASSERT>> ASSERT extractingIndex(
                final AssertFactory<? super Integer, ? extends ASSERT> factory) {
            return isNotNull()
                    .extracting(Path.Node::getIndex, factory);
        }

        // --------------------------------------------------------------------------------------------------------- key
        @Override
        public <KEY, ASSERT extends AbstractObjectAssert<ASSERT, ? extends KEY>> ASSERT extractingKey(
                final Function<? super Path.Node, ? extends KEY> extractor,
                final AssertFactory<? super KEY, ? extends ASSERT> factory) {
            Objects.requireNonNull(extractor, "extractor is null");
            return isNotNull()
                    .extracting(extractor, factory);
        }

        // -------------------------------------------------------------------------------------------------------- kind

        @Override
        public <ASSERT extends AbstractComparableAssert<?, ElementKind>> ASSERT extractingKind(
                final AssertFactory<? super ElementKind, ? extends ASSERT> factory) {
            return isNotNull()
                    .extracting(Path.Node::getKind, factory);
        }

        // -------------------------------------------------------------------------------------------------------- name

        @Override
        public <ASSERT extends AbstractStringAssert<? extends ASSERT>> ASSERT extractingName(
                final AssertFactory<? super String, ? extends ASSERT> factory) {
            return isNotNull()
                    .extracting(Path.Node::getName, factory);
        }

        // -------------------------------------------------------------------------------------------------- inIterable

        @Override
        public <ASSERT extends AbstractBooleanAssert<? extends ASSERT>> ASSERT extractingInIterable(
                final AssertFactory<? super Boolean, ? extends ASSERT> factory) {
            return isNotNull()
                    .extracting(Path.Node::isInIterable, factory);
        }
    }

    public abstract static class AbstractNodeAssert<SELF extends AbstractNodeAssert<SELF>>
            extends _AbstractNodeAssert<SELF, Path.Node> {

        AbstractNodeAssert(final Path.Node actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        // ---------------------------------------------------------------------------------------------------------- as
//        @Override
        public <NODE extends Path.Node, ASSERT extends PathAssert.NodeAssert<?, ? extends NODE>> ASSERT extractingAs(
                final Class<NODE> nodeType,
                final AssertFactory<? super NODE, ? extends ASSERT> assertFactory) {
            Objects.requireNonNull(nodeType, "nodeType is null");
            // TODO: implement!
            return null;
        }
    }

    abstract static class AbstractParameterizedNodeAssert<SELF extends AbstractParameterizedNodeAssert<SELF>>
            extends _AbstractNodeAssert<SELF, Path.ParameterNode>
            implements ParameterNodeAssert<SELF> {

        AbstractParameterizedNodeAssert(final Path.ParameterNode actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    abstract static class AbstractBeanNodeAssert<SELF extends AbstractBeanNodeAssert<SELF>>
            extends _AbstractNodeAssert<SELF, Path.BeanNode>
            implements BeanNodeAssert<SELF> {

        AbstractBeanNodeAssert(final Path.BeanNode actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        @Override
        public Assert<?, ? extends Class<?>> extractingContainerClass() {
            return isNotNull()
                    .extracting(Path.BeanNode::getContainerClass, InstanceOfAssertFactories.CLASS);
        }

        @Override
        public Assert<?, Integer> extractingTypeArgumentIndex() {
            return isNotNull()
                    .extracting(Path.BeanNode::getContainerClass, InstanceOfAssertFactories.INTEGER);
        }
    }

    abstract static class AbstractConstructorNodeAssert<SELF extends AbstractConstructorNodeAssert<SELF>>
            extends _AbstractNodeAssert<SELF, Path.ConstructorNode>
            implements ConstructorNodeAssert<SELF> {

        AbstractConstructorNodeAssert(final Path.ConstructorNode actual, final Class<?> selfType) {
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

    abstract static class AbstractCrossParameterNodeAssert<
            SELF extends AbstractCrossParameterNodeAssert<SELF>>
            extends _AbstractNodeAssert<SELF, Path.CrossParameterNode>
            implements CrossParameterNodeAssert<SELF> {

        AbstractCrossParameterNodeAssert(final Path.CrossParameterNode actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    abstract static class AbstractMethodNodeAssert<SELF extends AbstractMethodNodeAssert<SELF>>
            extends _AbstractNodeAssert<SELF, Path.MethodNode>
            implements MethodNodeAssert<SELF> {

        protected AbstractMethodNodeAssert(final Path.MethodNode actual, Class<?> selfType) {
            super(actual, selfType);
        }

        @SuppressWarnings({"unchecked"})
        public ListAssert<Class<?>> extractingParameterTypes() {
            return (ListAssert<Class<?>>) (Object) isNotNull()
                    .extracting(v -> ((Path.MethodNode) v).getParameterTypes(), Assertions::assertThat);
        }

        @Override
        public AbstractIterableAssert<?, ?, ? super Class<?>, ?> extractingParameterTypes4() {
            return null;
        }

        @Override
        public ListAssert<? super Class<?>> extractingParameterTypes5() {
            return null;
        }
    }

    public abstract static class AbstractParameterNodeAssert<SELF extends AbstractParameterNodeAssert<SELF>>
            extends _AbstractNodeAssert<SELF, Path.ParameterNode>
            implements ParameterNodeAssert<SELF> {

        protected AbstractParameterNodeAssert(final Path.ParameterNode actual, final Class<?> selfType) {
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

    public abstract static class AbstractPropertyNodeAssert<SELF extends AbstractPropertyNodeAssert<SELF>>
            extends _AbstractNodeAssert<SELF, Path.PropertyNode>
            implements PropertyNodeAssert<SELF> {

//        public static class DefaultPropertyNodeAssert
//                extends AbstractPropertyNodeAssert<DefaultPropertyNodeAssert, Path.PropertyNode> {
//
//            public DefaultPropertyNodeAssert(final Path.PropertyNode actual) {
//                super(actual, DefaultPropertyNodeAssert.class);
//            }
//        }

        protected AbstractPropertyNodeAssert(final Path.PropertyNode actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    public abstract static class AbstractReturnValueNodeAssert<SELF extends AbstractReturnValueNodeAssert<SELF>>
            extends _AbstractNodeAssert<SELF, Path.ReturnValueNode>
            implements ReturnValueNodeAssert<SELF> {

//        public static class DefaultReturnValueNodeAssert
//                extends AbstractReturnValueNodeAssert<DefaultReturnValueNodeAssert, Path.ReturnValueNode> {
//
//            public DefaultReturnValueNodeAssert(final Path.ReturnValueNode actual) {
//                super(actual, DefaultReturnValueNodeAssert.class);
//            }
//        }

        protected AbstractReturnValueNodeAssert(final Path.ReturnValueNode actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    protected AbstractPathAssert(final Path actual, final Class<?> selfType) {
        super(actual, selfType);
    }
}
