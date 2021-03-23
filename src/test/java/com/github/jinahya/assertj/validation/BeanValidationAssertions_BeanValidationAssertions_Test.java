package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class BeanValidationAssertions_BeanValidationAssertions_Test extends BeanValidationAssertionsTest {

    @Test
    void BeanValidationAssertions__() {
        assertThatCode(BeanValidationAssertions::new).doesNotThrowAnyException();
    }
}