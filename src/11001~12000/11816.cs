public class Program
{
    static void Main(string[] args)
    {
        string input = Console.ReadLine();

        int decimalValue;

        if (input.StartsWith("0x"))
        {
            decimalValue = Convert.ToInt32(input, 16);
        }
        else if (input.StartsWith("0"))
        {
            decimalValue = Convert.ToInt32(input, 8);
        }
        else
        {
            decimalValue = int.Parse(input);
        }

        Console.WriteLine(decimalValue);
    }
}