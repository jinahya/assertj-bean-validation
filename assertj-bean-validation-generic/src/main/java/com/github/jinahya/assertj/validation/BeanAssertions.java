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
 * A class for fluently creating instances of {@link BeanAssert} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see BeanAssert
 */
public final class BeanAssertions {

    /**
     * Creates a new assertion instance for specified bean object.
     *
     * @param actual the bean object to verify.
     * @return a new assertion instance for {@code actual}.
     * @see #assertThat(BeanWrapper)
     */
    public static BeanAssert assertBean(final Object actual) {
        return new BeanAssert(actual);
    }

    /**
     * Creates a new assertion instance for the bean object wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the actual bean value; must not be {@code null}.
     * @return a assertion instance for {@link BeanWrapper#getActual()} wrapper.actual}.
     * @see BeanWrapper#bean(Object)
     * @see #assertBean(Object)
     */
    public static BeanAssert assertThat(final BeanWrapper<?> wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertBean(wrapper.getActual());
    }

    private BeanAssertions() {
        throw new NonInstantiatableAssertionError();
    }
}
