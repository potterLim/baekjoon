namespace ConsoleApp1
{
    class Program
    {
        public static void Main(string[] args)
        {
            while (true)
            {
                string input = Console.ReadLine();
                if (string.IsNullOrEmpty(input))
                {
                    break;
                }

                string[] values = input.Split(' ');
                int n = int.Parse(values[0]);
                int s = int.Parse(values[1]);
                Console.WriteLine(s / (n + 1));
            }
        }
    }
}