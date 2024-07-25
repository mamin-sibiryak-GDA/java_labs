import morse.*;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner console = new Scanner(System.in);
        String mode = console.nextLine();
        String file_name = console.nextLine();
        String input_name = "C:\\Users\\User\\Desktop\\labs2\\java_labs\\lab1\\src\\" + file_name;

        Morse tmp_obj = new Morse(input_name);
        if (mode.equals("code"))
        {
            tmp_obj.code();
            tmp_obj.view_stat("C:\\Users\\User\\Desktop\\labs2\\java_labs\\lab1\\src\\stat_file.txt");
        }
        else if (mode.equals("decode"))
        {
            tmp_obj.decode();
        }
    }
}
