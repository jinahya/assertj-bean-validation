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

## Idioms

### for `actual` values of `java.lang.Object`

```java
Object b = bean();
BeanAssert a;

a = BeanAssertions.assertBean(b);
a = BeanAssertions.assertThat(BeanWrapper.bean(b));

// with static imports
a = assertBean(b);
a = assertThat(bean(b));

a.isValid();
```
```java
Object v = value();
ValueAssert a;
        
a = ValueAssertions.assertValue(v);
a = ValueAssertions.assertThat(ValueWrapper.value(v));

// with static imports
a = assertValue(b);
a = assertThat(value(b));

a.isValidFor(SomeBean.class, "someProperty");
```

### for `actual` values of `(jakarta|javax).validation.<Type>`

For given `<Type>` defined within Bean-Validation API, you can create assertions using either one of following idioms.

#### `<Type>Assertions.assert<Type>(actual)`

```java
<Type> actual;
<Type>Assert a;
a = <Type>Assertions.assert<Type>(actual);

ConstraintViolation<?> actual;
ConstrintViolationAssertions.assertConstraintViolation(actual);

// with static imports
assertConstraintViolation(actual).hasInvalidValueEqualTo(expected);

// see next heading for
// assertThat(constraintViolation(actual))
```

#### `<Type>Assertions.assertThat(<Type>Wrapper.<type>(actual))`

```java
<Type> actual;
<Type>Assert a;
a = <Type>Assertions.assertThat(<Type>Wrapper.<type>(actual));

// e.g.
Path actual;
PathAssert a = PathAssertions.assertThat(PathWrapper.path(actual));

// with static imports
assertThat(path(actual)).node(0).hasIndexEqualTo(null);

// see previous heading for
// assertPath(actual)
```

#### `<Type>Assertions.assertThat(<Type> actual)` (only for `-jakarta` and `-javax`)

With `assertj-bean-validation-jakarta` or `assertj-bean-validation-javax`, you can create assertions just like AssertJ.

```java
ConstructorNode actual;
ConstructorNodeAssert a = ConstructorNodeAssertion.asserThat(actual);

// of course, idioms previouls exaplained are also available
assertConstructorNode(actual);        
assertThat(constructorNode(actual));
```

## APIs

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
    assertThat(s).hasSize(1).element(0, as(CONSTRAINT_VIOLATION))
        .hasInvalidValueEqualTo(user.getAge())
        .hasLeafBeanSameAs(user)
        .hasMessageSatisfying(m -> {
            assertThat(m).isNotBlank();
        })
        .hasPropertyPathSatisfying(p -> {
            assertPath(p).isNotNull();
            assertPath(p).asIterable().hasSize(1);
            assertPath(p).node(0).hasIndexEqualTo(null);
            assertPath(p).node(0).hasKeyEqualTo(null);
            assertPath(p).node(0).hasNameEqualTo("age");
            assertPath(p).propertyNode(0).hasContainerClassSameAs(null);
            assertPath(p).propertyNode(0).hasTypeArgumentIndexEqualTo(null);
        })
        .hasRootBeanSameAs(user)
        .hasRootBeanClassSameAs(User.class)
        ;
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

