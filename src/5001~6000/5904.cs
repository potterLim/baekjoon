using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int number = int.Parse(Console.ReadLine());
            char result = CalculateMooRecursive(number);
            Console.WriteLine(result);
        }

        static char CalculateMooRecursive(int number)
        {
            if (number == 1)
            {
                return 'm';
            }
            if (number <= 3)
            {
                return 'o';
            }

            int size = 3;
            int index = 0;

            while (size < number)
            {
                index++;
                size = size * 2 + index + 3;
            }

            int frontBack = (size - index - 3) / 2;

            if (number == frontBack + 1)
            {
                return 'm';
            }
            if (size - frontBack + 1 <= number)
            {
                return CalculateMooRecursive(number - size + frontBack);
            }

            return 'o';
        }
    }
}
