package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.GroupDefinitionExceptionAssertions.assertGroupDefinitionException;
import static com.github.jinahya.assertj.validation.GroupDefinitionExceptionAssertions.assertThat;
import static com.github.jinahya.assertj.validation.GroupDefinitionExceptionTestUtils.newGroupDefinitionException;
import static com.github.jinahya.assertj.validation.GroupDefinitionExceptionWrapper.groupDefinitionException;

class GroupDefinitionExceptionAssertionsTest {

    @Test
    void assertThat__() {
        final RuntimeException actual = newGroupDefinitionException();
        final GroupDefinitionExceptionAssert a = assertThat(actual);
    }

    @Test
    void assertThat__Wrapper() {
        final RuntimeException actual = newGroupDefinitionException();
        final GroupDefinitionExceptionAssert a = assertThat(groupDefinitionException(actual));
    }

    @Test
    void assertGroupDefinitionException__() {
        final RuntimeException actual = newGroupDefinitionException();
        final GroupDefinitionExceptionAssert a = assertGroupDefinitionException(actual);
    }
}