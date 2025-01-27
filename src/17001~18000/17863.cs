public class Program
{
    static void Main(string[] args)
    {
        string input = Console.ReadLine();

        if (input.Substring(0, 3) == "555")
        {
            Console.WriteLine("YES");
        }
        else
        {
            Console.WriteLine("NO");
        }
    }
}