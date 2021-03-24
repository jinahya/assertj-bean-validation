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
 * A class for wrapping a property value.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see BeanPropertyValidationAssertions#assertThat(BeanPropertyWrapper)
 */
public class BeanPropertyWrapper {

    /**
     * Creates a new instance wraps specified property value.
     *
     * @param value the property value to wrap.
     * @return a new instance wraps {@code value}.
     */
    public static BeanPropertyWrapper beanProperty(final Object value) {
        return new BeanPropertyWrapper(value);
    }

    /**
     * Creates a new instance with specified property value.
     *
     * @param value the property value to wrap.
     */
    private BeanPropertyWrapper(final Object value) {
        super();
        this.value = value;
    }

    /**
     * Returns the wrapped property value.
     *
     * @return the wrapped property value.
     */
    Object getValue() {
        return value;
    }

    /**
     * The wrapped property value.
     */
    private final Object value;
}
