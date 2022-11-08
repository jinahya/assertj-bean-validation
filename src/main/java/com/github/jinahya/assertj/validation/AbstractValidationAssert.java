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

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

//    @Override
//    public SELF usingValidator(final Validator validator) {
//        setValidator(validator);
//        return myself;
//    }

    @Override
    public SELF targetingGroups(final Class<?>... groups) {
        setGroups(groups);
        return myself;
    }

//    @Override
//    public SELF consumingEachViolation(final Consumer<? super ConstraintViolation<?>> consumer) {
//        setEachConsumer(consumer);
//        return myself;
//    }

//    @Override
//    public <T> SELF consumingViolations(final Consumer<? super Iterable<? extends ConstraintViolation<T>>> consumer) {
//        setConsumer((Consumer<? super Iterable<? extends ConstraintViolation<?>>>) consumer);
//        return myself;
//    }

    /**
     * Returns the validator configured to use.
     *
     * @return the validator configured to use.
     */
    Validator getValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            return factory.getValidator();
        }
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

//    Consumer<? super ConstraintViolation<?>> getEachConsumer() {
//        return eachConsumer;
//    }
//
//    void setEachConsumer(final Consumer<? super ConstraintViolation<?>> consumer) {
//        this.eachConsumer = consumer;
//    }

//    <T> Consumer<? super Iterable<? extends ConstraintViolation<T>>> getConsumer() {
//        return consumer;
//    }
//
//    void setConsumer(final Consumer<? super Iterable<? extends ConstraintViolation<?>>> consumer) {
//        this.consumer = consumer;
//    }
//
//    <T> void acceptViolations(final Set<? extends ConstraintViolation<T>> violations) {
//        Objects.requireNonNull(violations, "violations is null");
//        Optional.ofNullable(this.<T>getConsumer())
//                .ifPresent(c -> c.accept(Collections.unmodifiableSet(violations)));
//    }

    /**
     * The targeting groups being used.
     */
    private final Set<Class<?>> groups = new HashSet<>();

////    /**
////     * The consumer accepts constraint violations, one by one.
////     */
////    private Consumer<? super ConstraintViolation<?>> eachConsumer;
//
//    /**
//     * The consumer accepts constraint violations, one by one.
//     */
//    private Consumer<? super Iterable<? extends ConstraintViolation<?>>> consumer;
}
