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

/**
 * A class for wrapping bean object.
 *
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public class BeanWrapper<ACTUAL>
        extends AbstractWrapper<ACTUAL> {

    /**
     * Creates a new instance wraps specified bean object.
     *
     * @param <ACTUAL> actual type parameter
     * @param actual   the bean object to wrap; must not be {@code null}.
     * @return a new instance wraps {@code object}.
     */
    public static <ACTUAL> BeanWrapper<ACTUAL> bean(final ACTUAL actual) {
        return new BeanWrapper<>(actual);
    }

    /**
     * Creates a new instance with specified bean object.
     *
     * @param actual the bean object to wrap; must not be {@code null}.
     */
    private BeanWrapper(final ACTUAL actual) {
        super(actual);
    }
}
