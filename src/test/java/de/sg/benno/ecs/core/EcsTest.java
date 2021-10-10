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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EcsTest {

    private Ecs ecs;

    private static class Position implements Component {}
    private static class Transform implements Component {}
    private static class Health implements Component {}
    private static class Velocity implements Component {}
    private static class Attack implements Component {}

    @BeforeEach
    void setUp() {
        ecs = new Ecs(
                Position.class,
                Transform.class,
                Health.class,
                Velocity.class,
                Attack.class
        );
    }

    @Test
    void getAllComponentTypes() {
        var componentTypes = ecs.getAllComponentTypes();
        assertEquals(5, componentTypes.size());
    }

    @Test
    void getEntityManager() {
        var em = ecs.getEntityManager();
        assertNotNull(em);
        assertEquals(EntityManager.class, em.getClass());
    }

    @Test
    void getSystemManager() {
        var sm = ecs.getSystemManager();
        assertNotNull(sm);
        assertEquals(SystemManager.class, sm.getClass());
    }

    @Test
    void init() {
    }

    @Test
    void input() {
    }

    @Test
    void update() {
        //ecs.update();
    }

    @Test
    void render() {
    }

    @Test
    void cleanUp() {
    }

    @Test
    void getComponentIndex() {
        var index2 = ecs.getComponentIndex(Health.class);
        var index4 = ecs.getComponentIndex(Attack.class);
        assertEquals(2, index2);
        assertEquals(4, index4);
    }
}
