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

import static com.github.jinahya.assertj.validation.ConstraintDeclarationExceptionUtils.requireInstanceOfConstraintDeclarationExceptionClass;

/**
 * An assertion class for verifying values of {@code ConstraintDeclarationException} class.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintDeclarationExceptionAssert
        extends AbstractConstraintDeclarationExceptionAssert<ConstraintDeclarationExceptionAssert, RuntimeException> {

    /**
     * An accessor class for getting values from an actual value of {@code ConstraintDeclarationException} class.
     */
    protected static class AccessorImpl
            implements Accessor<RuntimeException> {

    }

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to verify.
     */
    public ConstraintDeclarationExceptionAssert(final RuntimeException actual) {
        super(requireInstanceOfConstraintDeclarationExceptionClass(actual, true),
              ConstraintDeclarationExceptionAssert.class, new AccessorImpl());
    }
}
