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
 * An interface for verifying beans.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public interface BeanAssert<SELF extends BeanAssert<SELF, ACTUAL>, ACTUAL>
        extends IBeanAssert<SELF, ACTUAL, Validator> {

    /**
     * Verifies that the {@code actual} bean object is valid while accepting constraint violations, if any populated, to
     * specified consumer.
     *
     * @param consumer the consumer accepts constraint violations; not {@code null}.
     * @return this assertion object.
     * @see #isValid()
     */
    SELF isValid(Consumer<? super ConstraintViolation<ACTUAL>> consumer);

    /**
     * Verifies that the {@code actual} value is valid.
     *
     * @return this assertion object.
     * @implNote This method invokes {@link #isValid(Consumer)} method with a consumer does nothing.
     */
    @Override
    default SELF isValid() {
        return isValid(
                v -> {
                }
        );
    }

    /**
     * Verifies that all constraints placed on the property of specified name, of {@code actual} bean object, are
     * validated while accepting constraint violation, if any populated, to specified consumer.
     *
     * @param propertyName the name of the property whose constraints are validated; not {@code null}.
     * @param consumer     the consumer accepts constraint violations; not {@code null}.
     * @return this assertion object.
     * @see #hasValidProperty(String)
     */
    SELF hasValidProperty(String propertyName, Consumer<? super ConstraintViolation<ACTUAL>> consumer);

    /**
     * Verifies that all constraints placed on the property of specified name, of {@code actual} value, are validated.
     *
     * @param propertyName the name of the property whose constraints are validated; not {@code null}.
     * @return this assertion object.
     * @apiNote Note that the {@link javax.validation.Valid} is not honored by the {@link
     * Validator#validateProperty(Object, String, Class[])} method on which this method relies.
     * @implNote This method invokes {@link #hasValidProperty(String, Consumer)} method with {@code propertyName} and a
     * consumer does nothing.
     */
    @Override
    default SELF hasValidProperty(final String propertyName) {
        return hasValidProperty(
                propertyName,
                v -> {
                    // does nothing.
                }
        );
    }
}
