package com.github.jinahya.assertj.validation;

import static com.github.jinahya.assertj.validation.BeanValidationUtils.validateValue;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

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
     * Asserts the {@link #actual} property value is a valid property value of specified property name with specified
     * bean type.
     *
     * @param beanType     the bean type.
     * @param propertyName the property name.
     * @return {@link #myself}.
     * @see javax.validation.Validator#validateValue(Class, String, Object, Class[])
     */
    public BeanPropertyValidationAssert isValidFor(final Class<?> beanType, final String propertyName) {
        requireNonNull(beanType, "beanType is null");
        requireNonNull(propertyName, "propertyName is null");
        assertThat(validateValue(validator(), beanType, propertyName, actual, groups())).isEmpty();
        return this;
    }
}
