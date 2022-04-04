/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2021 the original author or authors.
 */
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

interface BeanAssert<
        SELF extends BeanAssert<SELF, ACTUAL, VALIDATOR, CONSTRAINT_VIOLATION>,
        ACTUAL,
        VALIDATOR,
        CONSTRAINT_VIOLATION>
        extends ValidationAssert<SELF, VALIDATOR> {

    SELF usingValidator(VALIDATOR validator);

    SELF targetingGroups(Class<?>... groups);

    /**
     * Verifies that the {@code actual} bean is valid.
     *
     * @return this assertion object.
     */
    SELF isValid();

    /**
     * Verifies that the {@code actual} bean has as valid property value of specified name.
     *
     * @param propertyName the name of the property to verify.
     * @return this assertion object.
     */
    SELF hasValidProperty(@NotNull String propertyName);
}