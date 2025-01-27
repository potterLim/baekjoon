class Program
{
    static void Main(string[] args)
    {
        string name = Console.ReadLine();
        int[] charCounts = new int[26];

        foreach (char c in name)
        {
            charCounts[c - 'A']++;
        }

        int oddCount = charCounts.Count(count => count % 2 != 0);

        if (oddCount > 1)
        {
            Console.WriteLine("I'm Sorry Hansoo");
            return;
        }

        char[] halfPalindrome = new char[name.Length / 2];
        char middleChar = '\0';
        int index = 0;

        for (int i = 0; i < 26; i++)
        {
            if (charCounts[i] % 2 != 0)
            {
                middleChar = (char)(i + 'A');
            }

            for (int j = 0; j < charCounts[i] / 2; j++)
            {
                halfPalindrome[index++] = (char)(i + 'A');
            }
        }

        Array.Sort(halfPalindrome);
        string firstHalf = new string(halfPalindrome);
        string secondHalf = new string(halfPalindrome.Reverse().ToArray());

        if (middleChar == '\0')
        {
            Console.WriteLine(firstHalf + secondHalf);
        }
        else
        {
            Console.WriteLine(firstHalf + middleChar + secondHalf);
        }
    }
}