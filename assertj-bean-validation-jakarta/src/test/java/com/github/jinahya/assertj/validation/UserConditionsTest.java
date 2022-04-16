package com.github.jinahya.assertj.validation;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserConditionsTest {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    void isSENIOR_Fail_59() {
        final User actual = User.of("None", 59);
        final Assert<?, User> assertion = assertThat(actual);
        final AssertionError error = assertThrows(AssertionError.class, () -> assertion.is(UserConditions.SENIOR));
    }

    @Test
    void isSENIOR_Pass_60() {
        final User actual = User.of("None", 60);
        assertThat(actual).is(UserConditions.SENIOR);
    }
}