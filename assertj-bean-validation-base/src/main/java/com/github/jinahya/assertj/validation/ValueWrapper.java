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
 * A class for wrapping a property value.
 *
 * @param <ACTUAL> property type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public class ValueWrapper<ACTUAL>
        extends AbstractWrapper<ACTUAL> {

    /**
     * Creates a new instance wraps specified property value.
     *
     * @param <ACTUAL> type parameter
     * @param actual   the value to wrap; may be {@code null}.
     * @return a new instance wraps {@code actual}.
     */
    public static <ACTUAL> ValueWrapper<ACTUAL> value(final ACTUAL actual) {
        return new ValueWrapper<>(actual);
    }

    /**
     * Creates a new instance wraps specified property value.
     *
     * @param actual the property value to wrap.
     */
    private ValueWrapper(final ACTUAL actual) {
        super(actual);
    }
}
