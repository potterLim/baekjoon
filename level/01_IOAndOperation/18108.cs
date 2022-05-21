using System;

namespace _11_ThailandYearConvertNormalYear
{
    class Program
    {
        static void Main(string[] args)
        {
            int thailandYear = int.Parse(Console.ReadLine());
            int normalYear = thailandYear - 543;
            Console.WriteLine(normalYear);
        }
    }
}
