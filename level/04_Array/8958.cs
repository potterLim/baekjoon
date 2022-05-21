using System;

namespace _06_OXQuiz
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            string testCase;
            int score = 0;
            int count = 0;
            for (int i = 0; i < n; i++)
            {
                testCase = Console.ReadLine();

                for (int j = 0; j < testCase.Length; j++)
                {
                    if (testCase[j] == 'O')
                    {
                        count++;
                    }

                    else
                    {
                        count = 0;
                    }
                    score += count;
                }
                Console.WriteLine(score);
                count = 0;
                score = 0;
            }
        }
    }
}
