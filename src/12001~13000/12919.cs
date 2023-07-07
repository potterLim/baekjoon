using System;
using System.Text;

namespace ConsoleApp1
{
    class Program
    {
        public static void Main(string[] args)
        {
            string originalString = Console.ReadLine().Trim();
            string targetString = Console.ReadLine();

            if (IsConvertible(originalString, targetString))
            {
                Console.WriteLine(1);
            }
            else
            {
                Console.WriteLine(0);
            }
        }

        static bool IsConvertible(string originalString, string targetString)
        {
            int originalStringLength = originalString.Length;

            if (targetString.Length == originalStringLength)
            {
                if (targetString.Equals(originalString))
                {
                    return true;
                }
                return false;
            }

            if (targetString[targetString.Length - 1] == 'A')
            {
                if (IsConvertible(originalString, targetString.Substring(0, targetString.Length - 1)))
                {
                    return true;
                }
            }

            if (targetString[0] == 'B')
            {
                StringBuilder reversedString = new StringBuilder();
                reversedString.Append(targetString.Substring(1, targetString.Length - 1));
                if (IsConvertible(originalString, ReverseString(reversedString.ToString())))
                {
                    return true;
                }
            }
            return false;
        }

        static string ReverseString(string str)
        {
            char[] charArray = str.ToCharArray();
            Array.Reverse(charArray);
            return new string(charArray);
        }
    }
}