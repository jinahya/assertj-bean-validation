package com.github.jinahya.assertj.validation.jakarta;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

final class ValidatorFactories {

    private static final class Holder {

        static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();

        private Holder() {
            throw new NonInstantiatableAssertionError();
        }
    }

    static ValidatorFactory getValidatorFactory() {
        return Holder.VALIDATOR_FACTORY;
    }

    private ValidatorFactories() {
        throw new NonInstantiatableAssertionError();
    }
}
