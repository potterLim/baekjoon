using System;

public class Program
{
    public static void Main(string[] args)
    {
        string input = Console.ReadLine();
        int n = input.Length;

        bool[,] isPalindrome = new bool[n, n];
        int[] dp = new int[n];

        for (int length = 1; length <= n; length++)
        {
            for (int i = 0; i <= n - length; i++)
            {
                int j = i + length - 1;
                if (length == 1)
                {
                    isPalindrome[i, j] = true;
                }
                else if (length == 2)
                {
                    isPalindrome[i, j] = input[i] == input[j];
                }
                else
                {
                    isPalindrome[i, j] = input[i] == input[j] && isPalindrome[i + 1, j - 1];
                }
            }
        }

        for (int i = 0; i < n; i++)
        {
            dp[i] = i;
            for (int j = 0; j <= i; j++)
            {
                if (isPalindrome[j, i])
                {
                    if (j == 0)
                    {
                        dp[i] = 0;
                    }
                    else
                    {
                        dp[i] = Math.Min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }

        Console.WriteLine(dp[n - 1] + 1);
    }
}