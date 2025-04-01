package oop.collections.tests_yayah;

import oop.collections.IMap;
import oop.collections.ICollection;

/**
 * Abstract base class for testing any IMap implementation.
 * Similar to ListTestBase, it defines general test methods 
 * that subclasses must pass.
 */
public abstract class MapTestBase {

    /**
     * Subclasses must provide a concrete map instance.
     */
    protected abstract IMap createMap();

    /**
     * Runs all tests on the map implementation.
     */
    public void runTests() {
        testPutAndGet();
        testUpdateValue();
        testRemove();
        testContains();
        testKeys();
        testValues();
        testEdgeCases();
        testStressTest();
    }

    /**
     * 1) Basic put/get tests.
     */
    public void testPutAndGet() {
        IMap map = createMap();
        map.put("k1", "v1");
        map.put("k2", "v2");

        assert map.get("k1").equals("v1") : "testPutAndGet failed (k1 -> v1)";
        assert map.get("k2").equals("v2") : "testPutAndGet failed (k2 -> v2)";
        assert map.get("doesNotExist") == null : "testPutAndGet failed (should return null if key not found)";

        System.out.println("testPutAndGet passed!");
    }

    /**
     * 2) Updating an existing key.
     */
    public void testUpdateValue() {
        IMap map = createMap();
        map.put("k1", "v1");
        Object oldValue = map.put("k1", "v1-updated");

        // oldValue should be the previous value, "v1"
        assert "v1".equals(oldValue) : "testUpdateValue failed (should return old value)";
        // new value should be "v1-updated"
        assert "v1-updated".equals(map.get("k1")) : "testUpdateValue failed (value wasn't updated)";

        System.out.println("testUpdateValue passed!");
    }

    /**
     * 3) Removing entries.
     */
    public void testRemove() {
        IMap map = createMap();
        map.put("k1", "v1");
        map.put("k2", "v2");

        // Remove existing key
        Object removed = map.remove("k1");
        assert "v1".equals(removed) : "testRemove failed (should remove k1 -> v1)";
        assert map.get("k1") == null : "testRemove failed (k1 should no longer exist)";

        // Remove non-existing key
        assert map.remove("k3") == null : "testRemove failed (removing unknown key should return null)";

        System.out.println("testRemove passed!");
    }

    /**
     * 4) Checking if the map contains a certain key.
     */
    public void testContains() {
        IMap map = createMap();
        map.put("k1", "v1");

        assert map.contains("k1") : "testContains failed (map should contain k1)";
        assert !map.contains("k2") : "testContains failed (map should not contain k2)";

        System.out.println("testContains passed!");
    }

    /**
     * 5) Iterating over keys.
     */
    public void testKeys() {
        IMap map = createMap();
        map.put("kA", "vA");
        map.put("kB", "vB");

        ICollection.Iterator keyIter = map.keys();
        int count = 0;
        while (keyIter.hasNext()) {
            Object key = keyIter.next();
            assert (key.equals("kA") || key.equals("kB")) : "testKeys failed (unexpected key: " + key + ")";
            count++;
        }
        // Check we have exactly 2 distinct keys
        assert count == 2 : "testKeys failed (should find 2 keys)";

        System.out.println("testKeys passed!");
    }

    /**
     * 6) Iterating over values.
     */
    public void testValues() {
        IMap map = createMap();
        map.put("kA", "vA");
        map.put("kB", "vB");

        ICollection.Iterator valueIter = map.values();
        int count = 0;
        while (valueIter.hasNext()) {
            Object value = valueIter.next();
            assert (value.equals("vA") || value.equals("vB")) : "testValues failed (unexpected value: " + value + ")";
            count++;
        }
        // Check we have exactly 2 distinct values
        assert count == 2 : "testValues failed (should find 2 values)";

        System.out.println("testValues passed!");
    }

    /**
     * 7) Edge cases like null keys/values.
     */
    public void testEdgeCases() {
        IMap map = createMap();

        // Insert with null key
        map.put(null, "nullValue");
        assert "nullValue".equals(map.get(null)) : "testEdgeCases failed (null key -> value not found)";

        // Insert null value
        map.put("keyWithNullValue", null);
        assert map.get("keyWithNullValue") == null : "testEdgeCases failed (should retrieve null value)";

        System.out.println("testEdgeCases passed!");
    }

    /**
     * 8) Stress test with many insertions.
     */
    public void testStressTest() {
        IMap map = createMap();
        int testSize = 50_000;

        // Insert many entries
        for (int i = 0; i < testSize; i++) {
            map.put(i, i + 1000);
        }

        // Validate random checks
        for (int i = 0; i < testSize; i += 5000) {
            assert map.get(i).equals(i + 1000) : "testStressTest failed (key " + i + " does not match correct value)";
        }

        // Check removing some
        for (int i = 0; i < testSize; i += 2) {
            map.remove(i);
        }

        // Now half the keys are removed
        int foundCount = 0;
        for (int i = 0; i < testSize; i++) {
            if (map.get(i) != null) {
                foundCount++;
            }
        }
        assert foundCount == (testSize / 2)
            : "testStressTest failed (should have half of the entries left)";

        System.out.println("testStressTest passed!");
    }
}
