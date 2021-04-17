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

/**
 * An abstract base assertion class for verifying instances of {@code ConstraintDeclarationException}.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> the actual type of {@code ConstraintViolationException}
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
abstract class AbstractConstraintDeclarationExceptionAssert<
        SELF extends AbstractConstraintDeclarationExceptionAssert<SELF, ACTUAL>,
        ACTUAL extends RuntimeException>
        extends AbstractExtendedValidationExceptionAssert<SELF, ACTUAL> {

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual   the actual value to verify.
     * @param selfType a self type.
     */
    AbstractConstraintDeclarationExceptionAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }
}
