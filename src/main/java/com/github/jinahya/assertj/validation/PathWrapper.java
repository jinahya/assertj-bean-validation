package com.github.jinahya.assertj.validation;

public final class PathWrapper extends Wrapper<Iterable<Object>> {

    public static class NodeWrapper extends Wrapper<Object> {

        public static NodeWrapper node(final Object wrapped) {
            return new NodeWrapper(wrapped);
        }

        private NodeWrapper(final Object wrapped) {
            super(wrapped);
        }
    }

    public static PathWrapper path(final Iterable<Object> wrapped) {
        return new PathWrapper(wrapped);
    }

    private PathWrapper(final Iterable<Object> wrapped) {
        super(wrapped);
    }
}
