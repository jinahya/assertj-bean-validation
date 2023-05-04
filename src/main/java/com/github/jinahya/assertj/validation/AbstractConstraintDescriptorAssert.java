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
import org.assertj.core.api.AssertFactory;
import org.assertj.core.api.ObjectAssertFactory;

import javax.validation.ConstraintTarget;
import javax.validation.ConstraintViolation;
import javax.validation.metadata.ConstraintDescriptor;
import java.lang.annotation.Annotation;
import java.util.function.Function;

/**
 * An abstract assertion class for verifying {@link ConstraintViolation} values.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @param <T>      constraint's annotation type
 */
public abstract class AbstractConstraintDescriptorAssert<
        SELF extends AbstractConstraintDescriptorAssert<SELF, ACTUAL, T>, ACTUAL extends ConstraintDescriptor<T>, T extends Annotation>
        extends AbstractAssert<SELF, ACTUAL>
        implements ConstraintDescriptorAssert<SELF, ACTUAL, T> {

    /**
     * Creates a new instance with specified arguments.
     *
     * @param actual   an actual value to verify.
     * @param selfType self type.
     */
    protected AbstractConstraintDescriptorAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    // ------------------------------------------------------------------------------------------------------ annotation

    @Override
    public <A extends AbstractAssert<?, T>> A extractingAnnotation(
            final Function<? super ACTUAL, ? extends T> annotationExtractor,
            final AssertFactory<? super T, ? extends A> assertFactory) {
        return isNotNull()
                .extracting(annotationExtractor, assertFactory);
    }
    // -------------------------------------------------------------------------------------------- composingConstraints

    // -------------------------------------------------------------------------------------- constraintValidatorClasses

    // ---------------------------------------------------------------------------------------------------------- groups

    // ------------------------------------------------------------------------------------------------- messageTemplate

    // --------------------------------------------------------------------------------------------------------- payload

    // --------------------------------------------------------------------------------------------- validationAppliesTo

    // --------------------------------------------------------------------------------------------------- valueWrapping

    // ----------------------------------------------------------------------------------------- reportAsSingleViolation

    @Override
    public SELF hostsConstraintTarget(final ConstraintTarget constraintTarget) {
        isNotNull()
                .extracting(ConstraintDescriptor::isReportAsSingleViolation, new ObjectAssertFactory<>())
                .isSameAs(constraintTarget);
        return myself;
    }

    // ---------------------------------------------------------------------------------------------------------- unwrap
}
