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

import javax.validation.ConstraintDeclarationException;

import static java.util.Objects.requireNonNull;

/**
 * A class for fluently creating assertion instances for {@link ConstraintDeclarationException}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class ConstraintDeclarationExceptionAssertions {

    /**
     * Creates a new assertion instance for specified actual value.
     *
     * @param actual the actual value to verify.
     * @return a new assertion instance for {@code actual}.
     */
    public static ConstraintDeclarationExceptionAssert assertThat(final ConstraintDeclarationException actual) {
        return new ConstraintDeclarationExceptionAssert(actual);
    }

    /**
     * Creates a new assertion instance for specified actual value.
     *
     * @param actual the actual value to verify.
     * @return a new assertion instance for {@code actual}.
     */
    public static ConstraintDeclarationExceptionAssert assertConstraintDeclarationException(
            final ConstraintDeclarationException actual) {
        return assertThat(actual);
    }

    /**
     * Creates a new assertion instance for the value wrapped int specified wrapper.
     *
     * @param wrapper the wrapper wraps the actual value; must not be {@code null}.
     * @return a new assertion instance for {@code wrapper.actual}.
     * @see ConstraintDeclarationExceptionWrapper#constraintDeclarationException(ConstraintDeclarationException)
     */
    public static ConstraintDeclarationExceptionAssert assertThat(
            final AbstractWrapper<? extends ConstraintDeclarationException> wrapper) {
        return assertConstraintDeclarationException(requireNonNull(wrapper).getActual());
    }

    private ConstraintDeclarationExceptionAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
