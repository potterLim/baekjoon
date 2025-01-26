public class Program
{
    static void Main(string[] args)
    {
        string input = Console.ReadLine().ToLower();
        int i;

        for (i = 0; i < input.Length - 1; ++i)
        {
            if (input.Substring(i, 2) == "d2")
            {
                Console.WriteLine("D2");
                break;
            }
        }

        if (i == input.Length - 1)
        {
            Console.WriteLine("unrated");
        }
    }
}