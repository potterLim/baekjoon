namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int N = int.Parse(Console.ReadLine());

            for (int i = 0; i < N; i++)
            {
                string str = Console.ReadLine();
                var words = str.Split(' ');

                Console.Write($"Case #{i + 1}:");
                for (int j = words.Length - 1; j >= 0; j--)
                {
                    Console.Write($" {words[j]}");
                }
                Console.WriteLine();
            }
        }
    }
}
