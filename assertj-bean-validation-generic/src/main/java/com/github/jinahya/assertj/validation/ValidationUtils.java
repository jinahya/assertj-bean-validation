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

import static com.github.jinahya.assertj.validation.ReflectionUtils.getClassForSuffixOrError;

/**
 * A utility class for Bean-Validation.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
final class ValidationUtils {

    private static final Class<?> VALIDATION_CLASS
            = getClassForSuffixOrError("Validation", ExceptionInInitializerError::new);

    private static final Object DEFAULT_VALIDATOR_FACTORY_INSTANCE;

    static {
        try {
            DEFAULT_VALIDATOR_FACTORY_INSTANCE
                    = VALIDATION_CLASS.getMethod("buildDefaultValidatorFactory").invoke(null);
        } catch (final ReflectiveOperationException roe) {
            throw new ExceptionInInitializerError(roe);
        }
    }

    static Object getValidator() {
        return ValidatorFactoryUtils.getValidator(DEFAULT_VALIDATOR_FACTORY_INSTANCE);
    }

    private ValidationUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
