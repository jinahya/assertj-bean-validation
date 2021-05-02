package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation-base
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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SuppressWarnings({"java:S119"})
public abstract class AbstractBeanAssertTest<
        ASSERT extends AbstractBeanAssert<ASSERT, Object, VALIDATOR, CONSTRAINT_VIOLATION>,
        VALIDATOR,
        CONSTRAINT_VIOLATION>
        extends AbstractValidationAssertTest<ASSERT, Object, VALIDATOR> {

    protected AbstractBeanAssertTest(final Class<ASSERT> assertClass) {
        super(assertClass, Object.class);
    }
}
