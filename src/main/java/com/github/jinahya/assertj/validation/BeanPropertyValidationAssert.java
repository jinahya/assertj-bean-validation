package com.github.jinahya.assertj.validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static com.github.jinahya.assertj.validation.BeanValidationUtils.validateValue;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An assertion class for validating property values.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class BeanPropertyValidationAssert extends AbstractBeanValidationAssert<BeanPropertyValidationAssert> {

    /**
     * Creates a new instance with specified property value.
     *
     * @param value the property value.
     */
    public BeanPropertyValidationAssert(final Object value) {
        super(value, BeanPropertyValidationAssert.class);
    }

    /**
     * Asserts the {@link #actual} value is a valid property value for specified property of specified class.
     *
     * @param beanType     the class whose property are examined.
     * @param propertyName the name of the property.
     * @return {@link #myself}.
     * @see javax.validation.Validator#validateValue(Class, String, Object, Class[])
     */
    public @NotNull BeanPropertyValidationAssert isValidFor(final @NotNull Class<?> beanType,
                                                            final @NotBlank String propertyName) {
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        assertThat(validateValue(validator(), beanType, propertyName, actual, groups())).isEmpty();
        return this;
    }
}
