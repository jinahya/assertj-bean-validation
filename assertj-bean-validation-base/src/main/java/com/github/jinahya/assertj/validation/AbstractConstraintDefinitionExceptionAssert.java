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

import static java.util.Objects.requireNonNull;

/**
 * An abstract assertion class for verifying instances of {@code ConstraintDefinitionException}.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> the actual type of {@code ConstraintDefinitionException}
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractConstraintDefinitionExceptionAssert<
        SELF extends AbstractConstraintDefinitionExceptionAssert<SELF, ACTUAL>,
        ACTUAL extends RuntimeException>
        extends AbstractExtendedValidationExceptionAssert<SELF, ACTUAL> {

    /**
     * An interface for getting values from an actual value of {@code ConstraintDefinitionException} class.
     *
     * @param <CONSTRAINT_DEFINITION_EXCEPTION> actual type of {@code ConstraintDefinitionException}.
     */
    protected interface Accessor<CONSTRAINT_DEFINITION_EXCEPTION extends RuntimeException> {

    }

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual   the actual value to verify.
     * @param selfType a self type.
     * @param accessor an accessor for getting values from {@code actual}.
     */
    protected AbstractConstraintDefinitionExceptionAssert(final ACTUAL actual, final Class<?> selfType,
                                                          final Accessor<ACTUAL> accessor) {
        super(actual, selfType);
        this.accessor = requireNonNull(accessor, "accessor is null");
    }

    /**
     * The accessor for getting values from {@link #actual}.
     */
    protected final Accessor<ACTUAL> accessor;
}
