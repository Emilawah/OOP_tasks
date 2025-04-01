package oop.collections.tests_yayah;

public class AllTests {
    public static void main(String[] args) {
        System.out.println("Running all tests...");

        try {
            
        	System.out.println("Running all arraylists tests...");
            new TestArrayList().runTests();
            System.out.println("Running all linkedlists tests...");
            new TestLinkedList().runTests();
            System.out.println("Running all hashtable tests...");//these tests take sometime it's not failing 
            new TestHashTable().runTests();

            System.out.println("All tests passed successfully!");
            System.exit(0);
        } catch (AssertionError e) {
            System.out.println("Test failed: " + e.getMessage());
            System.exit(1); // Error code for test failure
        }
    }
}
