/*
 * Copyright 2017 MovingBlocks
 *
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
 */
package org.terasology.world.Zones;

import org.junit.Before;
import org.junit.Test;
import org.terasology.math.geom.BaseVector3i;
import org.terasology.world.generation.Region;
import org.terasology.world.zones.ConfigurableZoneRegionFunction;
import org.terasology.world.zones.Zone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ZoneTest {

    private Zone zone;

    @Before
    public void setup() {
        zone = new Zone("Test", new ConfigurableZoneRegionFunction() {
            @Override
            public Boolean apply(BaseVector3i baseVector3i, Region region) {
                return true;
            }
        });
    }

    @Test
    public void testGetChildZones() {
        assertTrue(zone.getChildZones().isEmpty());
        Zone child = new Zone("Child", new ConfigurableZoneRegionFunction() {
            @Override
            public Boolean apply(BaseVector3i baseVector3i, Region region) {
                return false;
            }
        });
        zone.addZone(child);
        assertFalse(zone.getChildZones().isEmpty());
        assertTrue(zone.getChildZones().contains(child));

        try {
            zone.getChildZone("Invalid name");
            fail();
        } catch (Exception e) {}

        assertEquals(child, zone.getChildZone("Child"));
    }


}