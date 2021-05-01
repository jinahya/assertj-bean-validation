package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.jakarta.ValueAssert;
import com.github.jinahya.assertj.validation.jakarta.ValueAssertArgumentConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValueAssert_IsNotValidFor_User_Test {

    @DisplayName("assertValue(validAge).isNotValidFor(User.class, \"age\") throws AssertionError")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfValidAges.class)
    void isNotValidFor_Fail_ValidAge(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatThrownBy(() -> a.isNotValidFor(User.class, "age"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertValue(validName).isNotValidFor(User.class, \"name\") throws AssertionError")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfValidNames.class)
    void isNotValidFor_Fail_ValidName(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatThrownBy(() -> a.isNotValidFor(User.class, "name"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertValue(invalidAge).isNotValidFor(User.class, \"age\") does not throw any exceptions")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfInvalidAges.class)
    void isNotValidFor_Succeed_InvalidAge(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatCode(() -> a.isNotValidFor(User.class, "age"))
                .doesNotThrowAnyException();
    }

    @DisplayName("assertValue(invalidName).isNotValidFor(User.class, \"name\") does not throw any exceptions")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfInvalidNames.class)
    void isNotValidFor_Succeed_InvalidName(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatCode(() -> a.isNotValidFor(User.class, "name"))
                .doesNotThrowAnyException();
    }
}