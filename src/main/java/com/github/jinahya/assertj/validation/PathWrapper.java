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

public final class PathWrapper<PathType extends Iterable<NodeType>, NodeType> extends Wrapper<PathType> {

    abstract static class AbstractNodeWrapper extends Wrapper {

        protected AbstractNodeWrapper(final Object wrapped) {
            super(wrapped);
        }
    }

    public static class NodeWrapper extends AbstractNodeWrapper {

        public static NodeWrapper node(final Object wrapped) {
            return new NodeWrapper(wrapped);
        }

        private NodeWrapper(final Object wrapped) {
            super(wrapped);
        }
    }

    public static class BeanNodeWrapper extends AbstractNodeWrapper {

        public static BeanNodeWrapper beanNode(final Object wrapped) {
            return new BeanNodeWrapper(wrapped);
        }

        private BeanNodeWrapper(final Object wrapped) {
            super(wrapped);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    public static <PathType extends Iterable<NodeType>, NodeType> PathWrapper<PathType, NodeType> path(final PathType wrapped) {
        return new PathWrapper<>(wrapped);
    }

    private PathWrapper(final PathType wrapped) {
        super(wrapped);
    }
}
