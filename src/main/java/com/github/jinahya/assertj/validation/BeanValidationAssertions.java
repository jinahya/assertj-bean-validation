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
 * A class for creating bean validation assertion instances.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class BeanValidationAssertions {

    /**
     * Creates a new bean validation assertion instance for specified bean object.
     *
     * @param object the bean object to be verified; must be not {@code null}.
     * @return a new bean validation assertion instance.
     * @see #assertThat(BeanWrapper)
     */
    public static BeanValidationAssert assertBean(final Object object) {
        requireNonNull(object, "object is null");
        return new BeanValidationAssert(object);
    }

    /**
     * Creates a new bean validation assertion instance for the bean object wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the bean object to be verified; must be not {@code null}.
     * @return a new bean validation assertion instance.
     * @see #assertBean(Object)
     */
    public static BeanValidationAssert assertThat(final BeanWrapper wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertBean(wrapper.getObject());
    }

    /**
     * Creates a new instance.
     */
    protected BeanValidationAssertions() {
        super();
    }
}
