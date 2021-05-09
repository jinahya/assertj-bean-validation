package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

/**
 * An abstract class for testing subclasses of {@link AbstractValidationExceptionAssert} class.
 *
 * @param <T>      subclass type parameter
 * @param <SELF>   self type of {@link T}
 * @param <ACTUAL> actual type of {@link SELF}
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public abstract class AbstractValidationExceptionAssertTest<
        T extends AbstractValidationExceptionAssert<SELF, ACTUAL>,
        SELF extends T,
        ACTUAL extends RuntimeException> {

    /**
     * Creates a new instance with specified assert class.
     *
     * @param assertClass the assert class to test.
     * @see #assertClass
     */
    protected AbstractValidationExceptionAssertTest(final Class<T> assertClass) {
        super();
        this.assertClass = requireNonNull(assertClass, "assertClass is null");
    }

    /**
     * the assert class to test.
     */
    protected final Class<T> assertClass;
}