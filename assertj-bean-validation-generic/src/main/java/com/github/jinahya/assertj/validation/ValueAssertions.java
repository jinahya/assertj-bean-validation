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
 * A class for fluently creating instances of {@link ValueAssert}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class ValueAssertions {

    /**
     * Creates a new assertion instance for specified property value.
     *
     * @param actual the property value.
     * @return an assertion instance for {@code actual}.
     */
    public static ValueAssert assertThat(final Object actual) {
        return new ValueAssert(actual);
    }

    /**
     * Creates a new assertion instance for the property value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the property value; must not be {@code null}.
     * @return an assertion instance for {@link ValueWrapper#getActual() wrapper.actual}.
     */
    public static ValueAssert assertThat(final ValueWrapper wrapper) {
        return assertThat(requireNonNull(wrapper, "wrapper is null").getActual());
    }

    /**
     * Creates a new assertion instance for specified property value.
     *
     * @param actual the property value.
     * @return an assertion instance for {@code actual}.
     */
    public static ValueAssert assertValue(final Object actual) {
        return assertThat(actual);
    }

    /**
     * Creates a new instance.
     */
    private ValueAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
