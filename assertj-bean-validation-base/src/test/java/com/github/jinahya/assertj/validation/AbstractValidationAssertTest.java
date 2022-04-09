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

import java.lang.reflect.Constructor;

public abstract class AbstractValidationAssertTest<
        SELF extends AbstractValidationAssert<SELF, ACTUAL, VALIDATOR>,
        ACTUAL,
        VALIDATOR>
        extends ValidationAssertTest<SELF, ACTUAL, VALIDATOR> {

    protected AbstractValidationAssertTest(final Class<SELF> assertClass, final Class<ACTUAL> actualClass,
                                           final Class<VALIDATOR> validatorClass) {
        super(assertClass, actualClass, validatorClass);
    }

    protected SELF assertInstance(final ACTUAL actual) {
        try {
            final Constructor<SELF> constructor = assertClass.getDeclaredConstructor(actualClass);
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }
}
