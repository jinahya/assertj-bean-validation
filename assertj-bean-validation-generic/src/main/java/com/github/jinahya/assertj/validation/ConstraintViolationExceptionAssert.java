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

import java.util.Set;

import static com.github.jinahya.assertj.validation.ConstraintViolationExceptionUtils.requireInstanceOfConstraintViolationExceptionClass;

/**
 * An assertion class for verifying an actual value of {@code ConstraintViolationException} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintViolationExceptionAssert
        extends AbstractConstraintViolationExceptionAssert<ConstraintViolationExceptionAssert, RuntimeException, Object> {

    private static class AccessorImpl
            implements Accessor<RuntimeException, Object> {

        @Override
        public Set<?> getConstraintViolations(final RuntimeException actual) {
            return ConstraintViolationExceptionUtils.getConstraintViolations(actual);
        }
    }

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to verify.
     */
    public ConstraintViolationExceptionAssert(final RuntimeException actual) {
        super(requireInstanceOfConstraintViolationExceptionClass(actual, true),
              ConstraintViolationExceptionAssert.class, new AccessorImpl());
    }
}
