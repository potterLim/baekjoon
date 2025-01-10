public class Program
{
    static void Main(string[] args)
    {
        string input = Console.ReadLine();

        int lengthPassword = int.Parse(input.Split(' ')[0]);
        int countAvailableChar = int.Parse(input.Split(' ')[1]);

        char[] availableChar = new char[countAvailableChar];

        input = Console.ReadLine();

        for (int i = 0; i < countAvailableChar; ++i)
        {
            availableChar[i] = char.Parse(input.Split(' ')[i]);
        }

        Array.Sort(availableChar);

        List<string> result = new List<string>();
        generateCombinations(availableChar, lengthPassword, 0, "", result);

        for (int i = 0; i < result.Count; ++i)
        {
            if (isContaionVowel(result[i]) && hasAtLeastTwoConsonants(result[i]))
            {
                Console.WriteLine(result[i]);
            }
        }
    }

    private static void generateCombinations(char[] array, int length, int start, string current, List<string> result)
    {
        if (current.Length == length)
        {
            result.Add(current);
            return;
        }

        for (int i = start; i < array.Length; ++i)
        {
            generateCombinations(array, length, i + 1, current + array[i], result);
        }
    }

    private static bool isContaionVowel(string str)
    {
        for (int i = 0; i < str.Length; ++i)
        {
            if (str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u')
            {
                return true;
            }
        }

        return false;
    }

    private static bool hasAtLeastTwoConsonants(string str)
    {
        int countConsonant = 0;

        for (int i = 0; i < str.Length; ++i)
        {
            if (str[i] != 'a' && str[i] != 'e' && str[i] != 'i' && str[i] != 'o' && str[i] != 'u')
            {
                countConsonant++;
            }
        }

        if (countConsonant >= 2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}