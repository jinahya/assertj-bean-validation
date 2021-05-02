package com.github.jinahya.assertj.validation.jakarta;

/*-
 * #%L
 * assertj-bean-validation-jakarta
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

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import static com.github.jinahya.assertj.validation.ValueWrapper.value;
import static java.util.concurrent.ThreadLocalRandom.current;

public class ValueAssertArgumentConverter
        implements ArgumentConverter {

    @Override
    public Object convert(final Object source, final ParameterContext context) throws ArgumentConversionException {
        final ValueAssert va;
        switch (current().nextInt(3)) {
            case 0:
                va = ValueAssertions.assertThat(source);
                break;
            case 1:
                va = ValueAssertions.assertThat(value(source));
                break;
            default:
                va = ValueAssertions.assertValue(source);
                break;
        }
        return va;
    }
}
