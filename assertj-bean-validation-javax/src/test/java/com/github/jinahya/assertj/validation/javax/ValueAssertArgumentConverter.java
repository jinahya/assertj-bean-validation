package com.github.jinahya.assertj.validation.javax;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import static com.github.jinahya.assertj.validation.ValueWrapper.value;
import static java.util.concurrent.ThreadLocalRandom.current;

public class ValueAssertArgumentConverter
        implements ArgumentConverter {

    @Override
    public Object convert(final Object source, final ParameterContext context) throws ArgumentConversionException {
        final ValueAssert va;
        switch (current().nextInt(3)) {
            case 0:
                va = ValueAssertions.assertThat(source);
                break;
            case 1:
                va = ValueAssertions.assertThat(value(source));
                break;
            default:
                va = ValueAssertions.assertValue(source);
                break;
        }
        return va;
    }
}