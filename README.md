# assertj-bean-validation

[![CI](https://github.com/jinahya/assertj-bean-validation/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/assertj-bean-validation/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_assertj-bean-validation&metric=alert_status)](https://sonarcloud.io/dashboard?id=jinahya_assertj-bean-validation)
[![Known Vulnerabilities](https://snyk.io/test/github/jinahya/assertj-bean-validation/badge.svg)](https://snyk.io/test/github/jinahya/assertj-bean-validation)

[![Maven Central](https://img.shields.io/maven-central/v/com.github.jinahya/assertj-bean-validation-javax?label=maven-central%20%28javax%29)](https://search.maven.org/artifact/com.github.jinahya/assertj-bean-validation-javax)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/assertj-bean-validation-javax/javadoc.svg?label=javadoc.io%20%28javax%29)](https://javadoc.io/doc/com.github.jinahya/assertj-bean-validation-javax)

[![Maven Central](https://img.shields.io/maven-central/v/com.github.jinahya/assertj-bean-validation-jakarta?label=maven-central%20%28jakarta%29)](https://search.maven.org/artifact/com.github.jinahya/assertj-bean-validation-jakarta)
[![javadoc-](https://javadoc.io/badge2/com.github.jinahya/assertj-bean-validation-jakarta/javadoc.svg?label=javadoc.io%20%28jakarta%29)](https://javadoc.io/doc/com.github.jinahya/assertj-bean-validation-jakarta)

An [AssertJ](https://joel-costigliola.github.io/assertj/) extension for [Bean-Validation](https://beanvalidation.org/).

<img src="https://raw.githubusercontent.com/joel-costigliola/assertj/gh-pages/favicon.png" height="100"/> + 
<img src="https://beanvalidation.org/logo/logo.svg" height="100" alt="BeanValidation"/>

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

* Depends (as `provided`) on
  the [latest org.assertj:assertj-core](https://javadoc.io/doc/org.assertj/assertj-core/latest/index.html).
* Targets **Java 8**.

### JDK

This module requires the **latest JDK**(currently, 18), especially with its test sources, for building itself.

## Usages

Say, we have the following beans to verify.

```java
class User {

    @NotBlank String name;

    @Max(0x80) @Min(0x00) @PositiveOrZero int age;
}

class Registration {

    @Valid User user;
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
        assertThatBean(new User("Jane", 28))  // valid
                .isValid()                    // passes
                .hasValidProperty("name")     // passes
                .hasValidProperty("age");     // passes
    }
}
```

You can debug by analyzing (or verifying) constraint violations populated while validating.

```java
class Test {

    @Test
    void test() {
        assertThatBean(new User("", 27)) // invalid; name is blank
                .isValid(cv -> {         // should fail
                    assertThat(cv.getInvalidValue()).isEqualTo(actual.getName());
                    assertThat(cv.getPropertyPath())
                            .isNotEmpty()
                            .allSatisfy(n -> {
                                assertThat(n.getName()).isEqualTo("name");
                            });
                });
        assertThatBean(new User("John", 300))    // invalid; too old to be true
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

You can work with your own (extended) assertion class.

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

A number of static factory methods are prepared for extended assertion classes.

```java
class Test {

    @Test
    void test() {
        // specify your assert class along with specific actual class
        {
            final User actual = new User("Jane", 0);
            assertThatBean(UserAssert.class, User.class, actual)
                    .isValid()
                    .hasValidProperty("name")
                    .hasValidProperty("age")
                    .isNamedJane(); // should pass
        }
        // or emit the actual class
        {
            final User actual = new User("John", 1);
            assertThatBean(UserAssert.class, actual)
                    .isValid()
                    .hasValidProperty("name")
                    .hasValidProperty("age")
                    .isNamedJane(); // should fail
        }
        // or let it find whatever required
        {
            // tries to find a class named `UserAssert`
            // in the same package of `User` class
            final Object actual = new User("Jane", 0); // java.lang.Object
            assertThatVirtualBean(actual)              // mind the name of the method
                    .isValid()
                    .hasValidProperty("name")
                    .hasValidProperty("age")
                    .isNamedJane();
        }
    }
}
```

### Verifying values against properties

You can verify a value against specific property of specific bean type.

```java
class Test {

    @Test
    void test() {
        assertThatProperty("John").isValidFor(User.class, "name"); // should pass
        assertThatProperty(null).isValidFor(User.class, "name");   // should fail; @NotBlank
        assertThatProperty(" ").isValidFor(User.class, "name");    // should fail; @NotBlank
        assertThatProperty(31).isValidFor(User.class, "age");      // should pass
        assertThatProperty(-1).isValidFor(User.class, "age");      // should fail; @Min(0x00)
        assertThatProperty(297).isValidFor(User.class, "age");     // should fail; @Max(0x80)
    }
}
```

Note that a bean value can also be validated against a property of another bean.

```java
class Test {

    @Test
    void test() {
        assertThatBean(null).isValidFor(Registration.class, "user"); // should fail; @NotNull
    }
}
```

Note also that the `@Valid` is not honored by `Validator#validateProperty` method nor `Validator#validateValue` method.
See [6.1.1. Validation methods] (Jakarta Bean Validation specification).

```java
class Test {

    @Test
    void test() {
        User user = new User("John", 300);                           // not valid, obviously
        assertThatBean(user).isValid();                              // so does fail
        assertThatBean(user).isValidFor(Registration.class, "user"); // DOES NOT FAIL!
        assertThatBean(null).isValidFor(Registration.class, "user"); // while fails
        assertThatBean(new Registration(user)).isValid();            // fails, after ...
    }
}
```

### Using a `Validator` and/or targeting groups

You can configure a `Validator` or targeting groups for validating.

```java
assertThatBean(user)
        .usingValidator(...)       // null to reset
        .targetingGroups(...,...) // null or empty to reset
        .isValid();
```

[6.1.1. Validation methods]: https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#validationapi-validatorapi-validationmethods