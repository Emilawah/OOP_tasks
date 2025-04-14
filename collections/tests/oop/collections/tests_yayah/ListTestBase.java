package oop.collections.tests_yayah;

import oop.collections.ICollection;
import oop.collections.IList;

public abstract class ListTestBase {
    protected abstract IList createList(); // Chaque classe définira l'implémentation

    public void runTests() {
        testInsertAt();
        testUpdateAt();
        testRemoveAt();
        testContains();
        testIterator();
        testEdgeCases();
        testStressTest();
    }

    public void testInsertAt() {
        IList list = createList();
        list.insertAt(0, "A");
        list.insertAt(1, "B");
        list.insertAt(2, "C");

        assert list.elementAt(0).equals("A") : "testInsertAt failed";
        assert list.elementAt(1).equals("B") : "testInsertAt failed";
        assert list.elementAt(2).equals("C") : "testInsertAt failed";

        System.out.println("testInsertAt passed!");
    }

    public void testUpdateAt() {
        IList list = createList();
        list.insertAt(0, "A");
        list.insertAt(1, "B");

        assert list.updateAt(1, "X").equals("B") : "testUpdateAt failed";
        assert list.elementAt(1).equals("X") : "testUpdateAt failed";

        System.out.println("testUpdateAt passed!");
    }

    public void testRemoveAt() {
        IList list = createList();
        list.insertAt(0, "A");
        list.insertAt(1, "B");
        list.insertAt(2, "C");

        assert list.removeAt(1).equals("B") : "testRemoveAt failed";
        assert list.length() == 2 : "testRemoveAt failed";
        assert list.elementAt(1).equals("C") : "testRemoveAt failed";

        System.out.println("testRemoveAt passed!");
    }

    public void testContains() {
        IList list = createList();
        list.insertAt(0, "A");
        list.insertAt(1, "B");

        assert list.contains("A") : "testContains failed";
        assert list.contains("B") : "testContains failed";
        assert !list.contains("X") : "testContains failed";

        System.out.println(" testContains passed!");
    }

    public void testIterator() {
        IList list = createList();
        list.insertAt(0, "A");
        list.insertAt(1, "B");
        list.insertAt(2, "C");

        ICollection.Iterator iter = list.iterator();
        assert iter.hasNext() : "testIterator failed";
        assert iter.next().equals("A") : "testIterator failed";
        assert iter.next().equals("B") : "testIterator failed";
        assert iter.next().equals("C") : "testIterator failed";
        assert !iter.hasNext() : "testIterator failed";

        System.out.println("testIterator passed!");
    }

    public void testEdgeCases() {
        IList list = createList();
        list.insertAt(0, "Z");
        assert list.elementAt(0).equals("Z") : "testEdgeCases failed";
        assert list.length() == 1 : "testEdgeCases failed";

        try {
            list.insertAt(-1, "X");
            assert false : "testEdgeCases failed (negative index not throwing exception)";
        } catch (IndexOutOfBoundsException e) {
            System.out.println("testEdgeCases (negative index) passed!");
        }
    }

    public void testStressTest() {
        IList list = createList();
        int testSize = 100_000;

        for (int i = 0; i < testSize; i++) {
            list.insertAt(i, i);
        }

        assert list.elementAt(0).equals(0) : "testStressTest failed";
        assert list.elementAt(testSize - 1).equals(testSize - 1) : "testStressTest failed";

        for (int i = 0; i < testSize / 2; i++) {
            list.removeAt(0);
        }

        assert list.length() == testSize / 2 : "testStressTest failed";
        assert list.elementAt(0).equals(testSize / 2) : "testStressTest failed";

        System.out.println("testStressTest passed!");
    }
}
