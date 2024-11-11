package streams.task1;

import java.io.DataOutput;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UTFDataFormatException;

public class UTF8File {

  static void write(OutputStream os, String s) throws IOException {
    int length = s.length();
    for (int i = 0; i < length; i++) {
      char c = s.charAt(i);
      if ((c >= 0x0001) && (c <= 0x007F)) {
        os.write(c);				// on encode le caractere en ASCII directement
      } else if (c > 0x07FF) {		// on encode le caractère en 3 octets
        os.write(0xE0 | ((c >> 12) & 0x0F));
        os.write(0x80 | ((c >> 6) & 0x3F));   
        os.write(0x80 | ((c >> 0) & 0x3F));
      } else {						// on encode le caractère en 2 octets
        os.write(0xC0 | ((c >> 6) & 0x1F));
        os.write(0x80 | ((c >> 0) & 0x3F));
      }
    }
    os.write((byte) '\n');  // caractere pour montrer la fin de la chaine
  }

  static char readChar(InputStream is) throws IOException {
    int c1,c2,c3;
    c1 = (int) is.read() & 0xff;
    switch (c1 >> 4) {
    case 0:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
      /* 0xxxxxxx*/
      return (char) c1;
    case 12:
    case 13:
      /* 110x xxxx   10xx xxxx*/
      c2 = (int) is.read();			//			C0 = 11000000
      if ((c2 & 0xC0) != 0x80) // (c2 & 0xC0) => conserve les 2 bits les plus significatifs
        throw new UTFDataFormatException("malformed input");
      return (char) (((c1 & 0x1F) << 6) | (c2 & 0x3F));
    case 14:
      /* 1110 xxxx  10xx xxxx  10xx xxxx */
      c2 = (int) is.read();
      c3 = (int) is.read();
      if (((c2 & 0xC0) != 0x80) || ((c3 & 0xC0) != 0x80))
        throw new UTFDataFormatException("malformed input");
      return (char) (((c1 & 0x0F) << 12) | ((2 & 0x3F) << 6) | ((c3 & 0x3F) << 0));
    default:
      /* 10xx xxxx,  1111 xxxx */
      throw new UTFDataFormatException("malformed input");
    }

  }

  static String read(InputStream is) throws IOException {
    char[] buffer = new char[80];
    int nchars = 0;
    char c = readChar(is);
    while (c!='\n') {
      buffer[nchars++] = c;
      c = readChar(is);
    }
    return new String(buffer, 0, nchars);
  }

  public static void main(String[] args) {
    try {
      File file = new File("file.txt");
      OutputStream os = new FileOutputStream(file);
      for (int i = 0; i < args.length; i++)
        write(os, args[i]);
      os.close(); // always close the stream when you are done writing.
      String s;
      InputStream is = new FileInputStream(file);
      for (int i = 0; i < args.length; i++) {
        s = read(is);
        System.out.println(" " + s);
      }
      is.close(); // always close the stream when you are done reading.
      System.out.println("Done.");
    } catch (IOException ex) {
      ex.printStackTrace(System.err);
      System.exit(-1);
    }
  }

}
