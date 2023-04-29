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

import javax.validation.ConstraintViolation;
import java.util.Collections;
import java.util.Set;
import java.util.function.Consumer;

final class ValidationAssertUtils {

    static <T> void accept(final Set<? extends ConstraintViolation<T>> violations,
                           final Consumer<? super Set<ConstraintViolation<T>>> consumer) {
        try {
            consumer.accept(Collections.unmodifiableSet(violations));
        } catch (final Throwable t) {
            System.err.println("failed to accept violations to [" + consumer + "]; message: " + t.getMessage());
            t.printStackTrace();
        }
    }

    private ValidationAssertUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
