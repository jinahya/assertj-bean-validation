package com.github.jinahya.assertj.validation.javax;

public class NoOp {

    private NoOp() {
        throw new NonInstantiatableAssertionError();
    }
}
