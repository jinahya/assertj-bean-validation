package com.github.jinahya.assertj.validation.javax;

/*-
 * #%L
 * assertj-bean-validation-javax
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

import javax.validation.ConstraintViolation;

/**
 * A class for wrapping instances of {@link ConstraintViolation}.
 *
 * @param <T> the type of the root bean.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class ConstraintViolationWrapper<T>
        extends AbstractWrapper<ConstraintViolation<T>> {

    /**
     * Creates a new instance wraps specified actual value.
     *
     * @param actual the actual value to wrap.
     * @param <T>    the type of the root bean of the {@code actual}.
     * @return a new instance wraps {@code actual}.
     * @see ConstraintViolationAssertions#assertThat(AbstractWrapper)
     */
    public static <T> ConstraintViolationWrapper<T> constraintViolation(final ConstraintViolation<T> actual) {
        return new ConstraintViolationWrapper<>(actual);
    }

    private ConstraintViolationWrapper(final ConstraintViolation<T> actual) {
        super(actual);
    }
}
