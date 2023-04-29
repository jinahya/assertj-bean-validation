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

import javax.validation.Validator;
import java.util.Arrays;
import java.util.Objects;

public final class PropertyAssertCompanion {

    static <T> void isNotValidFor_(
            final AbstractPropertyAssert<?, ?> assertion, final Class<T> beanType, final String propertyName) {
        Objects.requireNonNull(assertion, "assertion is null");
        Objects.requireNonNull(beanType, "beanType is null");
        Objects.requireNonNull(propertyName, "propertyName is null");
        final Validator validator = assertion.delegate.getValidator();
        final Class<?>[] groups = assertion.delegate.getGroups();
        assertion.delegate.setViolations(validator.validateValue(beanType, propertyName, assertion.actual(), groups));
        Assertions.assertThat(assertion.delegate.getViolations())
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual: %s%n"
                    + "\tagainst%n"
                    + "\t\tbeanType: %s%n"
                    + "\t\tproperty: '%s'%n"
                    + "\tfor%n"
                    + "\t\tgroups: %s%n",
                    assertion.actual(),
                    beanType,
                    propertyName,
                    Arrays.asList(groups)
                )
                .withFailMessage("%nexpected to be not empty but empty")
                .isNotEmpty();
    }

    public static <ASSERT extends PropertyAssert<?, ?>, T> ASSERT isNotValidFor(
            final ASSERT assertion, final Class<T> beanType, final String propertyName) {
        isNotValidFor_((AbstractPropertyAssert<?, ?>) assertion, beanType, propertyName);
        return assertion;
    }

    private PropertyAssertCompanion() {
        throw new AssertionError("instantiation is not allowed");
    }
}
