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

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.assertj.core.api.Assertions;

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
        extends AbstractValidationAssert<SELF, ACTUAL, Validator>
        implements PropertyAssert<SELF, ACTUAL> {

    /**
     * Creates a new instance with specified actual value and self type.
     *
     * @param actual   the actual value to verify.
     * @param selfType the self type.
     */
    protected AbstractPropertyAssert(final ACTUAL actual, final Class<SELF> selfType) {
        super(actual, selfType);
    }

    @Override
    Validator getValidator() {
        final Validator validator = super.getValidator();
        if (validator != null) {
            return validator;
        }
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> SELF isValidFor(final Class<T> beanType, final String propertyName,
                               final Consumer<? super ConstraintViolation<T>> consumer) {
        Objects.requireNonNull(beanType, "beanType is null");
        Objects.requireNonNull(propertyName, "propertyName is null");
        Objects.requireNonNull(consumer, "consumer is null");
        return myself
                .satisfies(a -> {
                    final Validator validator = getValidator();
                    final Class<?>[] groups = getGroups();
                    final Set<ConstraintViolation<T>> violations
                            = validator.validateValue(beanType, propertyName, actual, groups);
                    violations.forEach(consumer);
                    Assertions.assertThat(violations)
                            .as("no constraint violations, against %s#%s", beanType.getSimpleName(), propertyName)
                            .withFailMessage("expected but got %s", violations)
                            .isEmpty();
                });
    }
}
