package streams.task5.hm;

import oop.streams.InputStream;

public class WordReader {
    private CharReader my_is;

    public WordReader(InputStream is) {
        this.my_is = new CharReader(is);
    }

    public String[] parse(){


        int nb_words = Integer.parseInt(my_is.readLine());
        String[] words = new String[nb_words];
        for (int i = 0; i < nb_words; i++) {
            words[i] = my_is.readLine();
      
        }
        return words;
    }
}