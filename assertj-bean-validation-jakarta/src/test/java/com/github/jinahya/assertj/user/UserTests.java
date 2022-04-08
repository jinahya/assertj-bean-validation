package com.github.jinahya.assertj.user;

import java.util.stream.IntStream;
import java.util.stream.Stream;

final class UserTests {

    static Stream<User> userWithInvalidAgeStream() {
        return IntStream.range(0, 8)
                .mapToObj(i -> User.newInstanceWithInvalidAge());
    }

    static Stream<User> userWithInvalidNameStream() {
        return IntStream.range(0, 8)
                .mapToObj(i -> User.newInstanceWithInvalidName());
    }

    static Stream<User> invalidUserStream() {
        return Stream.concat(
                userWithInvalidAgeStream(),
                userWithInvalidNameStream()
        );
    }

    static Stream<User> validUserStream() {
        return IntStream.range(0, 8)
                .mapToObj(i -> User.newValidInstance());
    }

    private UserTests() {
        throw new AssertionError("instantiation is not allowed");
    }
}
