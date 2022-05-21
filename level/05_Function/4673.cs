using System;

namespace _02_SelfNumber
{
    class Program
    {
        static bool[] bExist = new bool[10001];
        static void Main(string[] args)
        {
            SetExistSelfNumber();
            bExist[0] = true;

            for (int i = 0; i <= 10000; i++)
            {
                if (bExist[i] == false)
                {
                    Console.WriteLine(i);
                }
            }

        }

        static void SetExistSelfNumber()
        {
            for (int i = 1; i <= 10000; i++)
            {
                int sum = 0;
                string numStr = i.ToString();
                for (int j = 0; j < numStr.Length; j++)
                {
                    sum += numStr[j] - 48;
                }
                sum += i;

                if (sum <= 10000)
                {
                    bExist[sum] = true;
                }
            }
        }

    }
}
