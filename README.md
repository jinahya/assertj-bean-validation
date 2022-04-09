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

* Depends(as `provided`) on
  the [latest org.assertj:assertj-core](https://javadoc.io/doc/org.assertj/assertj-core/latest/index.html).
* Targets Java 8.

## Usages

Say, we have the following beans to verify.

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
    
    User user;
}

class SeniorRegistration
        extends Registration {

    @AssertTrue
    boolean isUserSenior() {
        return user == null || user.age >= 60;
    }
}
```

### Verifying beans and properties

Verifies that actual bean objects and/or their properties are valid.

```java
class Test {

    @Test
    void test() {
        assertBean(new User("Jane", 28))  // valid
                .isValid()                // passes
                .hasValidProperty("name") // passes
                .hasValidProperty("age"); // passes
    }
}
```

You can debug by verifying constraint violations, if any populated.

```java
class Test {

    @Test
    void test() {
        assertBean(new User("", 27))             // invalid; name is blank
                .isValid(cv -> {                 // should fail
                    assertThat(cv.getInvalidValue()).isEqualTo(actual.getName());
                    assertThat(cv.getPropertyPath())
                            .isNotEmpty()
                            .allSatisfy(n -> {
                                assertThat(n.getName()).isEqualTo("name");
                            });
                });
        assertBean(new User("John", 300))        // invalid; too old to be true
                .hasValidProperty("age", cv -> { // should fail
                    assertThat(cv.getInvalidValue()).isEqualTo(actual.getAge());
                    assertThat(cv.getPropertyPath())
                            .isNotEmpty()
                            .allSatisfy(n -> assertThat(n.getName()).isEqualTo("age"));
                });
    }
}
```

### Using extended assertion classes

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
class Test {

    @Test
    void test() {
        // specify your assert class along with specific actual class
        {
            final User actual = new User("Jane", 0);
            assertBean(UserAssert.class, User.class, actual)
                    .isValid()
                    .hasValidProperty("name")
                    .hasValidProperty("age")
                    .isNamedJane();
        }
        // or emit the actual class
        {
            final User actual = new User("John", 1);
            assertBean(UserAssert.class, actual)
                    .isValid()
                    .hasValidProperty("name")
                    .hasValidProperty("age")
                    .isNamedJane();
        }
        // or let it find whatever required
        {
            // tries to find a class named `UserAssert` class
            // in the same package of `User` class
            final Object actual = new User("Jane", 0); // java.lang.Object
            assertVirtualBean(actual)                  // mind the name of the method
                    .isValid()
                    .hasValidProperty("name")
                    .hasValidProperty("age")
                    .isNamedJane();
        }
    }
}
```

### Verifying property values

You can verify a value against a property of specific bean type.

```java
class Test {

    @Test
    void test() {
        assertBeanProperty("John").isValidFor(User.class, "name"); // passes
        assertBeanProperty(null).isValidFor(User.class, "name");   // fails
        assertBeanProperty("  ").isValidFor(User.class, "name");   // fails
        assertBeanProperty(31).isValidFor(User.class, "age");      // passes
        assertBeanProperty(-1).isValidFor(User.class, "age");      // fails
        assertBeanProperty(297).isValidFor(User.class, "age");     // fails
    }
}
```

Note that a bean can also be validated against a property of another bean.

```java
class Test {

    @Test
    void test() {
        assertBean(null).isValidFor(Registration.class, "user"); // should fail by 
    }
}
```

Note also that the `@Valid` is not honored by `Validator#validateProperty` method nor `Validator#validateValue` method.
See [6.1.1. Validation methods] (Jakarta Bean Validation specification).

```java
class Test {

    @Test
    void test() {
        User user = new User("John", 300);                       // invalid, obviously
        assertBean(user).isValid();                              // so does fail
        assertBean(user).isValidFor(Registration.class, "user"); // DOES NOT FAIL!
        assertBean(new Registration(user)).isValid();            // fails, after ...
    }
}
```

[6.1.1. Validation methods]: https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#validationapi-validatorapi-validationmethods