public class Program
{
    static void Main(string[] args)
    {
        int count = int.Parse(Console.ReadLine());
        for (int i = 0; i < count; ++i)
        {
            string[] inputStr = Console.ReadLine().Split(" ");
            Console.Write("god");
            for (int j = 1; j < inputStr.Length; ++j)
            {
                Console.Write(inputStr[j]);
            }
            Console.WriteLine();
        }
    }
}