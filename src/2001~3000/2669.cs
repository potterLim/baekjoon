public class Program
{
    public static void Main(string[] args)
    {
        int n = int.Parse(Console.ReadLine());

        int[] scores = Array.ConvertAll(Console.ReadLine().Split(' '), int.Parse);

        int maxScore = scores[0];
        int minScore = scores[0];

        for (int i = 1; i < n; ++i)
        {
            if (scores[i] > maxScore)
            {
                maxScore = scores[i];
            }

            if (scores[i] < minScore)
            {
                minScore = scores[i];
            }
        }

        Console.WriteLine(maxScore - minScore);
    }
}