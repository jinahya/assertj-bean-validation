package com.github.jinahya.assertj.validation.user;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class UserBeanArgumentsProviders {

    public static class OfInvalid
            implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(final ExtensionContext context) throws Exception {
            return IntStream.range(0, 16).mapToObj(i -> User.newInvalidInstance()).map(Arguments::of);
        }
    }

    public static class OfInvalidAges
            implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(final ExtensionContext context) throws Exception {
            return IntStream.range(0, 16).mapToObj(i -> User.newInstanceWithInvalidAge()).map(Arguments::of);
        }
    }

    public static class OfInvalidNames
            implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(final ExtensionContext context) throws Exception {
            return IntStream.range(0, 16).mapToObj(i -> User.newInstanceWithInvalidName()).map(Arguments::of);
        }
    }

    public static class OfValid
            implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(final ExtensionContext context) throws Exception {
            return IntStream.range(0, 16).mapToObj(i -> User.newValidInstance()).map(Arguments::of);
        }
    }

    private UserBeanArgumentsProviders() {
        throw new AssertionError("instantiation is not allowed");
    }
}