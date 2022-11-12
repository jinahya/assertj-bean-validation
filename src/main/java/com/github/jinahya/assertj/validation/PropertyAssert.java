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

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * An interface for verifying values against properties of bean types.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class PropertyAssert<SELF extends PropertyAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractAssert<SELF, ACTUAL>
        implements ValidationAssert<SELF, ACTUAL> {

    protected PropertyAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    @SuppressWarnings({
            "java:S1181", // catch(Throwable)
            "java:S106" // System.err
    })
    public <T> SELF isValidFor(final Class<T> beanType, final String propertyName,
                               final Consumer<? super Set<ConstraintViolation<T>>> consumer) {
        Objects.requireNonNull(beanType, "beanType is null");
        Objects.requireNonNull(propertyName, "propertyName is null");
        Objects.requireNonNull(consumer, "consumer is null");
        final Validator validator = delegate.getValidator();
        final Class<?>[] groups = delegate.getGroups();
        final Set<ConstraintViolation<T>> violations = validator.validateValue(beanType, propertyName, actual, groups);
        ValidationAssertUtils.accept(violations, consumer);
        Assertions.assertThat(violations)
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual: %s%n"
                    + "\tagainst%n"
                    + "\t\tbeanType: %s%n"
                    + "\t\tproperty: '%s'%n"
                    + "\t\t  groups: %s%n",
                    actual,
                    beanType,
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
        return myself;
    }

    <T> SELF isNotValidFor(final Class<T> beanType, final String propertyName) {
//        isNotValidForExtractingConstraintViolations(beanType, propertyName);
//        return myself;
        Objects.requireNonNull(beanType, "beanType is null");
        Objects.requireNonNull(propertyName, "propertyName is null");
        final Validator validator = delegate.getValidator();
        final Class<?>[] groups = delegate.getGroups();
        final Set<ConstraintViolation<T>> violations = validator.validateValue(beanType, propertyName, actual, groups);
        delegate.setViolations(violations);
        Assertions.assertThat(delegate.getViolations())
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual: %s%n"
                    + "\tagainst%n"
                    + "\t\tbeanType: %s%n"
                    + "\t\tproperty: '%s'%n"
                    + "\t\t  groups: %s%n",
                    actual,
                    beanType,
                    propertyName,
                    Arrays.asList(groups)
                )
                .withFailMessage(() -> String.format("%nexpected to be not empty but does not contain any element%n"))
                .isNotEmpty();
        return myself;
    }

    <T> IterableConstraintViolationAssert<?, T> isNotValidForExtractingConstraintViolations(
            final Class<T> beanType, final String propertyName) {
        Objects.requireNonNull(beanType, "beanType is null");
        Objects.requireNonNull(propertyName, "propertyName is null");
        final Validator validator = delegate.getValidator();
        final Class<?>[] groups = delegate.getGroups();
        final Set<ConstraintViolation<T>> violations = validator.validateValue(beanType, propertyName, actual, groups);
        Assertions.assertThat(violations)
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual: %s%n"
                    + "\tagainst%n"
                    + "\t\tbeanType: %s%n"
                    + "\t\tproperty: '%s'%n"
                    + "\t\t  groups: %s%n",
                    actual,
                    beanType,
                    propertyName,
                    Arrays.asList(groups)
                )
                .withFailMessage(() -> String.format("%nexpected to be not empty but does not contain any element%n")
                )
                .isNotEmpty();
        return ValidationAssertions.assertThatIterableConstraintViolations(violations);
    }

    /**
     * Verifies that the {@code actual} value is valid for the property of specified name of specified bean type.
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
     *         // @link region substring="assertThatProperty" target="ValidationAssertions#assertThatProperty(Object)"
     *         assertThatProperty("Jane").isValidFor(User.class, "name"); // should pass
     *         assertThatProperty(  null).isValidFor(User.class, "name"); // should fail // @highlight regex="\-?(null|name)" type=highlighted
     *         assertThatProperty(    "").isValidFor(User.class, "name"); // should fail // @highlight regex='(\"\"|name)' type=highlighted
     *         assertThatProperty(   " ").isValidFor(User.class, "name"); // should fail // @highlight regex='(\"\s\"|name)' type=highlighted
     *         assertThatProperty(     0).isValidFor(User.class,  "age"); // should pass
     *         assertThatProperty(    28).isValidFor(User.class,  "age"); // should pass
     *         assertThatProperty(    -1).isValidFor(User.class,  "age"); // should fail // @highlight regex="\-?(\d+|age)" type=highlighted
     *         assertThatProperty(   300).isValidFor(User.class,  "age"); // should fail // @highlight regex="\-?(\d+|age)" type=highlighted
     *         // @end
     *         // @end
     *     }
     * }
     *}
     *
     * @param beanType     the bean type; must be not {@code null}.
     * @param propertyName the name of the property; must be not {@code null}.
     * @param <T>          type of the object to validate
     * @return this assertion object.
     * @throws AssertionError when the {@code actual} is not valid for {@code beanType#propertyName}.
     * @apiNote Note that the {@link javax.validation.Valid @Valid} is not honored by the
     * {@link Validator#validateValue(Class, String, Object, Class[])} method on which this method relies.
     */
    public <T> SELF isValidFor(final Class<T> beanType, final String propertyName) {
        return isValidFor(
                beanType,
                propertyName,
                i -> {
                }
        );
    }

    /**
     * Configures this assertion object to use specified groups targeted for validation.
     *
     * @param groups the validation groups to use; may be {@code null} or empty.
     * @return this assertion object.
     */
    public SELF targetingGroups(final Class<?>... groups) {
        delegate.setGroups(groups);
        return myself;
    }

    private final ValidationAssertDelegate delegate = new ValidationAssertDelegate();
}
