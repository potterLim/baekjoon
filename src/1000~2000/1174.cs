using System;
using System.Collections.Generic;
using System.Linq;

namespace ConsoleApp1
{
    class DecreasingNumberFinder
    {
        private List<long> mDecreasingNumbers;

        public DecreasingNumberFinder()
        {
            mDecreasingNumbers = new List<long>();
            GenerateDecreasingNumbers(0, 10);
            mDecreasingNumbers.Sort();
        }

        public long GetNthDecreasingNumber(int index)
        {
            if (index >= mDecreasingNumbers.Count)
            {
                return -1;
            }

            return mDecreasingNumbers[index];
        }

        private void GenerateDecreasingNumbers(long number, int lastDigit)
        {
            mDecreasingNumbers.Add(number);

            for (int i = lastDigit - 1; i >= 0; i--)
            {
                GenerateDecreasingNumbers(number * 10 + i, i);
            }
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            int n;
            bool bIsValid = int.TryParse(Console.ReadLine(), out n);

            if (!bIsValid || n <= 0)
            {
                Console.WriteLine("-1");
                return;
            }

            DecreasingNumberFinder finder = new DecreasingNumberFinder();
            Console.WriteLine(finder.GetNthDecreasingNumber(n));
        }
    }
}