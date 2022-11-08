package com.github.jinahya.assertj.validation.example.user;

/*-
 * #%L
 * assertj-bean-validation
 * %%
 * Copyright (C) 2021 - 2022 Jinahya, Inc.
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

import com.github.jinahya.assertj.validation.ConstraintViolationAssert;
import lombok.extern.slf4j.Slf4j;

/**
 * A class for testing {@link ConstraintViolationAssert#hasLeafBean(Object)} method.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@Slf4j
class ConstraintViolationAssert_HasLeafBean_Test {

//    @Test
//    void __NameIsInvalid() {
//        final var bean = User.newInstance(false, true);
//        assertThatThrownBy(
//                () -> assertThatBean(bean).isValid(i -> {
//                    assertThat(i).singleElement().satisfies(cv -> {
//                        assertThatConstraintViolation(cv).hasLeafBean(bean);
//                        assertThatThrownBy(() -> assertThatConstraintViolation(cv).hasLeafBean(new User("", 0)))
//                                .isInstanceOf(AssertionError.class)
//                                .satisfies(ae -> {
//                                    log.debug("message: {}", ae.getMessage());
//                                });
//                    });
//                })
//        )
//                .isInstanceOf(AssertionError.class);
//    }
//
//    @Test
//    void __AgeIsInvalid() {
//        final var bean = User.newInstance(true, false);
//        assertThatThrownBy(
//                () -> assertThatBean(bean).isValid(i -> {
//                    assertThat(i).singleElement().satisfies(cv -> {
//                        assertThatConstraintViolation(cv).hasLeafBean(bean);
//                    });
//                })
//        )
//                .isInstanceOf(AssertionError.class);
//    }
//
//    @Test
//    void __BothAreInvalid() {
//        final var bean = User.newInstance(false, false);
//        assertThatThrownBy(
//                () -> assertThatBean(bean).isValid(i -> {
//                    assertThat(i).hasSize(2).allSatisfy(
//                            cv -> {
//                                assertThatConstraintViolation(cv)
//                                        .hasLeafBean(bean);
//                            }
//                    );
//                })
//        )
//                .isInstanceOf(AssertionError.class);
//    }
}
