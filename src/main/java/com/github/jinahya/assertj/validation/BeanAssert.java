package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation-javax
 * %%
 * Copyright (C) 2021 - 2022 Jinahya, Inc.
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

import javax.validation.Validator;

/**
 * An interface for verifying bean objects.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public interface BeanAssert<SELF extends BeanAssert<SELF, ACTUAL>, ACTUAL>
        extends PropertyAssert<SELF, ACTUAL> {

    /**
     * Verifies that the {@code actual} bean is valid.
     *
     * @return this assertion object.
     * @throws AssertionError when the {@code actual} is {@code null} or is not valid.
     */
    SELF isValid();

    /**
     * Verifies that all constraints placed on the property of specified name, of {@code actual} bean, are validated.
     * <p>
     *
     * @param propertyName the name of the property to be verified as valid; not {@code null}.
     * @return this assertion object.
     * @throws AssertionError when the {@code actual} is {@code null} or its current value of {@code propertyName} is
     *                        not valid.
     * @apiNote Note that the {@link javax.validation.Valid @Valid}, as specified, is not honored by the
     * {@link Validator#validateProperty(Object, String, Class[])} method on which this method relies.
     */
    SELF hasValidProperty(final String propertyName);
}
