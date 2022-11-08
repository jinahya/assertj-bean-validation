package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation-javax
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

import javax.validation.Path;

/**
 * An interface for verifying {@link Path} values.
 *
 * @param <SELF>   self type parameter
 * @param <ACTUAL> actual type parameter
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public interface PathAssert<SELF extends PathAssert<SELF, ACTUAL>, ACTUAL extends Path>
        extends ValidationAssert<SELF, ACTUAL> {

    /**
     * An interface for verifying {@link Path.Node} values.
     *
     * @param <SELF>   self type parameter
     * @param <ACTUAL> actual type parameter
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    interface NodeAssert<SELF extends NodeAssert<SELF, ACTUAL>, ACTUAL extends Path.Node>
            extends ValidationAssert<SELF, ACTUAL> {

        SELF hasIndex(Integer expectedIndex);

        SELF hasKey(Object expectedIndex);

//        SELF hasElementKind(ElementKind expectedElementKind);

        SELF hasName(String expectedName);
    }
}
