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
 * @see ValueAssert
 */
public final class ValueAssertions {

    /**
     * Creates a new assertion instance with specified property value.
     *
     * @param value the property value.
     * @return an assertion for {@code value}.
     */
    @SuppressWarnings({"java:S119"})
    public static ValueAssert assertValue(final Object value) {
        return new ValueAssert(value);
    }

    /**
     * Creates a new assertion instance for the property value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps an actual value to verify; must not be {@code null}.
     * @return an assertion for {@link ValueWrapper#getActual()} wrapper.actual}.
     * @see ValueWrapper#value(Object)
     * @see #assertValue(Object)
     */
    @SuppressWarnings({"java:S119"})
    public static ValueAssert assertThat(final ValueWrapper wrapper) {
        return assertValue(requireNonNull(wrapper).getActual());
    }

    /**
     * Creates a new instance.
     */
    private ValueAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
