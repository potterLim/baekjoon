namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            long[] fibonacci = new long[n + 1];
            fibonacci[0] = 0;
            fibonacci[1] = 1;

            for (int i = 2; i <= n; i++)
            {
                fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
            }

            Console.WriteLine(fibonacci[n]);
        }
    }
}