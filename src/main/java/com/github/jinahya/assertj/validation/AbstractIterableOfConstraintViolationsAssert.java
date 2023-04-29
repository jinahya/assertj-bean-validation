package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation
 * %%
 * Copyright (C) 2021 - 2023 Jinahya, Inc.
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

import org.assertj.core.api.AbstractIterableAssert;

import javax.validation.ConstraintViolation;

@SuppressWarnings({
        "java:S119" // <SELF>
})
abstract class AbstractIterableOfConstraintViolationsAssert<
        SELF extends AbstractIterableOfConstraintViolationsAssert<SELF, T>, T>
        extends AbstractIterableAssert<
        SELF, Iterable<? extends ConstraintViolation<T>>, ConstraintViolation<T>, ConstraintViolationAssert<T>> {

    protected AbstractIterableOfConstraintViolationsAssert(final Iterable<? extends ConstraintViolation<T>> actual,
                                                           final Class<?> selfType) {
        super(actual, selfType);
    }
}
