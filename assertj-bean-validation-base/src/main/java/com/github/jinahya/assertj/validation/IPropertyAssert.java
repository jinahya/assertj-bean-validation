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

@SuppressWarnings({"java:S119"})
interface IPropertyAssert<SELF extends IPropertyAssert<SELF, ACTUAL, VALIDATOR>, ACTUAL, VALIDATOR>
        extends ValidationAssert<SELF, ACTUAL, VALIDATOR> {

    /**
     * Verifies that the {@code actual} value is valid for the property of specified name of specified bean type.
     *
     * @param beanType     the bean type; not {@code null}.
     * @param propertyName the name of the property; not {@code null}.
     * @param <T>          type of the bean
     * @return this assertion instance.
     * @apiNote Note that the {@code @Valid} is not honored by the {@code Validator#validateValue(Class<T> beanType,
     * String propertyName, Object value, Class<?>... groups)} method on which this method relies.
     */
    <T> SELF isValidFor(Class<T> beanType, String propertyName);
}
