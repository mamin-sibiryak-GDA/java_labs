package morse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class EngToMorse
{
    Map<Character, String> dict = new HashMap<Character, String>();

    static String path = "C:\\Users\\User\\Desktop\\labs2\\java_labs\\lab1\\src\\morse_dict.txt";

    public EngToMorse(String path)
    {
        load_dict();
        this.path = path;
    }

    public String trans(char eng_char)
    {
        if ('a' <= eng_char && eng_char <= 'z')
        {
            eng_char -= 'a';
            eng_char += 'A';
        }
        else if (!this.dict.containsKey(eng_char))
        {
            if (!gen_morse_symb(eng_char))
                return "\0";
        }
        return this.dict.get(eng_char);
    }

    private boolean gen_morse_symb(char eng_char)
    {
        Random rand = new Random();
        String str = "";
        MorseToEng tmp_obj = new MorseToEng(path);

        int counter = 0;
        do
        {
            ++counter;
            int i = rand.nextInt(10);
            int j = rand.nextInt(10);
            str = this.dict.get((char) (i + '0')) + this.dict.get((char) (j + '0'));
        }
        while (tmp_obj.contains(str, eng_char) && counter < 100);

        if (counter == 100)
            return false;

        this.dict.put(eng_char, str);
        return true;
    }

    private void load_dict()
    {
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(this.path));
            String string_buffer;

            while ((string_buffer = reader.readLine()) != null)
                get_string(string_buffer);
        }
        catch (IOException e)
        {
            System.err.println("Error while reading file: \"" + this.path + "\"" + e.getLocalizedMessage());
        }
        finally
        {
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    private void get_string(String str_buffer)
    {
        String str = str_buffer.substring(0, str_buffer.indexOf(' '));
        Character c = str_buffer.charAt(str_buffer.length() - 1);
        this.dict.put(c, str);
    }
}
