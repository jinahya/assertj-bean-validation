package com.github.jinahya.assertj.validation.jakarta;

public class NoOp {

    private NoOp() {
        throw new NonInstantiatableAssertionError();
    }
}
