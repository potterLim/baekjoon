using System;
using System.Collections.Generic;

namespace Dial
{
    class Program
    {
        static void Main(string[] args)
        {
            Dictionary<char, int> dialTime = new Dictionary<char, int>() {
                { 'A', 3 }, { 'B', 3 }, { 'C', 3 } ,
                { 'D', 4 }, { 'E', 4 }, { 'F', 4 } ,
                { 'G', 5 }, { 'H', 5 }, { 'I', 5 } ,
                { 'J', 6 }, { 'K', 6 }, { 'L', 6 } ,
                { 'M', 7 }, { 'N', 7 }, { 'O', 7 } ,
                { 'P', 8 }, { 'Q', 8 }, { 'R', 8 } , { 'S', 8},
                { 'T', 9 }, { 'U', 9 }, { 'V', 9 } ,
                { 'W', 10 }, { 'X', 10 }, { 'Y', 10 } , {'Z', 10}

            };

            string str = Console.ReadLine();
            int totaltime = 0;
            int value = 0;

            for (int i = 0; i < str.Length; i++)
            {
                dialTime.TryGetValue(str[i], out value);
                totaltime += value;
            }

            Console.WriteLine(totaltime);
        }
    }
}
