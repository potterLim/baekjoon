using System;
using System.Text;

public class Program
{
    public static void Main(string[] args)
    {
        string binaryA = Console.ReadLine();
        string binaryB = Console.ReadLine();

        int length = binaryA.Length;
        StringBuilder andResult = new StringBuilder(length);
        StringBuilder orResult = new StringBuilder(length);
        StringBuilder xorResult = new StringBuilder(length);
        StringBuilder notAResult = new StringBuilder(length);
        StringBuilder notBResult = new StringBuilder(length);

        for (int i = 0; i < length; ++i)
        {
            char a = binaryA[i];
            char b = binaryB[i];

            andResult.Append(a == '1' && b == '1' ? '1' : '0');
            orResult.Append(a == '1' || b == '1' ? '1' : '0');
            xorResult.Append(a != b ? '1' : '0');
            notAResult.Append(a == '1' ? '0' : '1');
            notBResult.Append(b == '1' ? '0' : '1');
        }

        Console.WriteLine(andResult.ToString());
        Console.WriteLine(orResult.ToString());
        Console.WriteLine(xorResult.ToString());
        Console.WriteLine(notAResult.ToString());
        Console.WriteLine(notBResult.ToString());
    }
}