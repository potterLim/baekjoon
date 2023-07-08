using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            int targetNumber = int.Parse(Console.ReadLine());
            long leftNumber = 1;
            long rightNumber = 1;
            bool targetFound = false;

            while (true)
            {
                if (leftNumber * leftNumber - rightNumber * rightNumber == targetNumber)
                {
                    targetFound = true;
                    Console.WriteLine(leftNumber);
                }

                if (leftNumber - rightNumber == 1 && leftNumber * leftNumber - rightNumber * rightNumber > targetNumber)
                {
                    break;
                }

                if (leftNumber * leftNumber - rightNumber * rightNumber > targetNumber)
                {
                    rightNumber++;
                }
                else
                {
                    leftNumber++;
                }
            }

            if (targetFound == false)
            {
                Console.WriteLine(-1);
            }
        }
    }
}
