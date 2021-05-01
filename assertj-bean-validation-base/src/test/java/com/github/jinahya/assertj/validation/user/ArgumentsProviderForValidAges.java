package com.github.jinahya.assertj.validation.user;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.IntStream;
import java.util.stream.Stream;

class ArgumentsProviderForValidAges
        implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(final ExtensionContext context) throws Exception {
        return IntStream.range(0, 16).map(i -> User.newValidAge()).mapToObj(Arguments::of);
    }
}