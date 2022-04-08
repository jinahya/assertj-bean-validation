package com.github.jinahya.assertj.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
class Registration {

    @Valid
    @NotNull
    private final User user;
}
