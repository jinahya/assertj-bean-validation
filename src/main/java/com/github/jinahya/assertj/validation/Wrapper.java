package com.github.jinahya.assertj.validation;

/**
 * An abstract base class for wrapping specific type of actual values.
 *
 * @param <ACTUAL> actual value type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
abstract class Wrapper<ACTUAL> {

    /**
     * Creates a new instance wraps specified actual value.
     *
     * @param actual the actual value to wrap.
     */
    Wrapper(final ACTUAL actual) {
        super();
        this.actual = actual;
    }

    /**
     * Returns the actual value wrapped in this wrapper.
     *
     * @return the actual value wrapped in this wrapper.
     */
    ACTUAL getActual() {
        return actual;
    }

    private final ACTUAL actual;
}
