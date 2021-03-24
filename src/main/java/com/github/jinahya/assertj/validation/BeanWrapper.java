package com.github.jinahya.assertj.validation;

import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

/**
 * A class for wrapping bean object.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class BeanWrapper {

    /**
     * Creates a new instance wraps specified bean object.
     *
     * @param object the bean object to wrap; must be not {@code null}.
     * @return a new instance wraps {@code object}.
     */
    public static @NotNull BeanWrapper bean(final @NotNull Object object) {
        requireNonNull(object, "object is null");
        return new BeanWrapper(object);
    }

    /**
     * Creates a new instance with specified bean object.
     *
     * @param object the bean object to wrap; must be not {@code null}.
     */
    private BeanWrapper(final Object object) {
        super();
        this.object = requireNonNull(object, "object is null");
    }

    /**
     * Returns the wrapped bean object.
     *
     * @return the wrapped bean object.
     */
    Object getObject() {
        return object;
    }

    /**
     * The wrapped bean object.
     */
    @NotNull
    private final Object object;
}
