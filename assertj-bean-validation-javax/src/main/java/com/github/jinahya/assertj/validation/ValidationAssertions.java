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

/**
 * A utility class for fluently creating instances of {@link BeanAssertImpl}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public final class ValidationAssertions {

    /**
     * Creates a new assertion object for verifying specified bean object.
     *
     * @param <ACTUAL> type of actual bean
     * @param actual   the actual bean object to verify.
     * @return a new assertion instance for {@code actual}.
     */
    public static <ACTUAL> AbstractBeanAssert<?, ACTUAL> assertBean(final ACTUAL actual) {
        return new BeanAssertImpl<>(actual);
    }

    /**
     * Creates a new assertion object for verifying specified value against a property of specific bean type.
     *
     * @param <ACTUAL> type of actual value
     * @param actual   the actual property value to verify.
     * @return a new assertion instance for {@code actual}.
     */
    public static <ACTUAL> AbstractValueAssert<?, ACTUAL> assertValue(final ACTUAL actual) {
        return new ValueAssertImpl<>(actual);
    }

    /**
     * Creates a new instance.
     */
    private ValidationAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
