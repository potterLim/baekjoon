public class Program
{
    public static void Main(string[] args)
    {
        int n = int.Parse(Console.ReadLine());
        HashSet<string> uniqueWords = new HashSet<string>();

        for (int i = 0; i < n; i++)
        {
            string word = Console.ReadLine();
            if (!isCyclicWordInSet(word, uniqueWords))
            {
                uniqueWords.Add(word);
            }
        }

        Console.WriteLine(uniqueWords.Count);
    }

    private static bool isCyclicWordInSet(string word, HashSet<string> wordSet)
    {
        int length = word.Length;

        for (int i = 0; i < length; i++)
        {
            string rotatedWord = word.Substring(i) + word.Substring(0, i);
            if (wordSet.Contains(rotatedWord))
            {
                return true;
            }
        }

        return false;
    }
}
