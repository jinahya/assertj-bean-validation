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

/**
 * An assertion class for validating values against constraints defined on property of specified bean type.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
interface ValueAssert<SELF extends ValueAssert<SELF, ACTUAL, VALIDATOR>, ACTUAL, VALIDATOR>
        extends ValidationAssert<SELF, VALIDATOR> {

    /**
     * Verifies that the {@code actual} would be valid for specified property of specified class.
     *
     * @param beanType     the class whose all constraints placed on specified property are examined.
     * @param propertyName the name of the property.
     * @param <T>          bean type parameter
     * @return this assertion object.
     */
    <T> SELF isValidFor(@NotNull Class<T> beanType, @NotNull String propertyName);
}
