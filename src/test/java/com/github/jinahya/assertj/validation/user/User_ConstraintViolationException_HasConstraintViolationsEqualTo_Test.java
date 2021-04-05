package com.github.jinahya.assertj.validation.user;

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

import com.github.jinahya.assertj.validation.BeanValidationAssert;
import com.github.jinahya.assertj.validation.BeanValidationTestUtils;
import com.github.jinahya.assertj.validation.ConstraintViolationExceptionTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanValidationAssertions.assertBean;
import static com.github.jinahya.assertj.validation.ConstraintViolationExceptionAssertions.assertThat;
import static com.github.jinahya.assertj.validation.ConstraintViolationExceptionWrapper.constraintViolationException;
import static java.util.concurrent.ThreadLocalRandom.current;

@Slf4j
class User_ConstraintViolationException_HasConstraintViolationsEqualTo_Test {

    static Stream<String> validNames() {
        return Stream.of(Long.toString(System.nanoTime()));
    }

    static Stream<String> invalidNames() {
        return Stream.of(null, "", " ");
    }

    static IntStream validAges() {
        return IntStream.range(0, 8)
                .map(i -> current().nextInt() >>> 1);
    }

    static IntStream invalidAges() {
        return IntStream.range(0, 8)
                .map(i -> current().nextInt() | Integer.MIN_VALUE);
    }

    static Stream<User> validInstances() {
        return IntStream.rangeClosed(0, current().nextInt(16))
                .mapToObj(i -> User.newValidInstance());
    }

    static Stream<User> instancesWithInvalidName() {
        return IntStream.rangeClosed(0, current().nextInt(16))
                .mapToObj(i -> User.newInstanceWithInvalidName());
    }

    static Stream<User> instancesWithInvalidAge() {
        return IntStream.rangeClosed(0, current().nextInt(16))
                .mapToObj(i -> User.newInstanceWithInvalidAge());
    }

    static Stream<User> invalidInstances() {
        return IntStream.rangeClosed(0, current().nextInt(16))
                .mapToObj(i -> User.newInvalidInstance());
    }

    // -----------------------------------------------------------------------------------------------------------------
    @MethodSource({"invalidInstances"})
    @ParameterizedTest
    void hasConstraintViolationEqualsTo_Pass_(final User user) {
        final BeanValidationAssert<User> a = assertBean(user);
        final Set<Object> s = BeanValidationTestUtils.validate(null, user);
        final Object e = ConstraintViolationExceptionTestUtils.getConstraintViolationExceptionInstance(s);
        assertThat(constraintViolationException(e))
                .hasConstraintViolationsEqualTo(s);
    }
}
