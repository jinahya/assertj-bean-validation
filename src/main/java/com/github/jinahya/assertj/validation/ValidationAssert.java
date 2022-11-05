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

import org.assertj.core.api.Assert;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.function.Consumer;

/**
 * A base interface for verifying beans and values.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public interface ValidationAssert<SELF extends ValidationAssert<SELF, ACTUAL>, ACTUAL>
        extends Assert<SELF, ACTUAL> {

    /**
     * Configures this assertion object to use specified validator for validation.
     *
     * @param validator the validator to use; may be {@code null}.
     * @return this assertion object.
     */
    SELF usingValidator(Validator validator);

    /**
     * Configures this assertion object to use specified groups targeted for validation.
     *
     * @param groups the validation groups to use; may be {@code null} or empty.
     * @return this assertion object.
     */
    SELF targetingGroups(Class<?>... groups);

    /**
     * Configures this assertion object to accept constraint violations, if any populated while validating the
     * {@code actual} value, to specified consumer.
     *
     * @param consumer the consumer accepts each constraint violations; may be {@code null}.
     * @return this assertion object.
     */
    SELF consumingViolations(Consumer<? super ConstraintViolation<?>> consumer);
}
