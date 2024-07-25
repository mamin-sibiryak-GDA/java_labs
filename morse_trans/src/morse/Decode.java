package morse;

import java.io.*;

public class Decode
{
    private String input_name = "";
    private String out_name = "";
    private String dict_path = "";
    private BufferedReader reader = null;
    private boolean is_not_first_time = false;

    public Decode(String input_name, String out_name, String dict_path)
    {
        this.input_name = input_name;
        this.out_name = out_name;
        this.dict_path = dict_path;
    }

    public void trans()
    {
        try
        {
            reader = new BufferedReader(new FileReader(this.input_name));
            String string_buffer;

            while ((string_buffer = reader.readLine()) != null)
                get_string(string_buffer);

        }
        catch (IOException e)
        {
            System.err.println("Error while reading file: \"" + this.input_name + "\"" + e.getLocalizedMessage());
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

    private void write_eng_line(String sub_str)
    {
        BufferedWriter writer = null;

        try
        {
            writer = new BufferedWriter(new FileWriter(this.out_name, is_not_first_time));
            is_not_first_time = true;
            writer.write(sub_str + "\n");
        }
        catch (IOException e)
        {
            System.err.println(e.getLocalizedMessage());
        }
        finally
        {
            if (writer != null)
            {
                try
                {
                    writer.close();
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
        MorseToEng dictionaryLatin = new MorseToEng(dict_path);
        String sub_str = "";

        while (!string_buffer.isEmpty())
        {
            int end_idx = string_buffer.indexOf(' ', 1);
            String morse_char = "";

            if (end_idx > 0)
            {
                morse_char = string_buffer.substring(0, end_idx);
                string_buffer = string_buffer.substring(end_idx + 1);
            }
            else
            {
                morse_char = string_buffer;
                string_buffer = "";
            }
            sub_str += dictionaryLatin.trans(morse_char);
        }
        write_eng_line(sub_str);
    }
}
