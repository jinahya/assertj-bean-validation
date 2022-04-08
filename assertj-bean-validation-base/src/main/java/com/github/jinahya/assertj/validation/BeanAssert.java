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

import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"java:S119"})
public interface BeanAssert<SELF extends BeanAssert<SELF, ACTUAL, VALIDATOR>, ACTUAL, VALIDATOR>
        extends PropertyAssert<SELF, ACTUAL, VALIDATOR> {

    /**
     * Verifies that the {@code actual} bean is valid.
     *
     * @return this assertion object.
     */
    @NotNull SELF isValid();

    /**
     * Verifies that all constraints placed on the property of specified name, of {@code actual}, are validated.
     *
     * @param propertyName the name of the property whose constraints are validated.
     * @return this assertion object.
     * @apiNote Note that the {@code @Valid} is not honored by the {@code Validator#validateProperty(T object, String
     * propertyName, Class<?>... groups)} method which this method invokes.
     */
    @NotNull SELF hasValidProperty(@NotNull String propertyName);
}
