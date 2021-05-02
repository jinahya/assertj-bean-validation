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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;

import static com.github.jinahya.assertj.validation.ReflectionUtils.getClassForSuffixOrError;
import static java.util.Objects.requireNonNull;

/**
 * A utility class for Bean-Validation.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class ValidatorFactoryUtils {

    private static final Class<?> VALIDATOR_FACTORY_CLASS
            = getClassForSuffixOrError("ValidatorFactory", ExceptionInInitializerError::new);

    // -------------------------------------------------------------------------------------------------- getValidator()
    private static final Method GET_VALIDATOR_METHOD;

    private static final MethodHandle GET_VALIDATOR_METHOD_HANDLE;

    static {
        try {
            GET_VALIDATOR_METHOD = VALIDATOR_FACTORY_CLASS.getMethod("getValidator");
            GET_VALIDATOR_METHOD_HANDLE = MethodHandles.lookup().unreflect(GET_VALIDATOR_METHOD);
        } catch (final Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @SuppressWarnings({"unchecked"})
    static <VALIDATOR> VALIDATOR getValidator(final Object validatorFactory) {
        requireNonNull(validatorFactory, "validatorFactory is null");
        try {
            return (VALIDATOR) GET_VALIDATOR_METHOD_HANDLE.invoke(validatorFactory);
        } catch (final Throwable t) {
            throw new RuntimeException(t);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ValidatorFactoryUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
