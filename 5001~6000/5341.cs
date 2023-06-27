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

                int sum = 0;
                for (int i = 1; i <= n; i++)
                {
                    sum += i;
                }
                Console.WriteLine(sum);
            }
        }
    }
}