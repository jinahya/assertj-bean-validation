# assertj-bean-validation

An [AssertJ](https://joel-costigliola.github.io/assertj/) extension for Bean-Validation.


## Usages.

### Creating an assertion instance.

```java
Object object = bean();

BeanValidationAssert a1 = assertBean(object);
BeanValidationAssert a2 = assertThat(bean(object)); // equivalent

BeanValidationAssert a3 = assertBean(Vehicle.class, new Car());
BeanValidationAssert a4 = assertThat(bean(Vehicle.class, new Trunk())); // equivalent
```

### Using custom validators and/or groups.

```java
Validator validator = validator();
a.using(validator)....();

Class<?>[] groups = groups();
a.targeting(groups)....();

a.using(validator).targeting(groups)....();
```

### `isValid()` for `validate(T, Class<?>...)`

```java
class User {
    @NotBlank String name = "UNKNOWN";
    @PositiveOrZero int age;
}

assertBean(new User()).isValid();
```

### `hasValidProperty(String)`

Validates current value of a property of specified name. See [`validateProperty(T, String, Class<?>...)`][validateProperty].

```java
assertBean(new User()).hasValidProprty("name");
assertThat(bean(new User())).hasValidProperty("age");
```

### `wouldBeValidWithProperty(String, Object)`

Checks whether a value is valid for a property. See [`validateValue(T, String, Object, Class<?>...)`][validateValue].

```java
assertBeanType(User.class).isValidValueFor("name", "Jane");  // succeed
assertBean(User.class, null).isValidValueFor("name", null);  // fail
assertThat(beanType(User.class)).isValidProperty("age", 36); // succeed
assertThat(bean(new User())).isValidProperty("age", -1);     // fail
```

[validateProperty]: https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validateProperty-T-java.lang.String-java.lang.Class...-
[validateValue]: https://javadoc.io/static/jakarta.validation/jakarta.validation-api/3.0.0/jakarta/validation/Validator.html#validateValue-java.lang.Class-java.lang.String-java.lang.Object-java.lang.Class...-

