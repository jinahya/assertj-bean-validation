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
 * An abstract assert class for verifying a bean value.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see Validator#validate(Object, Class[])
 * @see Validator#validateProperty(Object, String, Class[])
 * @see Validator#validateValue(Class, String, Object, Class[])
 */
@SuppressWarnings({
        "java:S2160" // override equals/hashCode
})
public abstract class AbstractBeanAssert<SELF extends AbstractBeanAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractPropertyAssert<SELF, ACTUAL>
        implements BeanAssert<SELF, ACTUAL> {

    /**
     * Creates a new instance for verifying specified actual value.
     *
     * @param actual   the value of {@link ACTUAL} to verify.
     * @param selfType a class of {@link SELF}.
     */
    protected AbstractBeanAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    /**
     * Verifies that the {@code actual} bean is valid while accepting the set of constraint violations, which may be
     * empty, to specified consumer.
     *
     * @param consumer the consumer accepts the set of constraint violations which may be empty.
     * @return this assertion object.
     * @throws AssertionError when the {@code actual} is {@code null} or invalid.
     * @see #isValid()
     */
    // https://docs.oracle.com/en/java/javase/18/code-snippet/index.html
    public final SELF isValid(final Consumer<? super Set<ConstraintViolation<ACTUAL>>> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        isNotNull();
        final Validator validator = delegate.getValidator();
        final Class<?>[] groups = delegate.getGroups();
        delegate.setViolations(validator.validate(actual, groups));
        ValidationAssertUtils.accept(delegate.getViolations(), consumer);
        Assertions.assertThat(delegate.getViolations())
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
                        delegate.getViolations().size(),
                        ValidationAssertMessages.format(delegate.getViolations())
                ))
                .isEmpty();
        return myself;
    }

    public SELF isNotValid() {
        isNotNull();
        final Validator validator = delegate.getValidator();
        final Class<?>[] groups = delegate.getGroups();
        delegate.setViolations(validator.validate(actual, groups));
        Assertions.assertThat(delegate.getViolations())
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual: %s%n"
                    + "targeting%n"
                    + "\tgroups: %s%n",
                    actual,
                    Arrays.asList(groups)
                )
                .withFailMessage(() -> "%nexpected to be not empty but empty%n")
                .isNotEmpty();
        return myself;
    }

    /**
     * Verified that no constraint violations populated while validating all constraints placed on the property of
     * specified name of the {@code actual}, and accepts the set of constraint violations, which may be empty, to
     * specified consumer.
     *
     * @param propertyName the name of the property to be verified as valid; not {@code null}.
     * @param consumer     the consumer accepts the set of constraint violations.
     * @return this assertion object.
     * @throws AssertionError when the {@code actual} is {@code null} or its current value of {@code propertyName} is
     *                        not valid.
     * @apiNote Note that the {@link javax.validation.Valid @Valid} is not honored by the
     * {@link Validator#validateProperty(Object, String, Class[])} method on which this method relies. See <a
     * href="https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#validationapi-validatorapi-validationmethods">6.1.1.
     * Validation methods (Jakarta Bean Validation 3.0)</a>.
     * @see #hasValidProperty(String)
     */
    public SELF hasValidProperty(final String propertyName,
                                 final Consumer<? super Set<ConstraintViolation<ACTUAL>>> consumer) {
        Objects.requireNonNull(propertyName, "propertyName is null");
        Objects.requireNonNull(consumer, "consumer is null");
        isNotNull();
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
        return myself;
    }

    public SELF doesNotHaveValidProperty(final String propertyName) {
        Objects.requireNonNull(propertyName, "propertyName is null");
        isNotNull();
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
                .withFailMessage(() -> "%nexpected to be not empty but empty%n")
                .isEmpty();
        return myself;
    }
}
