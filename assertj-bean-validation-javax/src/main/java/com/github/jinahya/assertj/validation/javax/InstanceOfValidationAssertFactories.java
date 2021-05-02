package com.github.jinahya.assertj.validation.javax;

/*-
 * #%L
 * assertj-bean-validation-javax
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

import org.assertj.core.api.InstanceOfAssertFactory;

import javax.validation.ConstraintViolation;

public interface InstanceOfValidationAssertFactories {

    @SuppressWarnings({"unchecked"})
    InstanceOfAssertFactory<ConstraintViolation<?>, ConstraintViolationAssert<?>> CONSTRAINT_VIOLATION =
            new InstanceOfAssertFactory<>((Class<ConstraintViolation<?>>) (Object) ConstraintViolation.class,
                                          ConstraintViolationAssert::new);
}
