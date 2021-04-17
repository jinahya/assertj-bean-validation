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

import org.assertj.core.api.AbstractThrowableAssert;

abstract class AbstractValidationExceptionAssert<
        SELF extends AbstractValidationExceptionAssert<SELF, ACTUAL>,
        ACTUAL extends RuntimeException>
        extends AbstractThrowableAssert<SELF, ACTUAL> {

    AbstractValidationExceptionAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    // ---------------------------------------------------------------------------------- ConstraintDeclarationException

    /**
     * Verifies that the {@link #actual} is an instance of {@code ConstraintDeclarationException}.
     *
     * @return {@link #myself self}.
     */
    protected abstract SELF isConstraintDeclarationException();

    /**
     * Verifies that the {@link #actual} is an instance of {@code ConstraintDeclarationException} and returns an assert
     * of {@code ConstraintViolationException} for subsequent verifications.
     *
     * @return {@link #myself self}.
     */
    public abstract <T extends AbstractConstraintDeclarationExceptionAssert<T, ?>> T asConstraintDeclarationException();

    // ----------------------------------------------------------------------------------- ConstraintDefinitionException
    protected abstract SELF isConstraintDefinitionException();

    public abstract <T extends AbstractConstraintDefinitionExceptionAssert<T, ?>> T asConstraintDefinitionException();

    // ------------------------------------------------------------------------------------ ConstraintViolationException
    protected abstract SELF isConstraintViolationException();

    public abstract <T extends AbstractConstraintViolationExceptionAssert<T, ?, ?>> T asConstraintViolationException();

    // ---------------------------------------------------------------------------------------- GroupDefinitionException
    protected abstract SELF isGroupDefinitionException();

    public abstract <T extends AbstractGroupDefinitionExceptionAssert<T, ?>> T asGroupDefinitionException();
}
