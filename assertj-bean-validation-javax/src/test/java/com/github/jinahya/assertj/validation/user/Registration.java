package com.github.jinahya.assertj.validation.user;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@SuperBuilder
class Registration {

    @Valid
    @NotNull
    final User user;
}
