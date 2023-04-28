/**
 * Defines assertion classes, based on top of <a href="https://assertj.github.io/doc/">AssertJ</a>, for fluently
 * verifying objects and values using <a href="https://beanvalidation.org/">Bean-Validation</a>.
 * <p>
 * {@snippet lang = "java" id = "example":
 * class User {
 *     @NotBlank String name;
 *     @Max(0x7F) @PositiveOrZero int age;
 * }
 *
 * // @highlight region substring="fail" type=highlighted
 * // @link region substring="assertThatBean" target="com.github.jinahya.assertj.validation.ValidationAssertions#assertThatBean(Object)"
 * // @link region substring="assertThatProperty" target="ValidationAssertions#assertThatProperty(Object)"
 * assertThatBean(new User("Jane", 28)).hasValidProperty("name"); // should pass
 * assertThatBean(new User("John", 28)).hasValidProperty( "age"); // should pass
 * assertThatBean(new User(  null,  0)).hasValidProperty("name"); // should fail // @highlight regex="\-?(null|name)" type=highlighted
 * assertThatBean(new User(  null,  0)).hasValidProperty( "age"); // should pass
 * assertThatBean(new User("John", -1)).hasValidProperty("name"); // should pass
 * assertThatBean(new User("John", -1)).hasValidProperty( "age"); // should fail // @highlight regex="\-?(\d+|age)" type=highlighted
 *
 * assertThatProperty("Jane").isValidFor(User.class, "name"); // should pass
 * assertThatProperty(  null).isValidFor(User.class, "name"); // should fail // @highlight regex="\-?(null|name)" type=highlighted
 * assertThatProperty(    "").isValidFor(User.class, "name"); // should fail // @highlight regex='(\"\"|name)' type=highlighted
 * assertThatProperty(   " ").isValidFor(User.class, "name"); // should fail // @highlight regex='(\"\s\"|name)' type=highlighted
 * assertThatProperty(     0).isValidFor(User.class,  "age"); // should pass
 * assertThatProperty(    28).isValidFor(User.class,  "age"); // should pass
 * assertThatProperty(    -1).isValidFor(User.class,  "age"); // should fail // @highlight regex="\-?(\d+|age)" type=highlighted
 * assertThatProperty(   300).isValidFor(User.class,  "age"); // should fail // @highlight regex="\-?(\d+|age)" type=highlighted
 * // @end
 * // @end
 * // @end
 *}
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
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
