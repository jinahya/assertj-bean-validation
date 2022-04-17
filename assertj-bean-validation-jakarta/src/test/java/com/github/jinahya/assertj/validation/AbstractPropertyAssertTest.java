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

import jakarta.validation.Validator;

/**
 * An abstract class for testing subclasses of {@link AbstractPropertyAssert} class.
 *
 * @param <SELF>   assertion class type parameter
 * @param <ACTUAL> actual class type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractPropertyAssertTest<SELF extends AbstractPropertyAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractValidationAssertTest<SELF, ACTUAL, Validator> {

    /**
     * Creates a new instance with specified arguments.
     *
     * @param assertionClass an assertion class to test.
     * @param actualClass    an actual class of the {@code assertionClass}.
     */
    protected AbstractPropertyAssertTest(final Class<SELF> assertionClass, final Class<ACTUAL> actualClass) {
        super(assertionClass, actualClass, Validator.class);
    }
}
