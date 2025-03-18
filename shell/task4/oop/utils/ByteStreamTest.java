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

	private static byte[] m_bytes5 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	private static byte[] m_bytes_toComplete5 = new byte[m_bytes1.length];

	private static byte[] m_bytes6 = { 'P', 'o', 'o', ' ', 'I', 'N', 'F', 'O' };
	private static byte[] m_bytes_toComplete6 = new byte[m_bytes2.length];

	private static byte[] m_bytes7 = { 1 };
	private static byte[] m_bytes_toComplete7 = new byte[m_bytes3.length];

	// TESTS FAILED
	private static byte[] m_bytes4 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
	private static byte[] m_bytes_toComplete4 = new byte[m_bytes4.length + 30];

	public static void main(String args[]) {
		EventPump ep = new EventPump();

		ep.boot(new Runnable() {
			public void run() {
				// TEST 1 (ok)
				test(11, m_bytes1, m_bytes_toComplete1);

				// TEST 2 (ok)
				test(9, m_bytes2, m_bytes_toComplete2);

				// TEST 3 (ok)
				test(2, m_bytes3, m_bytes_toComplete3);

				// TEST 4 (ko)
				test(11, m_bytes4, m_bytes_toComplete4);

				// TESTS ByteBufferedOutputStream
				test_buff(6, m_bytes5, m_bytes_toComplete5);
				test_buff(3, m_bytes6, m_bytes_toComplete6);
				test_buff(1, m_bytes7, m_bytes_toComplete7);

				// finalCheck();

				shutdown(ep);
			}
		});

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
					System.out.println("Wrote : " + b);
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
		check(m_bytes, m_bytes_toComplete);

	}

	static void test_buff(int capacity, byte[] m_bytes, byte[] m_bytes_toComplete) {

		ByteOutputStream m_os = new ByteOutputStream(capacity);
		ByteInputStream m_is = new ByteInputStream(m_os);
		BufferedByteOutputStream m_bos = new BufferedByteOutputStream(capacity, m_os);

		// Initialisation des taches
		Task t = Task.task();
		Task producer = t.newTask("producerTask");
		Task consumer = t.newTask("consumerTask");

		int[] pos_chunk = { 0 };
		int[] pos_values = { 0 };
		producer.post(new Runnable() {
			public void run() {
				for (byte b : m_bytes) {
					if (m_bos.isFull()) {
						System.out.println("Chunk full, -> ...");
						m_bos.vider();
						while (m_is.available()) {
							byte value = m_is.read();
							m_bytes_toComplete[pos_chunk[0]++] = value;
							System.out.println("Read : " + value);
						}
					}

					m_bos.write(b);
					pos_values[0]++;
					System.out.println("Wrote : " + b);
				}

				m_bos.close();
				producer.terminate();
			}
		});

		consumer.post(new Runnable() {
			public void run() {

				while (!m_is.closed() || m_is.available()) {

					if (m_is.available()) {
						byte value = m_is.read();
						m_bytes_toComplete[pos_chunk[0]++] = value;
						System.out.println("Read : " + value);

					} else if (pos_values[0] <= pos_chunk[0]) {

						break;

					}
				}
				m_os.close();
				m_is.close();
				consumer.terminate();
			}
		});
		check(m_bytes, m_bytes_toComplete);

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

	static void shutdown(EventPump ep) {
		Task t = Task.task();
		Task shutdown = t.newTask("shutdown");
		shutdown.post(() -> {

			ep.shutdown();
		});
	}

}
