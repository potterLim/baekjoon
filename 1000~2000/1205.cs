using System;

namespace ConsoleApp1
{
    public class Program
    {
        static void Main(string[] args)
        {
            int newScore;
            int availableScores;
            int[] rankings;

            string[] input = Console.ReadLine().Split();
            int numberOfPlayers = int.Parse(input[0]);
            newScore = int.Parse(input[1]);
            availableScores = int.Parse(input[2]);

            if (numberOfPlayers == 0)
            {
                Console.WriteLine(1);
            }
            else
            {
                rankings = new int[numberOfPlayers + 1];
                string[] ranksInput = Console.ReadLine().Split();
                for (int i = 1; i <= numberOfPlayers; i++)
                {
                    rankings[i] = int.Parse(ranksInput[i - 1]);
                }

                int newPlayerRanking = numberOfPlayers + 1;

                if (numberOfPlayers == availableScores && rankings[numberOfPlayers] >= newScore)
                {
                    Console.WriteLine(-1);
                }
                else
                {
                    for (int i = 1; i <= numberOfPlayers; i++)
                    {
                        if (rankings[i] <= newScore)
                        {
                            newPlayerRanking = i;
                            break;
                        }
                    }

                    Console.WriteLine(newPlayerRanking);
                }
            }
        }
    }
}