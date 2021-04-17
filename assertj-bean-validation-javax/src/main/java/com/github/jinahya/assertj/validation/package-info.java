/**
 * Defines <a href="https://assertj.github.io/doc/">AssertJ</a> classes for fluently verifying objects and values
 * against <a href="https://beanvalidation.org/">Jakarta Bean-Validation</a>.
 * <blockquote><pre>
 * class User {
 *
 *     {@literal @}NotBlank String name = "UNKNOWN";
 *
 *     {@literal @}PositiveOrZero int age;
 *
 * }
 *
 *
 * assertBean(new User()).isValid();
 *
 * assertThat(bean(new User())).isValid();
 *
 *
 * assertBean(new User()).hasValidProperty("name");
 *
 * assertThat(bean(new User())).hasValidProperty("age");
 *
 *
 * assertBeanProperty(null).isValidFor(User.class, "name");         // fail
 *
 * assertBeanProperty("").isValidFor(User.class, "name");           // fail
 *
 * assertBeanProperty(" ").isValidFor(User.class, "name");          // fail
 *
 * assertThat(beanProperty("Jane")).isValidFor(User.class, "name"); // succeed
 *
 * assertThat(beanProperty(-1)).isValidFor(User.class, "age"); // fail
 *
 * assertBeanProperty(0).isValidFor(User.class, "age");        // succeed
 *
 * assertBeanProperty(1).isValidFor(User.class, "age");        // succeed
 * </pre></blockquote>
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
package com.github.jinahya.assertj.validation;
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
