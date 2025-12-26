using System;
using System.Collections.Generic;

namespace boj
{
    public static class Program
    {
        private static readonly int[] weights = new int[18]
        {
            10, 9, 8, 7, 6, 5, 4, 3, 2,
            10, 9, 8, 7, 6, 5, 4, 3, 2
        };

        public static void Main(string[] args)
        {
            string input = Console.ReadLine();
            if (input == null)
            {
                return;
            }

            input = input.Trim();
            int[] pattern = parsePattern(input);

            ulong[] aModCounts = buildModCountsForA(pattern);
            ulong[] acceptableByDateMod = buildAcceptableByDateMod(aModCounts, pattern[18]);

            ulong answer = countValidDates(pattern, acceptableByDateMod);
            Console.WriteLine(answer);
        }

        private static int[] parsePattern(string input)
        {
            int[] pattern = new int[19];

            for (int i = 0; i < 19; ++i)
            {
                char c = input[i];
                if (c == 'X')
                {
                    pattern[i] = -1;
                }
                else
                {
                    pattern[i] = c - '0';
                }
            }

            return pattern;
        }

        private static ulong[] buildModCountsForA(int[] pattern)
        {
            ulong[] dp = new ulong[19];
            dp[0] = 1UL;

            for (int pos = 8; pos <= 17; ++pos)
            {
                ulong[] next = new ulong[19];
                int weight = weights[pos];

                int fixedDigit = pattern[pos];
                if (fixedDigit >= 0)
                {
                    int add = (weight * fixedDigit) % 19;
                    for (int mod = 0; mod < 19; ++mod)
                    {
                        ulong cur = dp[mod];
                        if (cur == 0UL)
                        {
                            continue;
                        }

                        int newMod = mod + add;
                        newMod %= 19;
                        next[newMod] += cur;
                    }
                }
                else
                {
                    for (int mod = 0; mod < 19; ++mod)
                    {
                        ulong cur = dp[mod];
                        if (cur == 0UL)
                        {
                            continue;
                        }

                        for (int digit = 0; digit <= 9; ++digit)
                        {
                            int add = (weight * digit) % 19;
                            int newMod = mod + add;
                            newMod %= 19;
                            next[newMod] += cur;
                        }
                    }
                }

                dp = next;
            }

            return dp;
        }

        private static ulong[] buildAcceptableByDateMod(ulong[] aModCounts, int controlDigitPattern)
        {
            ulong[] acceptable = new ulong[19];

            for (int dateMod = 0; dateMod < 19; ++dateMod)
            {
                ulong total = 0UL;

                for (int aMod = 0; aMod < 19; ++aMod)
                {
                    ulong ways = aModCounts[aMod];
                    if (ways == 0UL)
                    {
                        continue;
                    }

                    int s = (dateMod + aMod) % 19;
                    int c = s <= 9 ? s : (19 - s);

                    if (controlDigitPattern >= 0 && controlDigitPattern != c)
                    {
                        continue;
                    }

                    total += ways;
                }

                acceptable[dateMod] = total;
            }

            return acceptable;
        }

        private static ulong countValidDates(int[] pattern, ulong[] acceptableByDateMod)
        {
            ulong total = 0UL;

            for (int year = 1; year <= 9999; ++year)
            {
                bool isLeap = isLeapYear(year);

                int yThousands = year / 1000;
                int yHundreds = (year / 100) % 10;
                int yTens = (year / 10) % 10;
                int yOnes = year % 10;

                if (!matchesDigit(pattern[4], yThousands) || !matchesDigit(pattern[5], yHundreds) || !matchesDigit(pattern[6], yTens) || !matchesDigit(pattern[7], yOnes))
                {
                    continue;
                }

                for (int month = 1; month <= 12; ++month)
                {
                    int mTens = month / 10;
                    int mOnes = month % 10;

                    if (!matchesDigit(pattern[2], mTens) || !matchesDigit(pattern[3], mOnes))
                    {
                        continue;
                    }

                    int maxDay = getDaysInMonth(year, month, isLeap);

                    for (int day = 1; day <= maxDay; ++day)
                    {
                        int dTens = day / 10;
                        int dOnes = day % 10;

                        if (!matchesDigit(pattern[0], dTens) || !matchesDigit(pattern[1], dOnes))
                        {
                            continue;
                        }

                        int sum = weights[0] * dTens + weights[1] * dOnes + weights[2] * mTens + weights[3] * mOnes + weights[4] * yThousands + weights[5] * yHundreds + weights[6] * yTens + weights[7] * yOnes;

                        int dateMod = sum % 19;
                        total += acceptableByDateMod[dateMod];
                    }
                }
            }

            return total;
        }

        private static bool matchesDigit(int patternDigit, int digit)
        {
            return patternDigit < 0 || patternDigit == digit;
        }

        private static bool isLeapYear(int year)
        {
            if (year % 400 == 0)
            {
                return true;
            }

            if (year % 100 == 0)
            {
                return false;
            }

            return year % 4 == 0;
        }

        private static int getDaysInMonth(int year, int month, bool isLeap)
        {
            if (month == 2)
            {
                return isLeap ? 29 : 28;
            }

            if (month == 4 || month == 6 || month == 9 || month == 11)
            {
                return 30;
            }

            return 31;
        }
    }
}
