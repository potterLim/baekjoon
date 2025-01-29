public class Program
{
    private static readonly char[] Alphabet = "abcdefghijklmnopqrstuvwxyz".ToCharArray();

    public static void Main()
    {
        string[] input = Console.ReadLine()!.Split();
        int dictionarySize = int.Parse(input[0]);
        string startWord = input[1];

        HashSet<string> dictionary = new HashSet<string>();

        for (int i = 0; i < dictionarySize; i++)
        {
            dictionary.Add(Console.ReadLine()!);
        }

        string longestWord = findLongestWord(startWord, dictionary);
        Console.WriteLine(longestWord);
    }

    private static string findLongestWord(string startWord, HashSet<string> dictionary)
    {
        Queue<string> queue = new Queue<string>();
        queue.Enqueue(startWord);

        string longestWord = startWord;

        while (queue.Count > 0)
        {
            string currentWord = queue.Dequeue();

            foreach (string newWord in generateNewWords(currentWord, dictionary))
            {
                queue.Enqueue(newWord);

                if (newWord.Length > longestWord.Length)
                {
                    longestWord = newWord;
                }
            }
        }

        return longestWord;
    }

    private static IEnumerable<string> generateNewWords(string word, HashSet<string> dictionary)
    {
        List<string> newWords = new List<string>();

        for (int i = 0; i <= word.Length; i++)
        {
            foreach (char c in Alphabet)
            {
                string newWord = word.Insert(i, c.ToString());

                if (dictionary.Contains(newWord))
                {
                    dictionary.Remove(newWord);
                    newWords.Add(newWord);
                }
            }
        }

        return newWords;
    }
}