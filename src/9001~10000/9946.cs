public class Program
{
    public static void Main(string[] args)
    {
        int caseNumber = 0;

        while (true)
        {
            string originalWord = Console.ReadLine();
            string recoveredWord = Console.ReadLine();

            if (originalWord == "END" && recoveredWord == "END")
            {
                break;
            }

            caseNumber++;
            string result = areWordsSame(originalWord, recoveredWord) ? "same" : "different";
            Console.WriteLine($"Case {caseNumber}: {result}");
        }
    }

    private static bool areWordsSame(string originalWord, string recoveredWord)
    {
        if (originalWord.Length != recoveredWord.Length)
        {
            return false;
        }

        Dictionary<char, int> charCount = new Dictionary<char, int>();

        foreach (char c in originalWord)
        {
            if (charCount.ContainsKey(c))
            {
                charCount[c]++;
            }
            else
            {
                charCount[c] = 1;
            }
        }

        foreach (char c in recoveredWord)
        {
            if (!charCount.ContainsKey(c) || charCount[c] == 0)
            {
                return false;
            }

            charCount[c]--;
        }

        return true;
    }
}