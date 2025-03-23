package oop.collections.tests;

import oop.collections.IList;
import oop.utils.collections.ArrayList;


public class AllTests {

	public static void main(String args[]) {

		// TESTS ArrayList
		ArrayListTests.execTests();
	}

	public static class ArrayListTests {
		static boolean test01() {
			boolean passed = true;
			Object[] m_obj = { 1, 2, 3, 4, 5 };
			IList list = new ArrayList(m_obj);
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

		static boolean test02() {
			boolean passed = true;

			IList list = new ArrayList();
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
				System.out.println("Wrong element at index 10 !\n");
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

		static boolean test03() {
			boolean passed = true;
			IList list = new ArrayList();
			for (int i = 0; i <= 15; i++) {
				list.insertAt(i, i);

				// enlève les nombres pairs
			}
			for (int j = 0; j < list.length(); j++) {
				list.removeAt(j % list.length());
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

		static boolean test04() {
			boolean passed = true;
			IList list = new ArrayList();
			Object[] objs = {"A","B","C","D"};
			list.toArray(objs);
			for(int i = 0 ; i < objs.length ; i++) {
				if(!list.elementAt(i).equals(objs[i]) || list.length() != 4) {
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
			IList.Iterator it = ((ArrayList)list).iterator();
			System.out.print("[ ");

			while (it.hasNext()) {
				System.out.print(it.next());
				if (it.hasNext()) {
					System.out.print(", ");

				}
			}
			System.out.print(" ] ");
		}

		static void execTests() {
			System.out.println("=======" + "  Tests ArrayList  " + "=======\n");
			if(test01() && test02() && test03() && test04()) {
				System.out.println("\nTests ArrayList : PASSED");
			}else {
				System.out.println("\nTests ArrayList : FAILED");
				System.exit(-1);
			}
		}
	}
}
