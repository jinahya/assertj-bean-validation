package com.github.jinahya.assertj.validation;

public class NoOp {

    private NoOp() {
        throw new NonInstantiatableAssertionError();
    }
}
