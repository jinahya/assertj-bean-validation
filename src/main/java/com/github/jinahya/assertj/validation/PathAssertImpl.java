package com.github.jinahya.assertj.validation;

import javax.validation.Path;

class PathAssertImpl
        extends PathAssert<PathAssertImpl, Path> {

    PathAssertImpl(final Path actual) {
        super(actual, PathAssertImpl.class);
    }
}
