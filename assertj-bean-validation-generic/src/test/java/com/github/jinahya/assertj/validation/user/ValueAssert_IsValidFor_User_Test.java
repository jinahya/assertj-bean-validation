package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.ValueAssert;
import com.github.jinahya.assertj.validation.ValueAssertArgumentConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValueAssert_IsValidFor_User_Test {

    @DisplayName("assertValue(validAge).isValidFor(User.class, \"age\") succeeds")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfValidAges.class)
    void isValidFor_Succeed_ValidAge(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatCode(() -> a.isValidFor(User.class, "age"))
                .doesNotThrowAnyException();
    }

    @DisplayName("assertValue(validName).isValidFor(User.class, \"name\") succeeds")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfValidNames.class)
    void isValidFor_Succeed_ValidName(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatCode(() -> a.isValidFor(User.class, "name"))
                .doesNotThrowAnyException();
    }

    @DisplayName("assertValue(invalidAge).isValidFor(User.class, \"age\") fails")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfInvalidAges.class)
    void isValidFor_Fail_InvalidAge(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatThrownBy(() -> a.isValidFor(User.class, "age"))
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertValue(invalidName).isValidFor(User.class, \"name\") fails")
    @ParameterizedTest
    @ArgumentsSource(UserValueArgumentsProviders.OfInvalidNames.class)
    void isValidFor_Fail_InvalidName(@ConvertWith(ValueAssertArgumentConverter.class) final ValueAssert a) {
        assertThatThrownBy(() -> a.isValidFor(User.class, "name"))
                .isInstanceOf(AssertionError.class);
    }
}