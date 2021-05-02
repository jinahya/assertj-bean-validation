package com.github.jinahya.assertj.validation.user;

/*-
 * #%L
 * assertj-bean-validation-javax
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

import com.github.jinahya.assertj.validation.javax.BeanAssert;
import com.github.jinahya.assertj.validation.javax.BeanAssertArgumentConverter;
import com.github.jinahya.assertj.validation.javax.BeanAssertions;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static com.github.jinahya.assertj.validation.BeanWrapper.bean;
import static com.github.jinahya.assertj.validation.javax.InstanceOfValidationAssertFactories.CONSTRAINT_VIOLATION;
import static com.github.jinahya.assertj.validation.javax.PathAssertions.assertThat;
import static com.github.jinahya.assertj.validation.javax.PathWrapper.path;
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
    void isNotValid_Fail_Valid(@ConvertWith(BeanAssertArgumentConverter.class) final BeanAssert<?> a) {
        assertThatThrownBy(a::isNotValid)
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertBean(invalid).isNotValid() succeeds")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfInvalid.class)
    void isNotValid_Succeed_Invalid(@ConvertWith(BeanAssertArgumentConverter.class) final BeanAssert<?> a) {
        // then
        assertThatCode(a::isNotValid)
                .doesNotThrowAnyException();
    }

    // -------------------------------------------------------------------------------------------- isNotValid(Consumer)
    @DisplayName("assertBean(invalid).isNotValid(consumer) accepts non-empty constraint violations")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfInvalid.class)
    void isNotValid_NonEmptyConstraintViolations_Invalid(
            @ConvertWith(BeanAssertArgumentConverter.class) final BeanAssert<?> a) {
        a.isNotValid(s -> {
            assertThat(s).hasSizeBetween(1, 2); // age, name, or both
        });
    }

    @DisplayName("assertBean(invalid.age).isNotValid(consumer) accepts non-empty constraint violations")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfInvalidAges.class)
    void isNotValid_NonEmptyConstraintViolations_InvalidAge(final User user) {
        // given, when
        final BeanAssert<?> beanAssert = BeanAssertions.assertThat(bean(user));
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
        final BeanAssert<?> beanAssert = BeanAssertions.assertThat(bean(user));
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
