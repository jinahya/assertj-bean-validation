# assertj-bean-validation

An [AssertJ](https://joel-costigliola.github.io/assertj/) extension for [Bean-Validation](https://beanvalidation.org/).

## Usages

### `isValid()`

Validate a bean object using [`validate(T, Class<?>...)`][validate] method.

```java
class User {

    @NotBlank String name = "UNKNOWN";

    @PositiveOrZero int age;
}

assertBean(new User()).isValid();
        assretThat(bean(new User())).isValid(); // equivalent
```

### `hasValidProperty(String)`

Validates current value of a property of specified name
using [`validateProperty(T, String, Class<?>...)`][validateProperty] method.

```java
assertBean(new User()).hasValidProprty("name");
        assertThat(bean(new User())).hasValidProperty("age");
```

### `isValidFor(Class<?>, String, Class<?>...)`

Checks whether a value is valid for a property using [`validateValue(T, String, Object, Class<?>...)`][validateValue]
method.

```java
assertBeanProperty(null).isValidFor(User.class, "name");         // fail
assertBeanProperty("").isValidFor(User.class, "name");           // fail
assertBeanProperty(" ").isValidFor(User.class, "name");          // fail
assertThat(beanProperty("Jane")).isValidFor(User.class, "name"); // succeed

assertThat(beanProperty(-1)).isValidFor(User.class, "age"); // fail
assertBeanProperty(0).isValidFor(User.class, "age");        // succeed
assertBeanProperty(1).isValidFor(User.class, "age");        // succeed
```

[validate]: https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validate-T-java.lang.Class...-

[validateProperty]: https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validateProperty-T-java.lang.String-java.lang.Class...-

[validateValue]: https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validateValue-java.lang.Class-java.lang.String-java.lang.Object-java.lang.Class...-

