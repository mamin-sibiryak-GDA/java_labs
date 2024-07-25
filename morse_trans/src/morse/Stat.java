package morse;

import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class Stat
{
    String out_name = "";
    String input_name = "";
    Set<Symb> data = new HashSet<Symb>();

    public Stat(String out_name, String input_name)
    {
        this.out_name = out_name;
        this.input_name = input_name;
    }

    public void view()
    {
        gen_data();
        BufferedWriter writer = null;

        try
        {
            writer = new BufferedWriter(new FileWriter(this.out_name));

            Iterator<Symb> iterate = data.iterator();

            while (iterate.hasNext())
            {
                writer.write(iterate.next().toString());
            }
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

    private void gen_data()
    {
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(this.input_name));
            String string_buffer = "";

            while ((string_buffer = reader.readLine()) != null)
            {
                Symb newline_s = new Symb('\n');
                data.add(newline_s);
                for (int i = 0; i < string_buffer.length(); ++i)
                {
                    Symb s = new Symb(string_buffer.charAt(i));
                    data.add(s);
                }
            }

        }
        catch (IOException e)
        {
            System.err.println(e.getLocalizedMessage());
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
}
