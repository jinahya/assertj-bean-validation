package com.github.jinahya.assertj.validation.user;

import com.github.jinahya.assertj.validation.BeanAssert;
import com.github.jinahya.assertj.validation.BeanAssertArgumentConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.github.jinahya.assertj.validation.BeanAssertions.assertThat;
import static com.github.jinahya.assertj.validation.BeanWrapper.bean;
import static com.github.jinahya.assertj.validation.InstanceOfValidationAssertFactories.CONSTRAINT_VIOLATION;
import static com.github.jinahya.assertj.validation.PathAssertions.assertThat;
import static com.github.jinahya.assertj.validation.PathWrapper.path;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class BeanAssert_IsNotValid_User_Test {

    // ---------------------------------------------------------------------------------------------------- isNotValid()
    @DisplayName("assertBean(valid).isNotValid() fails")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfValid.class)
    void isNotValid_Fail_Valid(@ConvertWith(BeanAssertArgumentConverter.class) final BeanAssert a) {
        assertThatThrownBy(a::isNotValid)
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertBean(invalid).isNotValid() succeeds")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfInvalid.class)
    void isNotValid_Succeed_Invalid(@ConvertWith(BeanAssertArgumentConverter.class) final BeanAssert a) {
        // then
        assertThatCode(a::isNotValid)
                .doesNotThrowAnyException();
    }

    // -------------------------------------------------------------------------------------------- isNotValid(Consumer)
    @DisplayName("assertBean(invalid).isNotValid(consumer) accepts non-empty constraint violations")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfInvalid.class)
    void isNotValid_NonEmptyConstraintViolations_Invalid(
            @ConvertWith(BeanAssertArgumentConverter.class) final BeanAssert a) {
        a.isNotValid(s -> {
            assertThat(s).hasSizeBetween(1, 2); // age, name, or both
        });
    }

    @DisplayName("assertBean(invalid.age).isNotValid(consumer) accepts non-empty constraint violations")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfInvalidAges.class)
    void isNotValid_NonEmptyConstraintViolations_InvalidAge(final User user) {
        // given, when
        final BeanAssert beanAssert = assertThat(bean(user));
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
                        assertThat(path(p)).isNotNull();
                        assertThat(path(p)).asIterable().hasSize(1);
                        assertThat(path(p)).node(0).hasIndexEqualTo(null);
                        assertThat(path(p)).node(0).hasKeyEqualTo(null);
                        assertThat(path(p)).node(0).hasNameEqualTo("age");
                        assertThat(path(p)).propertyNode(0).hasContainerClassSameAs(null);
                        assertThat(path(p)).propertyNode(0).hasTypeArgumentIndexEqualTo(null);
                    })
                    .hasRootBeanSameAs(user)
                    .hasRootBeanClassSameAs(User.class)
            ;
        });
    }

    @DisplayName("assertBean(invalid.name).isNotValid(consumer) accepts non-empty constraint violations")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfInvalidNames.class)
    void isNotValid_NonEmptyConstraintViolations_InvalidName(final User user) {
        // given, when
        final BeanAssert beanAssert = assertThat(bean(user));
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