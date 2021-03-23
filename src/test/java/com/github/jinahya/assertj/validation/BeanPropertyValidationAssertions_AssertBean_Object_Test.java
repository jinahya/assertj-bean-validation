package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.BeanPropertyValidationAssertions.assertBeanProperty;
import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * A class for testing {@link BeanPropertyValidationAssertions#assertBeanProperty(Object)} method.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class BeanPropertyValidationAssertions_AssertBean_Object_Test extends BeanPropertyValidationAssertionsTest {

    @DisplayName("assertBeanProperty(null) does not throw any exception")
    @Test
    void assertBeanProperty_NullPointerException_ValueIsNull() {
        assertThatCode(() -> assertBeanProperty(null))
                .doesNotThrowAnyException();
    }

    @DisplayName("assertBeanProperty(value) does not throw any exception")
    @Test
    void assertBeanProperty__() {
        assertThatCode(() -> assertBeanProperty(new Object()))
                .doesNotThrowAnyException();
    }
}