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
                var numsStr = str.Split(' ');
                int sum = 0;
                int minEven = 100;
                for (int j = 0; j < numsStr.Length; j++)
                {
                    
                    if (int.Parse(numsStr[j]) % 2 == 0)
                    {
                        sum += int.Parse(numsStr[j]);
                        if (int.Parse(numsStr[j]) < minEven)
                        {
                            minEven= int.Parse(numsStr[j]);
                        }
                    }

                    
                }
                Console.WriteLine($"{sum} {minEven}");
            }
        }
    }
}
