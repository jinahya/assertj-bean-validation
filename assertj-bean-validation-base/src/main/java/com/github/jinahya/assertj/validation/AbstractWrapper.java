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

import java.util.Objects;

/**
 * An abstract base class for wrapping specific type of actual values.
 *
 * @param <ACTUAL> actual value type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractWrapper<ACTUAL> {

    /**
     * Creates a new instance wraps specified actual value.
     *
     * @param actual the actual value to wrap.
     */
    protected AbstractWrapper(final ACTUAL actual) {
        super();
        this.actual = actual;
    }

    @Override
    public String toString() {
        return super.toString() + '{'
               + "actual=" + actual
               + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AbstractWrapper<?> that = (AbstractWrapper<?>) o;
        return Objects.equals(actual, that.actual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actual);
    }

    /**
     * Returns the actual value wrapped in this wrapper.
     *
     * @return the actual value wrapped in this wrapper.
     */
    public ACTUAL getActual() {
        return actual;
    }

    private final ACTUAL actual;
}
