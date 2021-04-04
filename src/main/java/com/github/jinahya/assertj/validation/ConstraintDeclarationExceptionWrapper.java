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
 * A class for wrapping actual values of {@code ...validation.ConstraintViolationException}.
 *
 * @param <ACTUAL> actual value type parameter
 */
public class ConstraintDeclarationExceptionWrapper<ACTUAL extends RuntimeException>
        extends Wrapper<ACTUAL> {

    /**
     * Creates a new instance wraps specified actual value.
     *
     * @param actual   the actual value to wrap.
     * @param <ACTUAL> actual value type parameter
     * @return a new instance wraps {@code actual}.
     */
    public static <ACTUAL extends RuntimeException> ConstraintDeclarationExceptionWrapper<ACTUAL>
    constraintDeclarationException(final ACTUAL actual) {
        return new ConstraintDeclarationExceptionWrapper<>(actual);
    }

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to wrap.
     */
    private ConstraintDeclarationExceptionWrapper(final ACTUAL actual) {
        super(actual);
    }
}
