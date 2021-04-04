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

import java.util.function.Consumer;

import static com.github.jinahya.assertj.validation.ElementKindUtils.requireElementKindInstance;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings({"java:S119"})
public class ElementKindAssert<ACTUAL>
        extends AbstractAssert<ElementKindAssert<ACTUAL>, ACTUAL> {

    /**
     * Creates a new instance for verifying specified actual value.
     *
     * @param actual the actual value to verify.
     */
    public ElementKindAssert(final ACTUAL actual) {
        super(requireElementKindInstance(actual), ElementKindAssert.class);
    }

    // ------------------------------------------------------------------------------------------------------- getName()

    /**
     * Verifies that the {@link Enum#name() actual.name()} satisfies some requirements by being accepted to specified
     * consumer.
     *
     * @param requirements the consumer accepts and verifies the value of {@code actual.name()}.
     * @return {@link #myself self}.
     */
    public ElementKindAssert<ACTUAL> hasNameSatisfying(final Consumer<? super String> requirements) {
        isNotNull();
        assertThat(ElementKindUtils.getName(actual))
                .satisfies(requirements::accept);
        return myself;
    }

    public ElementKindAssert<ACTUAL> hasNameEqualTo(final Object expected) {
        isNotNull();
        assertThat(ElementKindUtils.getName(actual))
                .isEqualTo(expected);
        return myself;
    }
}
