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

import org.assertj.core.api.Assertions;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * An abstract assertion class for validating a property value against constraints defined on a bean property.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual value type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractPropertyAssert<SELF extends AbstractPropertyAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractValidationAssert<SELF, ACTUAL>
        implements PropertyAssert<SELF, ACTUAL> {

    /**
     * Creates a new instance with specified actual value and self type.
     *
     * @param actual   the actual value to verify.
     * @param selfType the self type.
     */
    protected AbstractPropertyAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    public <T> SELF isValidFor(final Class<T> beanType, final String propertyName,
                               final Consumer<Iterable<ConstraintViolation<T>>> consumer) {
        Objects.requireNonNull(beanType, "beanType is null");
        Objects.requireNonNull(propertyName, "propertyName is null");
        Objects.requireNonNull(consumer, "consumer is null");
        final Validator validator = getValidator();
        final Class<?>[] groups = getGroups();
        final Set<ConstraintViolation<T>> violations = validator.validateValue(beanType, propertyName, actual, groups);
        consumer.accept(Collections.unmodifiableSet(violations));
        Assertions.assertThat(violations)
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual  : %s%n"
                    + "\tbeanType: %s%n"
                    + "\tproperty: %s%n"
                    + "\tgroups  : %s%n",
                    actual,
                    beanType,
                    propertyName,
                    Arrays.asList(groups)
                )
                .withFailMessage(() -> String.format(
                        "%nexpected to be empty but contains %1$d element(s)%n"
                        + "%2$s",
                        violations.size(),
                        Formats.format(violations)
                ))
                .isEmpty();
        return myself;
    }

    <T> SELF isNotValidFor(final Class<T> beanType, final String propertyName) {
        Objects.requireNonNull(beanType, "beanType is null");
        Objects.requireNonNull(propertyName, "propertyName is null");
        final Validator validator = getValidator();
        final Class<?>[] groups = getGroups();
        final Set<ConstraintViolation<T>> violations = validator.validateValue(beanType, propertyName, actual, groups);
        Assertions.assertThat(violations)
                .as("%nThe set of constraint violations resulted while validating%n"
                    + "\tactual  : %s%n"
                    + "\tbeanType: %s%n"
                    + "\tproperty: %s%n"
                    + "\tgroups  : %s%n",
                    actual,
                    beanType,
                    propertyName,
                    Arrays.asList(groups)
                )
                .withFailMessage("%nexpected to be not empty but is empty")
                .isNotEmpty();
        return myself;
    }
}
