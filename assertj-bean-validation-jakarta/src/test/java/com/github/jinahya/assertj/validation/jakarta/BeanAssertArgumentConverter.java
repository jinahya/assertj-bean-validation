package com.github.jinahya.assertj.validation.jakarta;

import com.github.jinahya.assertj.validation.BeanWrapper;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import static java.util.concurrent.ThreadLocalRandom.current;

public class BeanAssertArgumentConverter
        implements ArgumentConverter {

    @Override
    public Object convert(final Object source, final ParameterContext context) throws ArgumentConversionException {
        final BeanAssert<?> va;
        switch (current().nextInt(3)) {
            case 0:
                va = BeanAssertions.assertThat(source);
                break;
            case 1:
                va = BeanAssertions.assertThat(BeanWrapper.bean(source));
                break;
            default:
                va = BeanAssertions.assertBean(source);
                break;
        }
        return va;
    }
}