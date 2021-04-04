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
import com.github.jinahya.assertj.validation.PathTestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanValidationAssertions.assertBean;
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
class User_DoesNotHaveValidProperty_Test {

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
                                    assertThat(path(p)).doesNotContainNull().hasSize(1).allSatisfy(n -> {
                                        log.debug("node: {}({})", n, n.getClass());
                                        log.debug("propertyNode.containerClass: {}", PathTestUtils.PropertyNodeTestUtils.getContainerClass(n));
                                        log.debug("propertyNode.typeArgumentIndex: {}", PathTestUtils.PropertyNodeTestUtils.getTypeArgumentIndex(n));
                                        assertThat(node(n))
                                                .hasIndexEqualTo(null)
                                                .hasKeyEqualTo(null)
                                                .hasKindSatisfying(k -> {
                                                    assertThat(elementKind(k)).hasNameEqualTo("PROPERTY");
                                                })
                                                .hasNameEqualTo("name")
                                                .isNotInIterable()
                                                .isPropertyNode()
                                                .asPropertyNode()
                                                .hasContainerClassSameAs(null)
                                                .hasTypeArgumentIndexEqualTo(null)
                                        ;
                                    });
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
