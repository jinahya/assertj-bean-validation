package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.javax.BeanAssert;
import com.github.jinahya.assertj.validation.javax.BeanAssertArgumentConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BeanAssert_IsValid_User_Test {

    @DisplayName("assertBean(valid).isValid() succeeds")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfValid.class)
    void isValid_Succeed_Valid(@ConvertWith(BeanAssertArgumentConverter.class) final BeanAssert<?> a) {
        assertThatCode(a::isValid)
                .doesNotThrowAnyException();
    }

    @DisplayName("assertBean(invalid).isValid() fails")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfInvalid.class)
    void isValid_Fail_Invalid(@ConvertWith(BeanAssertArgumentConverter.class) final BeanAssert<?> a) {
        assertThatThrownBy(a::isValid)
                .isInstanceOf(AssertionError.class);
    }
}