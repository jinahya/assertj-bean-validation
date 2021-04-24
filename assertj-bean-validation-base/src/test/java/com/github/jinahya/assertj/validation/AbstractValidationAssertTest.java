package com.github.jinahya.assertj.validation;

import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.requireNonNull;

/**
 * An abstract class for testing subclasses of {@link AbstractValidationAssert} class.
 *
 * @param <ASSERT>    subclass type parameter
 * @param <ACTUAL>    actual type parameter
 * @param <VALIDATOR> the type of {@code ....validation.Validator}
 */
@Slf4j
@SuppressWarnings({"java:S119"})
public abstract class AbstractValidationAssertTest<
        ASSERT extends AbstractValidationAssert<ASSERT, ACTUAL, VALIDATOR>,
        ACTUAL,
        VALIDATOR> {

    protected AbstractValidationAssertTest(final Class<ASSERT> assertClass, final Class<ACTUAL> actualClass) {
        super();
        this.assertClass = requireNonNull(assertClass, "assertClass is null");
        this.actualClass = requireNonNull(actualClass, "actualClass is null");
    }

    /**
     * Returns a new instance of {@link #assertClass}.
     *
     * @param actual an actual value.
     * @return a new instance of {@link #assertClass}.
     */
    protected ASSERT assertInstance(final ACTUAL actual) {
        try {
            return assertClass.getConstructor(actualClass).newInstance(actual);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException(roe);
        }
    }

    /**
     * The assert class to test.
     */
    protected Class<ASSERT> assertClass;

    protected Class<ACTUAL> actualClass;
}