using System;

namespace _05_Average
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            double[] scores = new double[n];
            double max = -1;
            double sum = 0;
            double average;

            string scoreStr = Console.ReadLine();
            string[] scoresStrs = scoreStr.Split(" ");

            for (int i = 0; i < n; i++)
            {
                scores[i] = int.Parse(scoresStrs[i]);
                if (scores[i] > max)
                {
                    max = scores[i];
                }
            }

            for (int i = 0; i < n; i++)
            {
                scores[i] = scores[i] / max * 100;
                sum += scores[i];
            }

            average = sum / (double)n;
            Console.WriteLine(average);
        }
    }
}
