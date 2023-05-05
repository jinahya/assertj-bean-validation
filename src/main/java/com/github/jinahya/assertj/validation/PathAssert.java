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
import org.assertj.core.api.AbstractComparableAssert;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.EnumerableAssert;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.assertj.core.api.ListAssert;
import org.assertj.core.api.ObjectAssertFactory;

import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * An interface for verifying {@link Path} values.
 *
 * @param <SELF> self type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
interface PathAssert<SELF extends PathAssert<SELF>>
        extends Assert<SELF, Path>,
                EnumerableAssert<SELF, Path.Node> {

    /**
     * An interface for verifying {@link Path.Node} values.
     *
     * @param <SELF>   self type parameter
     * @param <ACTUAL> type of {@link Path.Node}
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    interface NodeAssert<SELF extends NodeAssert<SELF, ACTUAL>, ACTUAL extends Path.Node> {

        // ---------------------------------------------------------------------------------------------------------- as
//        <NODE extends Path.Node, ASSERT extends NodeAssert<?, ? extends NODE>> ASSERT extractingAs(
//                final Class<NODE> nodeType,
//                final AssertFactory<? super NODE, ? extends ASSERT> assertFactory
//        );

        // ------------------------------------------------------------------------------------------------------- index
        <ASSERT extends AbstractIntegerAssert<? extends ASSERT>> ASSERT extractingIndex(
                AssertFactory<? super Integer, ? extends ASSERT> factory
        );

        @SuppressWarnings({"unchecked"})
        default <ASSERT extends AbstractIntegerAssert<? extends ASSERT>> SELF hasIndexSatisfying(
                final AssertFactory<? super Integer, ? extends ASSERT> factory,
                final Consumer<? super ASSERT> consumer) {
            Objects.requireNonNull(consumer, "consumer is null");
            consumer.accept(extractingIndex(factory));
            return (SELF) this;
        }

        /**
         * Returns an assert for verifying {@link Path.Node#getIndex() actual.index} value.
         *
         * @return an assert for verifying {@link Path.Node#getIndex() actual.index} value.
         * @see #hasIndex(int)
         */
        default AbstractIntegerAssert<?> extractingIndex() {
            return extractingIndex(InstanceOfAssertFactories.INTEGER);
        }

        /**
         * Verifies that {@link Path.Node#getIndex() actual.index} value satisfies according to specified consumer.
         *
         * @param consumer the consumer verifies the {@link Path.Node#getIndex() actual.index} value.
         * @return this assertion object
         * @see #hasIndex(int)
         */
        @SuppressWarnings({"unchecked"})
        default SELF hasIndexSatisfying(final Consumer<? super AbstractIntegerAssert<?>> consumer) {
            Objects.requireNonNull(consumer, "consumer is null");
            consumer.accept(extractingIndex());
            return (SELF) this;
        }

        /**
         * Verifies that {@link Path.Node#getIndex() actual.index} is equal to specified value.
         *
         * @param expectedIndex expected value of {@link Path.Node#getIndex() actual.index}.
         * @return this assertion object.
         * @see #hasIndexSatisfying(Consumer)
         */
        @SuppressWarnings({
                "unchecked"
        })
        default SELF hasIndex(final int expectedIndex) {
            if (true) {
                return hasIndexSatisfying(a -> a.isEqualTo(expectedIndex));
            }
            extractingIndex().isEqualTo(expectedIndex);
            return (SELF) this;
        }

        // --------------------------------------------------------------------------------------------------------- key

        /**
         * Returns an assert for verifying {@link Path.Node#getKey()} actual.key} value.
         *
         * @param extractor a function for extracting {@link Path.Node#getKey() actual.key} value.
         * @param factory   an assertion factory.
         * @return an assert for verifying {@link Path.Node#getKey() actual.key} value.
         * @see #extractingKey()
         */
        <KEY, ASSERT extends AbstractObjectAssert<ASSERT, ? extends KEY>> ASSERT extractingKey(
                Function<? super Path.Node, ? extends KEY> extractor,
                AssertFactory<? super KEY, ? extends ASSERT> factory
        );

        @SuppressWarnings({"unchecked"})
        default <KEY, ASSERT extends AbstractObjectAssert<ASSERT, ? extends KEY>> SELF hasKeySatisfying(
                final Function<? super Path.Node, ? extends KEY> extractor,
                final AssertFactory<? super KEY, ? extends ASSERT> factory,
                final Consumer<? super ASSERT> consumer) {
            Objects.requireNonNull(consumer, "consumer is null");
            consumer.accept(extractingKey(extractor, factory));
            return (SELF) this;
        }

        /**
         * Returns an assert for verifying {@link Path.Node#getKey()} actual.key} value.
         *
         * @return an assert for verifying {@link Path.Node#getKey() actual.key} value.
         * @see #extractingKey()
         */
        default AbstractObjectAssert<?, Object> extractingKey() {
            return extractingKey(Path.Node::getKey, new ObjectAssertFactory<>());
        }

        @SuppressWarnings({"unchecked"})
        default SELF hasKeySatisfying(final Consumer<? super AbstractObjectAssert<?, Object>> consumer) {
            consumer.accept(extractingKey());
            return (SELF) this;
        }

        /**
         * Verifies that {@link Path.Node#getKey() actual.key} is {@link #isEqualTo(Object) equal} to specified value.
         *
         * @param expectedKey expected value of {@link Path.Node#getKey() actual.key}.
         * @return this assertion object.
         * @see #hasKeySatisfying(Consumer)
         */
        default SELF hasKey(final Object expectedKey) {
            return hasKeySatisfying(a -> a.isEqualTo(expectedKey));
        }

        // -------------------------------------------------------------------------------------------------------- kind
        <ASSERT extends AbstractComparableAssert<?, ElementKind>> ASSERT extractingKind(
                AssertFactory<? super ElementKind, ? extends ASSERT> factory
        );

        @SuppressWarnings({"unchecked"})
        default <ASSERT extends AbstractComparableAssert<?, ElementKind>> SELF hasKindSatisfying(
                final AssertFactory<? super ElementKind, ? extends ASSERT> factory,
                final Consumer<? super ASSERT> consumer) {
            Objects.requireNonNull(consumer, "consumer is null");
            consumer.accept(extractingKind(factory));
            return (SELF) this;
        }

        /**
         * Returns an assert for verifying {@link Path.Node#getKind() actual.kind} value.
         *
         * @return an assert for verifying {@link Path.Node#getKind() actual.kind} value.
         */
        default AbstractComparableAssert<?, ElementKind> extractingKind() {
            return extractingKind(InstanceOfAssertFactories.comparable(ElementKind.class));
        }

        default SELF hasKindSatisfying(
                final Consumer<? super AbstractComparableAssert<?, ElementKind>> consumer) {
            Objects.requireNonNull(consumer, "consumer is null");
            return hasKindSatisfying(InstanceOfAssertFactories.comparable(ElementKind.class), consumer);
        }

        /**
         * Verifies that {@link Path.Node#getKind() actual.kind} value is {@link #isEqualTo(Object) equal} to specified
         * value.
         *
         * @return this assertion object.
         */
        default SELF hasKind(final ElementKind expectedKind) {
            return hasKindSatisfying(a -> a.isSameAs(expectedKind));
        }

        // -------------------------------------------------------------------------------------------------------- name
        <ASSERT extends AbstractStringAssert<? extends ASSERT>> ASSERT extractingName(
                AssertFactory<? super String, ? extends ASSERT> factory
        );

        @SuppressWarnings({"unchecked"})
        default <ASSERT extends AbstractStringAssert<? extends ASSERT>> SELF hasNameSatisfying(
                final AssertFactory<? super String, ? extends ASSERT> factory,
                final Consumer<? super ASSERT> consumer) {
            Objects.requireNonNull(consumer, "consumer is null");
            consumer.accept(extractingName(factory));
            return (SELF) this;
        }

        /**
         * Returns an assert for verifying {@link Path.Node#getName() actual.name} value.
         *
         * @return an assert for verifying {@link Path.Node#getName() actual.name} value.
         */
        default AbstractStringAssert<?> extractingName() {
            return extractingName(InstanceOfAssertFactories.STRING);
        }

        default SELF hasNameSatisfying(final Consumer<? super AbstractStringAssert<?>> consumer) {
            return hasNameSatisfying(InstanceOfAssertFactories.STRING, consumer);
        }

        /**
         * Verifies that {@link Path.Node#getName() actual.name} value is {@link #isEqualTo(Object) equal} to specified
         * value.
         *
         * @return this assertion object.
         */
        default SELF hasName(final String expectedName) {
            return hasNameSatisfying(a -> a.isEqualTo(expectedName));
        }

        // -------------------------------------------------------------------------------------------------- inIterable
        <ASSERT extends AbstractBooleanAssert<? extends ASSERT>> ASSERT extractingInIterable(
                AssertFactory<? super Boolean, ? extends ASSERT> factory
        );

        @SuppressWarnings({"unchecked"})
        default <ASSERT extends AbstractBooleanAssert<? extends ASSERT>> SELF hasInIterableSatisfying(
                final AssertFactory<? super Boolean, ? extends ASSERT> factory,
                final Consumer<? super ASSERT> consumer) {
            Objects.requireNonNull(consumer, "consumer is null");
            consumer.accept(extractingInIterable(factory));
            return (SELF) this;
        }

        default AbstractBooleanAssert<?> extractingInIterable() {
            return extractingInIterable(InstanceOfAssertFactories.BOOLEAN);
        }

        default SELF hasInIterableSatisfying(final Consumer<? super AbstractBooleanAssert<?>> consumer) {
            return hasInIterableSatisfying(InstanceOfAssertFactories.BOOLEAN, consumer);
        }

        @SuppressWarnings({"unchecked"})
        default SELF isInIterable() {
            extractingInIterable().isTrue();
            return (SELF) this;
        }

        default SELF isNotInIterable() {
            return hasInIterableSatisfying(AbstractBooleanAssert::isFalse);
        }
    }

    // -------------------------------------------------------------------------------------------------------- BeanNode
    interface BeanNodeAssert<SELF extends BeanNodeAssert<SELF>>
            extends NodeAssert<SELF, Path.BeanNode> {

        // ---------------------------------------------------------------------------------------------- containerClass
        Assert<?, ? extends Class<?>> extractingContainerClass();

        @SuppressWarnings({
                "unchecked"
        })
        default SELF hasContainerClass(final Class<?> expectedContainerClass) {
            extractingContainerClass().isEqualTo(expectedContainerClass);
            return (SELF) this;
        }

        // ------------------------------------------------------------------------------------------- typeArgumentIndex
        Assert<?, Integer> extractingTypeArgumentIndex();

        @SuppressWarnings({
                "unchecked"
        })
        default SELF hasTypeArgumentIndex(final Integer expectedTypeArgumentIndex) {
            extractingTypeArgumentIndex().isEqualTo(expectedTypeArgumentIndex);
            return (SELF) this;
        }

        default SELF hasNoTypeArgumentIndex() {
            return hasTypeArgumentIndex(null);
        }
    }

    // ------------------------------------------------------------------------------------------------- ConstructorNode
    interface ConstructorNodeAssert<SELF extends ConstructorNodeAssert<SELF>>
            extends NodeAssert<SELF, Path.ConstructorNode> {

        // ---------------------------------------------------------------------------------------------- parameterTypes
        AbstractIterableAssert<?, ?, ? super Class<?>, ?> extractingParameterTypes4();

        ListAssert<? super Class<?>> extractingParameterTypes5();

        @SuppressWarnings({
                "unchecked"
        })
        default SELF hasParameterTypes(final List<Class<?>> expectedParameterTypes) {
            extractingParameterTypes5().isEqualTo(expectedParameterTypes);
            return (SELF) this;
        }
    }

    // -------------------------------------------------------------------------------------------- ContainerElementNode
    interface ContainerElementNodeAssert<SELF extends ContainerElementNodeAssert<SELF>>
            extends NodeAssert<SELF, Path.ContainerElementNode> {

    }

    // ---------------------------------------------------------------------------------------------- CrossParameterNode
    interface CrossParameterNodeAssert<SELF extends CrossParameterNodeAssert<SELF>>
            extends NodeAssert<SELF, Path.CrossParameterNode> {

    }

    // ------------------------------------------------------------------------------------------------------ MethodNode
    interface MethodNodeAssert<SELF extends MethodNodeAssert<SELF>>
            extends NodeAssert<SELF, Path.MethodNode> {

        // ---------------------------------------------------------------------------------------------- parameterTypes
        AbstractIterableAssert<?, ?, ? super Class<?>, ?> extractingParameterTypes4();

        ListAssert<? super Class<?>> extractingParameterTypes5();

        @SuppressWarnings({
                "unchecked"
        })
        default SELF hasParameterTypes(final List<Class<?>> expectedParameterTypes) {
            extractingParameterTypes5().isEqualTo(expectedParameterTypes);
            return (SELF) this;
        }
    }

    // --------------------------------------------------------------------------------------------------- ParameterNode
    interface ParameterNodeAssert<SELF extends ParameterNodeAssert<SELF>>
            extends NodeAssert<SELF, Path.ParameterNode> {

    }

    // ---------------------------------------------------------------------------------------------------- PropertyNode
    interface PropertyNodeAssert<SELF extends PropertyNodeAssert<SELF>>
            extends NodeAssert<SELF, Path.PropertyNode> {

        // ---------------------------------------------------------------------------------------------- containerClass
        Assert<?, ? extends Class<?>> extractingContainerClass();

        @SuppressWarnings({
                "unchecked"
        })
        default SELF hasContainerClass(final Class<?> expectedContainerClass) {
            extractingContainerClass().isEqualTo(expectedContainerClass);
            return (SELF) this;
        }

        // ------------------------------------------------------------------------------------------- typeArgumentIndex
        Assert<?, Integer> extractingTypeArgumentIndex();

        @SuppressWarnings({
                "unchecked"
        })
        default SELF hasTypeArgumentIndex(final Integer expectedTypeArgumentIndex) {
            extractingTypeArgumentIndex().isEqualTo(expectedTypeArgumentIndex);
            return (SELF) this;
        }

        default SELF hasNoTypeArgumentIndex() {
            return hasTypeArgumentIndex(null);
        }
    }

    // ------------------------------------------------------------------------------------------------- ReturnValueNode
    interface ReturnValueNodeAssert<SELF extends ReturnValueNodeAssert<SELF>>
            extends NodeAssert<SELF, Path.ReturnValueNode> {

    }
}
