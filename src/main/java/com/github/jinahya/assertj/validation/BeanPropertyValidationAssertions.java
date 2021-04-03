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
 * A class for fluently creating instances of {@link BeanPropertyValidationAssert}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public final class BeanPropertyValidationAssertions {

    /**
     * Creates a new assertion instance for specified property value.
     *
     * @param <ACTUAL> property type parameter
     * @param actual   the property value.
     * @return an assertion instance for {@code actual}.
     * @see #assertThat(BeanPropertyWrapper)
     */
    @SuppressWarnings({"java:S119"})
    public static <ACTUAL> BeanPropertyValidationAssert<ACTUAL> assertBeanProperty(final ACTUAL actual) {
        return new BeanPropertyValidationAssert<>(actual);
    }

    /**
     * Creates a new assertion instance for the property value wrapped in specified wrapper.
     *
     * @param <ACTUAL> property type parameter
     * @param wrapper  the wrapper wraps the property value; must not be {@code null}.
     * @return an assertion instance for {@link BeanPropertyWrapper#getActual() wrapper.actual}.
     * @see BeanPropertyWrapper#beanProperty(Object)
     * @see #assertBeanProperty(Object)
     */
    @SuppressWarnings({"java:S119"})
    public static <ACTUAL> BeanPropertyValidationAssert<ACTUAL> assertThat(
            final BeanPropertyWrapper<? extends ACTUAL> wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertBeanProperty(wrapper.getActual());
    }

    /**
     * Creates a new instance.
     */
    private BeanPropertyValidationAssertions() {
        throw new AssertionError("instantiation is not allowed");
    }
}
