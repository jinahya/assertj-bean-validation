package com.github.jinahya.assertj.validation.example.user;

/*-
 * #%L
 * assertj-bean-validation
 * %%
 * Copyright (C) 2021 - 2022 Jinahya, Inc.
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

import com.github.jinahya.assertj.validation.ValidationAssertionsTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
class BeanAssert_IsValid_Test {

    @DisplayName("(valid).isValid")
    @Test
    void __Valid() {
        final var bean = User.newInstance(true, true);
        assertThatCode(
                () -> assertThatBean(bean).isValid()
        )
                .doesNotThrowAnyException();
        assertThatCode(
                () -> assertThatBean(bean)
                        .isValid(ValidationAssertionsTestUtils.violationsConsumerSpy(i -> {
                            assertThat(i).isEmpty();
                        }))
        )
                .doesNotThrowAnyException();
    }

    @DisplayName("(valid).isValid(Consumer)")
    @Test
    void WithConsumer__Valid() {
        final var bean = User.newInstance(true, true);
        final var consumer = ValidationAssertionsTestUtils.<User>violationsConsumerSpy();
        assertThatCode(() -> assertThatBean(bean).isValid(consumer))
                .doesNotThrowAnyException();
        final var captor = ValidationAssertionsTestUtils.<User>constraintViolationsCaptor();
        verify(consumer, times(1)).accept(captor.capture());
        final Iterable<ConstraintViolation<User>> violations = captor.getValue();
        assertThat(violations).isEmpty();
    }

    @DisplayName("(nameIsInvalid).isValid()")
    @Test
    void __NameIsInvalid() {
        final var bean = User.newInstance(false, true);
        assertThatThrownBy(() -> assertThatBean(bean).isValid())
                .isInstanceOf(AssertionError.class)
                .satisfies(ae -> {
                    log.debug("message: {}", ae.getMessage());
                });
    }

    @DisplayName("(nameIsInvalid).isValid(consumer)")
    @Test
    void WithConsumer__NameIsInvalid() {
        final var bean = User.newInstance(false, true);
        final var consumer = ValidationAssertionsTestUtils.<User>violationsConsumerSpy();
        assertThatThrownBy(() -> assertThatBean(bean).isValid(consumer))
                .isInstanceOf(AssertionError.class)
                .satisfies(ae -> {
                    log.debug("message: {}", ae.getMessage());
                });
        final var captor = ValidationAssertionsTestUtils.<User>constraintViolationsCaptor();
        verify(consumer, times(1)).accept(captor.capture());
        final Iterable<ConstraintViolation<User>> violations = captor.getValue();
        assertThat(violations).singleElement().satisfies(cv -> {
        });
    }

    @Test
    void __AgeIsInvalid() {
        final var actual = User.newInstance(true, false);
        assertThatThrownBy(() -> assertThatBean(actual).isValid())
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void WithConsumer__AgeIsInvalid() {
        final var bean = User.newInstance(true, false);
        final var assertion = assertThatBean(bean);
        final var consumer = ValidationAssertionsTestUtils.<User>violationsConsumerSpy();
        // WHEN
        assertThatThrownBy(() -> assertion.isValid(consumer))
                .isInstanceOf(AssertionError.class);
        // THEN
        final var captor = ValidationAssertionsTestUtils.<User>constraintViolationsCaptor();
        verify(consumer, times(1)).accept(captor.capture());
        final var violations = captor.getValue();
        assertThat(violations).singleElement().satisfies(cv -> {
        });
    }

    @Test
    void __NameIsInvalidAgeIsInvalid() {
        final var actual = User.newInstance(false, false);
        final var assertion = assertThatBean(actual);
        assertThatThrownBy(assertion::isValid)
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void WithConsumer__Invalid() {
        final var bean = User.newInstance(false, false);
        final var assertion = assertThatBean(bean);
        final var consumer = ValidationAssertionsTestUtils.<User>violationsConsumerSpy();
        // WHEN
        assertThatThrownBy(() -> assertion.isValid(consumer))
                .isInstanceOf(AssertionError.class);
        // THEN
        final var captor = ValidationAssertionsTestUtils.<User>constraintViolationsCaptor();
        verify(consumer, times(1)).accept(captor.capture());
        final var violations = captor.getValue();
        assertThat(violations).hasSize(2);
    }
}
