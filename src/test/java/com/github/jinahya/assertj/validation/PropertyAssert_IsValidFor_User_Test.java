package com.github.jinahya.assertj.validation;

import com.github.jinahya.assertj.validation.example.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ElementKind;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatConstraintViolation;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatIterableOfConstraintViolations;
import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatProperty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class PropertyAssert_IsValidFor_User_Test {

    @Test
    void __validAge() {
        final var age = User.validAge();
        final var assertion = assertThatProperty(age);
        assertThatCode(
                () -> assertion.isValidFor(
                        User.class,
                        "age",
                        s -> {
                            assertThat(s).isEmpty();
                        }
                )
        )
                .doesNotThrowAnyException();
    }

    @Test
    void __invalidAge() {
        final var age = User.invalidAge();
        final var assertion = assertThatProperty(age);
        assertThatThrownBy(
                () -> assertion.isValidFor(
                        User.class,
                        "age"
                        ,
                        s -> {
//                            assertThat(s)
//                                    .extracting(ConstraintViolation::getLeafBean)
//                                    .singleElement()
//                                    .isNull();
//                            assertThat(s)
//                                    .extracting(ConstraintViolation::getRootBeanClass)
//                                    .singleElement()
//                                    .isEqualTo(User.class);
//                            assertThat(s)
//                                    .extracting(ConstraintViolation::getRootBeanClass)
//                                    .containsOnly(User.class);
//                            assertThat(s)
//                                    .extracting(ConstraintViolation::getRootBean)
//                                    .singleElement()
//                                    .isNull();
//                            assertThat(s)
//                                    .extracting(ConstraintViolation::getRootBean)
//                                    .containsOnlyNulls();
//                            assertThat(s)
//                                    .extracting(ConstraintViolation::getInvalidValue)
//                                    .singleElement()
//                                    .isEqualTo(age);
//                            assertThat(s)
//                                    .extracting(ConstraintViolation::getInvalidValue)
//                                    .containsOnly(age);
//                            assertThat(s)
//                                    .extracting(ConstraintViolation::getRootBeanClass)
//                                    .singleElement()
//                                    .isEqualTo(User.class);
//                            assertThat(s)
//                                    .extracting(ConstraintViolation::getRootBeanClass)
//                                    .containsOnly(User.class);
//                            assertThatIterableOfConstraintViolations(s)
//                                    .singleElement()
//                                    .hasRootBeanClass(User.class)
//                                    .hasRootBean(null)
//                                    .hasInvalidValue(age);
                            s.forEach(cv -> {
//                                assertThatConstraintViolation(cv)
//                                        .hasLeafBean(null)
//                                        .hasRootBeanClass(User.class)
//                                        .hasRootBean(null)
//                                        .hasInvalidValue(age);
                                assertThatConstraintViolation(cv)
                                        .extractingPropertyPath()
                                        .singleElement()
                                        .hasIndex(null) // TODO: Check why fails....!!!!
                                        .hasKey(null)
                                        .hasKind(ElementKind.PROPERTY)
                                        .hasName("age")
                                        .isNotInIterable();
                            });
                        }
                )
        )
                .isInstanceOf(AssertionError.class)
                .satisfies(ae -> {
                    log.debug("message: {}", ae.getMessage());
                });
    }

    @Test
    void __validName() {
        final var name = User.validName();
        final var assertion = assertThatProperty(name);
        assertThatCode(
                () -> assertion.isValidFor(
                        User.class,
                        "name",
                        s -> {
                            assertThat(s).isEmpty();
                        })
        )
                .doesNotThrowAnyException();
    }

    @Test
    void __invalidName() {
        final var name = User.invalidName();
        final var assertion = assertThatProperty(name);
        assertThatThrownBy(
                () -> assertion.isValidFor(
                        User.class,
                        "name",
                        s -> {
                            assertThat(s)
                                    .extracting(ConstraintViolation::getLeafBean)
                                    .singleElement()
                                    .isNull();
                            assertThat(s)
                                    .extracting(ConstraintViolation::getRootBeanClass)
                                    .singleElement()
                                    .isEqualTo(User.class);
                            assertThat(s)
                                    .extracting(ConstraintViolation::getRootBeanClass)
                                    .containsOnly(User.class);
                            assertThat(s)
                                    .extracting(ConstraintViolation::getRootBean)
                                    .singleElement()
                                    .isNull();
                            assertThat(s)
                                    .extracting(ConstraintViolation::getRootBean)
                                    .containsOnlyNulls();
                            assertThat(s)
                                    .extracting(ConstraintViolation::getInvalidValue)
                                    .singleElement()
                                    .isEqualTo(name);
                            assertThat(s)
                                    .extracting(ConstraintViolation::getInvalidValue)
                                    .containsOnly(name);
                            assertThat(s)
                                    .extracting(ConstraintViolation::getRootBeanClass)
                                    .singleElement()
                                    .isEqualTo(User.class);
                            assertThat(s)
                                    .extracting(ConstraintViolation::getRootBeanClass)
                                    .containsOnly(User.class);
                            assertThatIterableOfConstraintViolations(s)
                                    .singleElement()
                                    .hasLeafBean(null)
                                    .hasRootBeanClass(User.class)
                                    .hasRootBean(null)
                                    .hasInvalidValue(name);
                        }
                )
        )
                .isInstanceOf(AssertionError.class)
                .satisfies(ae -> {
                    log.debug("message: {}", ae.getMessage());
                });
    }
}
