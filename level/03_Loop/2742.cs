using System;
using System.Text;

namespace _06_FastOutputNTo1
{
    class Program
    {
        static void Main(string[] args)
        {
            StringBuilder sb = new StringBuilder(128);

            int n = int.Parse(Console.ReadLine());

            for (int i = n; i >= 1; --i)
            {
                sb.Append(i);
                sb.Append("\n");
            }

            Console.WriteLine(sb);
        }
    }
}
