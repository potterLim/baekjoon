using System;

namespace StringDifference
{
    public class Program
    {
        public static void Main()
        {
            string[] inputs = Console.ReadLine().Split();
            string str1 = inputs[0];
            string str2 = inputs[1];

            int minDifference = int.MaxValue;

            for (int i = 0; i <= str2.Length - str1.Length; ++i)
            {
                int difference = 0;

                for (int j = 0; j < str1.Length; ++j)
                {
                    if (str1[j] != str2[i + j])
                    {
                        ++difference;
                    }
                }

                if (difference < minDifference)
                {
                    minDifference = difference;
                }
            }

            Console.WriteLine(minDifference);
        }
    }
}
