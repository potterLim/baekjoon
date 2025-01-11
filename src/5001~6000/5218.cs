public class Program
{
    static void Main(string[] args)
    {
        int countStr = int.Parse(Console.ReadLine());
        for (int i = 0; i < countStr; ++i)
        {
            string[] separatedStr = Console.ReadLine().Split(' ');
            Console.Write("Distances: ");
            for (int j = 0; j < separatedStr[0].Length; ++j)
            {
                if (separatedStr[0][j] <= separatedStr[1][j])
                {
                    Console.Write($"{separatedStr[1][j] - separatedStr[0][j]}");
                    if (j != separatedStr[0].Length - 1)
                    {
                        Console.Write(" ");
                    }
                }
                else
                {
                    Console.Write($"{separatedStr[1][j] + 26 - separatedStr[0][j]}");
                    if (j != separatedStr[0].Length - 1)
                    {
                        Console.Write(" ");
                    }
                }
            }

            Console.WriteLine();
        }
    }
}