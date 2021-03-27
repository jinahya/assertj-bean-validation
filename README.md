# assertj-bean-validation

[![CI](https://github.com/jinahya/assertj-bean-validation/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/assertj-bean-validation/actions/workflows/maven.yml)
[![javax](https://github.com/jinahya/assertj-bean-validation/actions/workflows/javax.yml/badge.svg)](https://github.com/jinahya/assertj-bean-validation/actions/workflows/javax.yml)
[![jakarta](https://github.com/jinahya/assertj-bean-validation/actions/workflows/jakarta.yml/badge.svg)](https://github.com/jinahya/assertj-bean-validation/actions/workflows/jakarta.yml)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_assertj-bean-validation&metric=alert_status)](https://sonarcloud.io/dashboard?id=jinahya_assertj-bean-validation)
[![Known Vulnerabilities](https://snyk.io/test/github/jinahya/assertj-bean-validation/badge.svg)](https://snyk.io/test/github/jinahya/assertj-bean-validation)

[![Maven Central](https://img.shields.io/maven-central/v/com.github.jinahya/assertj-bean-validation)](https://search.maven.org/artifact/com.github.jinahya/assertj-bean-validation)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/assertj-bean-validation/javadoc.svg)](https://javadoc.io/doc/com.github.jinahya/assertj-bean-validation)

An [AssertJ](https://joel-costigliola.github.io/assertj/) extension for [Bean-Validation](https://beanvalidation.org/).

This module works for both `javax.validation.*` and `jakarta.validation.*` without directly (nor transitively) depending on any of these APIs.

## Usages

### Verifying a bean and/or its properties.

#### `isValid()`

Validates a bean object reference. (See [`validate`][validate].)

```java
class User {
    @NotBlank String name = "UNKNOWN";
    @PositiveOrZero int age;
}

assertBean(new User()).isValid();
assertThat(bean(new User())).isValid(); // equivalent
```

#### `hasValidProperty(String)`

Validates current value of a property of specified name. (See [`validateProperty`][validateProperty].)

```java
assertBean(new User()).hasValidProprty("name");
assertThat(bean(new User())).hasValidProperty("age"); // equivalent
```

### Verifying a value for a specific property of a bean type

#### `isValidFor(Class<?>, String)`

Checks whether a value would be valid for a property of a bean type. (See [`validateValue`][validateValue].)

```java
assertBeanProperty(null).isValidFor(User.class, "name");         // fail
assertBeanProperty("").isValidFor(User.class, "name");           // fail
assertBeanProperty(" ").isValidFor(User.class, "name");          // fail
assertThat(beanProperty("Jane")).isValidFor(User.class, "name"); // succeed

assertThat(beanProperty(-1)).isValidFor(User.class, "age"); // fail
assertBeanProperty(0).isValidFor(User.class, "age");        // succeed
assertBeanProperty(1).isValidFor(User.class, "age");        // succeed
```

### Using custom validators and/or targeting groups

```java
Validator validator = validator();
Class<?>[] groups = groups();
assert...(...)
        .using(validator)  // using a custom validator
        .targeting(groups) // using custom groups
        .(is|has)Valid...(...);
```

[validate]: https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validate-T-java.lang.Class...-

[validateProperty]: https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validateProperty-T-java.lang.String-java.lang.Class...-

[validateValue]: https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validateValue-java.lang.Class-java.lang.String-java.lang.Object-java.lang.Class...-

