package com.github.jinahya.assertj.validation;

import static com.github.jinahya.assertj.validation.BeanValidationUtils.validate;
import static com.github.jinahya.assertj.validation.BeanValidationUtils.validateProperty;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

public class BeanValidationAssert extends AbstractBeanValidationAssert<BeanValidationAssert> {

    public BeanValidationAssert(final Object object) {
        super(requireNonNull(object, "object is null"), BeanValidationAssert.class);
    }

    public BeanValidationAssert isValid() {
        isNotNull();
        assertThat(validate(validator(), actual, groups())).isEmpty();
        return this;
    }

    public BeanValidationAssert hasValidProperty(final String propertyName) {
        requireNonNull(propertyName, "propertyName is null");
        isNotNull();
        assertThat(validateProperty(validator(), actual, propertyName, groups())).isEmpty();
        return this;
    }
}
