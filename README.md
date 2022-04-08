# assertj-bean-validation

[![CI](https://github.com/jinahya/assertj-bean-validation/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/assertj-bean-validation/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_assertj-bean-validation&metric=alert_status)](https://sonarcloud.io/dashboard?id=jinahya_assertj-bean-validation)
[![Known Vulnerabilities](https://snyk.io/test/github/jinahya/assertj-bean-validation/badge.svg)](https://snyk.io/test/github/jinahya/assertj-bean-validation)

[![Maven Central](https://img.shields.io/maven-central/v/com.github.jinahya/assertj-bean-validation)](https://search.maven.org/artifact/com.github.jinahya/assertj-bean-validation)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/assertj-bean-validation/javadoc.svg)](https://javadoc.io/doc/com.github.jinahya/assertj-bean-validation)

An [AssertJ](https://joel-costigliola.github.io/assertj/) extension for [Bean-Validation](https://beanvalidation.org/).

## Coordinates

For Java EE,

```xml

<dependency>
  <groupId>com.github.jinahya</groupId>
  <artifactId>assertj-bean-validation-javax</artifactId>
</dependency>
```

For Jakarta EE,

```xml

<dependency>
  <groupId>com.github.jinahya</groupId>
  <artifactId>assertj-bean-validation-jakarta</artifactId>
</dependency>
```

## Compatibilities

* Depends(`provided`) on
  the [latest org.assertj:assertj-core](https://javadoc.io/doc/org.assertj/assertj-core/latest/index.html).
* Targets Java 8.

## Usages

```java
class User {

    @NotBlank
    String name;

    @Max(128)
    @Min(0)
    @PositiveOrZero
    int age;
}

class Registration {

    @Valid
    @NotNull
    User user;
}
```

### Verifying beans

Verifies that the actual bean object is valid.

```java
class UserTest {

    @Test
    void test1() {
        assertBean(new User("Jane", 28))
                .isValid()
                .hasValidProperty("name")
                .hasValidProperty("age");
    }

    @DisplayName("debug with constraint violations")
    @Test
    void test2() {
        assertThatThrownBy(() -> {
            assertBean(new User("", 27))
                    .isValid(cv -> {
                        assertThat(cv.getInvalidValue()).isEqualTo(actual.getName());
                        assertThat(cv.getPropertyPath())
                                .isNotEmpty()
                                .allSatisfy(n -> {
                                    assertThat(n.getName()).isEqualTo("name");
                                });
                    });
        }).isInstanceOf(AssertionError.class);
        assertThatThrownBy(() -> assertBean(new User("John", 300))
                .hasValidProperty("age", cv -> {
                    assertThat(cv.getInvalidValue()).isEqualTo(actual.getAge());
                    assertThat(cv.getPropertyPath())
                            .isNotEmpty()
                            .allSatisfy(n -> assertThat(n.getName()).isEqualTo("age"));
                })
        ).isInstanceOf(AssertionError.class);
    }
}
```

### Verifying beans using extended assert classes

You can work with your own (extended) assert class.

```java
class UserAssert
        extends AbstractBeanAssert<UserAssert, User> {

    UserAssert(User actual) {
        super(actua, UserAssert.class);
    }

    UserAssert isNamedJane() {
        return isNotNull()
                .is(new Condition<>(v -> "Jane".equalsIgnoreCase(v.getName()),
                                    "named Jane"));
    }
}
```

A number of static factory methods are prepared.

```java
class UserAssertTest {

    @Test
    void test1() {
        // specify your assert class along with actual class
        {
            final User actual = User.newValidInstance_().name("Jane").build();
            final UserAssert assertion = assertBean(UserAssert.class, User.class, actual);
            assertion.isValid()
                    .hasValidProperty("name")
                    .hasValidProperty("age")
                    .isNamedJane();
        }
        // or emit the actual class
        {
            final User actual = User.newValidInstance_().name("Jane").build();
            final UserAssert assertion = assertBean(UserAssert.class, actual);
            assertion.isValid()
                    .hasValidProperty("name")
                    .hasValidProperty("age")
                    .isNamedJane();
        }
        // or let it find whatever required
        {
            // finds the `UserAssert` class in the same package of `User` class
            final Object actual = User.newValidInstance_().name("Jane").build();
            final UserAssert assertion = assertVirtualBean(actual);
            assertion.isValid()
                    .hasValidProperty("name")
                    .hasValidProperty("age")
                    .isNamedJane();
        }
    }
}
```

### Verifying values for properties of bean types.

```java
class UserPropertyTest {

    @Test
    void test() {
        assertValue("John")
                .isValidFor(User.class, "name");
        assertValue(31)
                .isValidFor(User.class, "age");
    }
}
```

Note that a bean can also be validated for a property of other beans.
(Note also that the `@Valid` is not honored by `validateProperty` method nor `validateValue` method.)

```java
class UserPropertyTest {

    @Test
    void test() {
        User user = null;
        assertBean(null)
                .isValidFor(Registration.class, "user");
        user = new User("John", 300); // invalid
        assertThatCode(
                () -> assertBean(actual).isValidFor(Registration.class, "user")
        ).doesNotThrowAnyException();
        Registration registration = new Registration(user);
        assertThatThrownBy(
                () -> assertBean(registration).isValid()
        ).isInstanceOf(AssertionError.class);
    }
}
```