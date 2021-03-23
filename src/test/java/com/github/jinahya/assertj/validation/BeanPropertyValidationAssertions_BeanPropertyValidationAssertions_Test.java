package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class BeanPropertyValidationAssertions_BeanPropertyValidationAssertions_Test
        extends BeanPropertyValidationAssertionsTest {

    @Test
    void BeanValidationAssertions__() {
        assertThatCode(BeanPropertyValidationAssertions::new).doesNotThrowAnyException();
    }
}