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

/**
 * A abstract class for wrapping actual values of {@code Path}.
 *
 * @param <ACTUAL> the type of {@code Path}.
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractPathWrapper<ACTUAL>
        extends AbstractWrapper<ACTUAL> {

    abstract static class NodeBaseWrapper<ACTUAL>
            extends AbstractWrapper<ACTUAL> {

        NodeBaseWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    /**
     * An abstract class for wrapping actual values of {@code Node}.
     *
     * @param <ACTUAL> actual type parameter
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public abstract static class AbstractNodeWrapper<ACTUAL>
            extends NodeBaseWrapper<ACTUAL> {

        /**
         * Creates a new instance wrapping specified actual value.
         *
         * @param actual the actual value to wrap.
         */
        protected AbstractNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    /**
     * An abstract class for wrapping actual values of {@code BeanNode}.
     *
     * @param <ACTUAL> actual type parameter
     * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
     */
    public abstract static class AbstractBeanNodeWrapper<ACTUAL>
            extends NodeBaseWrapper<ACTUAL> {

        /**
         * Creates a new instance wrapping specified actual value.
         *
         * @param actual the actual value to wrap.
         */
        protected AbstractBeanNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public abstract static class AbstractConstructorNodeWrapper<ACTUAL>
            extends NodeBaseWrapper<ACTUAL> {

        protected AbstractConstructorNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public abstract static class AbstractContainerElementNodeWrapper<ACTUAL>
            extends NodeBaseWrapper<ACTUAL> {

        protected AbstractContainerElementNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public abstract static class AbstractCrossParameterNodeWrapper<ACTUAL>
            extends NodeBaseWrapper<ACTUAL> {

        protected AbstractCrossParameterNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public abstract static class AbstractMethodNodeWrapper<ACTUAL>
            extends NodeBaseWrapper<ACTUAL> {

        protected AbstractMethodNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public abstract static class AbstractParameterNodeWrapper<ACTUAL>
            extends NodeBaseWrapper<ACTUAL> {

        protected AbstractParameterNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public abstract static class AbstractPropertyNodeWrapper<ACTUAL>
            extends NodeBaseWrapper<ACTUAL> {

        protected AbstractPropertyNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    public abstract static class AbstractReturnValueNodeWrapper<ACTUAL>
            extends NodeBaseWrapper<ACTUAL> {

        protected AbstractReturnValueNodeWrapper(final ACTUAL actual) {
            super(actual);
        }
    }

    /**
     * Creates a new instance with specified actual value.
     *
     * @param actual the actual value to verify.
     */
    protected AbstractPathWrapper(final ACTUAL actual) {
        super(actual);
    }
}
