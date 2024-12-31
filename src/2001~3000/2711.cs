using System;

public class Program
{
    static void Main(string[] args)
    {
        int countTestCase = int.Parse(Console.ReadLine());
        string[] correctedStrings = new string[countTestCase];
        
        for (int i = 0; i < countTestCase; ++i)
        {
            string[] input = Console.ReadLine().Split();
            int typoIndex = int.Parse(input[0]) - 1;
            string originalString = input[1];

            correctedStrings[i] = originalString.Remove(typoIndex, 1);
        }

        for (int i = 0; i < countTestCase; ++i)
        {
            Console.WriteLine(correctedStrings[i]);
        }
    }
}