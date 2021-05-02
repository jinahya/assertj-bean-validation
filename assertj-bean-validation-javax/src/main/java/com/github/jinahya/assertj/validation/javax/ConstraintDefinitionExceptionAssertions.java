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

import com.github.jinahya.assertj.validation.AbstractWrapper;

import javax.validation.ConstraintDefinitionException;

import static java.util.Objects.requireNonNull;

/**
 * A class for fluently creating assertion instances for {@link ConstraintDefinitionException}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class ConstraintDefinitionExceptionAssertions {

    /**
     * Creates a new assertion instance for verifying specified actual value.
     *
     * @param actual the actual value to verify.
     * @return a new assertion instance.
     */
    public static ConstraintDefinitionExceptionAssert assertThat(final ConstraintDefinitionException actual) {
        return new ConstraintDefinitionExceptionAssert(actual);
    }

    /**
     * Creates a new assertion instance for verifying the actual value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the actual value to verify.
     * @return a new assertion instance.
     */
    public static ConstraintDefinitionExceptionAssert assertThat(
            final AbstractWrapper<? extends ConstraintDefinitionException> wrapper) {
        return assertThat(requireNonNull(wrapper, "wrapper is null").getActual());
    }

    /**
     * Creates a new assertion instance for verifying specified actual value.
     *
     * @param actual the actual value to verify.
     * @return a new assertion instance.
     */
    public static ConstraintDefinitionExceptionAssert assertConstraintDefinitionException(
            final ConstraintDefinitionException actual) {
        return assertThat(actual);
    }

    private ConstraintDefinitionExceptionAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
