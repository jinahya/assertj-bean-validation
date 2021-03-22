# assertj-bean-validation

An [AssertJ](https://joel-costigliola.github.io/assertj/) extension for Bean-Validation.


## Usages.

```java
final Object object = bean();
assertBean(object).isValid();
assertThat(bean(object)).isValid();

final Validator validator = validator();
assertBean(object).isValidWith(validator);
assertThat(bean(object)).withValidator(validator).isValid();

final Class<?>[] groups = groups();
assertThat(bean(object)).isValidFor(groups);
assertBean(object).forGroups(groups).isValid();

assertThat(bean(object)).isValid(validator, groups);
```
