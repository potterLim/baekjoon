public class Program
{
    public static void Main(string[] args)
    {
        string[] input = Console.ReadLine().Split(' ');
        int length1 = input[0].Length;
        int length2 = input[1].Length;

        int maxLength;
        if (length1 > length2)
        {
            maxLength = length1;
        }
        else
        {
            maxLength = length2;
        }

        List<int> digit1 = new List<int>(new int[maxLength]);
        List<int> digit2 = new List<int>(new int[maxLength]);

        for (int i = 0; i < maxLength; ++i)
        {
            if (i >= maxLength - length1)
            {
                digit1[i] = input[0][i - (maxLength - length1)] - '0';
            }

            if (i >= maxLength - length2)
            {
                digit2[i] = input[1][i - (maxLength - length2)] - '0';
            }
        }

        List<int> sumDigits = new List<int>();

        for (int i = digit1.Count - 1; i >= 0; --i)
        {
            sumDigits.Insert(0, digit1[i] + digit2[i]);
        }

        for (int i = sumDigits.Count - 1; i > 0; --i)
        {
            if (sumDigits[i] == 3)
            {
                sumDigits[i] = 1;
                sumDigits[i - 1]++;
            }
            else if (sumDigits[i] == 2)
            {
                sumDigits[i] = 0;
                sumDigits[i - 1]++;
            }
        }

        if (sumDigits[0] == 3)
        {
            sumDigits[0] = 1;
            sumDigits.Insert(0, 1);
        }
        else if (sumDigits[0] == 2)
        {
            sumDigits[0] = 0;
            sumDigits.Insert(0, 1);
        }

        for (int i = 0; i < sumDigits.Count; ++i)
        {
            if (sumDigits[i] == 1)
            {
                break;
            }
            else
            {
                sumDigits.RemoveAt(i);
                i--;
            }
        }
        Console.WriteLine(string.Join("", sumDigits));
    }
}