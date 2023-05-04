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

class SeniorRegistration
        extends Registration {

    static SeniorRegistration of(final User user) {
        return of(SeniorRegistration::new, user);
    }

    static SeniorRegistration seniorRegistrationOf(final User user) {
        return of(user);
    }

    SeniorRegistration(@Senior final User user) {
        super(user);
    }

    private SeniorRegistration() {
        this(null);
    }

    @Senior
    @Override
    User getUser() {
        return super.getUser();
    }
}
