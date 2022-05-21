using System;

namespace _12_Remainder
{
    class Program
    {
        static void Main(string[] args)
        {
            string twoNumberStr = Console.ReadLine();
            string[] numsStr = twoNumberStr.Split(" ");
            int A = int.Parse(numsStr[0]);
            int B = int.Parse(numsStr[1]);
            int C = int.Parse(numsStr[2]);

            int result1 = (A + B) % C;
            int result2 = ((A % C) + (B % C)) % C;
            int result3 = (A * B) % C;
            int result4 = ((A % C) * (B % C)) % C;

            Console.WriteLine(result1);
            Console.WriteLine(result2);
            Console.WriteLine(result3);
            Console.WriteLine(result4);
        }
    }
}
