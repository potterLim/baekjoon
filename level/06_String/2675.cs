using System;

namespace _04_RepeatString
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());

            for (int i = 0; i < n; i++)
            {
                string str = Console.ReadLine();
                for (int j = 2; j < str.Length; j++)
                {
                    for (int k = 0; k < str[0] - 48; k++)
                    {
                        Console.Write(str[j]);
                    }
                }
                Console.WriteLine();
            }
        }
    }
}
