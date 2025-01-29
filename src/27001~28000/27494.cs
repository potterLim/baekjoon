
public class Program
{
    private const string TARGET = "2023";

    public static void Main()
    {
        int ticketCount = int.Parse(Console.ReadLine()!);
        int winningCount = countWinningTickets(ticketCount);
        Console.WriteLine(winningCount);
    }

    private static int countWinningTickets(int ticketCount)
    {
        int count = 0;

        for (int i = 2023; i <= ticketCount; i++)
        {
            if (containsTargetSequence(i.ToString()))
            {
                count++;
            }
        }

        return count;
    }

    private static bool containsTargetSequence(string serial)
    {
        int index = 0;

        foreach (char digit in serial)
        {
            if (digit == TARGET[index])
            {
                index++;

                if (index == TARGET.Length)
                {
                    return true;
                }
            }
        }

        return false;
    }
}