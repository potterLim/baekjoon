using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string str = Console.ReadLine();
            string target = Console.ReadLine();
            int count = 0;
            int i = 0;

            while (true)
            {
                if (i > str.Length - target.Length)
                {
                    break;
                }
                bool match = true;

                for (int j = 0; j < target.Length; j++)
                {
                    if (str[i + j] != target[j])
                    {
                        match = false;
                        break;
                    }
                }

                if (match == true)
                {
                    count++;
                    i += target.Length;
                }
                else
                {
                    i++;
                }
            }

            Console.WriteLine(count);
        }
    }
}
