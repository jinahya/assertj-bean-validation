package com.github.jinahya.assertj.validation;

/*-
 * #%L
 * assertj-bean-validation
 * %%
 * Copyright (C) 2021 Jinahya, Inc.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

public final class PathTestUtils {

    public static final class NodeTestUtils {

        public static Integer getIndex(final Object actual) {
            return PathUtils.NodeUtils.getIndex(actual);
        }

        public static Object getKey(final Object actual) {
            return PathUtils.NodeUtils.getKey(actual);
        }

        public static Object getKind(final Object actual) {
            return PathUtils.NodeUtils.getKind(actual);
        }

        public static String getName(final Object actual) {
            return PathUtils.NodeUtils.getName(actual);
        }

        public static boolean isInIterable(final Object actual) {
            return PathUtils.NodeUtils.isInIterable(actual);
        }

        private NodeTestUtils() {
            throw new AssertionError("instantiation is not allowed");
        }
    }

    public static final class PropertyNodeTestUtils {

        public static Class<?> getContainerClass(final Object actual) {
            return PathUtils.PropertyNodeUtils.getContainerClass(actual);
        }

        public static Integer getTypeArgumentIndex(final Object actual) {
            return PathUtils.PropertyNodeUtils.getTypeArgumentIndex(actual);
        }

        private PropertyNodeTestUtils() {
            throw new NonInstantiatableAssertionError();
        }
    }

    private PathTestUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
