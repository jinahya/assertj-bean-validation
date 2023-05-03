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

import org.assertj.core.api.AbstractObjectAssert;
import org.assertj.core.api.Assert;
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.Assertions;

import javax.validation.metadata.ConstraintDescriptor;
import java.lang.annotation.Annotation;
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
        ACTUAL extends ConstraintDescriptor<T>,
        T extends Annotation>
        extends Assert<SELF, ACTUAL> {

    // ------------------------------------------------------------------------------------------------------ annotation
    <A extends AbstractObjectAssert<?, ? super T>> A extractingAnnotation(
            final Function<? super ACTUAL, ? extends T> annotationExtractor,
            final AssertFactory<? super T, ? extends A> assertFactory);

    default <A extends AbstractObjectAssert<?, ? super T>> A extractingAnnotation(
            final AssertFactory<? super T, ? extends A> assertFactory) {
        return extractingAnnotation(ConstraintDescriptor::getAnnotation, assertFactory);
    }

    default AbstractObjectAssert<?, T> extractingAnnotation() {
        return extractingAnnotation(Assertions::assertThat);
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
}
