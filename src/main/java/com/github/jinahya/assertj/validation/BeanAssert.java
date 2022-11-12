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

import org.assertj.core.api.Assertions;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * An interface for verifying bean objects.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({
        "java:S119" // <SELF, ACTUAL>
})
public abstract class BeanAssert<SELF extends BeanAssert<SELF, ACTUAL>, ACTUAL>
        extends PropertyAssert<SELF, ACTUAL> {

    protected BeanAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

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
    @SuppressWarnings({
            "java:S1181", // catch(Throwable)
            "java:S106" // System.err
    })
    public SELF isValid(final Consumer<? super Set<ConstraintViolation<ACTUAL>>> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        final SELF self = isNotNull();
        final Validator validator = delegate.getValidator();
        final Class<?>[] groups = delegate.getGroups();
        final Set<ConstraintViolation<ACTUAL>> violations = validator.validate(actual, groups);
        ValidationAssertUtils.accept(violations, consumer);
        Assertions.assertThat(violations)
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual: %s%n"
                    + "targeting%n"
                    + "\tgroups: %s%n",
                    actual,
                    Arrays.asList(groups)
                )
                .withFailMessage(() -> String.format(
                        "%nexpected to be empty but contains %1$d element(s)%n"
                        + "%2$s",
                        violations.size(),
                        ValidationAssertMessages.format(violations)
                ))
                .isEmpty();
        return self;
    }

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
    public SELF isValid() {
        return isValid(
                i -> {
                }
        );
    }

    SELF isNotValid() {
        final SELF self = isNotNull();
        final Validator validator = delegate.getValidator();
        final Class<?>[] groups = delegate.getGroups();
        final Set<ConstraintViolation<ACTUAL>> violations = validator.validate(actual, groups);
        Assertions.assertThat(violations)
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual: %s%n"
                    + "targeting%n"
                    + "\tgroups: %s%n",
                    actual,
                    Arrays.asList(groups)
                )
                .withFailMessage(() -> String.format("%nexpected to be not empty but does not contain any element%n"))
                .isNotEmpty();
        return self;
    }

    /**
     * Verified that no constraint violations populated while validating all constraints placed on the property of
     * specified name of the {@code actual}.
     *
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
    @SuppressWarnings({
            "java:S1181", // catch(Throwable)
            "java:S106" // System.err
    })
    public SELF hasValidProperty(final String propertyName,
                                 final Consumer<? super Set<ConstraintViolation<ACTUAL>>> consumer) {
        Objects.requireNonNull(propertyName, "propertyName is null");
        Objects.requireNonNull(consumer, "consumer is null");
        final SELF self = isNotNull();
        final Validator validator = delegate.getValidator();
        final Class<?>[] groups = delegate.getGroups();
        final Set<ConstraintViolation<ACTUAL>> violations = validator.validateProperty(actual, propertyName, groups);
        ValidationAssertUtils.accept(violations, consumer);
        Assertions.assertThat(violations)
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual: %s%n"
                    + "for its%n"
                    + "\tproperty: '%s'%n"
                    + "targeting %n"
                    + "\tgroups: %s%n",
                    actual,
                    propertyName,
                    Arrays.asList(groups)
                )
                .withFailMessage(() -> String.format(
                        "%nexpected to be empty but contains %1$d element(s)%n"
                        + "%2$s",
                        violations.size(),
                        ValidationAssertMessages.format(violations)
                ))
                .isEmpty();
        return self;
    }

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
     * @throws AssertionError when the {@code actual} is {@code null} or its current value of the {@code propertyName}
     *                        is not valid.
     * @apiNote Note that the {@link javax.validation.Valid @Valid}, as specified, is not honored by the
     * {@link Validator#validateProperty(Object, String, Class[])} method on which this method relies.
     */
    public SELF hasValidProperty(final String propertyName) {
        return hasValidProperty(
                propertyName,
                i -> {
                }
        );
    }

    public SELF doesNotHaveValidProperty(final String propertyName) {
        Objects.requireNonNull(propertyName, "propertyName is null");
        final SELF self = isNotNull();
        final Validator validator = delegate.getValidator();
        final Class<?>[] groups = delegate.getGroups();
        final Set<ConstraintViolation<ACTUAL>> violations = validator.validateProperty(actual, propertyName, groups);
        Assertions.assertThat(violations)
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual: %s%n"
                    + "for its%n"
                    + "\tproperty: '%s'%n"
                    + "targeting %n"
                    + "\tgroups: %s%n",
                    actual,
                    propertyName,
                    Arrays.asList(groups)
                )
                .withFailMessage(() -> String.format("%nexpected to be not empty but does not contains any element%n"))
                .isNotEmpty();
        return self;
    }

    private final ValidationAssertDelegate delegate = new ValidationAssertDelegate();
}
