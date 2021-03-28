package com.github.jinahya.assertj.validation;

import static java.util.Objects.requireNonNull;

/**
 * A class for wrapping bean object.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class BeanWrapper<T> extends Wrapper<T> {

    /**
     * Creates a new instance wraps specified bean object.
     *
     * @param wrapped the bean object to wrap; must not be {@code null}.
     * @return a new instance wraps {@code object}.
     */
    public static <T> BeanWrapper<T> bean(final T wrapped) {
        return new BeanWrapper<>(wrapped);
    }

    /**
     * Creates a new instance with specified bean object.
     *
     * @param wrapped the bean object to wrap; must not be {@code null}.
     */
    private BeanWrapper(final T wrapped) {
        super(wrapped);
    }
}
