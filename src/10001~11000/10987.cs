public class Program
{
    static void Main(string[] args)
    {
        string input = Console.ReadLine();
        int countVowels = 0;

        for (int i = 0; i < input.Length; ++i)
        {
            if (input[i] == 'a' || input[i] == 'e' || input[i] == 'i' || input[i] == 'o' || input[i] == 'u')
            {
                countVowels++;
            }
        }

        Console.WriteLine(countVowels);
    }
}