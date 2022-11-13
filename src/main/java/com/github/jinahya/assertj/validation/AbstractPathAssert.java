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

import org.assertj.core.api.AbstractBooleanAssert;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ObjectAssertFactory;

import javax.validation.ElementKind;
import javax.validation.Path;

/**
 * An interface for verifying {@link Path} values.
 *
 * @param <SELF> self type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S119" // <SELF>, <ACTUAL>
})
public abstract class AbstractPathAssert<SELF extends AbstractPathAssert<SELF>>
        extends AbstractIterableAssert<SELF, Path, Path.Node, AbstractPathAssert.NodeAssert>
        implements ValidationAssert<SELF, Path> {

    public abstract static class AbstractNodeAssert<
            SELF extends AbstractNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.Node>
            extends AbstractObjectAssert<SELF, ACTUAL> {

        AbstractNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        public SELF hasIndex(final Integer expectedIndex) {
            final SELF self = isNotNull();
            self.extracting(n -> {
                        final Integer r = n.getIndex();
//                        return n.getIndex();
                        return r;
                    }, Assertions.as(InstanceOfAssertFactories.INTEGER))
                    .as("index of %s", actual)
                    .isEqualTo(expectedIndex);
            return self;
        }

        public SELF hasKey(final Object expectedKey) {
            final SELF self = isNotNull();
            self.extracting(Path.Node::getKey, new ObjectAssertFactory<>())
                    .isEqualTo(expectedKey);
            return self;
        }

        public SELF hasKind(final ElementKind expectedKind) {
            final SELF self = isNotNull();
            self.extracting(Path.Node::getKind, new ObjectAssertFactory<>())
                    .isSameAs(expectedKind);
            return self;
        }

        public SELF hasName(final String expectedName) {
            final SELF self = isNotNull();
            self.extracting(Path.Node::getName, InstanceOfAssertFactories.STRING)
                    .isEqualTo(expectedName);
            return self;
        }

        private AbstractBooleanAssert<?> extractingInIterable() {
            return isNotNull()
                    .extracting(Path.Node::isInIterable, InstanceOfAssertFactories.BOOLEAN);
        }

        public SELF isInIterable() {
            extractingInIterable().isTrue();
            return myself;
        }

        public SELF isNotInIterable() {
            extractingInIterable().isFalse();
            return myself;
        }
    }

    public static class NodeAssert
            extends AbstractNodeAssert<NodeAssert, Path.Node> {

        NodeAssert(final Path.Node actual, Class<?> selfType) {
            super(actual, selfType);
        }

        NodeAssert(final Path.Node actual) {
            super(actual, NodeAssert.class);
        }
    }

    static class ParameterizedNodeAssert<SELF extends ParameterizedNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.Node>
            extends NodeAssert {

        ParameterizedNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    abstract static class AbstractBeanNodeAssert<
            SELF extends AbstractBeanNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.BeanNode>
            extends ParameterizedNodeAssert<SELF, ACTUAL> {

        static class BeanNodeAssert
                extends AbstractBeanNodeAssert<BeanNodeAssert, Path.BeanNode> {

            BeanNodeAssert(final Path.BeanNode actual) {
                super(actual, BeanNodeAssert.class);
            }
        }

        AbstractBeanNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    abstract static class AbstractConstructorNodeAssert<
            SELF extends AbstractConstructorNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.ConstructorNode>
            extends ParameterizedNodeAssert<SELF, ACTUAL> {

        static class ConstructorNodeAssert
                extends AbstractConstructorNodeAssert<ConstructorNodeAssert, Path.ConstructorNode> {

            ConstructorNodeAssert(final Path.ConstructorNode actual) {
                super(actual, ConstructorNodeAssert.class);
            }
        }

        AbstractConstructorNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        @SuppressWarnings({"unchecked"})
        public ListAssert<Class<?>> extractingParameterTypes() {
            return (ListAssert<Class<?>>) (Object) isNotNull()
                    .extracting(
                            v -> ((Path.ConstructorNode) v).getParameterTypes(),
                            InstanceOfAssertFactories.LIST
                    );
        }
    }

    public abstract static class AbstractCrossParameterNodeAssert<
            SELF extends AbstractCrossParameterNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.CrossParameterNode>
            extends ParameterizedNodeAssert<SELF, ACTUAL> {

        public static class CrossParameterNodeAssert
                extends AbstractCrossParameterNodeAssert<CrossParameterNodeAssert, Path.CrossParameterNode> {

            public CrossParameterNodeAssert(final Path.CrossParameterNode actual) {
                super(actual, CrossParameterNodeAssert.class);
            }
        }

        AbstractCrossParameterNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    public abstract static class AbstractMethodNodeAssert<
            SELF extends AbstractMethodNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.MethodNode>
            extends ParameterizedNodeAssert<SELF, ACTUAL> {

        public static class MethodNodeAssert
                extends AbstractMethodNodeAssert<MethodNodeAssert, Path.MethodNode> {

            public MethodNodeAssert(final Path.MethodNode actual) {
                super(actual, MethodNodeAssert.class);
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
            extends ParameterizedNodeAssert<SELF, ACTUAL> {

        public static class ParameterNodeAssert
                extends AbstractParameterNodeAssert<ParameterNodeAssert, Path.ParameterNode> {

            ParameterNodeAssert(final Path.ParameterNode actual) {
                super(actual, ParameterNodeAssert.class);
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
            extends ParameterizedNodeAssert<SELF, ACTUAL> {

        public static class PropertyNodeAssert
                extends AbstractPropertyNodeAssert<PropertyNodeAssert, Path.PropertyNode> {

            public PropertyNodeAssert(final Path.PropertyNode actual) {
                super(actual, PropertyNodeAssert.class);
            }
        }

        protected AbstractPropertyNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    public abstract static class AbstractReturnValueNodeAssert<
            SELF extends AbstractReturnValueNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.ReturnValueNode>
            extends ParameterizedNodeAssert<SELF, Path.ReturnValueNode> {

        public static class ReturnValueNodeAssert
                extends AbstractReturnValueNodeAssert<ReturnValueNodeAssert, Path.ReturnValueNode> {

            public ReturnValueNodeAssert(final Path.ReturnValueNode actual) {
                super(actual, ReturnValueNodeAssert.class);
            }
        }

        protected AbstractReturnValueNodeAssert(final Path.ReturnValueNode actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    protected AbstractPathAssert(final Path actual, final Class<?> selfType) {
        super(actual, selfType);
    }
}
