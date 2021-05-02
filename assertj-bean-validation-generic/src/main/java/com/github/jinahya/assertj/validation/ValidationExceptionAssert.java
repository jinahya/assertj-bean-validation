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

import static com.github.jinahya.assertj.validation.ConstraintDeclarationExceptionUtils.requireInstanceOfConstraintDeclarationExceptionClass;
import static com.github.jinahya.assertj.validation.ConstraintDefinitionExceptionUtils.requireInstanceOfConstraintDefinitionExceptionClass;
import static com.github.jinahya.assertj.validation.ConstraintViolationExceptionUtils.requireInstanceOfConstraintViolationExceptionClass;
import static com.github.jinahya.assertj.validation.ValidationExceptionUtils.requireValidationExceptionInstance;

@SuppressWarnings({"java:S119"})
public class ValidationExceptionAssert
        extends AbstractValidationExceptionAssert<ValidationExceptionAssert, RuntimeException> {

    public ValidationExceptionAssert(final RuntimeException actual) {
        super(requireValidationExceptionInstance(actual), ValidationExceptionAssert.class);
    }

    // ---------------------------------------------------------------------------------- ConstraintDeclarationException
    @Override
    protected ValidationExceptionAssert isConstraintDeclarationException() {
        return satisfies(a -> {
            requireInstanceOfConstraintDeclarationExceptionClass(a, true);
        });
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public ConstraintDeclarationExceptionAssert asConstraintDeclarationException() {
        isConstraintDeclarationException();
        return new ConstraintDeclarationExceptionAssert(actual);
    }

    // ----------------------------------------------------------------------------------- ConstraintDefinitionException
    @Override
    protected ValidationExceptionAssert isConstraintDefinitionException() {
        return satisfies(a -> {
            requireInstanceOfConstraintDefinitionExceptionClass(a, true);
        });
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public ConstraintDefinitionExceptionAssert asConstraintDefinitionException() {
        isConstraintDefinitionException();
        return new ConstraintDefinitionExceptionAssert(actual);
    }

    // ------------------------------------------------------------------------------------ ConstraintViolationException
    @Override
    protected ValidationExceptionAssert isConstraintViolationException() {
        return satisfies(a -> {
            requireInstanceOfConstraintViolationExceptionClass(a, true);
        });
    }

    @Override
    public ConstraintViolationExceptionAssert asConstraintViolationException() {
        isConstraintViolationException();
        return new ConstraintViolationExceptionAssert(actual);
    }

    // ---------------------------------------------------------------------------------------- GroupDefinitionException
    @Override
    protected ValidationExceptionAssert isGroupDefinitionException() {
        return satisfies(a -> {
            GroupDefinitionExceptionUtils.requireGroupDefinitionException(a, true);
        });
    }

    @Override
    public GroupDefinitionExceptionAssert asGroupDefinitionException() {
        isGroupDefinitionException();
        return new GroupDefinitionExceptionAssert(actual);
    }
}
