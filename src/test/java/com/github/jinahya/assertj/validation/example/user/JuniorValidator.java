package com.github.jinahya.assertj.validation.example.user;

/*-
 * #%L
 * assertj-bean-validation
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

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

class JuniorValidator
        implements ConstraintValidator<Junior, User> {

    static final int MAX_AGE_EXCLUSIVE = 60;

    @Override
    public void initialize(final Junior junior) {
        // empty
    }

    @Override
    public boolean isValid(final User value, final ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.age < MAX_AGE_EXCLUSIVE;
    }
}
