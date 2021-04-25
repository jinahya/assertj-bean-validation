package com.github.jinahya.assertj.validation.jakarta;

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

import com.github.jinahya.assertj.validation.AbstractWrapper;

import javax.validation.GroupDefinitionException;

/**
 * A class for wrapping actual values of {@link GroupDefinitionException}.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
public class GroupDefinitionExceptionWrapper
        extends ExtendedValidationExceptionWrapper<GroupDefinitionException> {

    /**
     * Creates a new instance wraps specified actual value.
     *
     * @param actual the actual value to wrap.
     * @return a new instance wraps {@code actual}.
     * @see GroupDefinitionExceptionAssertions#assertThat(AbstractWrapper)
     */
    public static GroupDefinitionExceptionWrapper groupDefinitionException(final GroupDefinitionException actual) {
        return new GroupDefinitionExceptionWrapper(actual);
    }

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to wrap.
     */
    private GroupDefinitionExceptionWrapper(final GroupDefinitionException actual) {
        super(actual);
    }
}
