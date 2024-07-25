package morse;

public class Symb
{
    public final char symb;
    public int count = 0;
    private static long general_count = 0;

    Symb(char symb)
    {
        this.symb = symb;
        ++count;
        ++general_count;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj != null && obj.getClass() == Symb.class)
        {
            Symb new_symb = (Symb) obj;
            boolean result = this.symb == new_symb.symb;

            count = Math.max(this.count, new_symb.count) + 1;
            new_symb.count = this.count;

            return result;
        }
        else
            return false;
    }

    @Override
    public int hashCode()
    {
        return (int) symb;
    }

    @Override
    public final String toString()
    {
        String result = "";

        switch (symb)
        {
            case '\n':
                result += "\'\\n\'";
                break;
            case ' ':
                result += "\' \'\t";
                break;
            default:
                result += symb + "\t";
        }

        double format_double = (double) (int) ((double) count / (double) general_count * 100000) / 1000;
        result += ":\t" + format_double + "%" + " (" + count + " times)\n";
        return result;
    }
}
