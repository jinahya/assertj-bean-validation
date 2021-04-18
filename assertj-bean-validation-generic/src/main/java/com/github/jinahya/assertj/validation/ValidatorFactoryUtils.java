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

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

/**
 * A utility class for Bean-Validation.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class ValidatorFactoryUtils {

    private static final String SUFFIX = "ValidatorFactory";

    static <R> R applyValidatorFactoryClass(final Function<? super Class<?>, ? extends R> function) {
        return ReflectionUtils.applyClassForSuffix(SUFFIX, function);
    }

    private static Class<?> validatorFactoryClass = null;

    static Class<?> getValidatorFactoryClass() {
        if (validatorFactoryClass == null) {
            validatorFactoryClass = applyValidatorFactoryClass(Function.identity());
        }
        return validatorFactoryClass;
    }

    @SuppressWarnings({"unchecked"})
    static <VALIDATOR> VALIDATOR getValidator(final Object validatorFactory) {
        requireNonNull(validatorFactory, "validatorFactory is null");
        try {
            return (VALIDATOR) getValidatorFactoryClass().getMethod("getValidator").invoke(validatorFactory);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ValidatorFactoryUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
