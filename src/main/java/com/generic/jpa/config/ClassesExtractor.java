package com.generic.jpa.config;

import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ClassesExtractor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClassesExtractor.class);
    private static final char CLASS_PACKAGE_NAME_DELIMITER = '.';
    private static final char DIRECTORY_SEPARATOR = '/';
    private static final String CLASS_EXTENSION = ".class";
    private static final String EXCEPTION_CAUGHT_WITH_TRACE = "exception caught with trace: {}, {}";

    static List<String> retrieveClasseNamesFrom(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(CLASS_PACKAGE_NAME_DELIMITER, DIRECTORY_SEPARATOR);
        List<String> classNames = new ArrayList<>();
        try (Stream<Path> paths = java.nio.file.Files.walk(java.nio.file.Paths.get(Objects.requireNonNull(classLoader.getResource(path)).toURI()))) {
            classNames = paths
                .filter(java.nio.file.Files::isRegularFile)
                .map(p -> packageName + CLASS_PACKAGE_NAME_DELIMITER + p.getFileName().toString().replace(CLASS_EXTENSION, ""))
                .filter(ClassesExtractor::isAbstract)
                .map(packaage -> packaage.replace(packageName + CLASS_PACKAGE_NAME_DELIMITER, ""))
                .toList();
        } catch (Exception exception) {
            LOGGER.error(EXCEPTION_CAUGHT_WITH_TRACE, exception.getClass(), exception.getMessage());
        }
        return classNames;
    }

    private static boolean isAbstract(String className) {
        try {
            return !Modifier.isAbstract(Class.forName(className).getModifiers());
        } catch (ClassNotFoundException classNotFoundException) {
            LOGGER.error(EXCEPTION_CAUGHT_WITH_TRACE, classNotFoundException.getClass(), classNotFoundException.getMessage());
            return false;
        }
    }

    private ClassesExtractor() {
    }
}
