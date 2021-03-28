package com.github.jinahya.assertj.validation;

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
