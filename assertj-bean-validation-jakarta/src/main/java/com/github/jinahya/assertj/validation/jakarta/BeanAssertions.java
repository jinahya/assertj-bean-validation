package com.github.jinahya.assertj.validation.jakarta;

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

import com.github.jinahya.assertj.validation.BeanWrapper;

import static java.util.Objects.requireNonNull;

/**
 * A class for fluently creating instances of {@link BeanAssert} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public final class BeanAssertions {

    /**
     * Creates a new assertion instance for specified bean.
     *
     * @param actual   the bean to verify.
     * @param <ACTUAL> bean type parameter
     * @return a new assertion instance for {@code actual}.
     */
    public static <ACTUAL> BeanAssert<ACTUAL> assertThat(final ACTUAL actual) {
        return new BeanAssert<>(actual);
    }

    /**
     * Creates a new assertion instance for the bean object reference wrapped in specified wrapper.
     *
     * @param <ACTUAL> actual value type parameter
     * @param wrapper  the wrapper wraps an actual value to verify; must not be {@code null}.
     * @return a assertion instance for {@link BeanWrapper#getActual()} wrapper.actual}.
     * @see BeanWrapper#bean(Object)
     * @see #assertBean(Object)
     */
    public static <ACTUAL> BeanAssert<ACTUAL> assertThat(final BeanWrapper<? extends ACTUAL> wrapper) {
        return assertThat(requireNonNull(wrapper, "wrapper is null").getActual());
    }

    /**
     * Creates a new assertion instance for specified bean object.
     *
     * @param <ACTUAL> actual value type parameter
     * @param actual   the bean object to verify.
     * @return a new assertion instance for {@code object}.
     * @see #assertThat(BeanWrapper)
     */
    public static <ACTUAL> BeanAssert<ACTUAL> assertBean(final ACTUAL actual) {
        return assertThat(actual);
    }

    private BeanAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
