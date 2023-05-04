package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation
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
import org.assertj.core.api.AbstractCollectionAssert;
import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.ObjectAssertFactory;

import javax.validation.ConstraintTarget;
import javax.validation.metadata.ConstraintDescriptor;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.function.Function;

/**
 * An assertion interface for verifying {@link ConstraintDescriptor} values.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @param <T>      type of constraint annotation
 */
public interface ConstraintDescriptorAssert<
        SELF extends ConstraintDescriptorAssert<SELF, ACTUAL, T>,
        ACTUAL extends ConstraintDescriptor<? extends T>,
        T extends Annotation>
        extends Assert<SELF, ACTUAL> {

    // ------------------------------------------------------------------------------------------------------ annotation
    <A extends AbstractAssert<?, T>> A extractingAnnotation(
            final Function<? super ACTUAL, ? extends T> annotationExtractor,
            final AssertFactory<? super T, ? extends A> assertFactory);

    default <A extends AbstractAssert<?, T>> A extractingAnnotation(
            final AssertFactory<? super T, ? extends A> assertFactory) {
        return extractingAnnotation(ConstraintDescriptor::getAnnotation, assertFactory);
    }

    default Assert<?, T> extractingAnnotation() {
        return extractingAnnotation(new ObjectAssertFactory<>());
    }

    // ------------------------------------------------------------------------------------------------------ attributes
//    <U, A extends AbstractMapAssert<?, ? extends Map<String, Object>, String, Object>> A extractingAttributes(
//            final Function<? super ACTUAL, ? extends Map<String, Object>> attributesExtractor,
//            final AssertFactory<? super U, ? extends A> assertFactory);
//
//    default <U, A extends AbstractMapAssert<?, ? extends Map<String, Object>, String, Object>> A extractingAttributes(
//            final AssertFactory<? super U, ? extends A> assertFactory) {
//        return extractingAttributes(ConstraintDescriptor::getAttributes, assertFactory);
//    }

    // -------------------------------------------------------------------------------------------- composingConstraints
    <A extends AbstractAssert<A, ? extends Set<ConstraintDescriptor<?>>>> A extractingComposingConstraints(
            Function<? super ACTUAL, ? extends Set<ConstraintDescriptor<?>>> extractor,
            AssertFactory<? super Set<ConstraintDescriptor<?>>, ? extends A> factory
    );

    <E extends AbstractAssert<?, ? extends ConstraintDescriptor<?>>> AbstractCollectionAssert<
            ?,
            Set<ConstraintDescriptor<?>>,
            ? extends ConstraintDescriptor<?>,
            ? extends E>
    extractingComposingConstraints(
    );

    // -------------------------------------------------------------------------------------- constraintValidatorClasses

    // ---------------------------------------------------------------------------------------------------------- groups

    // ------------------------------------------------------------------------------------------------- messageTemplate

    // --------------------------------------------------------------------------------------------------------- payload

    // --------------------------------------------------------------------------------------------- validationAppliesTo
    SELF hostsConstraintTarget(ConstraintTarget constraintTarget);

    default SELF doesNotHostAnyConstraintTarget() {
        return hostsConstraintTarget(null);
    }

    // --------------------------------------------------------------------------------------------------- valueWrapping

    // ----------------------------------------------------------------------------------------- reportAsSingleViolation

    // ---------------------------------------------------------------------------------------------------------- unwrap
    <U, A extends AbstractAssert<?, ? extends U>> A extractingAsUnwrapped(
            final Class<U> type,
            final AssertFactory<? super U, ? extends A> assertFactory
    );

    /**
     * Verifies that the {@code actual} constraint descriptor value is equal to specified value when
     * {@link ConstraintDescriptor#unwrap(Class) unwrapped} as specified type.
     *
     * @param type                   the to be unwrapped as.
     * @param expectedUnwrappedValue expected unwrapped value.
     * @param <U>                    the type of the unwrapped object.
     * @return this assertion object.
     * @see ConstraintDescriptor#unwrap(Class)
     */
    @SuppressWarnings({
            "unchecked"
    })
    default <U> SELF isEqualToWhenUnwrappedAs(final Class<U> type, final Object expectedUnwrappedValue) {
        extractingAsUnwrapped(type, new ObjectAssertFactory<>())
                .isEqualTo(expectedUnwrappedValue);
        return (SELF) this;
    }
}
