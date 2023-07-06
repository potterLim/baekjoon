namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int countApplicants;
            int countWinners;

            string[] str = Console.ReadLine().Split(" ");
            countApplicants = int.Parse(str[0]);
            countWinners = int.Parse(str[1]);

            string[] scoresStr = Console.ReadLine().Split(" ");
            int[] scores = new int[countApplicants];

            for (int i = 0; i < countApplicants; ++i)
            {
                scores[i] = int.Parse(scoresStr[i]);
            }

            Array.Sort(scores);
            Console.WriteLine(scores[scores.Length - countWinners]);
        }
    }
}