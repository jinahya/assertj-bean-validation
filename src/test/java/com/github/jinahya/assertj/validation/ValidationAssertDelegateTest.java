package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("ValidationAssertDelegate")
class ValidationAssertDelegateTest {

    @Nested
    class GroupsTest {

        @DisplayName("getGroups()")
        @Test
        void getGroups_NotNullEmpty_() {
            final var delegate = new ValidationAssertDelegate();
            final var groups = delegate.getGroups();
            assertThat(groups).isEmpty();
        }

        @DisplayName("setGroups(null)")
        @Test
        void setGroups__Null() {
            final var delegate = new ValidationAssertDelegate();
            delegate.setGroups(null);
            assertThat(delegate.groups).isEmpty();
        }

        @DisplayName("setGroups(empty)")
        @Test
        void setGroups__Empty() {
            final var delegate = new ValidationAssertDelegate();
            delegate.setGroups(new Class<?>[0]);
            assertThat(delegate.groups).isEmpty();
        }

        @DisplayName("setGroups(not-empty)")
        @Test
        void setGroups__() {
            final var delegate = new ValidationAssertDelegate();
            delegate.setGroups(Object.class);
            assertThat(delegate.groups).isNotEmpty().hasSize(1);
        }
    }

    @Nested
    class ValidatorTest {

        @DisplayName("getValidator()")
        @Test
        void getValidator_NotNull_() {
            final var delegate = new ValidationAssertDelegate();
            final var validator = delegate.getValidator();
            assertThat(validator).isNotNull();
        }

        @DisplayName("validator(null)")
        @Test
        void setValidator__Null() {
            final var delegate = new ValidationAssertDelegate();
            assertThatCode(() -> delegate.setValidator(null)).doesNotThrowAnyException();
        }

        @DisplayName("validator(null)")
        @Test
        void setValidator__Null_() {
            final var delegate = new ValidationAssertDelegate();
            final Validator validator;
            try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                validator = factory.getValidator();
            }
            delegate.setValidator(validator);
            assertThat(delegate.getValidator()).isSameAs(validator);
        }
    }
}
