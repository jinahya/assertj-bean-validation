package com.github.jinahya.assertj.validation;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.BooleanAssert;
import org.assertj.core.api.IterableAssert;

import java.util.List;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * An abstract assertion class for verifying instances of {@code Path} which is an iterable of {@code Node}s.
 *
 * @param <ACTUAL> actual type parameter
 * @param <NODE>   the type of {@code Node}
 * @author Jin Kwon &lt;onacit_at_gmail.com&gt;
 */
@SuppressWarnings({"java:S119"})
public abstract class AbstractPathAssert<ACTUAL extends Iterable<NODE>, NODE>
        extends IterableAssert<NODE> {

    interface NodeBaseAssertDelegate<NODE, ELEMENT_KIND> {

        Integer getIndex(NODE actual);

        Object getKey(NODE actual);

        ELEMENT_KIND getKind(NODE actual);

        String getName(NODE actual);

        boolean isInIterable(NODE actual);
    }

    public abstract static class AbstractNodeBaseAssert<
            SELF extends AbstractNodeBaseAssert<SELF, ACTUAL, ELEMENT_KIND, D>,
            ACTUAL,
            ELEMENT_KIND,
            D extends NodeBaseAssertDelegate<ACTUAL, ELEMENT_KIND>>
            extends AbstractAssert<SELF, ACTUAL> {

        protected AbstractNodeBaseAssert(final ACTUAL actual, final Class<?> selfType, final D delegate) {
            super(actual, selfType);
            this.delegate = requireNonNull(delegate, "delegate is null");
        }

        // -------------------------------------------------------------------------------------------------- getIndex()

        /**
         * Verifies that the {@code actual.getIndex()} satisfies the requirements by being accepted to specified
         * consumer.
         *
         * @param requirements the consumer accepts and verifies {@code actual.index}.
         * @return {@link #myself myself}.
         */
        public SELF hasIndexSatisfying(final Consumer<? super Integer> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getIndex(a)).satisfies(requirements::accept);
            });
        }

        /**
         * Verifies that the {@code actual.getIndex()} is {@link AbstractAssert#isEqualTo(Object) equal} to specified
         * value.
         *
         * @param expected the value expected to be equal to {@code actual.index}.
         * @return {@link #myself myself}.
         */
        public SELF hasIndexEqualTo(final Object expected) {
            return hasIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
        }

        // ---------------------------------------------------------------------------------------------------- getKey()

        /**
         * Verifies that the {@code actual.getKey()} satisfies the requirements by being accepted to specified
         * consumer.
         *
         * @param requirements the consumer accepts and verifies {@code actual.key}.
         * @return {@link #myself myself}.
         */
        public SELF hasKeySatisfying(final Consumer<Object> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getKey(a)).satisfies(requirements);
            });
        }

        /**
         * Verifies that the {@code actual.getKey()} is {@link AbstractAssert#isEqualTo(Object) equal} to specified
         * value.
         *
         * @param expected the value expected to be equal to {@code actual.key}.
         * @return {@link #myself myself}.
         */
        public SELF hasKeyEqualTo(final Object expected) {
            return hasKeySatisfying(v -> assertThat(v).isEqualTo(expected));
        }

        // --------------------------------------------------------------------------------------------------- getKind()

        /**
         * Verifies that the {@code actual.getKind()} satisfies the requirements by being accepted to specified
         * consumer.
         *
         * @param requirements the consumer accepts and verifies {@code actual.kind}.
         * @return {@link #myself myself}.
         */
        public SELF hasKindSatisfying(final Consumer<? super ELEMENT_KIND> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getKind(a)).satisfies(requirements::accept);
            });
        }

        /**
         * Verifies that the {@code actual.getKind()} is {@link AbstractAssert#isSameAs(Object) same} as specified
         * value.
         *
         * @param expected the value expected to be same as {@code actual.kind}.
         * @return {@link #myself myself}.
         */
        public SELF hasKindSameAs(final Object expected) {
            return hasKindSatisfying(v -> assertThat(v).isSameAs(expected));
        }

        // --------------------------------------------------------------------------------------------------- getName()

        /**
         * Verifies that the {@code actual.getName()} satisfies the requirements by being accepted to specified
         * consumer.
         *
         * @param requirements the consumer accepts and verifies {@code actual.name}.
         * @return {@link #myself myself}.
         */
        public SELF hasNameSatisfying(final Consumer<? super String> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getName(a)).satisfies(requirements::accept);
            });
        }

        /**
         * Verifies that {@code actual.getName()} is {@link AbstractAssert#isEqualTo(Object) equal} to specified value.
         *
         * @param expected the value expected to be equal to {@code actual.name}.
         * @return {@link #myself self}.
         */
        public SELF hasNameEqualTo(final Object expected) {
            return hasNameSatisfying(v -> assertThat(v).isEqualTo(expected));
        }

        // ---------------------------------------------------------------------------------------------- isInIterable()

        /**
         * Verifies that the value of {@code actual.isInIterable()} satisfies the requirements by being accepted to
         * specified consumer.
         *
         * @param requirements the consumer accepts and verifies the {@code actual.inIterable} value.
         * @return {@link #myself myself}.
         */
        public SELF hasInIterableSatisfying(final Consumer<? super Boolean> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.isInIterable(a)).satisfies(requirements::accept);
            });
        }

        /**
         * Verifies that the value of {@code actual.isInIterable()} is {@link BooleanAssert#isTrue() true}.
         *
         * @return {@link #myself self}.
         */
        public SELF isInIterable() {
            return hasInIterableSatisfying(v -> assertThat(v).isTrue());
        }

        /**
         * Verifies that the value of {@code actual.isInIterable()} is {@link BooleanAssert#isFalse() false}.
         *
         * @return {@link #myself self}.
         */
        public SELF isNotInIterable() {
            return hasInIterableSatisfying(v -> assertThat(v).isFalse());
        }

        protected final D delegate;
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface AbstractNodeAssertDelegate<NODE, ELEMENT_KIND>
            extends NodeBaseAssertDelegate<NODE, ELEMENT_KIND> {

    }

    protected abstract static class AbstractNodeAssert<
            SELF extends AbstractNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends AbstractNodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            NodeBaseAssertDelegate<ACTUAL, ELEMENT_KIND>> {

        protected AbstractNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                     final AbstractNodeAssertDelegate<ACTUAL, ELEMENT_KIND> delegate) {
            super(actual, selfType, delegate);
        }

        // ---------------------------------------------------------------------------------------------------- BeanNode
        public abstract SELF isBeanNode();

        public abstract <T extends AbstractBeanNodeAssert<T, ACTUAL, ELEMENT_KIND>> T asBeanNode();
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface BeanNodeAssertDelegate<ACTUAL, ELEMENT_KIND>
            extends NodeBaseAssertDelegate<ACTUAL, ELEMENT_KIND> {

        Class<?> getContainerClass(ACTUAL actual);

        Integer getTypeArgumentIndex(ACTUAL actual);
    }

    protected abstract static class AbstractBeanNodeAssert<
            SELF extends AbstractBeanNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends AbstractNodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            BeanNodeAssertDelegate<ACTUAL, ELEMENT_KIND>> {

        protected AbstractBeanNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                         final BeanNodeAssertDelegate<ACTUAL, ELEMENT_KIND> delegate) {
            super(actual, selfType, delegate);
        }

        public SELF hasContainerClassSatisfying(final Consumer<? super Class<?>> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getContainerClass(a)).satisfies(requirements::accept);
            });
        }

        public SELF hasContainerClassSameAs(final Object expected) {
            return hasContainerClassSatisfying(v -> assertThat(v).isSameAs(expected));
        }

        public SELF hasTypeArgumentIndexSatisfying(final Consumer<? super Integer> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getTypeArgumentIndex(a)).satisfies(requirements::accept);
            });
        }

        public SELF hasTypeArgumentIndexEqualTo(final Object expected) {
            return hasTypeArgumentIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    // ------------------------------------------------------------------------------------------------- ConstructorNode
    protected interface ConstructorNodeAssertDelegate<ACTUAL, ELEMENT_KIND>
            extends NodeBaseAssertDelegate<ACTUAL, ELEMENT_KIND> {

        List<Class<?>> getParameterTypes(ACTUAL actual);
    }

    protected abstract static class AbstractConstructorNodeAssert<
            SELF extends AbstractConstructorNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends AbstractNodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            ConstructorNodeAssertDelegate<ACTUAL, ELEMENT_KIND>> {

        protected AbstractConstructorNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                                final ConstructorNodeAssertDelegate<ACTUAL, ELEMENT_KIND> delegate) {
            super(actual, selfType, delegate);
        }

        public SELF hasParameterTypesSatisfying(
                final Consumer<? super List<? extends Class<?>>> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getParameterTypes(a)).satisfies(requirements::accept);
            });
        }

        public SELF hasParameterTypesEqualTo(final Object expected) {
            return hasParameterTypesSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface ContainerElementNodeAssertDelegate<ACTUAL, ELEMENT_KIND>
            extends NodeBaseAssertDelegate<ACTUAL, ELEMENT_KIND> {

        Class<?> getContainerClass(ACTUAL actual);

        Integer getTypeArgumentIndex(ACTUAL actual);
    }

    protected abstract static class AbstractContainerElementNodeAssert<
            SELF extends AbstractContainerElementNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends AbstractNodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            ContainerElementNodeAssertDelegate<ACTUAL, ELEMENT_KIND>> {

        protected AbstractContainerElementNodeAssert(
                final ACTUAL actual, final Class<?> selfType,
                final ContainerElementNodeAssertDelegate<ACTUAL, ELEMENT_KIND> delegate) {
            super(actual, selfType, delegate);
        }

        public SELF hasContainerClassSatisfying(final Consumer<? super Class<?>> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getContainerClass(a)).satisfies(requirements::accept);
            });
        }

        public SELF hasContainerClassSameAs(final Object expected) {
            return hasContainerClassSatisfying(v -> assertThat(v).isSameAs(expected));
        }

        public SELF hasTypeArgumentIndexSatisfying(final Consumer<? super Integer> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getTypeArgumentIndex(a)).satisfies(requirements::accept);
            });
        }

        public SELF hasTypeArgumentIndexEqualTo(final Object expected) {
            return hasTypeArgumentIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface CrossParameterNodeAssertDelegate<ACTUAL, ELEMENT_KIND>
            extends NodeBaseAssertDelegate<ACTUAL, ELEMENT_KIND> {

    }

    protected abstract static class AbstractCrossParameterNodeAssert<
            SELF extends AbstractCrossParameterNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends AbstractNodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            CrossParameterNodeAssertDelegate<ACTUAL, ELEMENT_KIND>> {

        protected AbstractCrossParameterNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                                   final CrossParameterNodeAssertDelegate<ACTUAL, ELEMENT_KIND> delegate) {
            super(actual, selfType, delegate);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface MethodNodeAssertDelegate<ACTUAL, ELEMENT_KIND>
            extends NodeBaseAssertDelegate<ACTUAL, ELEMENT_KIND> {

        List<Class<?>> getParameterTypes(ACTUAL actual);
    }

    protected abstract static class AbstractMethodNodeAssert<
            SELF extends AbstractMethodNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends AbstractNodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            MethodNodeAssertDelegate<ACTUAL, ELEMENT_KIND>> {

        protected AbstractMethodNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                           final MethodNodeAssertDelegate<ACTUAL, ELEMENT_KIND> delegate) {
            super(actual, selfType, delegate);
        }

        public SELF hasParameterTypesSatisfying(
                final Consumer<? super List<? extends Class<?>>> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getParameterTypes(a)).satisfies(requirements::accept);
            });
        }

        public SELF hasParameterTypesEqualTo(final Object expected) {
            return hasParameterTypesSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface ParameterNodeAssertDelegate<ACTUAL, ELEMENT_KIND>
            extends NodeBaseAssertDelegate<ACTUAL, ELEMENT_KIND> {

        int getParameterIndex(ACTUAL actual);
    }

    protected abstract static class AbstractParameterNodeAssert<
            SELF extends AbstractParameterNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends AbstractNodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            ParameterNodeAssertDelegate<ACTUAL, ELEMENT_KIND>> {

        protected AbstractParameterNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                              final ParameterNodeAssertDelegate<ACTUAL, ELEMENT_KIND> delegate) {
            super(actual, selfType, delegate);
        }

        public SELF hasParameterIndexSatisfying(final Consumer<? super Integer> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getParameterIndex(a)).satisfies(requirements::accept);
            });
        }

        public SELF hasParameterIndexEqualTo(final Object expected) {
            return hasParameterIndexSatisfying((Integer v) -> assertThat(v).isSameAs(expected));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface PropertyNodeAssertDelegate<ACTUAL, ELEMENT_KIND>
            extends NodeBaseAssertDelegate<ACTUAL, ELEMENT_KIND> {

        Class<?> getContainerClass(ACTUAL actual);

        Integer getTypeArgumentIndex(ACTUAL actual);
    }

    protected abstract static class AbstractPropertyNodeAssert<
            SELF extends AbstractPropertyNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends AbstractNodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            PropertyNodeAssertDelegate<ACTUAL, ELEMENT_KIND>> {

        protected AbstractPropertyNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                             final PropertyNodeAssertDelegate<ACTUAL, ELEMENT_KIND> delegate) {
            super(actual, selfType, delegate);
        }

        public SELF hasContainerClassSatisfying(final Consumer<? super Class<?>> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getContainerClass(a)).satisfies(requirements::accept);
            });
        }

        public SELF hasContainerClassSameAs(final Object expected) {
            return hasContainerClassSatisfying(v -> assertThat(v).isSameAs(expected));
        }

        public SELF hasTypeArgumentIndexSatisfying(final Consumer<? super Integer> requirements) {
            return isNotNull().satisfies(a -> {
                assertThat(delegate.getTypeArgumentIndex(a)).satisfies(requirements::accept);
            });
        }

        public SELF hasTypeArgumentIndexEqualTo(final Object expected) {
            return hasTypeArgumentIndexSatisfying(v -> assertThat(v).isEqualTo(expected));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected interface ReturnValueNodeAssertDelegate<ACTUAL, ELEMENT_KIND>
            extends NodeBaseAssertDelegate<ACTUAL, ELEMENT_KIND> {

    }

    protected abstract static class AbstractReturnValueNodeAssert<
            SELF extends AbstractReturnValueNodeAssert<SELF, ACTUAL, ELEMENT_KIND>,
            ACTUAL,
            ELEMENT_KIND>
            extends AbstractNodeBaseAssert<
            SELF,
            ACTUAL,
            ELEMENT_KIND,
            ReturnValueNodeAssertDelegate<ACTUAL, ELEMENT_KIND>> {

        protected AbstractReturnValueNodeAssert(final ACTUAL actual, final Class<?> selfType,
                                                final ReturnValueNodeAssertDelegate<ACTUAL, ELEMENT_KIND> delegate) {
            super(actual, selfType, delegate);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------
    protected AbstractPathAssert(final ACTUAL actual) {
        super(actual);
    }
}
