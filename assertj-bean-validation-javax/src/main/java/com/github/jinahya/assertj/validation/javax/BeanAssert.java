package com.github.jinahya.assertj.validation.javax;

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

import com.github.jinahya.assertj.validation.AbstractBeanAssert;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * An assertion class for validating a bean and its properties.
 *
 * @param <T> the type of actual bean.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public class BeanAssert<T>
        extends AbstractBeanAssert<BeanAssert<T>, T, Validator, ConstraintViolation<T>> {

    /**
     * Creates a new instance with specified bean object.
     *
     * @param actual the bean object verify.
     */
    public BeanAssert(final T actual) {
        super(actual, BeanAssert.class);
    }

    @Override
    protected Validator getDefaultValidator() {
        return ValidatorFactories.getValidatorFactory().getValidator();
    }

    @Override
    protected Set<ConstraintViolation<T>> validate(final T actual) {
        return validator().validate(actual, groups());
    }

    @Override
    protected Set<ConstraintViolation<T>> validateProperty(final T actual, final String propertyName) {
        return validator().validateProperty(actual, propertyName, groups());
    }
}
