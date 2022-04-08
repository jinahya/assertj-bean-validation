package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.AbstractBeanAssert;
import com.github.jinahya.assertj.validation.ValidationAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class User_AssertBean_Test {

    @DisplayName("assertBean(actual)")
    @Test
    void assertBean__() {
        final User actual = User.newValidInstance();
        final AbstractBeanAssert<?, User> assertion = ValidationAssertions.assertBean(actual);
        assertion.isValid()
                .hasValidProperty("name")
                .hasValidProperty("age");
    }

    @DisplayName("assertBean(selfClass, actualClass, actual)")
    @Test
    void assertBeanWithSelfClassAndActualClass__() {
        final User actual = User.newValidInstance_().name("Jane").build();
        final UserAssert assertion = ValidationAssertions.assertBean(UserAssert.class, User.class, actual);
        assertion.isValid()
                .hasValidProperty("name")
                .hasValidProperty("age")
                .isNamedJane();
    }

    @DisplayName("assertBean(selfClass, actual)")
    @Test
    void assertBeanWithSelfClass__() {
        final User actual = User.newValidInstance_().name("Jane").build();
        final UserAssert assertion = ValidationAssertions.assertBean(UserAssert.class, actual);
        assertion.isValid()
                .hasValidProperty("name")
                .hasValidProperty("age")
                .isNamedJane();
    }

    @DisplayName("assertVirtualBean(actual)")
    @Test
    void assertVirtualBean__() {
        final User actual = User.newValidInstance_().name("Jane").build();
        final UserAssert assertion = ValidationAssertions.assertVirtualBean(actual);
        assertion.isValid()
                .hasValidProperty("name")
                .hasValidProperty("age")
                .isNamedJane();
    }
}
