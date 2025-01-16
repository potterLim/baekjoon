public class Program
{
    static void Main(string[] args)
    {
        int countStr = int.Parse(Console.ReadLine());

        for (int i = 0; i < countStr; ++i)
        {
            string input = Console.ReadLine();
            Console.WriteLine($"{i+1}. {input}");
        }
    }
}