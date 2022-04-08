package com.github.jinahya.assertj.validation.user;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;

import javax.validation.Valid;

@Getter
@SuperBuilder
class Registration {

    @Valid
    @NotNull
    private User user;
}
