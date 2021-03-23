package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.BeanPropertyValidationAssertions.assertThat;
import static com.github.jinahya.assertj.validation.BeanPropertyWrapper.beanProperty;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * A class for testing {@link BeanPropertyValidationAssertions#assertThat(BeanPropertyWrapper)} method.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class BeanPropertyValidationAssertions_AssertThat_BeanPropertyWrapper_Test
        extends BeanPropertyValidationAssertionsTest {

    @DisplayName("assertThat(wrapper) throws NullPointerException when wrapper is null")
    @Test
    void assertThat_NullPointerException_WrapperIsNull() {
        assertThatThrownBy(() -> assertThat(null))
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("assertThat(wrapper) does not throw any exception")
    @Test
    void assertBeanType__() {
        assertThatCode(() -> assertThat(beanProperty(new Object())))
                .doesNotThrowAnyException();
    }
}