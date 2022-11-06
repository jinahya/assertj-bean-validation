# assertj-bean-validation

[![CI](https://github.com/jinahya/assertj-bean-validation/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/assertj-bean-validation/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_assertj-bean-validation&metric=alert_status)](https://sonarcloud.io/dashboard?id=jinahya_assertj-bean-validation)
[![Known Vulnerabilities](https://snyk.io/test/github/jinahya/assertj-bean-validation/badge.svg)](https://snyk.io/test/github/jinahya/assertj-bean-validation)

[![Maven Central](https://img.shields.io/maven-central/v/com.github.jinahya/assertj-bean-validation?label=maven-central)](https://search.maven.org/artifact/com.github.jinahya/assertj-bean-validation)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/assertj-bean-validation-javax/javadoc.svg?label=javadoc.io)](https://javadoc.io/doc/com.github.jinahya/assertj-bean-validation)

An [AssertJ](https://joel-costigliola.github.io/assertj/) extension for [Bean-Validation](https://beanvalidation.org/).

<img src="https://raw.githubusercontent.com/joel-costigliola/assertj/gh-pages/favicon.png" height="100"/> + 
<img src="https://beanvalidation.org/logo/logo.svg" height="100" alt="BeanValidation"/>

## Coordinates

```xml

<groupId>com.github.jinahya</groupId>
<artifactId>assertj-bean-validation</artifactId>
```

### Classifiers

classifier           | `release` | `javax.*` | `jakarta.*` |notes
---------------------|-----------|-----------|-------------|-----
NONE                 | 8         | ✓         |             |
`jakarta`            | 8         |           | ✓           |
`release-11`         | 11        | ✓         |             |
`release-11-jakarta` | 11        |           | ✓           |
`release-17`         | 17        | ✓         |             |
`release-17-jakarta` | 17        |           | ✓           |

## Compatibilities

* Depends, in `provided` scope, on
  the [latest org.assertj:assertj-core](https://javadoc.io/doc/org.assertj/assertj-core/latest/index.html).
* Targets **Java 8**.

### JDK

This module requires the **latest JDK**(currently, 19), especially with its test sources, for building itself.

## Usages

Say, we have the following beans to verify.

```java
class User {
    @NotBlank String name;
    @Max(0x7F) @PositiveOrZero int age;
}

abstract class Registration {
    @Valid @NotNull User user;
}

class SeniorRegistration extends Registration {
    @Senior User getUser() { return super.getUser(); }
}
```

### Verifying beans and properties

Verifies that actual bean objects and/or their properties are valid.

```java
class Test {
    @Test void test1() {
        assertThatBean(new User("Jane", 28))  // valid
                .isValid()                    // should pass
                .hasValidProperty("name")     // should pass
                .hasValidProperty("age");     // should pass
    }
}
```

### Verifying a value against specific property

You can verify a value against specific property of specific bean type.

```java
class Test {
    @Test void test2() {
        assertThatProperty("John").isValidFor(User.class, "name"); // should pass
        assertThatProperty(  null).isValidFor(User.class, "name"); // should fail; @NotBlank
        assertThatProperty(    "").isValidFor(User.class, "name"); // should fail; @NotBlank
        assertThatProperty(   " ").isValidFor(User.class, "name"); // should fail; @NotBlank
        assertThatProperty(    31).isValidFor(User.class,  "age"); // should pass
        assertThatProperty(    -1).isValidFor(User.class,  "age"); // should fail; @PositiveOrZero
        assertThatProperty(   297).isValidFor(User.class,  "age"); // should fail; @Max(0x7F)
    }
}
```

Note that a bean value can also be validated against a property of another type.

```java
class Test {
    @Test void test3() {
      assertThatBean(new User("Jane", 21))
              .isValidFor(Registration.class, "user");             // should pass
      assertThatBean(null).isValidFor(Registration.class, "user"); // should fail; @NotNull
      assertThatBean(new User("John", 59))
              .isValidFor(SeniorRegistration.class, "user");       // should fail; age should be >= 60
    }
}
```

Note also that the `@Valid` annotation is not honored by `Validator#validateProperty` method nor `Validator#validateValue` method.
See [6.1.1. Validation methods] (Jakarta Bean Validation specification).

```java
class Test {
    @Test void test4() {
        User user = new User("John", 300);                           // not valid, obviously
        assertThatBean(user).isValid();                              // so does fail
        assertThatBean(user).isValidFor(Registration.class, "user"); // DOES NOT FAIL!
        assertThatBean(null).isValidFor(Registration.class, "user"); // while fails
        assertThatBean(new SeniorRegistration(user)).isValid();      // should fail
    }
}
```

### Using a specific `Validator`

```java
assertThatBean(user)
        .usingValidator(...) // null to reset
        .isValid();
```

### Using validation groups

```java
assertThatBean(user)
        .targetingGroups(..., ...) // null or empty to reset
        .isValid();
```

### Consuming constraint violations

```java
assertThatBean(user)
        .consumingViolations(cv -> {
            // ... debug
        })
        .isValid();
```

[6.1.1. Validation methods]: https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#validationapi-validatorapi-validationmethods
