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

@SuppressWarnings({"java:S119"})
public class ValidationExceptionAssert<ACTUAL extends RuntimeException>
        extends AbstractValidationExceptionAssert<ValidationExceptionAssert<ACTUAL>, ACTUAL> {

    public ValidationExceptionAssert(final ACTUAL actual) {
        super(ValidationExceptionUtils.requireValidationExceptionInstance(actual),
              ValidationExceptionAssert.class);
    }

    @Override
    protected ValidationExceptionAssert<ACTUAL> isConstraintDeclarationException() {
        return isNotNull()
                .satisfies(ConstraintDeclarationExceptionUtils::isNullOrInstanceOfConstraintDeclarationExceptionClass);
    }

    @Override
    public <T extends AbstractConstraintDeclarationExceptionAssert<T, ?>> T asConstraintDeclarationException() {
        return null;
    }

    @Override
    protected ValidationExceptionAssert<ACTUAL> isConstraintDefinitionException() {
        return isNotNull()
                .satisfies(ConstraintDefinitionExceptionUtils::isNullOrInstanceOfConstraintDefinitionExceptionClass);
    }

    @Override
    public <T extends AbstractConstraintDefinitionExceptionAssert<T, ?>> T asConstraintDefinitionException() {
        return null;
    }

    @Override
    protected ValidationExceptionAssert<ACTUAL> isConstraintViolationException() {
        return isNotNull()
                .satisfies(ConstraintViolationExceptionUtils::requireInstanceOfConstraintViolationExceptionClass);
    }

    @Override
    public <T extends AbstractConstraintViolationExceptionAssert<T, ?, ?>> T asConstraintViolationException() {
        return null;
    }

    @Override
    protected ValidationExceptionAssert<ACTUAL> isGroupDefinitionException() {
        return isNotNull()
                .satisfies(GroupDefinitionExceptionUtils::isNullOrInstanceOfGroupDefinitionExceptionClass);
    }

    @Override
    public <T extends AbstractGroupDefinitionExceptionAssert<T, ?>> T asGroupDefinitionException() {
        return null;
    }
}
