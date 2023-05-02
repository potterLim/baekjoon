namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int totalScore = 0;
            int[] score = new int[10];
            for (int i = 0; i < 10; i++)
            {
                score[i] = int.Parse(Console.ReadLine());
            }
            for (int i = 0; i < 10; i++)
            {
                if (100 - totalScore < totalScore + score[i] - 100)
                {
                    break;
                }
                totalScore += score[i];
            }
            Console.WriteLine(totalScore);
        }
    }
}