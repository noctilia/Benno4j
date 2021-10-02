/*
 * This file is part of the Benno4j project.
 *
 * Copyright (c) 2021, stwe <https://github.com/stwe/Benno4j>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.sg.benno.ecs.core;

import java.util.ArrayList;
import java.util.BitSet;

/**
 * Represents the Entity Component System.
 */
public class Ecs {

    //-------------------------------------------------
    // Member
    //-------------------------------------------------

    /**
     * A list of all {@link Component} types used in this {@link Ecs}.
     */
    private final ArrayList<Class<? extends Component>> allComponentTypes;

    /**
     * The {@link EntityManager} object.
     */
    private final EntityManager entityManager;

    /**
     * A list of all {@link System} objects used in this {@link Ecs}.
     */
    private final ArrayList<System> systems = new ArrayList<>();

    //-------------------------------------------------
    // Ctors.
    //-------------------------------------------------

    /**
     * Constructs a new {@link Ecs} object.
     *
     * @param allComponentTypes A list of all {@link Component} types used in this {@link Ecs}.
     */
    public Ecs(ArrayList<Class<? extends Component>> allComponentTypes) {
        this.allComponentTypes = allComponentTypes;
        this.entityManager = new EntityManager(this);
    }

    //-------------------------------------------------
    // Getter
    //-------------------------------------------------

    /**
     * Get {@link #allComponentTypes}.
     *
     * @return {@link #allComponentTypes}
     */
    public ArrayList<Class<? extends Component>> getAllComponentTypes() {
        return allComponentTypes;
    }

    /**
     * Get {@link #entityManager}.
     *
     * @return {@link #entityManager}
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Get {@link #systems}.
     *
     * @return {@link #systems}
     */
    public ArrayList<System> getSystems() {
        return systems;
    }

    //-------------------------------------------------
    // Logic
    //-------------------------------------------------

    /**
     * Initializes all the {@link System} objects.
     *
     * @param params A list of params.
     *
     * @throws Exception If an error is thrown.
     */
    public void init(Object... params) throws Exception {
        for (var system : systems) {
            system.init(params);
        }
    }

    /**
     * Handle input for all the {@link System} objects.
     */
    public void input() {
        for (var system : systems) {
            system.input();
        }
    }

    /**
     * Updates all the {@link System} objects.
     */
    public void update() {
        for (var system : systems) {
            system.update();
        }
    }

    /**
     * Renders all the {@link System} objects.
     */
    public void render() {
        for (var system : systems) {
            system.render();
        }
    }

    /**
     * Clean up systems.
     */
    public void cleanUp() {
        for (var system : systems) {
            system.cleanUp();
        }
    }

    //-------------------------------------------------
    // Systems
    //-------------------------------------------------

    /**
     * Adds a {@link EntitySystem} to this {@link Ecs}.
     *
     * @param entitySystem {@link EntitySystem}
     */
    public void addSystem(EntitySystem entitySystem) {
        systems.add(entitySystem);
    }

    //-------------------------------------------------
    // Helper
    //-------------------------------------------------

    /**
     * Returns the index in {@link #allComponentTypes} of the given {@link Component} type.
     *
     * @param componentType The component type.
     *
     * @return The index as int.
     */
    public int getComponentIndex(Class<? extends Component> componentType) {
        return allComponentTypes.indexOf(componentType);
    }

    /**
     * {@link BitSet} to {@link String}.
     *
     * @param bits A {@link BitSet}.
     *
     * @return String
     */
    public static String getBitsString (BitSet bits) {
        var stringBuilder = new StringBuilder();

        var numBits = bits.length();
        for (var i = 0; i < numBits; i++) {
            stringBuilder.append(bits.get(i) ? "1" : "0");
        }

        return stringBuilder.toString();
    }
}
