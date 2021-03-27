package com.github.jinahya.assertj.validation;

import java.lang.reflect.Method;

import static java.util.Objects.requireNonNull;

final class ConstraintViolationUtils {

    private static Class<?> constraintViolationClassJakarta() throws ClassNotFoundException {
        return Class.forName("jakarta.validation.ConstraintViolation");
    }

    private static Class<?> constraintViolationClassJavax() throws ClassNotFoundException {
        return Class.forName("javax.validation.ConstraintViolation");
    }

    /**
     * Indicates whether specified object is an instance of {@code javax.validation.ConstraintViolation} or is an
     * instance of {@code jakarta.validation.ConstraintViolation}.
     *
     * @param object the object to be tested.
     * @return {@code true} if {@code object} is an instance of {@code ConstraintViolation}; {@code false} otherwise.
     * @see #constraintViolationClassJakarta()
     * @see #constraintViolationClassJavax()
     */
    static boolean isConstraintViolationInstance(final Object object) {
        Class<?> classJavax = null;
        try {
            classJavax = constraintViolationClassJavax();
        } catch (final ClassNotFoundException cnfe) {
            // empty
        }
        Class<?> classJakarta = null;
        try {
            classJakarta = constraintViolationClassJakarta();
        } catch (final ClassNotFoundException cnfe) {
            // empty
        }
        if (classJavax == null && classJakarta == null) {
            throw new RuntimeException("unable to find the ....validation.ConstraintViolation class");
        }
        return (classJavax != null && classJavax.isInstance(object))
               || (classJakarta != null && classJakarta.isInstance(object));
    }

    // ------------------------------------------------------------------------------------------------- getInvalidValue
    private static Method getInvalidValueMethod(final Object violation) throws NoSuchMethodException {
        requireNonNull(violation, "violation is null");
        final String name = "getInvalidValue";
        try {
            final Class<?> c = constraintViolationClassJavax();
            if (c.isInstance(violation)) {
                return c.getMethod(name);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        try {
            final Class<?> c = constraintViolationClassJakarta();
            if (c.isInstance(violation)) {
                return c.getMethod(name);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        throw new NoSuchMethodException("failed to get " + name + " method for " + violation);
    }

    static Object getInvalidValue(final Object violation) {
        requireNonNull(violation, "violation is null");
        try {
            return getInvalidValueMethod(violation).invoke(violation);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to invoke getInvalidValue() on " + violation);
        }
    }

    // ----------------------------------------------------------------------------------------------------- getLeafBean
    private static Method getLeafBeanMethod(final Object violation) throws NoSuchMethodException {
        requireNonNull(violation, "violation is null");
        final String name = "getLeafBean";
        try {
            final Class<?> c = constraintViolationClassJavax();
            if (c.isInstance(violation)) {
                return c.getMethod(name);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        try {
            final Class<?> c = constraintViolationClassJakarta();
            if (c.isInstance(violation)) {
                return c.getMethod(name);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        throw new NoSuchMethodException("failed to get " + name + " method for " + violation);
    }

    static Object getLeafBean(final Object violation) {
        requireNonNull(violation, "violation is null");
        try {
            return getLeafBeanMethod(violation).invoke(violation);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to invoke getLeafBean() on " + violation);
        }
    }

    // ------------------------------------------------------------------------------------------------------ getMessage
    private static Method getMessageMethod(final Object violation) throws NoSuchMethodException {
        requireNonNull(violation, "violation is null");
        final String name = "getMessage";
        try {
            final Class<?> c = constraintViolationClassJavax();
            if (c.isInstance(violation)) {
                return c.getMethod(name);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        try {
            final Class<?> c = constraintViolationClassJakarta();
            if (c.isInstance(violation)) {
                return c.getMethod(name);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        throw new NoSuchMethodException("failed to get " + name + " method for " + violation);
    }

    static String getMessage(final Object violation) {
        requireNonNull(violation, "violation is null");
        try {
            return (String) getMessageMethod(violation).invoke(violation);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to invoke getMessage() on " + violation);
        }
    }

    // ----------------------------------------------------------------------------------------------------- getRootBean
    private static Method getRootBeanMethod(final Object violation) throws NoSuchMethodException {
        requireNonNull(violation, "violation is null");
        final String name = "getRootBean";
        try {
            final Class<?> c = constraintViolationClassJavax();
            if (c.isInstance(violation)) {
                return c.getMethod(name);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        try {
            final Class<?> c = constraintViolationClassJakarta();
            if (c.isInstance(violation)) {
                return c.getMethod(name);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        throw new NoSuchMethodException("failed to get " + name + " method for " + violation);
    }

    static Object getRootBean(final Object violation) {
        requireNonNull(violation, "violation is null");
        try {
            return getRootBeanMethod(violation).invoke(violation);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to invoke getRootBean on " + violation, roe);
        }
    }

    // ------------------------------------------------------------------------------------------------ getRootBeanClass
    private static Method getRootBeanClassMethod(final Object violation) throws NoSuchMethodException {
        requireNonNull(violation, "violation is null");
        final String name = "getRootBeanClass";
        try {
            final Class<?> c = constraintViolationClassJavax();
            if (c.isInstance(violation)) {
                return c.getMethod(name);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        try {
            final Class<?> c = constraintViolationClassJakarta();
            if (c.isInstance(violation)) {
                return c.getMethod(name);
            }
        } catch (final ReflectiveOperationException roe) {
            // empty
        }
        throw new NoSuchMethodException("failed to get " + name + " method for " + violation);
    }

    static Class<?> getRootBeanClass(final Object violation) {
        requireNonNull(violation, "violation is null");
        try {
            final Method method = getRootBeanClassMethod(violation);
            return (Class<?>) method.invoke(violation);
        } catch (final ReflectiveOperationException roe) {
            throw new RuntimeException("failed to invoke getRootBeanClass on " + violation, roe);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    private ConstraintViolationUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
