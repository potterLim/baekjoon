public class Program
{
    static void Main(string[] args)
    {
        int count = int.Parse(Console.ReadLine());

        for (int i = 0; i < count; ++i)
        {
            string input = Console.ReadLine();
            Console.WriteLine(input.ToLower());
        }
    }
}