using System;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            string str = Console.ReadLine();

            if (IsPalindrome(str) == true)
            {
                Console.WriteLine(str.Length);
            }
            else
            {
                for (int i = 0; i < str.Length; i++)
                {
                    if (IsPalindrome(str.Substring(i)))
                    {
                        Console.WriteLine(str.Length + i);
                        break;
                    }
                }
            }
        }

        static bool IsPalindrome(string str)
        {
            int left = 0;
            int right = str.Length - 1;

            while (left < right)
            {
                if (str[left] != str[right])
                {
                    return false;
                }

                left++;
                right--;
            }

            return true;
        }
    }
}
