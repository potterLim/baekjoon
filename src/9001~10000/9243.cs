public class Program
{
    static void Main(string[] args)
    {
        int countReverse = int.Parse(Console.ReadLine());
        string originalBits = Console.ReadLine();
        string modifiedBits = Console.ReadLine();

        bool isDeletionSucceeded;

        if (countReverse % 2 == 0)
        {
            isDeletionSucceeded = originalBits == modifiedBits;
        }
        else
        {
            isDeletionSucceeded = isFlipped(originalBits, modifiedBits);
        }

        if (isDeletionSucceeded)
        {
            Console.WriteLine("Deletion succeeded");
        }
        else
        {
            Console.WriteLine("Deletion failed");
        }
    }

    private static bool isFlipped(string originalBits, string modifiedBits)
    {
        for (int i = 0; i < originalBits.Length; i++)
        {
            if (originalBits[i] == modifiedBits[i])
            {
                return false;
            }
        }
        return true;
    }
}