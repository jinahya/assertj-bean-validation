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

import static org.assertj.core.api.Assertions.assertThat;

abstract class AbstractValidationExceptionAssert<SELF extends AbstractValidationExceptionAssert<SELF>>
        extends AbstractThrowableAssert<SELF, RuntimeException> {

    protected AbstractValidationExceptionAssert(final Object actual, final Class<?> selfClass) {
        super((RuntimeException) actual, selfClass);
    }

    /**
     * Verifies that the {@link #actual} is an instance of {@code ....validation.ConstraintDeclarationException}.
     *
     * @return {@link #myself self}.
     */
    public SELF isConstraintDeclarationException() {
        return isNotNull().satisfies(a -> {
            assertThat(ConstraintDeclarationExceptionUtils.isConstraintDeclarationExceptionInstance(a)).isTrue();
        });
    }

    /**
     * Verifies that the {@link #actual} value is an instance of {@code ....validation.ConstraintDeclarationException},
     * and returns an instance of {@link ConstraintDeclarationExceptionAssert} for subsequent assertions.
     *
     * @param <T> constraint violation exception type parameter
     * @return {@link #myself self}.
     */
    @SuppressWarnings({"unchecked"})
    public <T> ConstraintDeclarationExceptionAssert<T> asConstraintDeclarationException() {
        isConstraintDeclarationException();
        return new ConstraintDeclarationExceptionAssert<>((T) actual);
    }

    /**
     * Verifies that the {@link #actual} is an instance of {@code ....validation.ConstraintDefinitionException}.
     *
     * @return {@link #myself self}.
     */
    public SELF isConstraintDefinitionException() {
        return isNotNull().satisfies(a -> {
            assertThat(ConstraintDefinitionExceptionUtils.isConstraintDefinitionExceptionInstance(a)).isTrue();
        });
    }

    /**
     * Verifies that the {@link #actual} value is an instance of {@code ....validation.ConstraintDefinitionException},
     * and returns an instance of {@link ConstraintDefinitionExceptionAssert} for subsequent assertions.
     *
     * @param <T> constraint violation exception type parameter
     * @return {@link #myself self}.
     */
    @SuppressWarnings({"unchecked"})
    public <T> ConstraintDefinitionExceptionAssert<T> asConstraintDefinitionException() {
        isConstraintDefinitionException();
        return new ConstraintDefinitionExceptionAssert<>((T) actual);
    }

    /**
     * Verifies that the {@link #actual} is an instance of {@code ....validation.ConstraintViolationException}.
     *
     * @return {@link #myself self}.
     */
    public SELF isConstraintViolationException() {
        return isNotNull().satisfies(a -> {
            assertThat(ConstraintViolationExceptionUtils.isConstraintViolationExceptionInstance(a)).isTrue();
        });
    }

    /**
     * Verifies that the {@link #actual} value is an instance of {@code ....validation.ConstraintViolationException},
     * and returns an instance of {@link ConstraintViolationExceptionAssert} for subsequent assertions.
     *
     * @param <T> constraint violation exception type parameter
     * @return {@link #myself self}.
     */
    @SuppressWarnings({"unchecked"})
    public <T> ConstraintViolationExceptionAssert<T> asConstraintViolationException() {
        isConstraintViolationException();
        return new ConstraintViolationExceptionAssert<>((T) actual);
    }

    /**
     * Verifies that the {@link #actual} is an instance of {@code ....validation.GroupDefinitionException}.
     *
     * @return {@link #myself self}.
     */
    public SELF isGroupDefinitionException() {
        return isNotNull().satisfies(a -> {
            assertThat(GroupDefinitionExceptionUtils.isGroupDefinitionExceptionInstance(a)).isTrue();
        });
    }

    /**
     * Verifies that the {@link #actual} value is an instance of {@code ....validation.GroupDefinitionException}, and
     * returns an instance of {@link GroupDefinitionExceptionAssert} for subsequent assertions.
     *
     * @param <T> group violation exception type parameter
     * @return {@link #myself self}.
     */
    @SuppressWarnings({"unchecked"})
    public <T> GroupDefinitionExceptionAssert<T> asGroupDefinitionException() {
        isGroupDefinitionException();
        return new GroupDefinitionExceptionAssert<>((T) actual);
    }
}
