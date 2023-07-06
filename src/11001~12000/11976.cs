using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int[] scoreDifferences = new int[4];

            for (int i = 0; i < 4; ++i)
            {
                string input = Console.ReadLine();
                string[] scores = input.Split(' ');
                int initialScore = int.Parse(scores[0]);
                int finalScore = int.Parse(scores[1]);

                scoreDifferences[i] = finalScore - initialScore;
            }

            int bronzeToSilver = scoreDifferences[3] + scoreDifferences[2] + scoreDifferences[1];
            int silverToGold = scoreDifferences[3] + scoreDifferences[2];
            int goldToPlatinum = scoreDifferences[3];

            Console.WriteLine(bronzeToSilver);
            Console.WriteLine(silverToGold);
            Console.WriteLine(goldToPlatinum);
        }
    }
}
