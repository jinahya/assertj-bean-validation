
package org.hibernate.validator.referenceguide.chapter01;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.hibernate.validator.referenceguide.chapter01.CarConstants.PROPERTY_MANUFACTURER;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.carOf;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.invalidManufacturer;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.validLicensePlate;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.validSeatCount;

class Car_DoesNotHaveValidProperty_Test {

    @Test
    void __() {
        final var car = carOf(invalidManufacturer(), validLicensePlate(), validSeatCount());
        final var assertion = assertThatBean(car);
        assertThatCode(() -> assertion.doesNotHaveValidProperty(PROPERTY_MANUFACTURER))
                .doesNotThrowAnyException();
    }
}
