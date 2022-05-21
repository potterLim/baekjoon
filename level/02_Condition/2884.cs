using System;

namespace _05_AlarmClock
{
    class Program
    {
        static void Main(string[] args)
        {
            string twoNumberStr = Console.ReadLine();
            string[] numsStr = twoNumberStr.Split(" ");
            int hour = int.Parse(numsStr[0]);
            int min = int.Parse(numsStr[1]);

            min = min - 45;

            if (min < 0)
            {
                hour--;
                min += 60;
            }

            if (hour < 0)
            {
                hour += 24;
            }

            Console.WriteLine(hour + " " + min);
        }
    }
}
