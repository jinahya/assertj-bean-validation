package com.github.jinahya.assertj.validation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Objects;

import static java.util.concurrent.ThreadLocalRandom.current;

@Setter
@Getter
class User implements Serializable {

    private static final long serialVersionUID = -6934212838224824723L;

    static User newValidInstance() {
        final User instance = new User();
        instance.setName(Long.toString(System.nanoTime()));
        instance.setAge(current().nextInt() & Integer.MAX_VALUE);
        return BeanValidationTestUtils.requireValid(instance);
    }

    static User newInvalidInstance() {
        final User instance = newValidInstance();
        do {
            if (current().nextBoolean()) {
                instance.setName(current().nextBoolean() ? "" : current().nextBoolean() ? " " : null);
            }
            if (current().nextBoolean()) {
                instance.setAge(current().nextInt() | Integer.MIN_VALUE);
            }
        } while (BeanValidationTestUtils.isValid(instance));
        return instance;
    }

    @Override
    public String toString() {
        return super.toString() + '{'
               + "name=" + name
               + ",age=" + age
               + '}';
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        final User that = (User) obj;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @NotBlank
    private String name;

    @PositiveOrZero
    private int age;
}
