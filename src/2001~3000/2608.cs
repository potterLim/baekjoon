using System;

public static class Program
{
    public static void Main(string[] args)
    {
        string firstRomanNumber = Console.ReadLine();
        string secondRomanNumber = Console.ReadLine();

        int firstArabicNumber = convertRomanToArabic(firstRomanNumber);
        int secondArabicNumber = convertRomanToArabic(secondRomanNumber);

        int sum = firstArabicNumber + secondArabicNumber;

        Console.WriteLine(sum);
        Console.WriteLine(convertArabicToRoman(sum));
    }

    private static int convertRomanToArabic(string romanNumber)
    {
        int arabicValue = 0;
        int previousValue = 0;

        for (int i = romanNumber.Length - 1; i >= 0; --i)
        {
            int currentValue = romanCharToValue(romanNumber[i]);

            if (currentValue < previousValue)
            {
                arabicValue -= currentValue;
            }
            else
            {
                arabicValue += currentValue;
            }

            previousValue = currentValue;
        }

        return arabicValue;
    }

    private static int romanCharToValue(char romanChar)
    {
        return romanChar switch
        {
            'M' => 1000,
            'D' => 500,
            'C' => 100,
            'L' => 50,
            'X' => 10,
            'V' => 5,
            'I' => 1,
        };
    }

    private static string convertArabicToRoman(int arabicNumber)
    {
        string romanNumber = string.Empty;

        (int Value, string Roman)[] romanValues = new[]
        {
            (1000, "M"), (900, "CM"), (500, "D"), (400, "CD"),
            (100, "C"), (90, "XC"), (50, "L"), (40, "XL"),
            (10, "X"), (9, "IX"), (5, "V"), (4, "IV"), (1, "I")
        };

        foreach (var (value, roman) in romanValues)
        {
            while (arabicNumber >= value)
            {
                romanNumber += roman;
                arabicNumber -= value;
            }
        }

        return romanNumber;
    }
}