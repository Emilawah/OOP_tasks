package oop.collections.tests;

import oop.collections.ICollection;
import oop.collections.IList;
import oop.utils.collections.ArrayList;
import oop.utils.collections.LinkedList;

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

		// All Tests passed ? ;)
		if (passed && nbTestsPassed == 2) {
			System.out.println("==> All Tests : PASSED");
		} else {
			System.out.println("==> All Tests : FAILED");
		}

	}

	static void initList(IList list) {
		while(list.length() > 0) {
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
		for (int j = 0; j < list.length() ; j++) {
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

}