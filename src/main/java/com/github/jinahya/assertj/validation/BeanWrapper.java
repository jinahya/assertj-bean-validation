package com.github.jinahya.assertj.validation;

/**
 * A class for wrapping bean object.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public class BeanWrapper<ACTUAL>
        extends Wrapper<ACTUAL> {

    /**
     * Creates a new instance wraps specified bean object.
     *
     * @param actual the bean object to wrap; must not be {@code null}.
     * @return a new instance wraps {@code object}.
     */
    public static <T> BeanWrapper<T> bean(final T actual) {
        return new BeanWrapper<>(actual);
    }

    /**
     * Creates a new instance with specified bean object.
     *
     * @param wrapped the bean object to wrap; must not be {@code null}.
     */
    private BeanWrapper(final ACTUAL wrapped) {
        super(wrapped);
    }
}
