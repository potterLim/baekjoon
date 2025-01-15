using System;

public class Program
{
    static void Main(string[] args)
    {
        string input = Console.ReadLine();
        int countJOI = 0;
        int countIOI = 0;

        for (int i = 0; i < input.Length - 2; ++i)
        {
            if (input[i] == 'J' && input[i + 1] == 'O' && input[i + 2] == 'I')
            {
                countJOI++;
            }
            else if (input[i] == 'I' && input[i + 1] == 'O' && input[i + 2] == 'I')
            {
                countIOI++;
            }
        }

        Console.WriteLine(countJOI);
        Console.WriteLine(countIOI);
    }
}