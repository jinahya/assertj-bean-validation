package com.github.jinahya.assertj.validation;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import static com.github.jinahya.assertj.validation.BeanAssertions.assertBean;
import static com.github.jinahya.assertj.validation.BeanAssertions.assertThat;
import static com.github.jinahya.assertj.validation.BeanWrapper.bean;
import static java.util.concurrent.ThreadLocalRandom.current;

public class BeanAssertArgumentConverter
        implements ArgumentConverter {

    @Override
    public Object convert(final Object source, final ParameterContext context) throws ArgumentConversionException {
        final BeanAssert va;
        switch (current().nextInt(3)) {
            case 0:
                va = assertThat(source);
                break;
            case 1:
                va = assertThat(bean(source));
                break;
            default:
                va = assertBean(source);
                break;
        }
        return va;
    }
}