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

import javax.validation.GroupDefinitionException;

import static java.util.Objects.requireNonNull;

/**
 * A class for fluently creating assertions for verifying {@link GroupDefinitionException}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class GroupDefinitionExceptionAssertions {

    /**
     * Creates a new assertion for verifying specified actual value.
     *
     * @param actual the actual value to verify.
     * @return a new assertion for {@code actual}.
     */
    public static GroupDefinitionExceptionAssert assertThat(final GroupDefinitionException actual) {
        return new GroupDefinitionExceptionAssert(actual);
    }

    /**
     * Creates a new assertion for verifying specified actual value.
     *
     * @param actual the actual value to verify.
     * @return a new assertion for {@code actual}.
     * @see #assertThat(GroupDefinitionException)
     */
    public static GroupDefinitionExceptionAssert assertGroupDefinitionException(final GroupDefinitionException actual) {
        return new GroupDefinitionExceptionAssert(actual);
    }

    /**
     * Creates a new assertion for verifying the actual value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the actual value to verify.
     * @return a new assertion for {@code wrapper.actual}.
     * @see #assertThat(GroupDefinitionException)
     * @see GroupDefinitionExceptionWrapper#groupDefinitionException(GroupDefinitionException)
     */
    public static GroupDefinitionExceptionAssert assertThat(
            final AbstractWrapper<? extends GroupDefinitionException> wrapper) {
        return assertThat(requireNonNull(wrapper).getActual());
    }

    private GroupDefinitionExceptionAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
