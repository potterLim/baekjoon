public class Program
{
    static void Main(string[] args)
    {
        string userMbti = Console.ReadLine();
        int friendCount = int.Parse(Console.ReadLine());
        int matchingCount = 0;

        for (int i = 0; i < friendCount; i++)
        {
            if (Console.ReadLine() == userMbti)
            {
                matchingCount++;
            }
        }

        Console.WriteLine(matchingCount);
    }
}