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
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * An abstract assertion class for verifying bean objects.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 * @see ValidationAssertions#assertBean(Object)
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractBeanAssert<SELF extends AbstractBeanAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractPropertyAssert<SELF, ACTUAL>
        implements BeanAssert<SELF, ACTUAL> {

    /**
     * Creates a new instance with specified actual value and self type.
     *
     * @param actual   the actual value to verify.
     * @param selfType the self type.
     */
    protected AbstractBeanAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    public SELF isValid(final Consumer<Iterable<ConstraintViolation<ACTUAL>>> consumer) {
        Objects.requireNonNull(consumer, "consumer is null");
        final SELF self = isNotNull();
        final Validator validator = getValidator();
        final Class<?>[] groups = getGroups();
        final Set<ConstraintViolation<ACTUAL>> violations = validator.validate(actual, groups);
        consumer.accept(Collections.unmodifiableSet(violations));
        Assertions.assertThat(violations).isEmpty();
        return self;
    }

    @Override
    public SELF hasValidProperty(final String propertyName,
                                 final Consumer<Iterable<ConstraintViolation<ACTUAL>>> consumer) {
        Objects.requireNonNull(propertyName, "propertyName is null");
        Objects.requireNonNull(consumer, "consumer is null");
        final SELF self = isNotNull();
        final Validator validator = getValidator();
        final Class<?>[] groups = getGroups();
        final Set<ConstraintViolation<ACTUAL>> violations = validator.validateProperty(actual, propertyName, groups);
        consumer.accept(Collections.unmodifiableSet(violations));
        Assertions.assertThat(violations).isEmpty();
        return self;
    }
}
