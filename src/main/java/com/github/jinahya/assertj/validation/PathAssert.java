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
import org.assertj.core.api.AbstractClassAssert;
import org.assertj.core.api.AbstractComparableAssert;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractIterableAssert;
import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.AbstractStringAssert;
import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.EnumerableAssert;
import org.assertj.core.api.ListAssert;

import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.List;

/**
 * An abstract class for verifying {@link Path} values.
 *
 * @param <SELF> self type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
interface PathAssert<SELF extends PathAssert<SELF>>
        extends Assert<SELF, Path>,
                EnumerableAssert<SELF, Path.Node> {

    interface NodeAssert<
            SELF extends NodeAssert<SELF, ACTUAL>, ACTUAL extends Path.Node> {

        // ---------------------------------------------------------------------------------------------------------- as
        <NODE extends Path.Node, ASSERT extends NodeAssert<?, NODE>> ASSERT extractingAs(
                final Class<NODE> nodeType,
                final AssertFactory<? super NODE, ? extends ASSERT> assertFactory
        );

        // ------------------------------------------------------------------------------------------------------- index
        AbstractIntegerAssert<?> extractingIndex();

        @SuppressWarnings({
                "unchecked"
        })
        default SELF hasIndex(final int expectedIndex) {
            extractingIndex().isEqualTo(expectedIndex);
            return (SELF) this;
        }

        // --------------------------------------------------------------------------------------------------------- key
        AbstractObjectAssert<?, Object> extractingKey();

        @SuppressWarnings({
                "unchecked"
        })
        default SELF hasKey(final Object expectedKey) {
            extractingKey().isEqualTo(expectedKey);
            return (SELF) this;
        }

        // -------------------------------------------------------------------------------------------------------- kind
        AbstractComparableAssert<?, ElementKind> extractingKind();

        @SuppressWarnings({
                "unchecked"
        })
        default SELF hasKind(final ElementKind expectedKind) {
            extractingKind().isSameAs(expectedKind);
            return (SELF) this;
        }

        // -------------------------------------------------------------------------------------------------------- name
        AbstractStringAssert<?> extractingName();

        @SuppressWarnings({
                "unchecked"
        })
        default SELF hasName(final String expectedName) {
            extractingName().isEqualTo(expectedName);
            return (SELF) this;
        }

        // -------------------------------------------------------------------------------------------------- inIterable
        AbstractBooleanAssert<?> extractingInIterable();

        @SuppressWarnings({
                "unchecked"
        })
        default SELF isInIterable() {
            extractingInIterable().isTrue();
            return (SELF) this;
        }

        @SuppressWarnings({
                "unchecked"
        })
        default SELF isNotInIterable() {
            extractingInIterable().isFalse();
            return (SELF) this;
        }
    }

    interface BeanNodeAssert<SELF extends BeanNodeAssert<SELF, ACTUAL>, ACTUAL extends Path.BeanNode>
            extends NodeAssert<SELF, ACTUAL> {

        // ---------------------------------------------------------------------------------------------- containerClass
        AbstractClassAssert<?> extractingContainerClass();

        @SuppressWarnings({
                "unchecked"
        })
        default SELF hasContainerClass(final Class<?> expectedContainerClass) {
            extractingContainerClass().isEqualTo(expectedContainerClass);
            return (SELF) this;
        }

        // ------------------------------------------------------------------------------------------- typeArgumentIndex
        AbstractIntegerAssert<?> extractingTypeArgumentIndex();

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

    interface ConstructorNodeAssert<
            SELF extends ConstructorNodeAssert<SELF, ACTUAL>,
            ACTUAL extends Path.ConstructorNode>
            extends NodeAssert<SELF, ACTUAL> {

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
}
