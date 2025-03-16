package oop.utils;

import oop.tasks.Task;
import oop.runtime.EventPump;

import java.util.Arrays;

public class ByteStreamTest {

	// TESTS SUCCESS
	private static byte[] m_bytes1 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	private static byte[] m_bytes_toComplete1 = new byte[m_bytes1.length];

	private static byte[] m_bytes2 = { 'P', 'o', 'o', ' ', 'I', 'N', 'F', 'O' };
	private static byte[] m_bytes_toComplete2 = new byte[m_bytes2.length];

	private static byte[] m_bytes3 = { 1 };
	private static byte[] m_bytes_toComplete3 = new byte[m_bytes3.length];

	// TESTS FAILED
	private static byte[] m_bytes4 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	private static byte[] m_bytes_toComplete4 = new byte[m_bytes4.length + 30];

	public static void main(String args[]) {
		EventPump ep = new EventPump();

		ep.boot(new Runnable() {
			public void run() {
				// TEST 1 (ok)
				test(11, m_bytes1, m_bytes_toComplete1);
				check(m_bytes1, m_bytes_toComplete1);

				// TEST 2 (ok)
				test(9, m_bytes2, m_bytes_toComplete2);
				check(m_bytes2, m_bytes_toComplete2);

				// TEST 3 (ok)
				test(2, m_bytes3, m_bytes_toComplete3);
				check(m_bytes3, m_bytes_toComplete3);

				// TEST 4 (ko)
				test(11, m_bytes4, m_bytes_toComplete4);
				check(m_bytes4, m_bytes_toComplete4);

			}
		});

		ep.shutdown();

	}

	static void test(int capacity, byte[] m_bytes, byte[] m_bytes_toComplete) {

		ByteOutputStream m_os = new ByteOutputStream(capacity);
		ByteInputStream m_is = new ByteInputStream(m_os);

		// Initialisation des taches
		Task t = Task.task();

		Task producer = t.newTask("producerTask");
		Task consumer = t.newTask("consumerTask");

		producer.post(new Runnable() {
			public void run() {
				for (byte b : m_bytes) {
					m_os.write(b);
					System.out.println("Wrote : " + (byte) b);
				}
				m_os.close();
				producer.terminate();
			}
		});

		consumer.post(new Runnable() {
			public void run() {
				int idx = 0;
				while (!m_is.closed() || m_is.available()) {
					if (m_is.available()) {
						byte value = m_is.read();
						m_bytes_toComplete[idx++] = value;
						System.out.println("Read : " + value);

					} else if (idx == capacity - 1) {
						break;
					} else {
						idx = capacity - 1;
					}
				}
				m_is.close();
				consumer.terminate();
			}
		});

	}

	static void check(byte[] b1, byte b2[]) {
		Task t = Task.task();
		Task check = t.newTask("checkTask");

		check.post(() -> {

			if (Arrays.equals(b1, b2)) {
				System.out.println("-------------\n" + "Test PASSED\n" + "-------------\n");
			} else {
				System.out.println("-------------\n" + "Test FAILED\n" + "-------------\n\n\n");
			}

			check.terminate();
		});
	}

}
