using System;

namespace _06_OvenWatch
{
    class Program
    {
        static void Main(string[] args)
        {
            string twoNumberStr = Console.ReadLine();
            string[] numsStr = twoNumberStr.Split(" ");
            int hour = int.Parse(numsStr[0]);
            int min = int.Parse(numsStr[1]);
            int time = int.Parse(Console.ReadLine());

            hour += time / 60;
            min += time % 60;

            if (min > 59)
            {
                hour++;
                min -= 60;
            }

            if (hour > 23)
            {
                hour -= 24;
            }

            Console.WriteLine(hour + " " + min);
        }
    }
}
