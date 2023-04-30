
package org.hibernate.validator.referenceguide.chapter01;

final class CarConstants {

    static final String PROPERTY_MANUFACTURER = "manufacturer";

    static final String PROPERTY_LICENSE_PLATE = "licensePlate";

    static final String PROPERTY_SEAT_COUNT = "seatCount";

    private CarConstants() {
        throw new AssertionError("instantiation is not allowed");
    }
}
