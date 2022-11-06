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

import javax.validation.Validator;

/**
 * An interface for verifying bean objects.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public interface BeanAssert<SELF extends BeanAssert<SELF, ACTUAL>, ACTUAL>
        extends PropertyAssert<SELF, ACTUAL> {

    /**
     * Verifies that the {@code actual} bean is valid.
     * <p>
     * {@snippet lang = "java" id = "example":
     * class User {
     *     @NotBlank String name;
     *     @Max(0x7F) @PositiveOrZero int age;
     * }
     *
     * class UserTest {
     *     @Test void test() {
     *         // @highlight region substring="fail" type=highlighted
     *         // @link region substring="assertThatBean" target="com.github.jinahya.assertj.validation.ValidationAssertions#assertThatBean(Object)"
     *         assertThatBean(new User("Jane", 28)).isValid(); // should pass
     *         assertThatBean(new User(  null,  0)).isValid(); // should fail // @highlight regex="\-?null" type=highlighted
     *         assertThatBean(new User("John", -1)).isValid(); // should fail // @highlight regex="\-?\d+" type=highlighted
     *         // @end
     *         // @end
     *     }
     * }
     *}
     *
     * @return this assertion object.
     * @throws AssertionError when the {@code actual} is {@code null} or invalid.
     */
    // https://docs.oracle.com/en/java/javase/18/code-snippet/index.html
    SELF isValid();

    /**
     * Verifies that all constraints placed on the property of specified name, of {@code actual} bean, are validated.
     * <p>
     * {@snippet lang = "java" id = "example":
     * class User {
     *     @NotBlank String name;
     *     @Max(0x7F) @PositiveOrZero int age;
     * }
     *
     * class UserTest {
     *     @Test void test() {
     *         // @highlight region substring="fail" type=highlighted
     *         // @link region substring="assertThatBean" target="com.github.jinahya.assertj.validation.ValidationAssertions#assertThatBean(Object)"
     *         assertThatBean(new User("Jane", 28)).hasValidProperty("name"); // should pass
     *         assertThatBean(new User("John", 28)).hasValidProperty( "age"); // should pass
     *         assertThatBean(new User(  null,  0)).hasValidProperty("name"); // should fail // @highlight regex="\-?(null|name)" type=highlighted
     *         assertThatBean(new User(  null,  0)).hasValidProperty( "age"); // should pass
     *         assertThatBean(new User("John", -1)).hasValidProperty("name"); // should pass
     *         assertThatBean(new User("John", -1)).hasValidProperty( "age"); // should fail // @highlight regex="\-?(\d+|age)" type=highlighted
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
     * @apiNote Note that the {@link javax.validation.Valid @Valid}, as specified, is not honored by the
     * {@link Validator#validateProperty(Object, String, Class[])} method on which this method relies.
     */
    SELF hasValidProperty(final String propertyName);
}