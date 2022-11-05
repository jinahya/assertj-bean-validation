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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@Slf4j
class User_IsValid_Test {

    @Test
    void __Valid() {
        final User bean = User.newInstance(true, true);
        assertThatCode(() -> assertThatBean(bean).isValid()).doesNotThrowAnyException();
    }

    @Test
    void WithConsumer__Valid() {
        final var bean = User.newInstance(true, true);
        final var assertion = assertThatBean(bean);
        final Consumer<ConstraintViolation<?>> consumer = spy(new Consumer<ConstraintViolation<?>>() {
            @Override
            public void accept(final ConstraintViolation<?> constraintViolation) {
            }
        });
        // WHEN
        assertThatCode(() -> assertion.consumingViolations(consumer).isValid()).doesNotThrowAnyException();
        // THEN
        verify(consumer, never()).accept(Mockito.any());
    }

    @Test
    void __NameIsInvalid() {
        final var bean = User.newInstance(false, true);
        final var assertion = assertThatBean(bean);
        assertThatThrownBy(assertion::isValid)
                .isInstanceOf(AssertionError.class)
                .satisfies(ae -> {
                    log.debug("message: {}", ae.getMessage());
                })
        ;
    }

    @Test
    void WithConsumer__NameIsInvalid() {
        final var bean = User.newInstance(false, true);
        final var assertion = assertThatBean(bean);
        final Consumer<ConstraintViolation<?>> consumer = spy(new Consumer<ConstraintViolation<?>>() {
            @Override
            public void accept(final ConstraintViolation<?> constraintViolation) {
            }
        });
        // WHEN
        assertThatThrownBy(() -> assertion.consumingViolations(consumer).isValid()).isInstanceOf(AssertionError.class);
        // THEN
        @SuppressWarnings({"unchecked"})
        final ArgumentCaptor<ConstraintViolation<User>> captor = ArgumentCaptor.forClass(ConstraintViolation.class);
        verify(consumer, times(1)).accept(captor.capture());
        final ConstraintViolation<?> violation = captor.getValue();
        assertThat(violation.getRootBeanClass()).isSameAs(User.class);
        assertThat(violation.getPropertyPath().iterator().next().getName()).isEqualTo(User.PROPERTY_NAME_NAME);
    }

    @Test
    void __AgeIsInvalid() {
        final var actual = User.newInstance(true, false);
        final var assertion = assertThatBean(actual);
        assertThatThrownBy(assertion::isValid).isInstanceOf(AssertionError.class);
    }

    @Test
    void WithConsumer__AgeIsInvalid() {
        final var bean = User.newInstance(true, false);
        final var assertion = assertThatBean(bean);
        final Consumer<ConstraintViolation<?>> consumer = spy(new Consumer<ConstraintViolation<?>>() {
            @Override
            public void accept(final ConstraintViolation<?> constraintViolation) {
            }
        });
        // WHEN
        assertThatThrownBy(() -> assertion.consumingViolations(consumer).isValid()).isInstanceOf(AssertionError.class);
        // THEN
        @SuppressWarnings({"unchecked"})
        final ArgumentCaptor<ConstraintViolation<User>> captor = ArgumentCaptor.forClass(ConstraintViolation.class);
        verify(consumer, times(1)).accept(captor.capture());
        final ConstraintViolation<?> violation = captor.getValue();
        assertThat(violation.getRootBeanClass()).isSameAs(User.class);
        assertThat(violation.getPropertyPath().iterator().next().getName()).isEqualTo(User.PROPERTY_NAME_AGE);
    }

    @Test
    void __Invalid() {
        final var actual = User.newInstance(false, false);
        final var assertion = assertThatBean(actual);
        assertThatThrownBy(assertion::isValid).isInstanceOf(AssertionError.class);
    }

    @Test
    void WithConsumer__Invalid() {
        final var bean = User.newInstance(false, false);
        final var assertion = assertThatBean(bean);
        final Consumer<ConstraintViolation<?>> consumer = spy(new Consumer<ConstraintViolation<?>>() {
            @Override
            public void accept(final ConstraintViolation<?> constraintViolation) {
                log.debug("violation: {}", constraintViolation);
            }
        });
        // WHEN
        assertThatThrownBy(() -> assertion.consumingViolations(consumer).isValid()).isInstanceOf(AssertionError.class);
        // THEN
        @SuppressWarnings({"unchecked"})
        final ArgumentCaptor<ConstraintViolation<User>> captor = ArgumentCaptor.forClass(ConstraintViolation.class);
        verify(consumer, times(2)).accept(captor.capture());
        final ConstraintViolation<?> violation = captor.getValue();
    }
}
