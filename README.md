# assertj-bean-validation

[![CI](https://github.com/jinahya/assertj-bean-validation/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/assertj-bean-validation/actions/workflows/maven.yml)
[![javax](https://github.com/jinahya/assertj-bean-validation/actions/workflows/javax.yml/badge.svg)](https://github.com/jinahya/assertj-bean-validation/actions/workflows/javax.yml)
[![jakarta](https://github.com/jinahya/assertj-bean-validation/actions/workflows/jakarta.yml/badge.svg)](https://github.com/jinahya/assertj-bean-validation/actions/workflows/jakarta.yml)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_assertj-bean-validation&metric=alert_status)](https://sonarcloud.io/dashboard?id=jinahya_assertj-bean-validation)
[![Known Vulnerabilities](https://snyk.io/test/github/jinahya/assertj-bean-validation/badge.svg)](https://snyk.io/test/github/jinahya/assertj-bean-validation)

[![Maven Central](https://img.shields.io/maven-central/v/com.github.jinahya/assertj-bean-validation)](https://search.maven.org/artifact/com.github.jinahya/assertj-bean-validation)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/assertj-bean-validation/javadoc.svg)](https://javadoc.io/doc/com.github.jinahya/assertj-bean-validation)

An [AssertJ](https://joel-costigliola.github.io/assertj/) extension for [Bean-Validation](https://beanvalidation.org/).

## Compatibility

There are, due to the [Transition from Java EE to Jakarta EE](https://blogs.oracle.com/javamagazine/transition-from-java-ee-to-jakarta-ee), currently two packages for Bean-Validation. One is `javax.validation.*` and the other is `jakarta.validation.*`. This project, hence, defines three artifacts applicable for both/each environment(s).

|artifact                         |`jakrta.*`|`javax.*`|notes|
|---------------------------------|----------|---------|-----|
|`assertj-bean-validation-generic`|&check;   |&check;  |     |
|`assertj-bean-validation-jakrta` |&check;   |&cross;  |[`jakarta.valiation:jakarta.validation-api:[3.0.0,)`][jakarta.validation:jakarta.validation-api]|
|`assertj-bean-validation-javax`  |&cross;   |&check;  |[`javax.validation:validation-api`][javax.validation:validation-api], `jakrta.validation:jakarta.validation-api:(,2.0.2]`|

## Usages

### Verifying a bean and/or its properties.

#### `isValid()`

Verifies that the `actual` bean is valid.

```java
class User {
    @NotBlank String name = "UNKNOWN";
    @PositiveOrZero int age;
}

User user = new User();

assertBean(user).isValid();
assertThat(bean(user)).isValid(); // equivalent
```

#### `isNotValid()`, `isNotValid(Consumer)`

Verifies that the `actual` bean is not valid and, optionally, accepts a non-empty set of constraint violations to specified consumer.

```java
User user = new User();

user.setName(null);

assertThat(bean(user)).isNotValid(); // succeeds

asserBean(user).isNotValid(s -> {
    assertThat(s).isNotEmpty().doesNotContainNull().hasSize(1);
    assertThat(s).allSatisfy(v -> {
        assertThat(constraintViolation(v))
            .hasInvalidValueEqualTo(user.getName())
            .hasLeafBeanSameAs(user)
            .hasMessageEqualTo("must not be blank")
            .hasPropertyPathSatisfying(p -> {
                assertThat(path(p)).doesNotContainNull().hasSize(1);
                assertThat(path(p)).allSatisfy(n -> {
                     assertThat(node(n))
                          .hasIndexEqualTo(null)
                          .hasKeyEqualTo(null)
                          .hasKindSameAs(ElementKind.PROPERTY)
                          .hasNameEqualTo("name")
                          .isNotInIterable();
                });
             })
            .hasRootBeanClassSameAs(User.class)
            .hasRootBeanSameAs(user);
    });
});
```

#### `hasValidProperty(String)`

Verifies that the current value of the property of specified name is valid.

```java
User user = new User();

assertBean(user).hasValidProprty("name");
assertThat(bean(user)).hasValidProperty("age");
```

#### `doesNotHaveValidProperty(String)`, `doesNotHaveValidProperty(String, Consumer)`

Verifies that the current value of the property of specified name is not valid.

```java
assertBean(user).doesNotHaveValidProperty("age");       // fails

user.setAge(-1);

assertThat(bean(user)).doesNotHaveValidProperty("age"); // succeeds
```

### Verifying a value for a property of a bean type

#### `isValidFor(Class<?>, String)`

Verifies that the `actual` value would be valid for a property of a bean type.

```java
assertThat(beanProperty("Jane")).isValidFor(User.class, "name"); // succeeds
assertBeanProperty(null).isValidFor(User.class, "name");         // fails

assertThat(beanProperty(0)).isValidFor(User.class, "age"); // succeeds
assertBeanProperty(-1).isValidFor(User.class, "age");      // fails
```

#### `isValidFor(Class<?>, String)`, `isNotValidFor(Class<?>, String, Consumer)`

Verifies that the `actual` value is not valid for a property of specified name of specified class and, optionally, accepts a non-empty set of `ConstraintViolation`s to specified consumer. 

```java
assertBeanProperty("John").isNotValidFor(User.class, "name");            // fails
        
String invalidName = getInvalidName();
assertThat(beanProperty(invalidName)).isNotValidFor(User.class, "name"); // succeeds
assertBeanProperty(invalidName).isNotValidFor(User.class, "name", s -> { // succeeds
});

assertBeanProperty(1).isNotValidFor(User.class, "age");                 // fails
        
int invalidAge = getInvalidAge();
assertThat(beanProperty(invalidAge)).isNotValidFor(User.class, "name"); // succeeds
assertBeanProperty(invalidAge).isNotValidFor(User.class, "name", s -> { // succeeds
    assertThat(s).isNotNull().doesNotContainNull().isNotNull();
    // ...
});
```

### Using custom validators and/or targeting groups

```java
Validator validator = validator();
Class<?>[] groups = groups();
assert...(...)
        .using(validator)  // using a custom validator
        .targeting(groups) // using custom groups
        .(is|has)...(...);
```

[validate]: https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validate-T-java.lang.Class...-

[validateProperty]: https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validateProperty-T-java.lang.String-java.lang.Class...-

[validateValue]: https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validateValue-java.lang.Class-java.lang.String-java.lang.Object-java.lang.Class...-

[jakarta.validation:jakarta.validation-api]: https://search.maven.org/artifact/jakarta.validation/jakarta.validation-api
[javax.validation:validation-api]: https://search.maven.org/artifact/javax.validation/validation-api

