package com.github.jinahya.assertj.validation.javax;

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

import com.github.jinahya.assertj.validation.AbstractValidationExceptionAssert;

import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintDefinitionException;
import javax.validation.ConstraintViolationException;
import javax.validation.GroupDefinitionException;
import javax.validation.ValidationException;

public class ValidationExceptionAssert
        extends AbstractValidationExceptionAssert<ValidationExceptionAssert, ValidationException> {

    /**
     * Creates a new instance for verifying specified actual value.
     *
     * @param actual the actual value to verify.
     */
    protected ValidationExceptionAssert(final ValidationException actual) {
        super(actual, ValidationExceptionAssert.class);
    }

    @Override
    protected ValidationExceptionAssert isConstraintDeclarationException() {
        return isInstanceOf(ConstraintDeclarationException.class);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public ConstraintDeclarationExceptionAssert asConstraintDeclarationException() {
        isConstraintDeclarationException();
        return new ConstraintDeclarationExceptionAssert((ConstraintDeclarationException) actual);
    }

    @Override
    protected ValidationExceptionAssert isConstraintDefinitionException() {
        return isInstanceOf(ConstraintDefinitionException.class);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public ConstraintDefinitionExceptionAssert asConstraintDefinitionException() {
        isConstraintDefinitionException();
        return new ConstraintDefinitionExceptionAssert((ConstraintDefinitionException) actual);
    }

    // ------------------------------------------------------------------------------------ ConstraintViolationException
    @Override
    protected ValidationExceptionAssert isConstraintViolationException() {
        return isInstanceOf(ConstraintViolationException.class);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public ConstraintViolationExceptionAssert asConstraintViolationException() {
        isConstraintViolationException();
        return new ConstraintViolationExceptionAssert((ConstraintViolationException) actual);
    }

    // ---------------------------------------------------------------------------------------- GroupDefinitionException
    @Override
    protected ValidationExceptionAssert isGroupDefinitionException() {
        return isInstanceOf(GroupDefinitionException.class);
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public GroupDefinitionExceptionAssert asGroupDefinitionException() {
        isGroupDefinitionException();
        return new GroupDefinitionExceptionAssert((GroupDefinitionException) actual);
    }
}
