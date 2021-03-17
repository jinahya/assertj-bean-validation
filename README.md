# assertj-bean-validation

An [AssertJ](https://joel-costigliola.github.io/assertj/) extension for Bean-Validation.


## Usages.

```java
final Object bean = bean();
assertBean(bean).isValid();

final Validator validator = validator();
assertBean(bean).isValid(validator);
assertBean(bean).usingValidator(validator).isValid();

final Class<?>[] groups = groups();
assertBean(bean).isValid(groups);
assertBean(bean).usingGroups(groups).isValid();

assertBean(bean).isValid(validator, groups);
```
