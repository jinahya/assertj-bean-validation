package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.BeanValidationTestUtils;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import static java.util.concurrent.ThreadLocalRandom.current;

@Data
class User {

    static User newValidInstance() {
        final User instance = new User();
        instance.setName(Long.toString(System.nanoTime()));
        instance.setAge(current().nextInt() & Integer.MAX_VALUE);
        return BeanValidationTestUtils.requireValid(instance);
    }

    private static User setInvalidName(final User instance) {
        instance.setName(current().nextBoolean() ? "" : current().nextBoolean() ? " " : null);
        return instance;
    }

    private static User setInvalidAge(final User instance) {
        instance.setAge(current().nextInt() | Integer.MIN_VALUE);
        return instance;
    }

    static User newInvalidInstance() {
        final User instance = newValidInstance();
        do {
            if (current().nextBoolean()) {
                setInvalidName(instance);
            }
            if (current().nextBoolean()) {
                setInvalidAge(instance);
            }
        } while (BeanValidationTestUtils.isValid(instance));
        return instance;
    }

    static User newInstanceWithInvalidName() {
        return setInvalidName(newValidInstance());
    }

    static User newInstanceWithInvalidAge() {
        return setInvalidAge(newValidInstance());
    }

    @NotBlank
    private String name;

    @PositiveOrZero
    private int age;
}
