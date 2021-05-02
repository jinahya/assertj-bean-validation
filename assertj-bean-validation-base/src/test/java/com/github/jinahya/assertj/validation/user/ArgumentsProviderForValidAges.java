package com.github.jinahya.assertj.validation.user;

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

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class ArgumentsProviderForValidAges
        implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(final ExtensionContext context) throws Exception {
        return IntStream.range(0, 16).map(i -> User.newValidAge()).mapToObj(Arguments::of);
    }
}
