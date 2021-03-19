# assertj-bean-validation

An [AssertJ](https://joel-costigliola.github.io/assertj/) extension for Bean-Validation.


## Usages.

```java
final Object bean = bean();
assertBean(bean).isValid();

final Validator validator = validator();
assertBean(bean).isValidWith(validator);
assertBean(bean).withValidator(validator).isValid();

final Class<?>[] groups = groups();
assertBean(bean).isValidFor(groups);
assertBean(bean).forGroups(groups).isValid();

assertBean(bean).isValid(validator, groups);
```
