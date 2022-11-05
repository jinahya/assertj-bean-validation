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

import org.assertj.core.api.AbstractAssert;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

/**
 * An abstract base class for verifying beans and values.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractValidationAssert<SELF extends AbstractValidationAssert<SELF, ACTUAL>, ACTUAL>
        extends AbstractAssert<SELF, ACTUAL>
        implements ValidationAssert<SELF, ACTUAL> {

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual   the actual value to verify.
     * @param selfType self type class.
     */
    protected AbstractValidationAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    public SELF usingValidator(final Validator validator) {
        setValidator(validator);
        return myself;
    }

    @Override
    public SELF targetingGroups(final Class<?>... groups) {
        setGroups(groups);
        return myself;
    }

    @Override
    public SELF consumingViolations(final Consumer<? super ConstraintViolation<?>> consumer) {
        setConsumer(consumer);
        return myself;
    }

    /**
     * Returns the validator configured to use.
     *
     * @return the validator configured to use.
     */
    Validator getValidator() {
        if (validator == null) {
            try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                setValidator(factory.getValidator());
            }
        }
        return validator;
    }

    /**
     * Replaces currently configured validator with specified value.
     *
     * @param validator new validator to use; may be {@code null}.
     */
    void setValidator(final Validator validator) {
        this.validator = validator;
    }

    /**
     * Returns currently configured groups targeted for validation.
     *
     * @return an array of targeting groups; may be {@code empty}.
     */
    Class<?>[] getGroups() {
        return groups.toArray(new Class<?>[0]);
    }

    /**
     * Replaces currently configured groups for validation with specified value.
     *
     * @param groups new targeting groups; may be {@code null}.
     */
    void setGroups(final Class<?>[] groups) {
        this.groups.clear();
        if (groups != null) {
            Arrays.stream(groups)
                    .filter(Objects::nonNull)
                    .forEach(this.groups::add);
        }
    }

    Consumer<? super ConstraintViolation<?>> getConsumer() {
        return consumer;
    }

    void setConsumer(final Consumer<? super ConstraintViolation<?>> consumer) {
        this.consumer = consumer;
    }

    /**
     * The validator being used.
     */
    private Validator validator;

    /**
     * The targeting groups being used.
     */
    private final Set<Class<?>> groups = new HashSet<>();

    /**
     * The consumer accepts constraint violations, one by one.
     */
    private Consumer<? super ConstraintViolation<?>> consumer;
}
