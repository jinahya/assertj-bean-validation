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

public final class BeanAssertCompanion {

    static <ACTUAL> void isNotValid_(final AbstractBeanAssert<?, ACTUAL> assertion) {
        Objects.requireNonNull(assertion, "assertion is null");
        assertion.isNotNull();
        final Validator validator = assertion.delegate.getValidator();
        final Class<?>[] groups = assertion.delegate.getGroups();
        assertion.delegate.setViolations(validator.validate(assertion.actual(), groups));
        Assertions.assertThat(assertion.delegate.getViolations())
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual: %s%n"
                    + "targeting%n"
                    + "\tgroups: %s%n",
                    assertion.actual(),
                    Arrays.asList(groups)
                )
                .withFailMessage(() -> "%nexpected to be not empty but empty%n")
                .isNotEmpty();
    }

    public static <ASSERT extends BeanAssert<?, ?>> ASSERT isNotValid(final ASSERT assertion) {
        isNotValid_((AbstractBeanAssert<?, ?>) assertion);
        return assertion;
    }

    static <ACTUAL> void doesNotHaveValidProperty_(
            final AbstractBeanAssert<?, ACTUAL> assertion, final String propertyName) {
        Objects.requireNonNull(assertion, "assertion is null");
        Objects.requireNonNull(propertyName, "propertyName is null");
        assertion.isNotNull();
        final Validator validator = assertion.delegate.getValidator();
        final Class<?>[] groups = assertion.delegate.getGroups();
        final Set<ConstraintViolation<ACTUAL>> violations =
                validator.validateProperty(assertion.actual(), propertyName, groups);
        Assertions.assertThat(violations)
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual: %s%n"
                    + "for its%n"
                    + "\tproperty: '%s'%n"
                    + "targeting %n"
                    + "\tgroups: %s%n",
                    assertion.actual(),
                    propertyName,
                    Arrays.asList(groups)
                )
                .withFailMessage(() -> "%nexpected to be not empty but empty%n")
                .isNotEmpty();
    }

    public static <ASSERT extends BeanAssert<?, ?>> ASSERT doesNotHaveValidProperty(
            final ASSERT assertion, final String propertyName) {
        doesNotHaveValidProperty_((AbstractBeanAssert<?, ?>) assertion, propertyName);
        return assertion;
    }

    private BeanAssertCompanion() {
        throw new AssertionError("instantiation is not allowed");
    }
}
