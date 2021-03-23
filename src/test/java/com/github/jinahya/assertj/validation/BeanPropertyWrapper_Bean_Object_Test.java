package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.BeanPropertyWrapper.beanProperty;
import static org.assertj.core.api.Assertions.assertThatCode;

class BeanPropertyWrapper_Bean_Object_Test extends BeanPropertyWrapperTest {

    @DisplayName("beanProperty(null) does not throw any exception")
    @Test
    void bean_NullPointerException_ObjectIsNull() {
        assertThatCode(() -> beanProperty(null))
                .doesNotThrowAnyException();
    }

    @DisplayName("beanProperty(value) does not throw any exception")
    @Test
    void bean_NoException_() {
        assertThatCode(() -> beanProperty(new Object()))
                .doesNotThrowAnyException();
    }
}