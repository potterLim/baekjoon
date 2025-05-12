using System;
using System.Collections.Generic;

namespace GuitarSerialSorter
{
    public class Program
    {
        public static void Main()
        {
            int n = int.Parse(Console.ReadLine());
            List<string> serials = new List<string>();

            for (int i = 0; i < n; ++i)
            {
                serials.Add(Console.ReadLine());
            }

            serials.Sort((a, b) =>
            {
                if (a.Length != b.Length)
                {
                    return a.Length - b.Length;
                }

                int aSum = getDigitSum(a);
                int bSum = getDigitSum(b);

                if (aSum != bSum)
                {
                    return aSum - bSum;
                }

                return string.Compare(a, b, StringComparison.Ordinal);
            });

            foreach (string serial in serials)
            {
                Console.WriteLine(serial);
            }
        }

        private static int getDigitSum(string serial)
        {
            int sum = 0;

            foreach (char ch in serial)
            {
                if (char.IsDigit(ch))
                {
                    sum += ch - '0';
                }
            }

            return sum;
        }
    }
}