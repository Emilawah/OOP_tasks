package oop.collections.tests;

public class MiniTestHashTable {
	static void test(String prefix, Object o1, Object o2) {
		System.out.printf("%s1=%s\n", prefix, o1.toString());
		System.out.printf("%s2=%s\n", prefix, o2.toString());
		System.out.printf("%s1.hashCode()=%d\n", prefix, o1.hashCode());
		System.out.printf("%s2.hashCode()=%d\n", prefix, o2.hashCode());
		System.out.printf("%s1==%s2 is %b\n", prefix, prefix, (o1 == o2));
		System.out.printf("%s1.equals(%s2) is %b\n", prefix, prefix, o1.equals(o2));
	}

	public static void main(String[] args) {
		String s1 = "Hello";
		String s2 = new String(s1);
		test("s", s1, s2);
		System.out.println();
		int[] a1 = new int[] { 1, 2, 3 }; 
		int[] a2 = new int[] { 1, 2, 3 };
		test("a", a1, a2);
		boolean eq = java.util.Arrays.equals(a1, a2);
		System.out.println("java.util.Arrays.equals(a1,a2) is " + eq);
	}
}
