
package org.hibernate.validator.referenceguide.chapter01;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.hibernate.validator.referenceguide.chapter01.CarConstants.PROPERTY_LICENSE_PLATE;
import static org.hibernate.validator.referenceguide.chapter01.CarConstants.PROPERTY_MANUFACTURER;
import static org.hibernate.validator.referenceguide.chapter01.CarConstants.PROPERTY_SEAT_COUNT;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.carOf;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.validLicensePlate;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.validManufacturer;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.validSeatCount;

class Car_HasValidProperty_Test {

    @Test
    void __() {
        final var car = carOf(validManufacturer(), validLicensePlate(), validSeatCount());
        final var assertion = assertThatBean(car);
        assertThatCode(() -> assertion.hasValidProperty(PROPERTY_MANUFACTURER))
                .doesNotThrowAnyException();
        assertThatCode(() -> assertion.hasValidProperty(PROPERTY_LICENSE_PLATE))
                .doesNotThrowAnyException();
        assertThatCode(() -> assertion.hasValidProperty(PROPERTY_SEAT_COUNT))
                .doesNotThrowAnyException();
    }
}
