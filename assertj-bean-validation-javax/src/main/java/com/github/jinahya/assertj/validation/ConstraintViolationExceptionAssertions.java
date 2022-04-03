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

import javax.validation.ConstraintViolationException;

import static java.util.Objects.requireNonNull;

/**
 * A class for fluently creating instances of {@link ConstraintViolationExceptionAssert} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintViolationExceptionAssertions {

    /**
     * Creates a new assertion instance for specified actual value.
     *
     * @param actual the actual value to verify.
     * @return a new assertion instance.
     */
    public static ConstraintViolationExceptionAssert assertConstraintViolationException(
            final ConstraintViolationException actual) {
        return new ConstraintViolationExceptionAssert(actual);
    }

    /**
     * Creates a new assertion instance for the actual value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the actual value; must not be {@code null}.
     * @return a new assertion instance.
     */
    public static ConstraintViolationExceptionAssert assertThat(
            final AbstractWrapper<? extends ConstraintViolationException> wrapper) {
        return assertConstraintViolationException(requireNonNull(wrapper).getActual());
    }

    /**
     * Creates a new instance which is not possible.
     */
    private ConstraintViolationExceptionAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
