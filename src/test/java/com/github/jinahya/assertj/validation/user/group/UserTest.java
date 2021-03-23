package com.github.jinahya.assertj.validation.user.group;

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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanValidationAssertions.assertBean;
import static com.github.jinahya.assertj.validation.user.group.User.setInvalidAge;
import static com.github.jinahya.assertj.validation.user.group.User.setInvalidName;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserTest {

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
        return IntStream.rangeClosed(0, current().nextInt(8))
                .mapToObj(i -> User.newInvalidInstance());
    }

    @MethodSource({"validInstances"})
    @ParameterizedTest
    void isValid_Succeed_Valid(final User user) {
        assertBean(user).targeting(NameOnly.class).isValid();
        assertBean(user).targeting(AgeOnly.class).isValid();
        assertBean(user).targeting(NameOnly.class, AgeOnly.class).isValid();
    }

    @MethodSource({"invalidInstances"})
    @ParameterizedTest
    void isValid_Fail_Invalid(final User user) {
        final BeanValidationAssert a = assertBean(user).targeting(NameOnly.class, AgeOnly.class);
        assertThatThrownBy(a::isValid)
                .isInstanceOf(AssertionError.class);
    }

    @MethodSource({"instancesWithInvalidName"})
    @ParameterizedTest
    void isValid_FailTargetingNameOnly_InvalidName(final User user) {
        final BeanValidationAssert a = assertBean(user).targeting(NameOnly.class);
        assertThatThrownBy(a::isValid).isInstanceOf(AssertionError.class);
    }

    @MethodSource({"instancesWithInvalidName"})
    @ParameterizedTest
    void isValid_SucceedTargetingAgeOnly_InvalidName(final User user) {
        final BeanValidationAssert a = assertBean(user).targeting(AgeOnly.class);
        assertThatCode(a::isValid).doesNotThrowAnyException();
    }

    @MethodSource({"instancesWithInvalidAge"})
    @ParameterizedTest
    void isValid_SucceedTargetingNameOnly_InvalidAge(final User user) {
        final BeanValidationAssert a = assertBean(user).targeting(NameOnly.class);
        assertThatCode(a::isValid).doesNotThrowAnyException();
    }

    @MethodSource({"instancesWithInvalidAge"})
    @ParameterizedTest
    void isValid_FailTargetingAgeOnly_InvalidAge(final User user) {
        final BeanValidationAssert a = assertBean(user).targeting(AgeOnly.class);
        assertThatThrownBy(a::isValid).isInstanceOf(AssertionError.class);
    }

    // ------------------------------------------------------------------------------------------------ hasValidProperty
    @MethodSource({"instancesWithInvalidName"})
    @ParameterizedTest
    void hasValidProperty_FailTargetingNameOnly_InvalidName(final User user) {
        final BeanValidationAssert a = assertBean(user).targeting(NameOnly.class);
        assertThatThrownBy(() -> a.hasValidProperty("name"))
                .isInstanceOf(AssertionError.class);
        assertThatCode(() -> a.hasValidProperty("age")).doesNotThrowAnyException();
    }

    @MethodSource({"instancesWithInvalidName"})
    @ParameterizedTest
    void hasValidProperty_SucceedTargetingNameOnly_InvalidName(final User user) {
        final BeanValidationAssert a = assertBean(user).targeting(NameOnly.class);
        setInvalidAge(user);
        assertThatCode(() -> a.hasValidProperty("age")).doesNotThrowAnyException();
    }

    @MethodSource({"instancesWithInvalidAge"})
    @ParameterizedTest
    void hasValidProperty_FailTargetingNameOnly_InvalidAge(final User user) {
        final BeanValidationAssert a = assertBean(user).targeting(AgeOnly.class);
        assertThatThrownBy(() -> a.hasValidProperty("age"))
                .isInstanceOf(AssertionError.class);
        assertThatCode(() -> a.hasValidProperty("name")).doesNotThrowAnyException();
    }

    @MethodSource({"instancesWithInvalidName"})
    @ParameterizedTest
    void hasValidProperty_SucceedTargetingNameOnly_InvalidAge(final User user) {
        final BeanValidationAssert a = assertBean(user).targeting(AgeOnly.class);
        setInvalidName(user);
        assertThatCode(() -> a.hasValidProperty("name")).doesNotThrowAnyException();
    }
}
