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

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ServiceLoader;

final class SpiUtils {

    static Optional<Object> loadValidationProviderJakarta() {
        try {
            final Class<?> service = Class.forName("jakarta.validation.spi.ValidationProvider");
            return Optional.of(ServiceLoader.load(service).iterator().next());
        } catch (ReflectiveOperationException | NoSuchElementException e) {
            // empty
        }
        return Optional.empty();
    }

    static boolean providedByJakarta() {
        return loadValidationProviderJakarta().isPresent();
    }

    static Optional<Object> loadValidationProviderJavax() {
        try {
            final Class<?> service = Class.forName("javax.validation.spi.ValidationProvider");
            return Optional.of(ServiceLoader.load(service).iterator().next());
        } catch (ReflectiveOperationException | NoSuchElementException e) {
            // empty
        }
        return Optional.empty();
    }

    static boolean providedByJavax() {
        return loadValidationProviderJavax().isPresent();
    }

    static Object loadValidationProvider() {
        Object validationProviderJakarta = loadValidationProviderJakarta().orElse(null);
        Object validationProviderJavax = loadValidationProviderJavax().orElse(null);
        if (validationProviderJakarta == null && validationProviderJavax == null) {
            throw new RuntimeException("service provider not loaded for either jakarta nor javax");
        }
        if (validationProviderJakarta != null && validationProviderJavax != null) {
            throw new RuntimeException("service provider loaded for both jakarta and javax");
        }
        if (validationProviderJakarta != null) {
            return validationProviderJakarta;
        }
        return validationProviderJavax;
    }

    private SpiUtils() {
        throw new AssertionError("instantiation is not allowed");
    }
}
