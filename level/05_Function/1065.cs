using System;

namespace _03_HanSoo
{
    class Program
    {
        static void Main(string[] args)
        {
            int n = int.Parse(Console.ReadLine());
            bool[] bHanSoo = isHanSoo(n);

            int count = 0;

            for (int i = 0; i < bHanSoo.Length; i++)
            {
                if (bHanSoo[i] == true)
                {
                    count++;
                }
            }

            Console.WriteLine(count);
        }

        static bool[] isHanSoo(int n)
        {
            if (n == 0)
            {
                return null;
            }

            bool[] bHanSoo = new bool[n];
            for (int i = 1; i <= n; i++)
            {
                if (i == 10)
                {
                    break;
                }
                bHanSoo[i - 1] = true;
            }

            if (n < 10)
            {
                return bHanSoo;
            }

            int difference = 0;

            for (int i = 10; i <= n; i++)
            {
                string numStr = i.ToString();

                difference = numStr[1] - numStr[0];
                for (int j = 1; j < numStr.Length; j++)
                {
                    if (numStr[j] - numStr[j - 1] != difference)
                    {
                        bHanSoo[i - 1] = false;
                        break;
                    }

                    bHanSoo[i - 1] = true;
                }
            }

            return bHanSoo;
        }
    }
}
