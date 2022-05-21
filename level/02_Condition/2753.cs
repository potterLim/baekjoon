using System;

namespace _03_LeapYear
{
    class Program
    {
        static void Main(string[] args)
        {
            bool bLeapYear;
            int year = int.Parse(Console.ReadLine());
            if (year % 4 == 0)
            {
                if (year % 100 == 0 && year % 400 != 0)
                {
                    bLeapYear = false;
                }
                else
                {
                    bLeapYear = true;
                }
            }
            else
            {
                bLeapYear = false;
            }

            if (bLeapYear == true)
            {
                Console.WriteLine("1");
            }

            else
            {
                Console.WriteLine("0");
            }
        }
    }
}
