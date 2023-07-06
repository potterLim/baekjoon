namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            while (true)
            {
                int n = int.Parse(Console.ReadLine());
                if (n == 0)
                {
                    break;
                }

                string[] words = new string[n];
                for (int i = 0; i < n; i++)
                {
                    words[i] = Console.ReadLine();
                }

                Array.Sort(words, StringComparer.OrdinalIgnoreCase);
                Console.WriteLine(words[0]);
            }
        }
    }
}