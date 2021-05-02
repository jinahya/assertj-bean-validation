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

/**
 * An abstract assertion class for subclasses of {@code ValidationException} class.
 *
 * @param <SELF>   a self type
 * @param <ACTUAL> an actual type of the subclass of {@code validationException} class.
 */
public abstract class AbstractExtendedValidationExceptionAssert<
        SELF extends AbstractExtendedValidationExceptionAssert<SELF, ACTUAL>,
        ACTUAL extends RuntimeException>
        extends AbstractThrowableAssert<SELF, ACTUAL> {

    /**
     * Creates a new instance for verifying specified actual value.
     *
     * @param actual   the actual value to verify.
     * @param selfType a class of self type.
     */
    protected AbstractExtendedValidationExceptionAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }
}
