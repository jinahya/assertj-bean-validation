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
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S119" // <SELF>, <ACTUAL>
})
public abstract class PathAssert<SELF extends PathAssert<SELF, ACTUAL>, ACTUAL extends Path>
        extends AbstractAssert<SELF, ACTUAL>
        implements ValidationAssert<SELF, ACTUAL> {

    abstract static class AbstractNodeAssert<SELF extends AbstractNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.Node>
            extends AbstractObjectAssert<SELF, ACTUAL> {

        AbstractNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        public SELF hasIndex(final Integer expectedIndex) {
            final SELF self = isNotNull();
            self.extracting(Path.Node::getIndex, InstanceOfAssertFactories.INTEGER)
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

    abstract static class BeanNodeAssert<SELF extends BeanNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.BeanNode>
            extends AbstractNodeAssert<SELF, ACTUAL> {

        static class BeanNodeAssertImpl
                extends BeanNodeAssert<BeanNodeAssertImpl, Path.BeanNode> {

            BeanNodeAssertImpl(final Path.BeanNode actual) {
                super(actual, BeanNodeAssertImpl.class);
            }
        }

        BeanNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    abstract static class ConstructorNodeAssert<
            SELF extends ConstructorNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.ConstructorNode>
            extends AbstractNodeAssert<SELF, ACTUAL> {

        static class ConstructorNodeAssertImpl
                extends ConstructorNodeAssert<ConstructorNodeAssertImpl, Path.ConstructorNode> {

            ConstructorNodeAssertImpl(final Path.ConstructorNode actual) {
                super(actual, ConstructorNodeAssertImpl.class);
            }
        }

        ConstructorNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        public ListAssert<Class<?>> extractingParameterTypes() {
            return isNotNull()
                    .extracting(Path.ConstructorNode::getParameterTypes, Assertions::assertThat);
        }
    }

    abstract static class CrossParameterNodeAssert<
            SELF extends CrossParameterNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.CrossParameterNode>
            extends AbstractNodeAssert<SELF, ACTUAL> {

        static class CrossParameterNodeAssertImpl
                extends CrossParameterNodeAssert<CrossParameterNodeAssertImpl, Path.CrossParameterNode> {

            CrossParameterNodeAssertImpl(final Path.CrossParameterNode actual) {
                super(actual, CrossParameterNodeAssertImpl.class);
            }
        }

        CrossParameterNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    abstract static class MethodNodeAssert<SELF extends MethodNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.MethodNode>
            extends AbstractNodeAssert<SELF, ACTUAL> {

        static class MethodNodeAssertImpl
                extends MethodNodeAssert<MethodNodeAssertImpl, Path.MethodNode> {

            MethodNodeAssertImpl(final Path.MethodNode actual) {
                super(actual, MethodNodeAssertImpl.class);
            }
        }

        MethodNodeAssert(final ACTUAL actual, Class<?> selfType) {
            super(actual, selfType);
        }

        public ListAssert<Class<?>> extractingParameterTypes() {
            return isNotNull()
                    .extracting(Path.MethodNode::getParameterTypes, Assertions::assertThat);
        }
    }

    abstract static class ParameterNodeAssert<
            SELF extends ParameterNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.ParameterNode>
            extends AbstractNodeAssert<SELF, ACTUAL> {

        static class ParameterNodeAssertImpl
                extends ParameterNodeAssert<ParameterNodeAssertImpl, Path.ParameterNode> {

            ParameterNodeAssertImpl(final Path.ParameterNode actual) {
                super(actual, ParameterNodeAssertImpl.class);
            }
        }

        ParameterNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }

        public AbstractIntegerAssert<?> extractingParameterIndex() {
            return isNotNull()
                    .extracting(Path.ParameterNode::getParameterIndex, InstanceOfAssertFactories.INTEGER);
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
            return myself;
        }
    }

    abstract static class PropertyNodeAssert<SELF extends PropertyNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.PropertyNode>
            extends AbstractNodeAssert<SELF, ACTUAL> {

        static class PropertyNodeAssertImpl
                extends PropertyNodeAssert<PropertyNodeAssertImpl, Path.PropertyNode> {

            PropertyNodeAssertImpl(final Path.PropertyNode actual) {
                super(actual, PropertyNodeAssertImpl.class);
            }
        }

        PropertyNodeAssert(final ACTUAL actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    abstract static class ReturnValueNodeAssert<
            SELF extends ReturnValueNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.ReturnValueNode>
            extends AbstractNodeAssert<SELF, Path.ReturnValueNode> {

        static class ReturnValueNodeAssertImpl
                extends ReturnValueNodeAssert<ReturnValueNodeAssertImpl, Path.ReturnValueNode> {

            ReturnValueNodeAssertImpl(final Path.ReturnValueNode actual) {
                super(actual, ReturnValueNodeAssertImpl.class);
            }
        }

        ReturnValueNodeAssert(final Path.ReturnValueNode actual, final Class<?> selfType) {
            super(actual, selfType);
        }
    }

    protected PathAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }
}
