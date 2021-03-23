package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.BeanValidationAssertions.assertThat;
import static com.github.jinahya.assertj.validation.BeanWrapper.bean;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * A class for testing {@link BeanValidationAssertions#assertThat(BeanWrapper)} method.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class BeanValidationAssertions_AssertThat_BeanWrapper_Test extends BeanValidationAssertionsTest {

    @DisplayName("assertThat(wrapper) throws NullPointerException when wrapper is null")
    @Test
    void assertThat_NullPointerException_WrapperIsNull() {
        assertThatThrownBy(() -> assertThat(null))
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("assertThat(wrapper) does not throw any exception")
    @Test
    void assertBeanType__() {
        assertThatCode(() -> assertThat(bean(new Object())))
                .doesNotThrowAnyException();
    }
}