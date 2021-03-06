package com.github.jinahya.assertj.validation.user;

/*-
 * #%L
 * assertj-bean-validation-generic
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

    @DisplayName("assertBean(withInvalidAge).isNotValid(consumer) accepts non-empty constraint violations")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfInvalidAge.class)
    void isNotValid_NonEmptyConstraintViolations_InvalidAge(final User withInvalidAge) {
        // given, when
        final BeanAssert beanAssert = assertThat(bean(withInvalidAge));
        // then
        beanAssert.isNotValid(s -> {
            assertThat(s).hasSize(1).element(0, as(CONSTRAINT_VIOLATION))
                    .hasInvalidValueEqualTo(withInvalidAge.getAge())
                    .hasLeafBeanSameAs(withInvalidAge)
                    .hasMessageSatisfying(m -> {
                        log.debug("message: {}", m);
                        assertThat(m).isNotBlank();
                    })
                    .hasPropertyPathSatisfying(p -> {
                        log.debug("propertyPath: {}", p);
                        assertThat(path(p)).isNotNull();
                        assertThat(path(p)).asIterable().hasSize(1);
                        {
                            assertThat(path(p)).node(0).index().isEqualTo(null);
                            assertThat(path(p)).node(0).hasIndexEqualTo(null);
                        }
                        {
                            assertThat(path(p)).node(0).key().isEqualTo(null);
                            assertThat(path(p)).node(0).hasKeyEqualTo(null);
                        }
                        {
                            assertThat(path(p)).node(0).hasKindNameEqualTo("PROPERTY");
                        }
                        {
                            assertThat(path(p)).node(0).name().isEqualTo("age");
                            assertThat(path(p)).node(0).hasNameEqualTo("age");
                        }
                        {
                            assertThat(path(p)).node(0).inIterable().isFalse();
                            assertThat(path(p)).node(0).isNotInIterable();
                        }
                        assertThat(path(p)).node(0).asPropertyNode().hasContainerClassSameAs(null);
                        assertThat(path(p)).propertyNode(0).hasContainerClassSameAs(null);
                        assertThat(path(p)).node(0).asPropertyNode().hasTypeArgumentIndexEqualTo(null);
                        assertThat(path(p)).propertyNode(0).hasTypeArgumentIndexEqualTo(null);
                    })
                    .hasRootBeanSameAs(withInvalidAge)
                    .hasRootBeanClassSameAs(User.class)
            ;
            assertThat(s).element(0, as(CONSTRAINT_VIOLATION)).invalidValue().isEqualTo(withInvalidAge.getAge());
            assertThat(s).element(0, as(CONSTRAINT_VIOLATION)).hasInvalidValueEqualTo(withInvalidAge.getAge());
            assertThat(s).element(0, as(CONSTRAINT_VIOLATION)).leafBean().isSameAs(withInvalidAge);
            assertThat(s).element(0, as(CONSTRAINT_VIOLATION)).hasLeafBeanSameAs(withInvalidAge);
//            assertThat(s).element(0, as(CONSTRAINT_VIOLATION)).message();
//            assertThat(s).element(0, as(CONSTRAINT_VIOLATION)).hasMessageEqualTo();
//            assertThat(s).element(0, as(CONSTRAINT_VIOLATION)).propertyPath();
//            assertThat(s).element(0, as(CONSTRAINT_VIOLATION)).propertyPath().isEqualTo();
            assertThat(s).element(0, as(CONSTRAINT_VIOLATION)).hasRootBeanSameAs(withInvalidAge);
            assertThat(s).element(0, as(CONSTRAINT_VIOLATION)).rootBeanClass().isSameAs(User.class);
            assertThat(s).element(0, as(CONSTRAINT_VIOLATION)).hasRootBeanClassSameAs(User.class);
        });
    }

    @DisplayName("assertBean(withInvalidName).isNotValid(consumer) accepts non-empty constraint violations")
    @ParameterizedTest
    @ArgumentsSource(UserBeanArgumentsProviders.OfInvalidName.class)
    void isNotValid_NonEmptyConstraintViolations_InvalidName(final User withInvalidName) {
        // given, when
        final BeanAssert beanAssert = assertThat(bean(withInvalidName));
        // then
        beanAssert.isNotValid(s -> {
            assertThat(s).hasSize(1).element(0, as(CONSTRAINT_VIOLATION))
                    .hasInvalidValueEqualTo(withInvalidName.getName())
                    .hasLeafBeanSameAs(withInvalidName)
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
                    .hasRootBeanSameAs(withInvalidName)
                    .hasRootBeanClassSameAs(User.class)
            ;
        });
    }
}
