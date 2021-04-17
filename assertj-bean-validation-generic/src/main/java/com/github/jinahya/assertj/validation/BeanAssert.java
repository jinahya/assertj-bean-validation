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

import java.util.Set;

/**
 * An assertion class for validating a bean and/or its properties.
 *
 * @param <ACTUAL> actual bean type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public class BeanAssert<ACTUAL>
        extends AbstractBeanAssert<BeanAssert<ACTUAL>, ACTUAL, Object, Object> {

    /**
     * Creates a new instance with specified bean.
     *
     * @param actual the actual bean to verify.
     * @see #actual
     */
    public BeanAssert(final ACTUAL actual) {
        super(actual, BeanAssert.class);
    }

    // -------------------------------------------------------------------------------------------------------- validate
    @Override
    Object getDefaultValidator() {
        return BeanUtils.validator();
    }

    // -----------------------------------------------------------------------------------------------------------------
    @Override
    Set<Object> validate(final ACTUAL actual) {
        return BeanUtils.validate(validator(), actual, groups());
    }

    @Override
    Set<Object> validateProperty(final ACTUAL actual, String propertyName) {
        return BeanUtils.validateProperty(validator(), actual, propertyName, groups());
    }
}
