using System;

namespace ConsoleApp1
{
    public class Program1
    {
        static void Main(string[] args)
        {
            var N = int.Parse(Console.ReadLine());
            var p = new int[N];
            var order = new int[N];
            var cards = new int[N];

            var input1 = Console.ReadLine().Split();
            for (int i = 0; i < N; i++)
            {
                p[i] = int.Parse(input1[i]);
            }

            var input2 = Console.ReadLine().Split();
            for (int i = 0; i < N; i++)
            {
                order[int.Parse(input2[i])] = i;
                cards[i] = i % 3;
            }

            var compare = cards.ToArray();
            var next = new int[N];

            int result = 0;
            while (cards.SequenceEqual(p) == false && (result != 0 && cards.SequenceEqual(compare)) == false)
            {
                for (int j = 0; j < N; j++)
                {
                    next[order[j]] = cards[j];
                }

                cards = next.ToArray();
                result++;
            }

            if (result != 0 && cards.SequenceEqual(compare))
            {
                Console.WriteLine(-1);
            }
            else
            {
                Console.WriteLine(result);
            }
        }
    }
}