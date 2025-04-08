package oop.collections.tests_yayah;

import oop.utils.collections.LinkedList;
import oop.collections.IList;

public class TestLinkedList extends ListTestBase {
    @Override
    protected IList createList() {
        return new LinkedList();
    }
}
