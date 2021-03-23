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

public class BeanPropertyValidationAssertions {

    /**
     * Creates a new assert for specified property value.
     *
     * @param value the property value.
     * @return an assert for {@code value}.
     */
    public static BeanPropertyValidationAssert assertBeanProperty(final Object value) {
        return new BeanPropertyValidationAssert(value);
    }

    /**
     * Creates a new assert for the property value wrapped in specified wrapper.
     *
     * @param wrapper the wrapper wraps the property value.
     * @return an assert for {@code wrapper.value}.
     */
    public static BeanPropertyValidationAssert assertThat(final BeanPropertyWrapper wrapper) {
        requireNonNull(wrapper, "wrapper is null");
        return assertBeanProperty(wrapper.getValue());
    }

    /**
     * Creates a new instance.
     */
    protected BeanPropertyValidationAssertions() {
        super();
    }
}
