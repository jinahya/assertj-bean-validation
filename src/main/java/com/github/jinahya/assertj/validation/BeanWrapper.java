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

import static java.util.Objects.requireNonNull;

/**
 * A class for wrapping bean object.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class BeanWrapper {

    /**
     * Creates a new instance wraps specified bean object.
     *
     * @param object the bean object to wrap; must be not {@code null}.
     * @return a new instance wraps {@code object}.
     */
    public static BeanWrapper bean(final Object object) {
        requireNonNull(object, "object is null");
        return new BeanWrapper(object);
    }

    /**
     * Creates a new instance with specified bean object.
     *
     * @param object the bean object to wrap; must be not {@code null}.
     */
    private BeanWrapper(final Object object) {
        super();
        this.object = requireNonNull(object, "object is null");
    }

    /**
     * Returns the wrapped bean object.
     *
     * @return the wrapped bean object.
     */
    Object getObject() {
        return object;
    }

    /**
     * The wrapped bean object.
     */
    private final Object object;
}
