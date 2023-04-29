package com.github.jinahya.assertj.validation.example.user;

import com.github.jinahya.assertj.validation.AbstractPropertyAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.github.jinahya.assertj.validation.ValidationAssertions.assertThatBean;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests {@link AbstractPropertyAssert#targetingGroups(Class[])} method.
 *
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
class User_TargetingGroups_Test {

    @DisplayName("assertThatBean(junior).targetingGroups(Junior.class).isValid()")
    @Test
    void _targetingJunior_Junior() {
        final var junior = User.newJunior();
        assertThatBean(junior)
                .targetingGroups(Junior.class)
                .isValid()
        ;
    }

    @DisplayName("assertThatBean(junior).targetingGroups(Senior.class).isValid()")
    @Test
    void _targetingSenior_Junior() {
        final var junior = User.newJunior();
        final var assertion = assertThatBean(junior);
        assertion.targetingGroups(Senior.class);
        assertThatThrownBy(() -> assertion.isValid())
                .isInstanceOf(AssertionError.class);
    }

    @DisplayName("assertThatBean(senior).targetingGroups(Senior.class).isValid()")
    @Test
    void _targetingSenior_Senior() {
        final var senior = User.newSenior();
        final var assertion = assertThatBean(senior);
        assertion.targetingGroups(Senior.class);
        assertion.isValid();
    }

    @DisplayName("assertThatBean(senior).targetingGroups(Junior.class).isValid()")
    @Test
    void _targetingJunior_Senior() {
        final var senior = User.newSenior();
        final var assertion = assertThatBean(senior);
        assertion.targetingGroups(Junior.class);
        assertThatThrownBy(() -> assertion.isValid())
                .isInstanceOf(AssertionError.class);
    }
}
