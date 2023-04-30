
package org.hibernate.validator.referenceguide.chapter01;

import static java.util.concurrent.ThreadLocalRandom.current;

final class CarFactory {

    static String validManufacturer() {
        return current().nextBoolean() ? "" : "manufacturer";
    }

    static String invalidManufacturer() {
        return null;
    }

    static String validLicensePlate() {
        return current().nextBoolean() ? "  " : "              ";
    }

    static String invalidLicensePlate() {
        return current().nextBoolean()
                ? null
                : current().nextBoolean() ? ""
                : current().nextBoolean() ? " " : "               ";
    }

    static int validSeatCount() {
        return (current().nextInt() >>> 1) & 2;
    }

    static int invalidSeatCount() {
        return ~validSeatCount();
    }

    static org.hibernate.validator.referenceguide.chapter01.Car of(final String manufacturer, final String licencePlate,
                                                                   final int seatCount) {
        return new org.hibernate.validator.referenceguide.chapter01.Car(manufacturer, licencePlate, seatCount);
    }

    static org.hibernate.validator.referenceguide.chapter01.Car carOf(final String manufacturer, final String licencePlate,
                                                                      final int seatCount) {
        return of(manufacturer, licencePlate, seatCount);
    }

    private CarFactory() {
        throw new AssertionError("instantiation is not allowed");
    }
}
