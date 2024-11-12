package streams.task2.step1;

public class OutputStream {

	
	private byte[] buffer;
	private int index;
  /**
   * Constructs an output stream from an array of bytes
   * Do NOT copy the buffer, alias it.
   * @param buff
   */
  public OutputStream(byte[] buff) {
	  
	  this.buffer = buff;
	  this.index = 0;
  }

  /**
   * Write a new byte to this output stream. <br>
   * Notice that this suggests a notion of a current position, where to write
   * the new byte. Of course, the current position needs to be advanced.
   */
  public void write(byte value) {
	  buffer[index] = value;
	  index++;
  }
}
