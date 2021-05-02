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

import com.github.jinahya.assertj.validation.BeanWrapper;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import static java.util.concurrent.ThreadLocalRandom.current;

public class BeanAssertArgumentConverter
        implements ArgumentConverter {

    @Override
    public Object convert(final Object source, final ParameterContext context) throws ArgumentConversionException {
        final BeanAssert<?> va;
        switch (current().nextInt(3)) {
            case 0:
                va = BeanAssertions.assertThat(source);
                break;
            case 1:
                va = BeanAssertions.assertThat(BeanWrapper.bean(source));
                break;
            default:
                va = BeanAssertions.assertBean(source);
                break;
        }
        return va;
    }
}
