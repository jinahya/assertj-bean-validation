package com.github.jinahya.assertj.validation;

import org.assertj.core.api.AbstractAssert;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getInvalidValue;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getLeafBean;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getMessage;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getPropertyPath;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getRootBean;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.getRootBeanClass;
import static com.github.jinahya.assertj.validation.ConstraintViolationUtils.requireConstraintViolationInstance;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for verifying an instance of {@code ConstraintViolation}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class ConstraintViolationAssert extends AbstractAssert<ConstraintViolationAssert, Object> {

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to verify; may be {@code null} yet must be an instance of either {@code
     *               javax.validation.ConstraintViolation} or {@code jakarta.validation.ConstraintViolation}.
     * @see #actual
     */
    public ConstraintViolationAssert(final Object actual) {
        super(requireConstraintViolationInstance(actual), ConstraintViolationAssert.class);
    }

    // ------------------------------------------------------------------------------------------------- getInvalidValue

    /**
     * Accepts the {@link jakarta.validation.ConstraintViolation#getInvalidValue() actual.invalidValue} to specified
     * consumer.
     *
     * @param requirements the consumer accepts {@code actual.invalidValue}.
     * @return {@link #myself self}.
     */
    public ConstraintViolationAssert hasInvalidValueSatisfying(final Consumer<Object> requirements) {
        isNotNull();
        assertThat(getInvalidValue(actual)).satisfies(requirements);
        return myself;
    }

    /**
     * Verifies that the {@link jakarta.validation.ConstraintViolation#getInvalidValue() actual.invalidValue} {@link
     * AbstractAssert#isEqualTo(Object) is equals to} specified value.
     *
     * @param expected the expected value of {@code actual.invalidValue}.
     * @return {@link #myself self}.
     */
    public ConstraintViolationAssert hasInvalidValue(final Object expected) {
        return hasInvalidValueSatisfying(v -> assertThat(v).isEqualTo(expected));
    }

    // ----------------------------------------------------------------------------------------------------- getLeafBean

    /**
     * Accepts the {@link jakarta.validation.ConstraintViolation#getLeafBean() actual.leafBean} to specified consumer.
     *
     * @param requirements the consumer accepts {@code actual.leafBean}.
     * @return {@link #myself self}.
     */
    public ConstraintViolationAssert hasLeafBeanSatisfying(final Consumer<Object> requirements) {
        isNotNull();
        assertThat(getLeafBean(actual)).satisfies(requirements);
        return myself;
    }

    /**
     * Verifies that the {@link jakarta.validation.ConstraintViolation#getLeafBean() actual.leafBean} {@link
     * AbstractAssert#isSameAs(Object) is same as} specified value.
     *
     * @param expected the expected value of {@code actual.leafBean} method.
     * @return {@link #myself self}.
     */
    public ConstraintViolationAssert hasLeafBean(final Object expected) {
        return hasLeafBeanSatisfying(v -> assertThat(v).isSameAs(expected));
    }

    // ------------------------------------------------------------------------------------------------------ getMessage

    /**
     * Accepts the {@link jakarta.validation.ConstraintViolation#getMessage() actual.message} to specified consumer.
     *
     * @param requirements the consumer accepts {@code actual.message}.
     * @return {@link #myself self}.
     */
    public ConstraintViolationAssert hasMessageSatisfying(final Consumer<? super String> requirements) {
        isNotNull();
        assertThat(getMessage(actual)).satisfies(requirements::accept);
        return myself;
    }

    /**
     * Verifies that the {@link jakarta.validation.ConstraintViolation#getMessage() actual.message} {@link
     * AbstractAssert#isEqualTo(Object) is equal to} specified value.
     *
     * @param expected the expected value of {@code actual.message}.
     * @return {@link #myself self}.
     */
    public ConstraintViolationAssert hasMessage(final String expected) {
        return hasMessageSatisfying(v -> assertThat(v).isEqualTo(expected));
    }

    // ------------------------------------------------------------------------------------------------- getPropertyPath
    @SuppressWarnings({"unchecked"})
    public <PathType extends Iterable<?>> ConstraintViolationAssert hasPropertyPathSatisfying(
            final Consumer<? super PathType> requirements) {
        requireNonNull(requirements, "requirements is null");
        isNotNull();
        final PathType propertyPath = getPropertyPath(actual);
        assertThat(propertyPath).satisfies(v -> requirements.accept((PathType) v));
        return myself;
    }

    public <PathType> ConstraintViolationAssert hasPropertyPath(final PathType expected) {
        return hasPropertyPathSatisfying(v -> assertThat(v).isEqualTo(expected));
    }

    // ----------------------------------------------------------------------------------------------------- getRootBean
    @SuppressWarnings({"unchecked"})
    public <T> ConstraintViolationAssert hasRootBeanSatisfying(final Consumer<? super T> requirements) {
        requireNonNull(requirements, "requirements is null");
        isNotNull();
        assertThat(getRootBean(actual)).satisfies(v -> requirements.accept((T) v));
        return myself;
    }

    public <T> ConstraintViolationAssert hasRootBean(final T expected) {
        return hasRootBeanSatisfying(v -> assertThat(v).isSameAs(expected));
    }

    // ------------------------------------------------------------------------------------------------ getRootBeanClass
    public ConstraintViolationAssert hasRootBeanClassSatisfying(final Consumer<? super Class<?>> requirements) {
        requireNonNull(requirements, "requirements is null");
        isNotNull();
        assertThat(getRootBeanClass(actual)).satisfies(requirements::accept);
        return myself;
    }

    public ConstraintViolationAssert hasRootBeanClass(final Class<?> expected) {
        return hasRootBeanClassSatisfying(v -> assertThat(v).isSameAs(expected));
    }

    // ---------------------------------------------------------------------------------- getRootBeanClass / getRootBean
    @SuppressWarnings({"unchecked"})
    public <T, U extends T> ConstraintViolationAssert hasRootBeanSatisfying(
            final BiConsumer<? super Class<T>, ? super U> requirements) {
        requireNonNull(requirements, "requirements is null");
        return hasRootBeanClassSatisfying(c -> hasRootBeanSatisfying(b -> requirements.accept((Class<T>) c, (U) b)));
    }

    public <T, U extends T> ConstraintViolationAssert hasRootBean(final Class<T> expectedRootBeanClass,
                                                                  final U expectedRootBean) {
        return hasRootBeanSatisfying((c, b) -> {
            assertThat(c).isSameAs(expectedRootBeanClass);
            assertThat(b).isSameAs(expectedRootBean);
        });
    }
}
