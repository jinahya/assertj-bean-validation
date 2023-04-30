
package org.hibernate.validator.referenceguide.chapter01;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hibernate.validator.referenceguide.chapter01.CarConstants.PROPERTY_LICENSE_PLATE;
import static org.hibernate.validator.referenceguide.chapter01.CarConstants.PROPERTY_MANUFACTURER;
import static org.hibernate.validator.referenceguide.chapter01.CarConstants.PROPERTY_SEAT_COUNT;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.carOf;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.invalidLicensePlate;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.invalidManufacturer;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.invalidSeatCount;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.validLicensePlate;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.validManufacturer;
import static org.hibernate.validator.referenceguide.chapter01.CarFactory.validSeatCount;

class Car_DoesNotHaveValidProperty_Test {

    @RepeatedTest(1024)
    @Test
    void __invalidManufacturer() {
        final var car = carOf(invalidManufacturer(), validLicensePlate(), validSeatCount());
        final var assertion = assertThatBean(car);
        assertThatCode(() -> assertion.doesNotHaveValidProperty(PROPERTY_MANUFACTURER))
                .doesNotThrowAnyException();
        assertThatThrownBy(() -> assertion.doesNotHaveValidProperty(PROPERTY_LICENSE_PLATE))
                .isInstanceOf(AssertionError.class);
        assertThatThrownBy(() -> assertion.doesNotHaveValidProperty(PROPERTY_SEAT_COUNT))
                .isInstanceOf(AssertionError.class);
    }

    @RepeatedTest(1024)
    @Test
    void __invalidLicensePlate() {
        final var car = carOf(validManufacturer(), invalidLicensePlate(), validSeatCount());
        final var assertion = assertThatBean(car);
        assertThatThrownBy(() -> assertion.doesNotHaveValidProperty(PROPERTY_MANUFACTURER))
                .isInstanceOf(AssertionError.class);
        assertThatCode(() -> assertion.doesNotHaveValidProperty(PROPERTY_LICENSE_PLATE))
                .doesNotThrowAnyException();
        assertThatThrownBy(() -> assertion.doesNotHaveValidProperty(PROPERTY_SEAT_COUNT))
                .isInstanceOf(AssertionError.class);
    }

    @RepeatedTest(1024)
    @Test
    void __invalidSeatCount() {
        final var car = carOf(validManufacturer(), validLicensePlate(), invalidSeatCount());
        final var assertion = assertThatBean(car);
        assertThatThrownBy(() -> assertion.doesNotHaveValidProperty(PROPERTY_MANUFACTURER))
                .isInstanceOf(AssertionError.class);
        assertThatThrownBy(() -> assertion.doesNotHaveValidProperty(PROPERTY_LICENSE_PLATE))
                .isInstanceOf(AssertionError.class);
        assertThatCode(() -> assertion.doesNotHaveValidProperty(PROPERTY_SEAT_COUNT))
                .doesNotThrowAnyException();
    }
}
