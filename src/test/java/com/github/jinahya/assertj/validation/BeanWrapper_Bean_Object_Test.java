package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.BeanWrapper.bean;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BeanWrapper_Bean_Object_Test {

    @DisplayName("bean(null) throws NullPointerException")
    @Test
    void bean_NullPointerException_ObjectIsNull() {
        assertThatThrownBy(() -> bean(null))
                .isInstanceOf(NullPointerException.class);
    }

    @DisplayName("bean(object) does not throw any exception")
    @Test
    void bean_NoException_() {
        assertThatCode(() -> bean(new Object()))
                .doesNotThrowAnyException();
    }
}