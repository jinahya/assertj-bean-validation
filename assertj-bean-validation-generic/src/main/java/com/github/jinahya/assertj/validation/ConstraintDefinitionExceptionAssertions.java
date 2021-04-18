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

public class ConstraintDefinitionExceptionAssertions {

    public static <ACTUAL extends RuntimeException> ConstraintDefinitionExceptionAssert<ACTUAL>
    assertConstraintDefinitionException(final ACTUAL actual) {
        return new ConstraintDefinitionExceptionAssert<>(actual);
    }

    public static <ACTUAL extends RuntimeException> ConstraintDefinitionExceptionAssert<ACTUAL> assertThat(
            final ConstraintDefinitionExceptionWrapper<? extends ACTUAL> wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertConstraintDefinitionException(requireNonNull(wrapper).getActual());
    }

    private ConstraintDefinitionExceptionAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}