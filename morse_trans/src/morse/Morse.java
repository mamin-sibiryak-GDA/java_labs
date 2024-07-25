package morse;

public class Morse
{
    private String input_name;
    private String out_name = "C:\\Users\\User\\Desktop\\labs2\\java_labs\\lab1\\src\\output.txt";
    private String dict_path = "C:\\Users\\User\\Desktop\\labs2\\java_labs\\lab1\\src\\morse_dict.txt";

    public Morse(String input_name)
    {
        this.input_name = input_name;
    }

    public void save_changes(String new_name)
    {
        this.input_name = new_name;
    }

    public void load_changes(String new_name)
    {
        this.out_name = new_name;
    }

    public void code()
    {
        Code tmp_obj = new Code(input_name, out_name, dict_path);
        tmp_obj.trans();
    }

    public void decode()
    {
        Decode tmp_obj = new Decode(input_name, out_name, dict_path);
        tmp_obj.trans();
    }

    public void view_stat(String filename)
    {
        Stat tmp_obj = new Stat(filename, this.input_name);
        tmp_obj.view();
    }

    public void change_dict(String dict_path)
    {
        this.dict_path = dict_path;
    }
}
