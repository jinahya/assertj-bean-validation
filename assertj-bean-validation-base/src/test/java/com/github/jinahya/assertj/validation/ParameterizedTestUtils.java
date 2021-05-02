package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation-base
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

import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public final class ParameterizedTestUtils {

    public static <B, A extends AbstractBeanAssert<A, ?, ?, ?>> Stream<A> beanAssertions(
            final Stream<? extends B> beans,
            final Function<? super B, ? extends A> mapper) {
        requireNonNull(beans, "beans is null");
        requireNonNull(mapper, "mapper is null");
        return beans.map(mapper);
    }

    public static <V, A extends AbstractValueAssert<A, ?, ?>> Stream<A> valueAssertions(
            final Stream<? extends V> values,
            final Function<? super V, ? extends A> mapper) {
        requireNonNull(values, "values is null");
        requireNonNull(mapper, "mapper is null");
        return values.map(mapper);
    }

    private ParameterizedTestUtils() {
        throw new NonInstantiatableAssertionError();
    }
}
