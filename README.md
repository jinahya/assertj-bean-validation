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

## Binary Compatibilities

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
```

### Verifying Bean Objects

Verifies that the actual bean object is valid.

```java
class UserTest {

    @Test
    void test() {
        ValidationAssertions.assertBean(new User("Jane", 28))
                .isValid()
                .hasValidProperty("name")
                .hasValidProperty("age");
    }
}
```

Debug with constraint violations.

```java
class UserTest {

    @Test
    void test() {
        Assertions.assertThatThrownBy(() -> {
                    ValidationAssertions.assertBean(new User("", 27))
                            .isValid(cv -> {
                                log.debug("cv: {}", cv);
                            });
                })
                .isInstanceOf(AssertionError.class);
        Assertions.assertThatThrownBy(() -> {
                    ValidationAssertions.assertBean(new User("John", 300))
                            .hasValidProperty("age", cv -> {
                                log.debug("cv: {}", cv);
                            });
                })
                .isInstanceOf(AssertionError.class);
    }
}
```

### Verifying values for properties of bean types.