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

import com.github.jinahya.assertj.validation.BeanAssert;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.validation.ConstraintViolation;
import java.util.function.Consumer;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
class User_HasValidProperty_Test {

    @Test
    void __NameIsValid() {
        final var bean = new User("Jane", -1);
        final var assertion = assertThatBean(bean);
        assertThatCode(() -> assertion.hasValidProperty(User.PROPERTY_NAME_NAME)).doesNotThrowAnyException();
    }

    @Test
    void WithConsumer__NameIsValid() {
        final var bean = new User("Jane", -1);
        final var assertion = assertThatBean(bean);
        assertThatCode(
                () -> assertion.consumingViolations(cv -> {
                    throw new RuntimeException("shouldn't see me being thrown");
                }).hasValidProperty(User.PROPERTY_NAME_NAME))
                .doesNotThrowAnyException();
    }

    @Test
    void __NameIsInvalid() {
        final var bean = new User(null, 27);
        final var assertion = assertThatBean(bean);
        assertThatThrownBy(() -> assertion.hasValidProperty(User.PROPERTY_NAME_NAME))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void WithConsumer__NameIsInvalid() {
        // GIVEN
        final var bean = new User(null, 27);
        final var assertion = assertThatBean(bean);
        final Consumer<ConstraintViolation<?>> consumer = spy(new Consumer<ConstraintViolation<?>>() {
            @Override
            public void accept(final ConstraintViolation<?> constraintViolation) {
            }
        });
        // WHEN
        assertThatThrownBy(() -> assertion.consumingViolations(consumer).hasValidProperty(User.PROPERTY_NAME_NAME))
                .isInstanceOf(AssertionError.class);
        // THEN
        @SuppressWarnings({"unchecked"})
        final ArgumentCaptor<ConstraintViolation<User>> captor = ArgumentCaptor.forClass(ConstraintViolation.class);
        verify(consumer, times(1)).accept(captor.capture());
        final ConstraintViolation<?> violation = captor.getValue();
        assertThat(violation.getRootBeanClass()).isSameAs(User.class);
        assertThat(violation.getPropertyPath().iterator().next().getName()).isEqualTo(User.PROPERTY_NAME_NAME);
    }

    @Test
    void __AgeIsValid() {
        final var bean = new User(null, 27);
        final var assertion = Mockito.<BeanAssert<?, User>>spy(assertThatBean(bean));
        assertThatCode(() -> assertion.hasValidProperty(User.PROPERTY_NAME_AGE)).doesNotThrowAnyException();
    }

    @Test
    void WithConsumer__AgeIsValid() {
        final var bean = new User("", 0);
        final var assertion = assertThatBean(bean);
        assertThatCode(
                () -> assertion.consumingViolations(cv -> {
                    throw new RuntimeException("shouldn't see me being thrown");
                }).hasValidProperty(User.PROPERTY_NAME_AGE))
                .doesNotThrowAnyException();
    }

    @Test
    void __AgeIsInvalid() {
        final var bean = new User("name", User.MAX_AGE + 1);
        final var assertion = assertThatBean(bean);
        assertThatThrownBy(() -> assertion.hasValidProperty(User.PROPERTY_NAME_AGE))
                .isInstanceOf(AssertionError.class);
    }

    @Test
    void WithConsumer__AgeIsInvalid() {
        // GIVEN
        final var bean = new User("name", -1);
        final var assertion = assertThatBean(bean);
        final Consumer<ConstraintViolation<?>> consumer = spy(new Consumer<ConstraintViolation<?>>() {
            @Override
            public void accept(final ConstraintViolation<?> constraintViolation) {
            }
        });
        // WHEN
        assertThatThrownBy(() -> assertion.consumingViolations(consumer).hasValidProperty(User.PROPERTY_NAME_AGE))
                .isInstanceOf(AssertionError.class);
        // THEN
        @SuppressWarnings({"unchecked"})
        final ArgumentCaptor<ConstraintViolation<User>> captor = ArgumentCaptor.forClass(ConstraintViolation.class);
        verify(consumer, times(1)).accept(captor.capture());
        final ConstraintViolation<?> violation = captor.getValue();
        assertThat(violation.getRootBeanClass()).isSameAs(User.class);
        assertThat(violation.getPropertyPath().iterator().next().getName()).isEqualTo(User.PROPERTY_NAME_AGE);
    }
}