using System;

public class Program
{
    public static void Main(string[] args)
    {
        while (true)
        {
            string input = Console.ReadLine();
            if (input == "#")
            {
                break;
            }


            char targetChar = input[0];
            string sentence = input.Substring(2);

            targetChar = char.ToLower(targetChar);
            sentence = sentence.ToLower();

            int count = 0;
            for (int i = 0; i < sentence.Length; ++i)
            {
                if (sentence[i] == targetChar)
                {
                    count++;
                }
            }


            Console.WriteLine($"{targetChar} {count}");
        }
    }
}