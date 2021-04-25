package com.github.jinahya.assertj.validation.javax;

import com.github.jinahya.assertj.validation.user.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.github.jinahya.assertj.validation.BeanWrapper.bean;
import static com.github.jinahya.assertj.validation.javax.BeanAssertions.assertThat;
import static com.github.jinahya.assertj.validation.javax.InstanceOfValidationAssertFactories.CONSTRAINT_VIOLATION;
import static com.github.jinahya.assertj.validation.javax.PathAssertions.assertPath;
import static com.github.jinahya.assertj.validation.javax.PathAssertions.assertThat;
import static com.github.jinahya.assertj.validation.javax.PathWrapper.path;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class BeanAssert_IsNotValid_User_Test {

    private static Stream<User> validUsers() {
        return IntStream.range(0, 16).mapToObj(i -> User.newValidInstance());
    }

    private static Stream<User> invalidUsers() {
        return IntStream.range(0, 1).mapToObj(i -> User.newInvalidInstance());
    }

    private static Stream<User> usersWithInvalidAge() {
        return IntStream.range(0, 16).mapToObj(i -> User.newInstanceWithInvalidAge());
    }

    private static Stream<User> usersWithInvalidName() {
        return IntStream.range(0, 16).mapToObj(i -> User.newInstanceWithInvalidName());
    }

    @DisplayName("assertBean(valid).isNotValid() throws an AssertionError")
    @ParameterizedTest
    @MethodSource({"validUsers"})
    void isNotValid_Fail_Valid(final User user) {
        final BeanAssert<User> beanAssert = assertThat(bean(user));
        assertThatThrownBy(beanAssert::isNotValid)
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertBean(invalid).isNotValid() does not throw any exception")
    @ParameterizedTest
    @MethodSource({"invalidUsers"})
    void isNotValid_Succeed_Invalid(final User user) {
        final BeanAssert<User> beanAssert = assertThat(bean(user));
        assertThatCode(beanAssert::isNotValid)
                .doesNotThrowAnyException();
    }

    @DisplayName("assertBean(invalid).isNotValid(consumer) accepts non-empty constraint violations")
    @ParameterizedTest
    @MethodSource({"usersWithInvalidAge"})
    void isNotValid_NonEmptyConstraintViolations_InvalidAge(final User user) {
        // given, when
        final BeanAssert<User> beanAssert = BeanAssertions.assertThat(bean(user));
        // then
        beanAssert.isNotValid(s -> {
            assertThat(s).hasSize(1).element(0, as(CONSTRAINT_VIOLATION))
                    .hasInvalidValueEqualTo(user.getAge())
                    .hasLeafBeanSameAs(user)
                    .hasMessageSatisfying(m -> {
                        assertThat(m).isNotBlank();
                        log.debug("message: {}", m);
                    })
                    .hasPropertyPathSatisfying(p -> {
                        assertThat(p).isNotNull();
                        assertThat(p).asIterable().hasSize(1);
                        assertThat(p).node(0).hasIndexEqualTo(null);
                        assertThat(p).node(0).hasKeyEqualTo(null);
                        assertThat(p).node(0).hasNameEqualTo("age");
                        assertThat(p).propertyNode(0).hasContainerClassSameAs(null);
                        assertThat(p).propertyNode(0).hasTypeArgumentIndexEqualTo(null);
                    })
                    .hasRootBeanSameAs(user)
                    .hasRootBeanClassSameAs(User.class)
            ;
        });
    }

    @DisplayName("assertBean(invalid).isNotValid(consumer) accepts non-empty constraint violations")
    @ParameterizedTest
    @MethodSource({"usersWithInvalidName"})
    void isNotValid_NonEmptyConstraintViolations_InvalidName(final User user) {
        // given, when
        final BeanAssert<User> beanAssert = assertThat(bean(user));
        // then
        beanAssert.isNotValid(s -> {
            assertThat(s).hasSize(1).element(0, as(CONSTRAINT_VIOLATION))
                    .hasInvalidValueEqualTo(user.getName())
                    .hasLeafBeanSameAs(user)
                    .hasMessageSatisfying(m -> {
                        assertThat(m).isNotBlank();
                        log.debug("message: {}", m);
                    })
                    .hasPropertyPathSatisfying(p -> {
                        assertThat(path(p)).isNotNull();
                        assertThat(path(p)).asIterable().hasSize(1);
//                        PathAssertions.assertThat(path(p)).asIterable().element(0).satisfies(v -> {
//                            log.debug("index: {}", PathUtils.NodeUtils.getIndex(v));
//                            log.debug("key: {}", PathUtils.NodeUtils.getKey(v));
//                            log.debug("kind: {}", PathUtils.NodeUtils.getKey(v));
//                            log.debug("name: {}", PathUtils.NodeUtils.getName(v));
//                            log.debug("inIterable: {}", PathUtils.NodeUtils.isInIterable(v));
//                            log.debug("containerClass: {}", PathUtils.PropertyNodeUtils.getContainerClass(v));
//                            log.debug("typeArgumentIndex: {}", PathUtils.PropertyNodeUtils.getTypeArgumentIndex(v));
//                        });
                        assertThat(path(p)).node(0).hasIndexEqualTo(null);
                        assertThat(path(p)).node(0).hasKeyEqualTo(null);
                        assertThat(path(p)).node(0).hasNameEqualTo("name");
                        assertThat(path(p)).propertyNode(0).hasContainerClassSameAs(null);
                        assertThat(path(p)).propertyNode(0).hasTypeArgumentIndexEqualTo(null);
                    })
                    .hasRootBeanSameAs(user)
                    .hasRootBeanClassSameAs(User.class)
            ;
        });
    }
}