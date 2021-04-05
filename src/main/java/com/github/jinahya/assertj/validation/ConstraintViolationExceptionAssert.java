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
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"java:S119"})
public class ConstraintViolationExceptionAssert<ACTUAL>
        extends AbstractValidationExceptionAssert<ConstraintViolationExceptionAssert<ACTUAL>> {

    public ConstraintViolationExceptionAssert(final ACTUAL actual) {
        super(ConstraintViolationExceptionUtils.requireConstraintViolationExceptionInstance(actual),
              ConstraintViolationExceptionAssert.class);
    }

    public <T> ConstraintViolationExceptionAssert<ACTUAL> hasConstraintViolationsSatisfying(
            final Consumer<? super Set<? extends T>> requirements) {
        requireNonNull(requirements, "requirements is null");
        return isNotNull().satisfies(a -> {
            final Set<T> constraintViolations = ConstraintViolationExceptionUtils.getConstraintViolations(a);
            requirements.accept(constraintViolations);
        });
    }

    public <T> ConstraintViolationExceptionAssert<ACTUAL> hasConstraintViolationsEqualTo(final Object expected) {
        return this.<T>hasConstraintViolationsSatisfying(v -> {
            assertThat(v).isEqualTo(expected);
        });
    }
}
