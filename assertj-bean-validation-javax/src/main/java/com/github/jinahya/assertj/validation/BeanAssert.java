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
     * Verifies that the {@code actual} value is valid while accepting constraint violations, if any populated, to
     * specified consumer.
     *
     * @param consumer the consumer accepts constraint violations; not {@code null}.
     * @return this assertion object.
     * @throws AssertionError when the {@code actual} is {@code null} or is not valid.
     * @see #isValid()
     */
    SELF isValid(Consumer<? super ConstraintViolation<ACTUAL>> consumer);

    /**
     * Verifies that the {@code actual} value is valid.
     *
     * @return this assertion object.
     * @throws AssertionError when the {@code actual} is {@code null} or is not valid.
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
     * Verifies that all constraints placed on the property of specified name, of {@code actual}, are validated while
     * accepting constraint violation, if any populated, to specified consumer.
     *
     * @param propertyName the name of the property to validate; not {@code null}.
     * @param consumer     the consumer accepts constraint violations; not {@code null}.
     * @return this assertion object.
     * @throws AssertionError when the {@code actual} is {@code null} or its current value of {@code propertyName} is
     *                        not valid.
     * @apiNote Note that the {@link javax.validation.Valid @Valid} is not honored by the
     * {@link Validator#validateProperty(Object, String, Class[])} method on which this method relies.
     * @see #hasValidProperty(String)
     */
    SELF hasValidProperty(String propertyName, Consumer<? super ConstraintViolation<ACTUAL>> consumer);

    /**
     * Verifies that all constraints placed on the property of specified name, of {@code actual} value, are validated.
     * <p>
     * {@snippet lang = "java" id = "example":
     * class User {
     *     @Max(0x80) @Min(0x00) int age;
     * }
     *
     * class UserTest {
     *     @Test void test() {
     *         // @highlight region substring="fail" type=highlighted
     *         // @link region substring="assertProperty" target="ValidationAssertions#assertProperty(Object)"
     *         assertProperty( -1).isValidFor(User.class, "age"); // should fail // @highlight regex="\-?\d+" type=highlighted
     *         assertProperty(300).isValidFor(User.class, "age"); // should fail // @highlight regex="\-?\d+" type=highlighted
     *         assertProperty(  0).isValidFor(User.class, "age"); // should pass
     *         assertProperty( 28).isValidFor(User.class, "age"); // should pass
     *         // @end
     *         // @end
     *     }
     * }
     *}
     *
     * @param propertyName the name of the property to be verified as valid; not {@code null}.
     * @return this assertion object.
     * @throws AssertionError when the {@code actual} is {@code null} or its current value of {@code propertyName} is
     *                        not valid.
     * @apiNote Note that the {@link javax.validation.Valid @Valid} is not honored by the
     * {@link Validator#validateProperty(Object, String, Class[])} method on which this method relies.
     * @implNote This method invokes {@link #hasValidProperty(String, Consumer)} method with {@code propertyName} and a
     * consumer does nothing.
     * @see #hasValidProperty(String, Consumer)
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
