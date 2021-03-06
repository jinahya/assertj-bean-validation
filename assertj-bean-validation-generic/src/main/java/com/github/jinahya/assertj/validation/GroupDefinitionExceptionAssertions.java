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

/**
 * A class for fluently creating assertions for values of {@code GroupDefinitionException} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class GroupDefinitionExceptionAssertions {

    public static GroupDefinitionExceptionAssert assertThat(final RuntimeException actual) {
        return new GroupDefinitionExceptionAssert(actual);
    }

    public static GroupDefinitionExceptionAssert assertThat(final AbstractWrapper<? extends RuntimeException> wrapper) {
        return assertThat(requireNonNull(wrapper, "wrapper is null").getActual());
    }

    public static GroupDefinitionExceptionAssert assertGroupDefinitionException(final RuntimeException actual) {
        return new GroupDefinitionExceptionAssert(actual);
    }

    private GroupDefinitionExceptionAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
