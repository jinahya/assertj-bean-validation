package com.github.jinahya.assertj.validation.user;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public final class UserValueArgumentsProviders {

    public static class OfInvalidAges
            implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(final ExtensionContext context) throws Exception {
            return IntStream.range(0, 16).map(i -> User.newInvalidAge()).mapToObj(Arguments::of);
        }
    }

    public static class OfInvalidNames
            implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(final ExtensionContext context) throws Exception {
            return IntStream.range(0, 16).mapToObj(i -> User.newInvalidName()).map(Arguments::of);
        }
    }

    public static class OfValidAges
            implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(final ExtensionContext context) throws Exception {
            return IntStream.range(0, 16).map(i -> User.newValidAge()).mapToObj(Arguments::of);
        }
    }

    public static class OfValidNames
            implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(final ExtensionContext context) throws Exception {
            return IntStream.range(0, 16).mapToObj(i -> User.newValidName()).map(Arguments::of);
        }
    }

    private UserValueArgumentsProviders() {
        throw new AssertionError("instantiation is not allowed");
    }
}