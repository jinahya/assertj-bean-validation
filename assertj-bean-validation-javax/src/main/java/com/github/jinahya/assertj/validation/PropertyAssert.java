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

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.function.Consumer;

/**
 * An interface for verifying values against properties of bean types.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public interface PropertyAssert<SELF extends PropertyAssert<SELF, ACTUAL>, ACTUAL>
        extends ValidationAssert<SELF, ACTUAL, Validator> {

    /**
     * Verifies that the {@code actual} value is valid for the property of specified name of specified bean type while
     * accepting constraint violations, if any populated, to specified consumer.
     *
     * @param beanType     the bean type; not {@code null}.
     * @param propertyName the name of the property; not {@code null}.
     * @param consumer     the consumer accepts constraint violations; not {@code null}.
     * @param <T>          type of the object to validate
     * @return this assertion object.
     * @throws AssertionError when the {@code actual} is {@code null} or is not valid for
     *                        {@code beanType#propertyName}.
     * @see #isValidFor(Class, String)
     */
    <T> SELF isValidFor(Class<T> beanType, String propertyName, Consumer<? super ConstraintViolation<T>> consumer);

    /**
     * Verifies that the {@code actual} value is valid for the property of specified name of specified bean type.
     *
     * @param beanType     the bean type; not {@code null}.
     * @param propertyName the name of the property; not {@code null}.
     * @param <T>          type of the object to validate
     * @return this assertion object.
     * @throws AssertionError when the {@code actual} is {@code null} or is not valid for
     *                        {@code beanType#propertyName}.
     * @apiNote Note that the {@link javax.validation.Valid @Valid} is not honored by the
     * {@link Validator#validateValue(Class, String, Object, Class[])} method on which this method relies.
     * @implNote This method invokes {@link #isValidFor(Class, String, Consumer)} method with {@code beanType},
     * {@code propertyName}, and a consumer does nothing.
     */
    default <T> SELF isValidFor(final Class<T> beanType, final String propertyName) {
        return isValidFor(
                beanType,
                propertyName,
                v -> {
                    // does nothing.
                }
        );
    }
}
