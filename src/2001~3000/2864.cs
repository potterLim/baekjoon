using System;

public class Program
{
    public static void Main(string[] args)
    {
        string input = Console.ReadLine();
        string[] numbers = input.Split(' ');

        string strNum1 = numbers[0];
        string strNum2 = numbers[1];

        string strNum1Min = string.Empty;
        string strNum1Max = string.Empty;

        string strNum2Min = string.Empty;
        string strNum2Max = string.Empty;

        for (int i = 0; i < strNum1.Length; ++i)
        {
            if (strNum1[i] == '5')
            {
                strNum1Min += '5';
                strNum1Max += '6';
            }
            else if (strNum1[i] == '6')
            {
                strNum1Min += '5';
                strNum1Max += '6';
            }
            else
            {
                strNum1Min += strNum1[i];
                strNum1Max += strNum1[i];
            }
        }

        for (int i = 0; i < strNum2.Length; ++i)
        {
            if (strNum2[i] == '5')
            {
                strNum2Min += '5';
                strNum2Max += '6';
            }
            else if (strNum2[i] == '6')
            {
                strNum2Min += '5';
                strNum2Max += '6';
            }
            else
            {
                strNum2Min += strNum2[i];
                strNum2Max += strNum2[i];
            }
        }

        int num1Min = int.Parse(strNum1Min);
        int num1Max = int.Parse(strNum1Max);

        int num2Min = int.Parse(strNum2Min);
        int num2Max = int.Parse(strNum2Max);

        int totalMin = num1Min + num2Min;
        int totalMax = num1Max + num2Max;

        Console.WriteLine($"{totalMin} {totalMax}");
    }
}