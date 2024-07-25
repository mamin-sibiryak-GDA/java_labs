package morse;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class MorseToEng
{
    Map<String, Character> dict = new HashMap<String, Character>();

    static String path = "C:\\Users\\User\\Desktop\\labs2\\java_labs\\lab1\\src\\morse_dict.txt";

    public MorseToEng(String path)
    {
        load_dict();
        this.path = path;
    }

    public char trans(String morse_char)
    {
        if (this.dict.containsKey(morse_char))
            return this.dict.get(morse_char);
        else
            return '\0';
    }

    public boolean contains(String str, char latin_char)
    {
        if (this.dict.containsKey(str))
            return true;
        else
        {
            this.dict.put(str, latin_char);
            put_in_dict(str, latin_char);
            return false;
        }
    }

    void put_in_dict(String str, char latin_char)
    {
        BufferedWriter writer = null;

        try
        {
            writer = new BufferedWriter(new FileWriter(this.path, true));
            writer.write(str + " " + latin_char + "\n");
        }
        catch (IOException e)
        {
            System.err.println(e.getLocalizedMessage());
        }
        finally
        {
            try
            {
                writer.close();
            }
            catch (IOException e)
            {
                System.err.println(e.getLocalizedMessage());
            }
        }
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

    private void get_string(String string_buffer)
    {
        String str = string_buffer.substring(0, string_buffer.indexOf(' '));
        Character c = string_buffer.charAt(string_buffer.length() - 1);
        this.dict.put(str, c);
    }
}
