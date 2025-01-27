class Program
{
    static void Main(string[] args)
    {
        string word1 = Console.ReadLine();
        string word2 = Console.ReadLine();

        int[] frequency1 = new int[26];
        int[] frequency2 = new int[26];

        foreach (char c in word1)
        {
            frequency1[c - 'a']++;
        }

        foreach (char c in word2)
        {
            frequency2[c - 'a']++;
        }

        int removalCount = 0;

        for (int i = 0; i < 26; i++)
        {
            removalCount += Math.Abs(frequency1[i] - frequency2[i]);
        }

        Console.WriteLine(removalCount);
    }
}