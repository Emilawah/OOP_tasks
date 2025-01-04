package streams.task5.hm;

import oop.streams.InputStream;
import oop.streams.OutputStream;
import oop.streams.wjs.WrappedJavaInputStream;
import oop.streams.wjs.WrappedJavaOutputStream;

public class Task5Main {
	public static void main(String[] args) throws Exception {

		OutputStream out = new WrappedJavaOutputStream(System.out);
		InputStream in = new WrappedJavaInputStream(System.in);

		Game game = new Game(args, in, out);
		game.play();
	}
}