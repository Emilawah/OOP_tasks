package oop.collections.tests_yayah;

import oop.collections.IMap;
import oop.utils.collections.HashTable;

/**
 * Concrete test class that extends the generic MapTestBase 
 * and uses HashTable as the map implementation.
 */
public class TestHashTable extends MapTestBase {

    @Override
    protected IMap createMap() {
        // Return your custom HashTable implementation here
        return new HashTable();
    }
}
