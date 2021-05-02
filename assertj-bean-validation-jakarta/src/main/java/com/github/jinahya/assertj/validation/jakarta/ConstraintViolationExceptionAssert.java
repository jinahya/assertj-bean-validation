package com.github.jinahya.assertj.validation.jakarta;

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

import com.github.jinahya.assertj.validation.AbstractConstraintViolationExceptionAssert;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import java.util.Set;

/**
 * An assertion class for {@link jakarta.validation.ConstraintViolationException}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintViolationExceptionAssert
        extends AbstractConstraintViolationExceptionAssert
                        <ConstraintViolationExceptionAssert, ConstraintViolationException, ConstraintViolation<?>> {

    private static class AccessorImpl
            implements Accessor<ConstraintViolationException, ConstraintViolation<?>> {

        @Override
        public Set<? extends ConstraintViolation<?>> getConstraintViolations(
                final ConstraintViolationException actual) {
            return actual.getConstraintViolations();
        }
    }

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to verify.
     */
    public ConstraintViolationExceptionAssert(final ConstraintViolationException actual) {
        super(actual, ConstraintViolationExceptionAssert.class, new AccessorImpl());
    }
}
