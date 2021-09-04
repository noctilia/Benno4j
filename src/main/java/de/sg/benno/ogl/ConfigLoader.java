/*
 * This file is part of the Benno4j project.
 *
 * Copyright (c) 2021. stwe <https://github.com/stwe/Benno4j>
 *
 * License: GPLv2
 */

package de.sg.benno.ogl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Objects;
import java.util.Properties;

/**
 * Loads a config file.
 */
public class ConfigLoader {

    //-------------------------------------------------
    // Load
    //-------------------------------------------------

    /**
     * Loads a config file.
     *
     * @param configClass The config class.
     * @param path The path to the config file.
     * @throws IOException If an I/O error is thrown.
     * @throws IllegalAccessException If an error is thrown.
     */
    public static void load(Class<?> configClass, String path) throws IOException, IllegalAccessException {
        Objects.requireNonNull(configClass, "configClass must not be null");
        Objects.requireNonNull(path, "path must not be null");

        var in = ConfigLoader.class.getResourceAsStream(path);
        if (in == null) {
            throw new FileNotFoundException("Config file " + path + " not found.");
        }

        var properties = new Properties();
        properties.load(in);

        for (var field : configClass.getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) {
                field.set(null, getValue(properties, field.getName(), field.getType()));
            }
        }
    }

    //-------------------------------------------------
    // Helper
    //-------------------------------------------------

    /**
     * Get configuration value.
     *
     * @param properties A {@link Properties} object.
     * @param name The name of the config value.
     * @param type The type of the config value.
     *
     * @return {@link Object}
     */
    private static Object getValue(Properties properties, String name, Class<?> type) {
        var value = Objects.requireNonNull(properties).getProperty(Objects.requireNonNull(name));

        if (value == null) {
            throw new OglRuntimeException("Missing configuration value: " + name);
        }

        if (type == String.class) {
            return value;
        }

        if (type == boolean.class) {
            return Boolean.parseBoolean(value);
        }

        if (type == int.class) {
            return Integer.parseInt(value);
        }

        if (type == float.class) {
            return Float.parseFloat(value);
        }

        if (type == double.class) {
            return Double.parseDouble(value);
        }

        throw new OglRuntimeException("Unknown configuration value type: " + type.getName());
    }
}
