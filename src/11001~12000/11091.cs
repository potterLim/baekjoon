public class Program
{
    static void Main(string[] args)
    {
        int count = int.Parse(Console.ReadLine());
        for (int i = 0; i < count; ++i)
        {
            bool[] existAlpha = new bool[26];
            string input = Console.ReadLine().ToLower();
            bool bAllTrue = true;

            for (int j = 0; j < input.Length; ++j)
            {
                if (char.IsLetter(input[j]))
                {
                    existAlpha[input[j] - 'a'] = true;
                }
            }

            for (int j = 0; j < 26; ++j)
            {
                if (existAlpha[j] == false)
                {
                    bAllTrue = false;
                    break;
                }
            }

            if (bAllTrue)
            {
                Console.WriteLine("pangram");
            }
            else
            {
                Console.Write("missing ");
                for (int j = 0; j < 26; ++j)
                {
                    if (existAlpha[j] == false)
                    {
                        Console.Write($"{(char)(j + 'a')}");
                    }
                }
                Console.WriteLine();
            }
        }
    }
}