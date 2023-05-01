# assertj-bean-validation

[![CI](https://github.com/jinahya/assertj-bean-validation/actions/workflows/maven.yml/badge.svg)](https://github.com/jinahya/assertj-bean-validation/actions/workflows/maven.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=jinahya_assertj-bean-validation&metric=alert_status)](https://sonarcloud.io/dashboard?id=jinahya_assertj-bean-validation)
[![Known Vulnerabilities](https://snyk.io/test/github/jinahya/assertj-bean-validation/badge.svg)](https://snyk.io/test/github/jinahya/assertj-bean-validation)

[![Maven Central](https://img.shields.io/maven-central/v/com.github.jinahya/assertj-bean-validation?label=maven-central)](https://search.maven.org/artifact/com.github.jinahya/assertj-bean-validation)
[![javadoc](https://javadoc.io/badge2/com.github.jinahya/assertj-bean-validation/javadoc.svg)](https://javadoc.io/doc/com.github.jinahya/assertj-bean-validation)

An [AssertJ](https://assertj.github.io/doc/) extension for [Bean-Validation](https://beanvalidation.org/).

<img src="https://raw.githubusercontent.com/joel-costigliola/assertj/gh-pages/favicon.png" height="100"/> + 
<img src="https://beanvalidation.org/logo/logo.svg" height="100" alt="BeanValidation"/>

## Coordinates

See [Maven Central](https://search.maven.org/artifact/com.github.jinahya/assertj-bean-validation) for the latest
version.

```xml

<dependency>
  <groupId>com.github.jinahya</groupId>
  <artifactId>assertj-bean-validation</artifactId>
</dependency>
```

### Classifiers

 classifier           | `release` | `javax.*` | `jakarta.*` | notes 
----------------------|:---------:|:---------:|:-----------:|-------
 NONE                 |     8     |     ✓     |             |
 `jakarta`            |     8     |           |      ✓      |
 `release-11`         |    11     |     ✓     |             |
 `release-11-jakarta` |    11     |           |      ✓      |
 `release-17`         |    17     |     ✓     |             |
 `release-17-jakarta` |    17     |           |      ✓      |

## Compatibilities

This module has the [latest org.assertj:assertj-core](https://javadoc.io/doc/org.assertj/assertj-core/latest/index.html)
as its *provided*-scoped dependency.

### JDK

The main sources's target/release is `8` yet requires the **latest JDK**, especially with its test sources, for building
itself.

## Usages

See
the [package-info](https://javadoc.io/doc/com.github.jinahya/assertj-bean-validation/latest/com/github/jinahya/assertj/validation/package-summary.html).

[6.1.1. Validation methods]: https://jakarta.ee/specifications/bean-validation/3.0/jakarta-bean-validation-spec-3.0.html#validationapi-validatorapi-validationmethods
