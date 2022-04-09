package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation-base
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

import java.util.Objects;

public abstract class ValidationAssertTest<SELF extends ValidationAssert<SELF, ACTUAL, VALIDATOR>, ACTUAL, VALIDATOR> {

    protected ValidationAssertTest(final Class<SELF> assertClass, final Class<ACTUAL> actualClass,
                                   final Class<VALIDATOR> validatorClass) {
        super();
        this.assertClass = Objects.requireNonNull(assertClass, "assertClass is null");
        this.actualClass = Objects.requireNonNull(actualClass, "actualClass is null");
        this.validatorClass = Objects.requireNonNull(validatorClass, "validatorClass is null");
    }

    protected final Class<SELF> assertClass;

    protected final Class<ACTUAL> actualClass;

    protected final Class<VALIDATOR> validatorClass;
}
