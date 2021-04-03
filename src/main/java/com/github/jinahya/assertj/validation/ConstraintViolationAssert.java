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

import java.util.function.Function;

import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.applyConstraintViolationClassFor;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.requireConstraintViolationInstance;

/**
 * An assertion class for verifying an instance of {@code ConstraintViolation}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintViolationAssert<T>
        extends AbstractConstraintViolationAssert<ConstraintViolationAssert<T>, Object, T> {

    /**
     * Creates a new instance for specified actual value.
     *
     * @param actual the actual value to verify; must not be {@code null} and must be an instance of either {@code
     *               javax.validation.ConstraintViolation} or {@code jakarta.validation.ConstraintViolation}.
     * @see #actual
     */
    public ConstraintViolationAssert(final Object actual) {
        super(requireConstraintViolationInstance(actual), ConstraintViolationAssert.class,
              applyConstraintViolationClassFor(actual, Function.identity()));
    }
}
