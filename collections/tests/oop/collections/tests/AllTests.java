package oop.collections.tests;

import oop.collections.ICollection;

import oop.collections.IList;
import oop.utils.collections.ArrayList;
import oop.utils.collections.HashTable;
import oop.utils.collections.LinkedList;

import oop.collections.IMap;

public class AllTests {

	private static int nbTestsPassed = 0;
	private static boolean passed = false;

	public static void main(String args[]) {

		// TESTS ArrayList
		System.out.println("=======" + "  Tests ArrayList  " + "=======\n");
		passed = execTests(new ArrayList());
		if (passed) {
			nbTestsPassed++;
		}
		System.out.println("\n");

		// TESTS LinkedList
		System.out.println("=======" + "  Tests LinkedList  " + "=======\n");
		passed = execTests(new LinkedList());
		if (passed) {
			nbTestsPassed++;
		}
		System.out.println("\n");

		// TESTS Hashtable
		System.out.println("=======" + "  Tests HashTable  " + "=======\n");
		passed = runTestsIMap(new HashTable());
		if (passed) {
			nbTestsPassed++;
		}
		System.out.println("\n");

		// All Tests passed ? ;)
		if (passed && nbTestsPassed == 3) {
			System.out.println("==> All Tests : PASSED");
		} else {
			System.out.println("==> All Tests : FAILED");
		}

	}

	static void initList(IList list) {
		while (list.length() > 0) {
			list.removeAt(0);
		}
	}

	static boolean test01(IList list) {
		boolean passed = true;
		initList(list);
		Object[] m_obj = { 1, 2, 3, 4, 5 };
		list.toArray(m_obj);
		if (list.length() != 5) {
			System.out.println("Wrong size ! \n");
			passed = false;
		}
		for (int i = 0; i < list.length(); i++) {
			if (!list.elementAt(i).equals(m_obj[i])) {
				System.out.println("Wrong element ! \n");
				passed = false;
			}
		}

		iteratorTest(list);

		if (!passed) {
			System.out.println("--> TEST 01 : KO");
			return false;
		} else {
			System.out.println("--> TEST 01 : OK");
			return true;

		}

	}

	static boolean test02(IList list) {
		boolean passed = true;
		initList(list);
		list.insertAt(0, "Hello");
		list.insertAt(1, "World");
		if (!list.elementAt(0).equals("Hello")) {
			System.out.println("Wrong element at index 0 !\n");
			passed = false;
		}
		if (!list.elementAt(1).equals("World")) {
			System.out.println("Wrong element at index 1 !\n");
			passed = false;
		}
		list.updateAt(1, "everyone");
		if (!list.elementAt(1).equals("everyone")) {
			System.out.println("Wrong element at index 1 !\n");
			passed = false;
		}
		list.insertAt(4, "coucou");
		if (!list.elementAt(4).equals("coucou") || list.length() != 5) {
			System.out.println("Wrong element at index 4 !\n");
			passed = false;
		}

		iteratorTest(list);

		if (!passed) {
			System.out.println("--> TEST 02 : KO");
			return false;
		} else {
			System.out.println("--> TEST 02 : OK");
			return true;

		}
	}

	static boolean test03(IList list) {

		boolean passed = true;
		initList(list);

		for (int i = 0; i <= 15; i++) {
			list.insertAt(i, i);

		}
		// enlève les nombres pairs
		for (int j = 0; j < list.length(); j++) {
			if ((int) list.elementAt(j) % 2 == 0) {
				list.removeAt(j);
			}
		}
		for (int i = 0; i < list.length(); i++) {
			if ((int) list.elementAt(i) % 2 == 0) {
				passed = false;
				break;
			}
		}

		list.remove(1);
		list.remove(3);
		list.remove(9);
		if ((list.contains(1) || list.contains(3) || list.contains(9)) || (list.length() != 5)) {
			passed = false;
		}

		iteratorTest(list);

		if (!passed) {
			System.out.println("--> TEST 03 : KO");
			return false;
		} else {
			System.out.println("--> TEST 03 : OK");
			return true;

		}
	}

	static boolean test04(IList list) {
		boolean passed = true;
		initList(list);

		Object[] objs = { "A", "B", "C", "D" };
		list.toArray(objs);
		for (int i = 0; i < objs.length; i++) {
			if (!list.elementAt(i).equals(objs[i])) {
				System.out.println("Error\n");
				passed = false;
			}
		}

		iteratorTest(list);
		if (!passed) {
			System.out.println("--> TEST 04 : KO");
			return false;
		} else {
			System.out.println("--> TEST 04 : OK");
			return true;

		}
	}

	static void iteratorTest(IList list) {
		ICollection.Iterator it = list.iterator();
		System.out.print("[ ");

		while (it.hasNext()) {
			System.out.print(it.next());
			if (it.hasNext()) {
				System.out.print(", ");

			}
		}
		System.out.print(" ] ");
	}

	static boolean execTests(IList list) {
		if (test01(list) && test02(list) && test03(list) && test04(list)) {
			System.out.println("\nTests : PASSED");
			return true;
		} else {
			System.out.println("\nTests : FAILED");
			return false;
		}
	}

//////////////////////////      HASH TABLE    ///////////////////

	static boolean testPutandGet(IMap map) {
		boolean passed = true;
		map.put("First", 1);
		map.put("Second", 2);
		map.put("Third", 3);
		map.put("Fourth", 4);
		map.put("Fifth", 5);
		if (!map.get("First").equals(1) || !map.get("Second").equals(2) || !map.get("Third").equals(3)
				|| !map.get("Fourth").equals(4) || !map.get("Fifth").equals(5)) {
			passed = false;
		}
		MapIteratorTest(map);
		if (!passed) {
			System.out.println("--> TEST Put/Get : KO");
			return false;
		} else {
			System.out.println("--> TEST Put/Get : OK");
			return true;

		}
	}

	static boolean testRemove(IMap map) {
		boolean passed = true;
		initMap(map);
		for (int i = 0; i <= 5; i++) {
			map.put(i, i * 10);
		}
		Object[] keys = new Object[map.length()];
		map.keysToArray(keys);

		// Enlève les clés qui sont pairs
		for(Object key : keys) {
			if((int) key%2==0) {
				map.remove(key);
			}
		}
		
		if ((map.contains(0) || map.contains(2) || map.contains(4)) &&
			 !map.contains(1) || !map.contains(3) || !map.contains(5)) {
			passed = false;
		}

		MapIteratorTest(map);
		if (!passed) {
			System.out.println("--> TEST Remove : KO");
			return false;
		} else {
			System.out.println("--> TEST Remove : OK");
			return true;

		}
	}

	static boolean testContains(IMap map) {
		boolean passed = true;
		initMap(map);
		map.put("Ananas", 3.99);
		map.put("Orange", 2.05);
		map.put("Banana", 5);
		map.put("Chocolate", 10.90);
		map.put("Strawberries", 1);

		if (map.contains("Vanilla") || !map.contains("Chocolate") || !map.contains("Orange")) {
			passed = false;
		}
		
		MapIteratorTest(map);
		if (!passed) {
			System.out.println("--> TEST Contains : KO");
			return false;
		} else {
			System.out.println("--> TEST Contains : OK");
			return true;

		}
	}

	static boolean testKeyArray(IMap map) {
		boolean passed = true;
		initMap(map);
		Object[] f1 = { "Mercedes", "Red-Bull", "Ferrari", "McLaren" };

		map.put("Mercedes", "Russel");
		map.put("Red-Bull", "Verstappen");
		map.put("Ferrari", "Leclerc");
		map.put("McLaren", "Norris");

		Object[] keys = new Object[map.length()];
		map.keysToArray(keys);

		for (Object mark : f1) {
			boolean in = false;
			for (Object key : keys) {
				if (key.equals(mark)) {
					in = true;
					break;
				}
				
			}
			if (!in) {
				passed = false;
				System.out.println("Missing key : " + mark);
			}

		}
		keyIteratorTest(map);
		if (!passed) {
			System.out.println("--> TEST toKeyArray : KO");
			return false;
		} else {
			System.out.println("--> TEST toKeyArray : OK");
			return true;

		}
	}

	static boolean testValueArray(IMap map) {
		boolean passed = true;
		initMap(map);
		map.put("Mercedes", "Russel");
		map.put("Red-Bull", "Verstappen");
		map.put("Ferrari", "Leclerc");
		map.put("McLaren", "Norris");

		Object[] values = new Object[map.length()];
		map.valuesToArray(values);

		Object[] prices = { "Russel", "Verstappen", "Leclerc", "Norris" };
		for (Object price : prices) {
			boolean in = false;
			for (Object value : values) {
				if (value.equals(price)) {
					in = true;
					break;
				}
			}
			if (!in) {
				passed = false;
			}
		}
		valueIteratorTest(map);
		if (!passed) {
			System.out.println("--> TEST toValueArray : KO");
			return false;
		} else {
			System.out.println("--> TEST toValueArray : OK");
			return true;

		}
	}

	static boolean testArray(IMap map) {
		boolean passed = true;
		initMap(map);
		map.put("Apple", 1.99);
		map.put("Banana", 2.07);
		map.put("Orange", 5.001);
		map.put("Cherry", 631);

		Object[] elems = new Object[map.length()];
		map.toArray(elems);

		Object[][] dico = { { "Apple", 1.99 }, { "Banana", 2.07 }, { "Orange", 5.001 }, { "Cherry", 631 } };

		for(int i = 0 ; i < elems.length ; i++) {
	     
	        HashTable.Pair pair = (HashTable.Pair)elems[i];
	        boolean in = false;

	        for (Object[] tab : dico) {
	            if (pair.key.equals(tab[0]) && pair.value.equals(tab[1])) {
	                in = true;
	                break;
	            }
	        }
	        if(!in) {
	        	passed = false;
	        }
	      }
		MapIteratorTest(map);
		if (!passed) {
			System.out.println("--> TEST toArray : KO");
			return false;
		} else {
			System.out.println("--> TEST toArray : OK");
			return true;

		}
	}
	
	static void MapIteratorTest(IMap map) {
		ICollection.Iterator it = map.iterator();
		System.out.print("[ ");

		while (it.hasNext()) {
			HashTable.Pair pair = (HashTable.Pair) it.next();
			System.out.print(pair.key + "=" + pair.value);
			if (it.hasNext()) {
				System.out.print(", ");

			}
		}
		System.out.print(" ] ");
	}
	
	static void keyIteratorTest(IMap map) {
		ICollection.Iterator ki = map.keys();
		System.out.print("[ ");

		while (ki.hasNext()) {
			System.out.print(ki.next());
			if (ki.hasNext()) {
				System.out.print(", ");

			}
		}
		System.out.print(" ] ");
	}

	static void valueIteratorTest(IMap map) {
		ICollection.Iterator vi = map.values();
		System.out.print("[ ");

		while (vi.hasNext()) {
			System.out.print(vi.next());
			if (vi.hasNext()) {
				System.out.print(", ");

			}
		}
		System.out.print(" ] ");
	}


	static void initMap(IMap map) {
		Object[] keys = new Object[map.length()];
		map.keysToArray(keys);
		for(Object key : keys) {
			map.remove(key);
		}
	}
	
	static boolean runTestsIMap(IMap map) {
		if (testPutandGet(map) && testRemove(map) && testContains(map) && testKeyArray(map) && testValueArray(map)
				&& testArray(map)) {
			System.out.println("\nTests : PASSED");
			return true;
		} else {
			System.out.println("\nTests : FAILED");
			return false;
		}
	}

}