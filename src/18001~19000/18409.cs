public class Program
{
    static void Main(string[] args)
    {
        int length = int.Parse(Console.ReadLine());
        string input = Console.ReadLine();

        int countVowels = 0;

        for (int i = 0; i < length; ++i)
        {
            if (input[i] == 'a' || input[i] == 'e' || input[i] == 'i' || input[i] == 'o' || input[i] == 'u')
            {
                countVowels++;
            }
        }

        Console.WriteLine(countVowels);
    }
}