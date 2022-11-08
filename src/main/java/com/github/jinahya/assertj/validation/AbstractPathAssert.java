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
import org.assertj.core.api.IterableAssert;

import javax.validation.Path;

public abstract class AbstractPathAssert<SELF extends AbstractPathAssert<SELF, ACTUAL>, ACTUAL extends Path>
        extends AbstractValidationAssert<SELF, ACTUAL>
        implements PathAssert<SELF, ACTUAL> {

    /**
     * Creates a new instance with specified actual value and self type.
     *
     * @param actual   the actual value to verify.
     * @param selfType the self type.
     */
    protected AbstractPathAssert(final ACTUAL actual, final Class<?> selfType) {
        super(actual, selfType);
    }

    @Override
    public IterableAssert<Path.Node> asIterableAssert() {
        final SELF self = isNotNull();
        Assertions.assertThat(new String(""));
        return Assertions.assertThat(actual);
    }
}
