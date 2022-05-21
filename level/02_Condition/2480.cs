using System;

namespace _07_ThreeDice
{
    class Program
    {
        static void Main(string[] args)
        {
            string twoNumberStr = Console.ReadLine();
            string[] numsStr = twoNumberStr.Split(" ");
            int[] dices = new int[3];
            int max = -1;

            for (int i = 0; i < 3; i++)
            {
                dices[i] = int.Parse(numsStr[i]);
                if (dices[i] > max)
                {
                    max = dices[i];
                }
            }

            if (dices[0] == dices[1] && dices[0] == dices[2])
            {
                Console.WriteLine(10000 + dices[0] * 1000);
            }

            else if (dices[0] == dices[1] || dices[0] == dices[2])
            {
                Console.WriteLine(1000 + dices[0] * 100);
            }

            else if (dices[1] == dices[2])
            {
                Console.WriteLine(1000 + dices[1] * 100);
            }

            else
            {
                Console.WriteLine(max * 100);
            }
        }
    }
}
