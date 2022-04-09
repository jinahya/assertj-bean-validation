package com.github.jinahya.assertj.validation.user;

import lombok.experimental.SuperBuilder;

import javax.validation.constraints.AssertTrue;

@SuperBuilder
class SeniorRegistration
        extends Registration {

    @AssertTrue
    boolean isUserSenior() {
        return user == null || user.getAge() > 60;
    }
}
