using System;

public class Program
{
    static void Main(string[] args)
    {
        string numbersStr = Console.ReadLine();
        int countNumbers = 1;

        for (int i = 0; i < numbersStr.Length; ++i)
        {
            if (numbersStr[i] == ',')
            {
                countNumbers++;
            }
        }

        Console.WriteLine(countNumbers);

    }
}