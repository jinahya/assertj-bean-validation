package com.github.jinahya.assertj.validation.user;

/*-
 * #%L
 * assertj-bean-validation
 * %%
 * Copyright (C) 2021 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.github.jinahya.assertj.validation.BeanValidationAssert;
import com.github.jinahya.assertj.validation.ConstraintViolationTestUtils;
import com.github.jinahya.assertj.validation.PathTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanValidationAssertions.assertBean;
import static com.github.jinahya.assertj.validation.BeanValidationAssertions.assertThat;
import static com.github.jinahya.assertj.validation.BeanValidationTestUtils.validator;
import static com.github.jinahya.assertj.validation.BeanWrapper.bean;
import static com.github.jinahya.assertj.validation.ConstraintViolationAssertions.assertThat;
import static com.github.jinahya.assertj.validation.ConstraintViolationWrapper.constraintViolation;
import static com.github.jinahya.assertj.validation.ElementKindAssertions.assertThat;
import static com.github.jinahya.assertj.validation.ElementKindWrapper.elementKind;
import static com.github.jinahya.assertj.validation.PathAssertions.NodeAssertions.assertThat;
import static com.github.jinahya.assertj.validation.PathAssertions.assertThat;
import static com.github.jinahya.assertj.validation.PathWrapper.NodeWrapper.node;
import static com.github.jinahya.assertj.validation.PathWrapper.path;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class UserTest {

    static Stream<String> validNames() {
        return Stream.of(Long.toString(System.nanoTime()));
    }

    static Stream<String> invalidNames() {
        return Stream.of(null, "", " ");
    }

    static IntStream validAges() {
        return IntStream.range(0, 8)
                .map(i -> current().nextInt() >>> 1);
    }

    static IntStream invalidAges() {
        return IntStream.range(0, 8)
                .map(i -> current().nextInt() | Integer.MIN_VALUE);
    }

    static Stream<User> validInstances() {
        return IntStream.rangeClosed(0, current().nextInt(16))
                .mapToObj(i -> User.newValidInstance());
    }

    static Stream<User> instancesWithInvalidName() {
        return IntStream.rangeClosed(0, current().nextInt(16))
                .mapToObj(i -> User.newInstanceWithInvalidName());
    }

    static Stream<User> instancesWithInvalidAge() {
        return IntStream.rangeClosed(0, current().nextInt(16))
                .mapToObj(i -> User.newInstanceWithInvalidAge());
    }

    static Stream<User> invalidInstances() {
        return IntStream.rangeClosed(0, current().nextInt(16))
                .mapToObj(i -> User.newInvalidInstance());
    }

    // --------------------------------------------------------------------------------------------------------- isValid
    @MethodSource({"validInstances"})
    @ParameterizedTest
    void isValid_Succeed_Valid(final User user) {
        assertBean(user).isValid();
        assertThat(bean(user)).isValid();
        assertBean(user).using(validator()).isValid();
        assertThat(bean(user)).using(validator()).isValid();
    }

    @MethodSource({"invalidInstances"})
    @ParameterizedTest
    void isValid_Fail_Invalid(final User user) {
        final BeanValidationAssert a = assertBean(user);
        assertThatThrownBy(a::isValid)
                .isInstanceOf(AssertionError.class);
        assertThat(a.using(validator())).isNotNull();
        assertThatThrownBy(a::isValid)
                .isInstanceOf(AssertionError.class);
    }

    // ------------------------------------------------------------------------------------------------------ isNotValid
    @MethodSource({"validInstances"})
    @ParameterizedTest
    void isInvalid_Fail_Valid(final User user) {
        final BeanValidationAssert a = assertThat(bean(user));
        assertThatThrownBy(() -> a.isNotValid(null)).isInstanceOf(AssertionError.class);
        assertThatThrownBy(
                () -> a.isNotValid(s -> {
                }))
                .isInstanceOf(AssertionError.class);
    }

    @MethodSource({"invalidInstances"})
    @ParameterizedTest
    void isInvalid_Succeed_Invalid(final User user) {
        final BeanValidationAssert<User> a = assertBean(user);
        assertThatCode(() -> a.isNotValid(null)).doesNotThrowAnyException();
        assertThatCode(
                () -> a.isNotValid(s -> {
                    assertThat(s).isNotEmpty();
                    s.forEach(v -> {
                        assertThat(constraintViolation(v))
                                .hasLeafBeanSameAs(user)
                                .hasRootBeanClassSameAs(User.class)
                                .hasRootBeanSameAs(user)
                        ;
//                        assertThat(path(getPropertyPath(v))).satisfies(i -> {
//                            i.forEach(n -> {
//                                log.debug("node: {}({})", n, n.getClass());
//                                assertThat(n).isNotNull();
//                            });
//                        });
                    });
                }))
                .doesNotThrowAnyException();
    }

    @MethodSource({"instancesWithInvalidName"})
    @ParameterizedTest
    void isNotValid_Succeed_InvalidName(final User user) {
        Assertions.setMaxStackTraceElementsDisplayed(1024);
        final BeanValidationAssert<User> a = assertBean(user);
        assertThatCode(() -> a.isNotValid(s -> {
            assertThat(s).isNotEmpty().doesNotContainNull().hasSize(1).allSatisfy(v -> {
                assertThat(constraintViolation(v))
                        .hasInvalidValueEqualTo(user.getName())
                        .hasLeafBeanSameAs(user)
                        .hasMessageEqualTo("must not be blank")
                        .hasPropertyPathSatisfying(p -> {
                            assertThat(path(p)).doesNotContainNull().hasSize(1).allSatisfy(n -> {
                                log.debug("node: {}({})", n, n.getClass());
                                log.debug("node.index: {}", PathTestUtils.NodeTestUtils.getIndex(n));
                                log.debug("node.key: {}", PathTestUtils.NodeTestUtils.getKey(n));
                                log.debug("node.kind: {}", PathTestUtils.NodeTestUtils.getKind(n));
                                log.debug("node.name: {}", PathTestUtils.NodeTestUtils.getName(n));
                                log.debug("node.iterable: {}", PathTestUtils.NodeTestUtils.isInIterable(n));
                                assertThat(node(n))
                                        .hasIndexEqualTo(null)
                                        .hasKeyEqualTo(null)
                                        .hasKindSatisfying(k -> {
                                            assertThat(elementKind(k)).hasNameEqualTo("PROPERTY");
                                        })
                                        .hasNameEqualTo("name")
                                        .isNotInIterable()
                                ;
                            });
                        })
                        .hasRootBeanClassSameAs(User.class)
                        .hasRootBeanSameAs(user)
                ;
            });
        }))
                .doesNotThrowAnyException();
    }

    @MethodSource({"instancesWithInvalidAge"})
    @ParameterizedTest
    void isNotValid_Succeed_InvalidAge(final User user) {
        final BeanValidationAssert<User> a = assertBean(user);
        assertThatCode(
                () -> a.isNotValid(s -> {
                    assertThat(s).isNotEmpty().doesNotContainNull().hasSize(1).allSatisfy(v -> {
                        log.debug("violation: {}", v);
                        log.debug("violation.message: {}", ConstraintViolationTestUtils.getMessage(v));
                        assertThat(constraintViolation(v))
                                .hasInvalidValueEqualTo(user.getAge())
                                .hasLeafBeanSameAs(user)
                                .hasMessageEqualTo("must be greater than or equal to 0")
                                .hasPropertyPathSatisfying(p -> {
                                    assertThat(path(p)).doesNotContainNull().hasSize(1).allSatisfy(n -> {
                                        log.debug("node: {}({})", n, n.getClass());
                                        log.debug("node.index: {}", PathTestUtils.NodeTestUtils.getIndex(n));
                                        log.debug("node.key: {}", PathTestUtils.NodeTestUtils.getKey(n));
                                        log.debug("node.kind: {}", PathTestUtils.NodeTestUtils.getKind(n));
                                        log.debug("node.name: {}", PathTestUtils.NodeTestUtils.getName(n));
                                        log.debug("node.iterable: {}", PathTestUtils.NodeTestUtils.isInIterable(n));
                                        assertThat(node(n))
                                                .hasIndexEqualTo(null)
                                                .hasKeyEqualTo(null)
                                                .hasKindSatisfying(k -> {
                                                    assertThat(elementKind(k)).hasNameEqualTo("PROPERTY");
                                                })
                                                .hasNameEqualTo("age")
                                                .isNotInIterable()
                                        ;
                                    });
                                })
                                .hasRootBeanClassSameAs(User.class)
                                .hasRootBeanSameAs(user)
                        ;
                    });
                }))
                .doesNotThrowAnyException();
    }

    // ------------------------------------------------------------------------------------------------ hasValidProperty
    @MethodSource({"validInstances"})
    @ParameterizedTest
    void hasValidProperty_Succeed_Valid(final User user) {
        assertBean(user).hasValidProperty("name");
        assertBean(user).hasValidProperty("age");
        assertThat(bean(user)).hasValidProperty("name");
        assertThat(bean(user)).hasValidProperty("age");
    }

    @MethodSource({"instancesWithInvalidName"})
    @ParameterizedTest
    void hasValidProperty_Fail_InvalidName(final User userWithInvalidName) {
        final BeanValidationAssert<User> a = assertBean(userWithInvalidName);
        assertThatThrownBy(() -> a.hasValidProperty("name")).isInstanceOf(AssertionError.class);
        a.hasValidProperty("age");
    }

    @MethodSource({"instancesWithInvalidAge"})
    @ParameterizedTest
    void hasValidProperty_Fail_InvalidAge(final User userWithInvalidAge) {
        final BeanValidationAssert<User> a = assertBean(userWithInvalidAge);
        assertThatThrownBy(() -> a.hasValidProperty("age")).isInstanceOf(AssertionError.class);
        a.hasValidProperty("name");
    }

    // ---------------------------------------------------------------------------------------- doesNotHaveValidProperty
    @MethodSource({"validInstances"})
    @ParameterizedTest
    void doesNotHaveValidProperty_Fail_Valid(final User user) {
        final BeanValidationAssert<User> a = assertBean(user);
        assertThatThrownBy(
                () -> {
                    a.doesNotHaveValidProperty("name", null);
                })
                .isInstanceOf(AssertionError.class);
        assertThatThrownBy(
                () -> {
                    a.doesNotHaveValidProperty("age", null);
                })
                .isInstanceOf(AssertionError.class);
    }

    @MethodSource({"instancesWithInvalidName"})
    @ParameterizedTest
    void doesNotHaveValidProperty_Succeed_InvalidName(final User user) {
        final BeanValidationAssert<User> a = assertBean(user);
        assertThatCode(
                () -> a.doesNotHaveValidProperty("name", s -> {
                    assertThat(s).isNotEmpty().doesNotContainNull().hasSize(1).allSatisfy(v -> {
                        assertThat(constraintViolation(v))
                                .hasInvalidValueEqualTo(user.getName())
                                .hasLeafBeanSameAs(user)
                                .hasMessageEqualTo("must not be blank")
                                .hasPropertyPathSatisfying(p -> {
//                                    assertThat(path(p)).doesNotContainNull().hasSize(1).allSatisfy(n -> {
//                                        assertThat(node(n))
//                                                .hasIndex(null)
//                                                .hasKey(null)
//                                                .hasKindSatisfying(k -> {
//                                                    assertThat(elementKind(k)).hasName("PROPERTY");
//                                                })
//                                                .hasName("name")
//                                                .isNotInIterable()
//                                        ;
//                                    });
                                })
                                .hasRootBeanSameAs(user)
                                .hasRootBeanClassSameAs(User.class);
                    });
                }))
                .doesNotThrowAnyException();
    }

    @MethodSource({"instancesWithInvalidAge"})
    @ParameterizedTest
    void doesNotHaveValidProperty_Fail_InvalidAge(final User user) {
        final BeanValidationAssert<User> a = assertBean(user);
        assertThatCode(() -> a.doesNotHaveValidProperty("age"))
                .doesNotThrowAnyException();
        assertThatCode(
                () -> a.doesNotHaveValidProperty("age", s -> {
                    assertThat(s).isNotNull().doesNotContainNull().hasSize(1).allSatisfy(v -> {
                        assertThat(constraintViolation(v))
                                .hasInvalidValueEqualTo(user.getAge())
                                .hasLeafBeanSameAs(user)
                                .hasMessageEqualTo("must be greater than or equal to 0")
                                .hasPropertyPathSatisfying(p -> {
                                    assertThat(path(p)).doesNotContainNull().hasSize(1).allSatisfy(n -> {
                                        assertThat(node(n))
                                                .hasIndexEqualTo(null)
                                                .hasKeyEqualTo(null)
                                                .hasKindSatisfying(k -> {
                                                    assertThat(elementKind(k)).hasNameEqualTo("PROPERTY");
                                                })
                                                .hasNameEqualTo("age")
                                                .isNotInIterable()
                                        ;
                                    });
                                })
                                .hasRootBeanSameAs(user)
                                .hasRootBeanClassSameAs(User.class);
                    });
                }))
                .doesNotThrowAnyException();
    }
}
