/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2021 the original author or authors.
 */
package com.github.jinahya.assertj.validation;

/**
 * Base class for all implementations of assertions for {@code bean}s.
 *
 * @param <SELF> self type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class BeanPropertyValidationAssert<SELF extends BeanPropertyValidationAssert<SELF, T>, T>
        extends AbstractBeanValidationAssert2<SELF, BeanPropertyWrapper<T>> {

    protected BeanPropertyValidationAssert(final BeanPropertyWrapper<T> actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    public SELF isValid() {
        return null;
    }
}
