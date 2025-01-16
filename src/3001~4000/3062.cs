public class Program
{
    static void Main(string[] args)
    {
        int testCaseCount = int.Parse(Console.ReadLine());
        for (int i = 0; i < testCaseCount; ++i)
        {
            string inputNumberString = Console.ReadLine();
            int originalNumber = int.Parse(inputNumberString);
            int reversedNumber = int.Parse(reverseString(inputNumberString));
            int sumOfNumbers = originalNumber + reversedNumber;
            string sumOfNumbersString = sumOfNumbers.ToString();

            bool isPalindrome = true;
            for (int j = 0; j < sumOfNumbersString.Length - 1 / 2; ++j)
            {
                if (sumOfNumbersString[j] != sumOfNumbersString[sumOfNumbersString.Length - 1 - j])
                {
                    Console.WriteLine("NO");
                    isPalindrome = false;
                    break;
                }
            }

            if (isPalindrome)
            {
                Console.WriteLine("YES");
            }
        }
    }

    private static string reverseString(string str)
    {
        string reversedString = "";

        for (int i = str.Length - 1; i >= 0; --i)
        {
            reversedString += str[i];
        }

        return reversedString;
    }
}