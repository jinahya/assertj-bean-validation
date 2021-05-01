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

There are, due to the [Transition from Java EE to Jakarta EE](https://blogs.oracle.com/javamagazine/transition-from-java-ee-to-jakarta-ee), currently two packages for Bean-Validation. One is `javax.validation.*` and the other is `jakarta.validation.*`. This project, hence, defines three artifacts which each is applicable for both/each environment(s).

|artifact                         |`jakrta.*`|`javax.*`|notes|
|---------------------------------|----------|---------|-----|
|`assertj-bean-validation-generic`|&check;   |&check;  |     |
|`assertj-bean-validation-jakrta` |&check;   |&cross;  |     |
|`assertj-bean-validation-javax`  |&cross;   |&check;  |     |

### assertj-bean-validation-generic

Works for both `jakarta.validation:jakarta.validation-api` and `javax.validation:validation-api` using reflections.

### assertj-bean-validation-jakarta

Works for `jakarta.validation:jakarta.validation-api:[3.0.0,)`.

### assertj-bean-validation-javax

Works for `javax.validation:validation-api` and `jakarta.validation:jakarta.validation-api:(,2.0.2]`

## Idioms

### for an `actual` value of `java.lang.Object`

Special idioms are provided for each assertion to avoid confusion with AssertJ's `assertThat` idiom.

```java
Object b = getBean();
BeanAssert a;

a = BeanAssertions.assertThat(b);
a = BeanAssertions.assertThat(BeanWrapper.bean(b));
a = BeanAssertions.assertBean(b);

// with static imports
a = assertThat(b); // mind the import static ...BeanAssertions.assertThat;
a = assertThat(bean(b));
a = assertBean(b);

assertThat(a).isValid();
assertThat(bean(a)).isNotValid();
assertBean(a).isNotValid(s -> {});
```
```java
Object v = getValue();
ValueAssert a;

a = ValueAssertions.assertThat(v);
a = ValueAssertions.assertThat(ValueWrapper.value(v));
a = ValueAssertions.assertValue(v);

// with static imports
a = assertThat(v); // mind the import static ...ValueAssertions.assertThat;
a = assertThat(value(v));
a = assertValue(v);

assertThat(v).isValidFor(SomeBean.class, "someProperty");
assertThat(value(v)).isNotValidFor(SomeBean.class, "someProeprty");
assertValue(v).isNotValidFor(SomeBean.class, "someProperty", s -> {});
```

### for an `actual` value of `(jakarta|javax).validation.<Type>`

For an actual of `<Type>` defined within Bean-Validation API, you can create assertions using either one of following idioms.

#### `<Type>Assertions.assertThat((Object|<Type>) actual)`

Same as AssertJ does.

#### `<Type>Assertions.assert<Type>(Object actual)`

```java
<Type> actual;
<Type>Assert a;
a = <Type>Assertions.assert<Type>(actual);

// e.g.
ConstraintViolation<?> actual;
ConstrintViolationAssertions.assertConstraintViolation(actual);

// with static imports
assertConstraintViolation(actual).hasInvalidValueEqualTo(expected);
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
```

#### `<Type>Assertions.assertThat(<Type> actual)` (only for `-jakarta` and `-javax`)

With `assertj-bean-validation-jakarta` or `assertj-bean-validation-javax`, you can create assertions just like AssertJ.

```java
ConstructorNode actual;
ConstructorNodeAssert a = ConstructorNodeAssertion.asserThat(actual);

assertThat(actual).hasParameterTypeEqualTo(expected);
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

assertThat(bean(user)).isNotValid(); // fails

user.setName(null);

assertThat(bean(user)).isNotValid(); // succeeds

asserBean(user).isNotValid(s -> {    // succeeds
    assertThat(s).hasSize(1).element(0, as(CONSTRAINT_VIOLATION))
        .hasInvalidValueEqualTo(user.getAge())
        .hasLeafBeanSameAs(user)
        .hasMessageSatisfying(m -> {
            assertThat(m).isNotBlank(); // "must not be blank"
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

Verifies that the current value of a property of the actual bean is valid.

```java
User user = new User();

assertBean(user).hasValidProprty("name");       // succeeds
assertThat(bean(user)).hasValidProperty("age"); // succeeds
```

#### `doesNotHaveValidProperty(String)`, `doesNotHaveValidProperty(String, Consumer)`

Verifies that the current value of a property of the actual bean is not valid.

```java
assertBean(user).doesNotHaveValidProperty("age");       // fails

user.setAge(-1);

assertThat(bean(user)).doesNotHaveValidProperty("age"); // succeeds
```

### Verifying a value against a property of a bean type

#### `isValidFor(Class<?>, String)`

Verifies that the `actual` value would be valid for a property of a bean type.

```java
assertThat(value("Jane")).isValidFor(User.class, "name"); // succeeds
assertValue(null).isValidFor(User.class, "name");         // fails

assertThat(vaue(0)).isValidFor(User.class, "age");        // succeeds
assertValue(-1).isValidFor(User.class, "age");            // fails
```

#### `isValidFor(Class<?>, String)`, `isNotValidFor(Class<?>, String, Consumer)`

Verifies that the `actual` value is not valid for a property specified class and, optionally, accepts a non-empty set of `ConstraintViolation`s to specified consumer. 

```java
assertValue("John").isNotValidFor(User.class, "name");            // fails
        
String invalidName = getInvalidName();
assertThat(value(invalidName)).isNotValidFor(User.class, "name"); // succeeds
assertValue(invalidName).isNotValidFor(User.class, "name", s -> { // succeeds
});

assertValue(1).isNotValidFor(User.class, "age");                  // fails
        
int invalidAge = getInvalidAge();
assertThat(value(invalidAge)).isNotValidFor(User.class, "name");  // succeeds
assertValue(invalidAge).isNotValidFor(User.class, "name", s -> {  // succeeds
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

