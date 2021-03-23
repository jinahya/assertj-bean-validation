package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.BeanValidationAssertions.assertBean;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * A class for testing {@link BeanValidationAssertions#assertBean(Object)} method.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class BeanValidationAssertions_AssertBean_Object_Test extends BeanValidationAssertionsTest {

    @DisplayName("assertBean(object) throws NullPointerException when object is null")
    @Test
    void assertBean_NullPointerException_ObjectIsNull() {
        assertThatThrownBy(() -> assertBean(null))
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("assertBean(object) does not throw any exception")
    @Test
    void assertBean__() {
        assertThatCode(() -> assertBean(new Object()))
                .doesNotThrowAnyException();
    }
}